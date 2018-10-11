package com.ntc.mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ntc.mall.common.pojo.MallResult;
import com.ntc.mall.common.util.JsonUtils;
import com.ntc.mall.content.service.ContentService;
import com.ntc.mall.jedis.JedisClient;
import com.ntc.mall.mapper.TbContentMapper;
import com.ntc.mall.pojo.TbContent;
import com.ntc.mall.pojo.TbContentExample;

/**
 * @author Michael-Chen
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper mapper;
	
	@Autowired
	private JedisClient jedisClient;

	@Value("${CONTENT_KEY}")
	private String CONTENT_KEY;
	
	@Override
	public MallResult saveContent(TbContent content) {
		// 补全属性
		content.setCreated(new Date());
		content.setUpdated(content.getCreated());
		
		// 插入
		mapper.insertSelective(content);
		
		// 缓存同步
		try {
			jedisClient.hdel(CONTENT_KEY, content.getCategoryId()+"");
			System.out.println("当插入时，清空缓存");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return MallResult.ok();
	}

	@Override
	public List<TbContent> getContentListByCategoryId(Long categoryId) {
		
		try {
			// 判断 redis中是否有数据，如果有就从redis中获取数据
			String jsonStr = jedisClient.hget(CONTENT_KEY, categoryId+"");
			// 如果存在，说明有缓存
			if(StringUtils.isNotBlank(jsonStr)){
				System.out.println("使用缓存");
				return JsonUtils.jsonToList(jsonStr, TbContent.class);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		// select * from tbcontent where category_id = 
		
		List<TbContent> list = mapper.selectByExample(example);
		System.out.println("无缓存");
		
		// 将数据写入redis数据库中
		// 注入 jedisClient
		// 调用方法写入,不能影响正常的业务逻辑，所以要加try
		try {
			jedisClient.hset(CONTENT_KEY, categoryId+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
