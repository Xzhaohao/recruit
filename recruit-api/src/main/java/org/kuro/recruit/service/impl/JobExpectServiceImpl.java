package org.kuro.recruit.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import org.kuro.recruit.exceptions.BusinessException;
import org.kuro.recruit.mapper.JobExpectMapper;
import org.kuro.recruit.model.entity.JobExpect;
import org.kuro.recruit.model.result.ResultCode;
import org.kuro.recruit.service.JobExpectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


@Service
public class JobExpectServiceImpl implements JobExpectService {

    @Autowired
    private JobExpectMapper __job_expect_mapper;

    @Override
    public void saveJobExpect(JobExpect expect) {
        String loginId = StpUtil.getLoginIdAsString();
        Example example = new Example(JobExpect.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("enabled", 2);
        criteria.andEqualTo("userId", loginId);
        int count = __job_expect_mapper.selectCountByExample(example);
        if (count >= 3) {
            throw new BusinessException(ResultCode.JOB_EXPECT_MORE3);
        }

        expect.setId(IdUtil.objectId());
        expect.setUserId(loginId);
        expect.setEnabled(2);
        expect.setCreateTime(new Date());

        __job_expect_mapper.insertSelective(expect);
    }

    @Override
    public void updateJobExpect(JobExpect expect) {
        __job_expect_mapper.updateByPrimaryKeySelective(expect);
    }

    @Override
    public List<JobExpect> jobExpectList() {
        return __job_expect_mapper.selectExpects(2, StpUtil.getLoginIdAsString());
    }

    @Override
    public JobExpect queryById(String id) {
        return __job_expect_mapper.selectByPrimaryKey(id);
    }
}
