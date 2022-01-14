package web.servlet;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import pojo.Brand;
import pojo.PageBean;
import service.BrandService;
import service.impl.BrandServiceImpl;
import util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Create by LCX on 1/13/2022 3:56 PM
 */
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService=new BrandServiceImpl();

    /**
     * 查询所有品牌
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用service查询所有商品
        List<Brand> brands = brandService.selectAll();
        //将查询结果转换为JSON
        String jsonString = JSON.toJSONString(brands);
        //响应JSON数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 根据id查询某个品牌
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectBrandById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 1);
        //调用service根据id查询该品牌
        Brand brand = brandService.selectBrandById(id);
        //将查询结果转换为JSON
        String jsonString = JSON.toJSONString(brand);
        //响应JSON数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 添加品牌
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //接收品牌数据
        BufferedReader reader = req.getReader();
        String jsonString = reader.readLine();//json字符串
        //转为Brand对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);
        //调用service添加
        brandService.add(brand);
        //响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 修改品牌
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //接收品牌数据
        BufferedReader reader = req.getReader();
        String jsonString = reader.readLine();//json字符串
        //转为Brand对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);
        //调用service修改
        brandService.update(brand);
        //响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //接收数据 [1,2,3]
        BufferedReader reader = req.getReader();
        String jsonString = reader.readLine();//json字符串

        //转为int[]
        int[] ids = JSON.parseObject(jsonString, int[].class);
        //调用service添加
        brandService.deleteByIds(ids);
        //响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 根据id删除品牌
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //接收id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用service添加
        brandService.deleteById(id);
        //响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前页码和每页展示条数 url?currentPage=1&pageSize=5;
        int currentPage =WebUtils.parseInt(req.getParameter("currentPage"), 1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"),5);
        //调用service查询当前页商品
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //将查询结果转换为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //响应JSON数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
    /**
     * 分页条件查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前页码和每页展示条数 url?currentPage=1&pageSize=5;
        int currentPage =WebUtils.parseInt(req.getParameter("currentPage"), 1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"),5);
        //获取查询条件对象
        String params = req.getReader().readLine();
        //将JSON数据转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //调用service查询当前页商品
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);

        //将查询结果转换为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //响应JSON数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
