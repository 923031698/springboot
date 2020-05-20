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

<body>

<div style="text-align: center;margin: 45px;">
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/boot/register"
          method="post" target="registerFrame">

        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">手机</label>
            <div class="col-sm-10">
                <input type="text" id="phone" name="phone" placeholder="请输入名字">
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
                <input type="password" id="password" name="password" placeholder="请输入密码">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">注 册</button>
                <a href="${pageContext.request.contextPath}/login.jsp">已有账号?</a>
            </div>
        </div>
    </form>

    <!--使用一个iframe实现页面不刷新的上传-->
    <iframe name="registerFrame" style="display: none;"></iframe>
</div>

<!--导入layer的弹层组件的js文件-->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>

<script type="text/javascript">
    function uploadOK (result) {
        if (result == "OK") {
            layer.alert('注册成功', function(index){
                window.location.href = "${pageContext.request.contextPath}/";
                layer.close(index);
            });
        } else {
            layer.alert("注册失败");
        }
    }
</script>
</body>
</html>