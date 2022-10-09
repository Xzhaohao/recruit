package org.kuro.recruit.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houkunlin.system.dict.starter.json.DictText;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "sys_job_expect")
public class JobExpect {

    @Id
    private String id;

    @ApiModelProperty(value = "1全职，2兼职")
    private Integer type;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "期望职位ID")
    private Integer positionId;

    @ApiModelProperty(value = "期望职位")
    private String position;

    @ApiModelProperty(value = "薪资要求")
    private String salary;

    // 离职-随时到岗
    @ApiModelProperty(value = "求职状态")
    private String status;

    @DictText("enabled")
    @ApiModelProperty(value = "1删除，2正常")
    private Integer enabled;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date updateTime;
}
