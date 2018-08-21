package com.example.adminletdemo.page;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<?> data;

    public PageInfo(List<T> datas) {
        this.recordsTotal = (long) datas.size();
        this.recordsFiltered = (long) datas.size();
        this.data = datas;
    }

}