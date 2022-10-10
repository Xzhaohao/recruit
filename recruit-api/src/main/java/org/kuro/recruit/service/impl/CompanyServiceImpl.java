package org.kuro.recruit.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuro.recruit.mapper.CompanyMapper;
import org.kuro.recruit.model.page.PageResult;
import org.kuro.recruit.model.vo.CompanyVo;
import org.kuro.recruit.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper __company_mapper;


    @Override
    public PageResult<CompanyVo> companyList(Integer page, Integer limit, String name) {
        if (page != null && limit != null) {
            PageHelper.startPage(page, limit);
        }

        List<CompanyVo> list = __company_mapper.selectCompanyList(name);
        list.forEach(item -> {
            if (StrUtil.isNotBlank(item.getTags())) {
                String[] split = item.getTags().split(",");
                // 如果关键词超过4个则截取前4个
                if (split.length > 4) {
                    item.setTagArr(Arrays.copyOf(split, 4));
                } else {
                    item.setTagArr(split);
                }
            }
        });

        PageInfo<CompanyVo> info = new PageInfo<>(list);
        return new PageResult<>(
                info.getTotal(),
                info.getPageNum(),
                info.getPages(),
                list
        );
    }

}
