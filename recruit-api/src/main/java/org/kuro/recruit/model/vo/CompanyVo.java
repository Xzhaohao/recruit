package org.kuro.recruit.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kuro.recruit.model.entity.Company;

@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyVo extends Company {

    private String[] tagArr;
}
