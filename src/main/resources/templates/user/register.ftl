<!DOCTYPE HTML>
<html>
<head>
    <title>注册</title>
    <!-- Custom Theme files -->
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="/layui/layui.js"></script>
</head>
<body style="background: " class="register">
<div class="login">
    <h1>请注册，以使用 InterestBlog</h1>
    <div class="login-top">
        <h1>REGISTER FORM</h1>
        <form method="post" enctype="multipart/form-data" action="/user/register">
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" name="email" required lay-verify="required" placeholder="请输入邮箱"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">博客名称</label>
                <div class="layui-input-block">
                    <input type="text" name="blogName" required lay-verify="required" placeholder="请输入名称"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">请输入自我介绍</label>
                <div class="layui-input-block">
                    <textarea name="aboutme" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">请选择头像</label>
                <div class="layui-input-block">
                    <div id="test-image-preview" style="border: 1px solid #ccc; width: 100%; height: 200px; background-size: contain; background-repeat: no-repeat; background-position: center center;"></div>
                    <input type="file" id="test-image-file" name="avatarFile">
                </div>
            </div>
            <div style="text-align: center;">
                <button type="submit" class="layui-btn layui-btn-lg">登录</button>
            </div>
        </form>

    </div>
    <div class="login-bottom">
        <h3>Welcome</h3>
    </div>
</div>
<div class="copyright">
    <p>Copyright &copy; 2019.Company name All rights reserved Hugo-Gao</p>
</div>

<script type="text/javascript">
    //检查是否支持FileReader对象
    var
        fileInput = document.getElementById('test-image-file'),
        info = document.getElementById('test-file-info'),
        preview = document.getElementById('test-image-preview');
    // 监听change事件:
    fileInput.addEventListener('change', function () {
        // 清除背景图片:
        preview.style.backgroundImage = '';
        // 检查文件是否选择:
        if (!fileInput.value) {
            info.innerHTML = '没有选择文件';
            return;
        }
        // 获取File引用:
        var file = fileInput.files[0];
        // 获取File信息:
        if (file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
            alert('不是有效的图片文件!');
            return;
        }
        // 读取文件:
        var reader = new FileReader();
        reader.onload = function(e) {
            var
                data = e.target.result; // 'data:image/jpeg;base64,/9j/4AAQSk...(base64编码)...'
            preview.style.backgroundImage = 'url(' + data + ')';
        };
        // 以DataURL的形式读取文件:
        reader.readAsDataURL(file);
    });

    // layui.use('layer', function(){
    //     var layer = layui.layer;
    //     layer.msg('hello');
    // });
</script>
</body>


</html>