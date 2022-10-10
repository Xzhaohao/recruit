package org.kuro.recruit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kuro.recruit.model.page.PageResult;
import org.kuro.recruit.model.result.Result;
import org.kuro.recruit.model.vo.CompanyVo;
import org.kuro.recruit.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
@Api(value = "企业模块", tags = "企业相关")
public class CompanyController {

    @Autowired
    private CompanyService __company_service;

    @ApiOperation(value = "企业列表", notes = "获取企业列表")
    @GetMapping("/list")
    public Result fetchCompListApi(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(name = "name", required = false) String name
    ) {
        PageResult<CompanyVo> result = __company_service.companyList(page, limit, name);
        return Result.ok().data(result);
    }

}
