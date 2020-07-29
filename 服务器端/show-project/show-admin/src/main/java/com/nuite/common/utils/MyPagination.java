package com.nuite.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 页码工具类
 *
 * @Author: yangchuang
 * @Date: 2019/1/22 14:37
 * @Version: 1.0
 */

@Data
public class MyPagination<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总数
     */
    private int total;

    /**
     * 每页显示条数
     */
    private int size;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 当前页
     */
    private int current = 1;

    /**
     * 查询数据列表
     */
    private List<T> records;

    public MyPagination(Page<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getCurrent();
        this.pages = page.getPages();
    }

    /**
     * @param total   总记录数
     * @param size    每页数量
     * @param current 当前页
     * @param records 当前页数据列表
     */
    public MyPagination(int total, int size, int current, List<T> records) {
        this.total = total;
        this.size = size;
        this.current = current;
        this.records = records;
        this.pages = (int) Math.ceil(total / size);
    }

}
