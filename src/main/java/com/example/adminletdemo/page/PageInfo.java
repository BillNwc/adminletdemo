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

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

}