package com.ntc.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntc.mall.mapper.TestMapper;
import com.ntc.mall.service.TestService;

/**
 * @author Michael-Chen
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapper testMapper;
	
	@Override
	public String queryNow() {
		return testMapper.queryNow();
	}

}
