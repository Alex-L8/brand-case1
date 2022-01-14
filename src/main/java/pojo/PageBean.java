package pojo;

import java.util.List;

/**
 * Create by LCX on 1/13/2022 7:41 PM
 */
public class PageBean<T> {

    //纪录总条数
    private int totalCount;
    //当前页数据
    private List<T> rows;

    public PageBean() {
    }

    public PageBean(int totalCount, List<T> rows) {
        this.totalCount = totalCount;
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "pageBean{" +
                "totalCount=" + totalCount +
                ", rows=" + rows +
                '}';
    }
}
