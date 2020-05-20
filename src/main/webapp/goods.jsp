<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>SpringBoot CRUD</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="margin: 45px;">

<table class="table table-striped">
    <c:choose>
        <c:when test="${empty sessionScope.users}">
            <caption><a href="${pageContext.request.contextPath}/login.jsp">登录</a></caption>
        </c:when>
        <c:otherwise>
            <caption><a href="${pageContext.request.contextPath}/boot/logout">退出</a></caption>
        </c:otherwise>
    </c:choose>

    <caption>信息列表</caption>
    <thead>
    <tr>
        <th>序号</th>
        <th>商品名称</th>
        <th>剩余库存</th>
        <th>操作</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${goodsList}" var="goods" varStatus="goodsStatus">
        <tr>
            <td>${goodsStatus.count}</td>
            <td>${goods.name}</td>
            <td>${goods.store}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty users}">
                        <a href="${pageContext.request.contextPath}/boot/toOrder?goodsId=${goods.id}">下单</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/login.jsp">登录后下单</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<!--导入layer的弹层组件的js文件-->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>

<script type="text/javascript">
    function deleteFile(filesId) {
        $.ajax({
            url : "${pageContext.request.contextPath}/boot/deleteFile",
            type : "POST",
            dataType : "text",
            data : {"filesId" : filesId},
            success : function (responseMessage) {
                alert(responseMessage);
                if (responseMessage == "OK") {
                    layer.alert('文件删除成功', function(index){
                        //刷新当前页面
                        window.location.reload();
                        layer.close(index);
                    });
                } else {
                    layer.alert("文件删除失败");
                }
            },
            error : function() {
                alert(123);
            }
        });
    }
</script>

</body>
</html>