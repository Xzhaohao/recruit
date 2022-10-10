package org.kuro.recruit.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.houkunlin.system.dict.starter.json.DictText;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_company")
public class Company {

    @Id
    private String id;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "公司类型")
    private String type;

    @ApiModelProperty(value = "公司logo")
    private String logo;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "tags")
    private String tags;

    @ApiModelProperty(value = "公司服务类型")
    private String serviceType;

    @ApiModelProperty(value = "工作时间")
    private String workHours;

    @ApiModelProperty(value = "简介")
    private String introduce;

    @ApiModelProperty(value = "融资类型")
    private String financingType;

    @ApiModelProperty(value = "公司规模")
    private String population;

    @ApiModelProperty(value = "官网")
    private String officialWebsite;

    @ApiModelProperty(value = "成立时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date foundingTime;

    @DictText("enabled")
    @ApiModelProperty(value = "1删除，2正常")
    private Integer enabled;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date updateTime;
}
