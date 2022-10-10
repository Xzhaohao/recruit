package org.kuro.recruit.service;

import org.kuro.recruit.model.page.PageResult;
import org.kuro.recruit.model.vo.CompanyVo;

public interface CompanyService {

    /**
     * 分页获取企业列表
     *
     * @param page  当前页
     * @param limit 每页显示条数
     * @param name  企业名称关键字
     * @return 企业列表
     */
    PageResult<CompanyVo> companyList(Integer page, Integer limit, String name);

}
