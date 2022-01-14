package web.servlet;

import com.alibaba.fastjson.JSON;
import pojo.Brand;
import service.BrandService;
import service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Create by LCX on 1/12/2022 8:22 PM
 */
//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private BrandService service=new BrandServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service查询所有商品
        List<Brand> brands = service.selectAll();
        //将查询结果转换为JSON
        String jsonString = JSON.toJSONString(brands);
        //响应JSON数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
