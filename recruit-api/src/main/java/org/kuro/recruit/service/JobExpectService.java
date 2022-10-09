package org.kuro.recruit.service;

import org.kuro.recruit.model.entity.JobExpect;

import java.util.List;

public interface JobExpectService {

    // 添加求职期望
    void saveJobExpect(JobExpect expect);

    // 修改求职期望
    void updateJobExpect(JobExpect expect);

    // 查询登录用户的求职期望
    List<JobExpect> jobExpectList();

    // 根据id查询求职期望
    JobExpect queryById(String id);
}
