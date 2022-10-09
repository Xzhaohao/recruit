package org.kuro.recruit.model.bo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class JobExpectBo {

    private String id;

    // 1新增，2修改
    @Range(min = 1, max = 2, message = "2为修改，1为新增")
    private Integer operate;

    // 1全职，2兼职
    @NotNull(message = "求职类型不能为空！")
    @Range(min = 1, max = 2, message = "求职类型传1全职或2兼职")
    private Integer type;

    @NotBlank(message = "工作城市不能为空！")
    private String city;

    @NotNull(message = "职位ID不能为空！")
    private Integer positionId;

    @NotBlank(message = "期望职位不能为空！")
    private String position;

    private String salary;

    // 求职状态：离职-随时到岗
    @NotBlank(message = "求职状态不能为空！")
    private String status;
}
