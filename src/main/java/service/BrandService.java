package service;

import org.apache.ibatis.annotations.Param;
import pojo.Brand;
import pojo.PageBean;

import java.util.List;

/**
 * Create by LCX on 1/12/2022 8:47 PM
 */
public interface BrandService {
    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> selectAll();


    /**
     * 查询某个品牌
     * @param id
     * @return
     */
    Brand selectBrandById(int id);
    /**
     * 添加品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 修改品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 批量删除品牌
     * @param ids
     */
    void deleteByIds(int[] ids);

    /**
     * 根据id删除品牌
     * @param id
     */
    void deleteById(int id);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */

    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    /**
     * 分页条件查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param brand 查询条件
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);

}
