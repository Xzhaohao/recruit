package org.kuro.recruit.mapper;

import org.apache.ibatis.annotations.Select;
import org.kuro.recruit.model.entity.JobExpect;
import org.springframework.data.repository.query.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface JobExpectMapper extends Mapper<JobExpect> {

    @Select("select id,type,city,position_id,position,salary,status,enabled,user_id from sys_job_expect where enabled = #{state} and user_id = #{userId}")
    List<JobExpect> selectExpects(@Param("state") Integer state, @Param("userId") String userId);
}
