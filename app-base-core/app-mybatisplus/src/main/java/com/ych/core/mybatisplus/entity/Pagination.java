package com.ych.core.mybatisplus.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Pagination<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private long current;

    private long size;

    private long total;

    private long pages;

    private List<T> records;

    public Pagination(IPage<T> page) {
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.records = page.getRecords();
    }

}
