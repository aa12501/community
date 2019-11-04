package com.aa12501.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;
    private Integer page;   //当前页
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    //totalCount是总条数，totalPage是总页数
    public void setPagination(Integer totalPage, Integer page, Integer size) {
        this.page = page;
        this.totalPage = totalPage;

        pages.add(page);
        for (int i = 1; i <= 2; i++) {
            if (page - i >= 1) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(pages.size(), page + i);
            }
        }

        //是否展示上一页按钮
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        //是否展示下一页按钮
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //是否展示首页按钮
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否展示最后一页按钮
        if (pages.contains(totalPage)) {
            showLastPage = false;
        } else {
            showLastPage = true;
        }
    }
}
