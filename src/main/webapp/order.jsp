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
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/boot/order"
          method="post" target="orderFrame">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">名字</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" value="${goods.name}" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="price" class="col-sm-2 control-label">价格</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="price" value="${goods.price}" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="store" class="col-sm-2 control-label">库存</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="store" value="${goods.store}" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="buyNum" class="col-sm-2 control-label">购买数量</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="buyNum" name="buyNum" placeholder="请输入购买数量">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" id="goodsId" name="goodsId" value="${goods.id}">
                <button type="submit" class="btn btn-default">提 交</button>
            </div>
        </div>
    </form>

    <!--使用一个iframe实现页面不刷新的上传-->
    <iframe name="orderFrame" style="display: none;"></iframe>

<!--导入layer的弹层组件的js文件-->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>

<script type="text/javascript">
    function orderOK (result) {
        if (result == 0) {
            layer.alert('下单成功', function(index){
                window.location.href = "${pageContext.request.contextPath}/";
                layer.close(index);
            });
        } else {
            layer.alert("下单失败");
        }
    }
</script>
</body>
</html>