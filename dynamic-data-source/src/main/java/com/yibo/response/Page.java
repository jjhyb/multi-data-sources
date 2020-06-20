package com.yibo.response;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author: huangyibo
 * @Date: 2020/6/14 3:01
 * @Description:
 */
public class Page<T> implements Serializable {

    /**
     * 每页最大数据条数
     */
    private static final int MAX_PAGE_SIZE = 200;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 总数据条数
     */
    private Integer totalCount;

    /**
     * 页码总数
     */
    private Integer totalPages;

    /**
     * 数据集合
     */
    private Collection<T> items;

    public static int getMaxPageSize() {
        return MAX_PAGE_SIZE;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = items;
    }
}
