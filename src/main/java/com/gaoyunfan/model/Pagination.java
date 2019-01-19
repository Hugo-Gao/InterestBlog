package com.gaoyunfan.model;

import lombok.Data;

/**
 * @author yunfan.gyf
 **/
@Data
public class Pagination {
    private int pageIndex;
    private final int pageNum = 6;

    public Pagination(int page) {
        this.pageIndex = (page - 1) * 6;
    }

    public Pagination() {
    }
}
