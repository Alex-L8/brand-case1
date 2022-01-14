package service.impl;

import mapper.BrandMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Brand;
import pojo.PageBean;
import service.BrandService;
import util.SqlSessionFactoryUtils;

import java.util.List;

/**
 * Create by LCX on 1/12/2022 8:49 PM
 */
public class BrandServiceImpl implements BrandService {
    //创建SqlSessionFactory工厂对象
    SqlSessionFactory sqlSessionFactory= SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有品牌
     * @return
     */
    @Override
    public List<Brand> selectAll(){
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = brandMapper.selectAll();
        //释放资源
        sqlSession.close();
        return brands;
    }

    @Override
    public Brand selectBrandById(int id) {
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = brandMapper.selectBrandById(id);
        return brand;
    }

    /**
     * 添加品牌
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改品牌
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }
    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据id删除该品牌
     * @param id
     */
    @Override
    public void deleteById(int id) {
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取brandMapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //开始索引
        int begin=(currentPage-1)*pageSize;
        //查询条数
        int size=pageSize;
        //调用分页查询的方法查询当前页品牌集合
        List<Brand> rows = brandMapper.selectByPage(begin, size);
        //当前页品牌的总条数
        int totalCount = brandMapper.selectTotalCount();
        //封装到PageBean对象中
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setTotalCount(totalCount);
        pageBean.setRows(rows);

        sqlSession.close();

        return pageBean;
    }

    /**
     * 分页条件查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param brand 查询条件
     * @return
     */
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize,Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //开始索引
        int begin=(currentPage-1)*pageSize;
        //查询条数
        int size=pageSize;
        //处理brand条件
        String brandName=brand.getBrandName();
        if(brandName!=null && brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName=brand.getCompanyName();
        if(companyName!=null && companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }
        //调用分页查询的方法查询当前页品牌集合
        List<Brand> rows = brandMapper.selectByPageAndCondition(begin, size,brand);
        //当前页品牌的总条数
        int totalCount = brandMapper.selectTotalCountByCondition(brand);
        //封装到PageBean对象中
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setTotalCount(totalCount);
        pageBean.setRows(rows);

        sqlSession.close();

        return pageBean;
    }

}
