package org.kuro.recruit.model.res;

public class CompanyRes {

    private Boolean status;
    private Integer code;
    private String message;
    private PageCompany data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageCompany getData() {
        return data;
    }

    public void setData(PageCompany data) {
        this.data = data;
    }
}
