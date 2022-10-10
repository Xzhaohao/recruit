package org.kuro.recruit.mapper;

import org.kuro.recruit.model.entity.Company;
import org.kuro.recruit.model.vo.CompanyVo;
import org.springframework.data.repository.query.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CompanyMapper extends Mapper<Company> {

    List<CompanyVo> selectCompanyList(@Param("name") String name);
}
