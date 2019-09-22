package com.zkai.hero.servlet;

import com.zkai.hero.entity.Hero;
import com.zkai.hero.entity.Page;
import com.zkai.hero.service.IHeroService;
import com.zkai.hero.service.impl.HeroServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryByPageServlet")
public class QueryByPageServlet extends HttpServlet {

    //
    private IHeroService<Hero> heroService = new HeroServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        Page page=new Page();
        //总数据
        int totalCount=heroService.queryCount();
        page.setTotalCount(totalCount);
        //当前页
        String flag=request.getParameter("currentPage");
        if(flag==null){
            flag="1";
        }
        int currentPage=Integer.parseInt(flag);
        page.setCurrentPage(currentPage);
        //页面大小
        int pageSize=7;
        page.setPageSize(pageSize);
        //数据集合
        List<Hero> heroes=heroService.queryByPage(currentPage,pageSize);
        page.setHeroList(heroes);
        //总页数(自动计算)

        request.setAttribute("pageData",page);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
