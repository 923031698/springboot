<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件上传</title>
</head>
<body>
<!--文件上传的表单的提交方式必须是post-->
<form action="fileUploadController" method="post" enctype="multipart/form-data">
    上传文件 <input type="file" name="filename"/><br/>
    <input type="submit">
</form>
</body>
</html>