<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>动态英雄列表展示</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </head>
  <body>
      <div class="container">
        <div class="row">
          <div class="col-md-6 col-md-offset-3 main">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>编号</th>
                  <th>名称</th>
                  <th>性别</th>
                  <th>年龄</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="pageData" items="${requestScope.pageData.heroList }">
                  <tr>
                    <td>${pageData.id }</td>
                    <td>${pageData.name }</td>
                    <td>${pageData.sex }</td>
                    <td>${pageData.age }</td>
                    <td><a href="#">删除</a></td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
            <nav aria-label="...">
              <ul class="pager">
                <li class="previous" id="lastPage"><a href="QueryByPageServlet?currentPage=${requestScope.pageData.currentPage-1}"><span aria-hidden="true">&larr;</span> 上一页</a></li>
                <li class="next" id="nextPage"><a href="QueryByPageServlet?currentPage=${requestScope.pageData.currentPage+1 }">下一页 <span aria-hidden="true">&rarr;</span></a></li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
      <script>
        $(function () {
          if("${requestScope.pageData.currentPage}"<=1){
            $("#lastPage").attr("class","previous disabled");
            $("#lastPage > a").attr("href","#");
          }
          if("${requestScope.pageData.currentPage}">="${requestScope.pageData.totalPage}"){
            $("#nextPage").attr("class","next disabled");
            $("#nextPage > a").attr("href","#");
          }
        })
      </script>
  </body>
</html>
