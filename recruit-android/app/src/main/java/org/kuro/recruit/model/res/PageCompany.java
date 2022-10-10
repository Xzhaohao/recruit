package org.kuro.recruit.model.res;

import org.kuro.recruit.model.entity.Company;

import java.util.List;

public class PageCompany {

    private long total;

    // 当前页
    private int pageNum;
    // 总页数
    private int pages;

    private List<Company> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Company> getRows() {
        return rows;
    }

    public void setRows(List<Company> rows) {
        this.rows = rows;
    }
}
