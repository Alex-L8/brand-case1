package web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pojo.Brand;
import service.BrandService;
import service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Create by LCX on 1/12/2022 9:36 PM
 */
//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    BrandService brandService=new BrandServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String jsonString = reader.readLine();//json字符串
        //转为Brand对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);
        //调用service添加
        brandService.add(brand);
        //响应成功标识
        response.getWriter().write("success");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
