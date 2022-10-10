package org.kuro.recruit.model.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private long total;

    // 当前页
    private int pageNum;
    // 总页数
    private int pages;

    private List<T> rows;

}
