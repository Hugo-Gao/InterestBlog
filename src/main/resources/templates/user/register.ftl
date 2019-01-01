<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>
<body class="">
<div class="page">
    <div class="page-single">
        <div class="container">
            <div class="row">
                <div class="col col-login mx-auto">
                    <div class="text-center mb-6">
                        <img src="https://i.loli.net/2018/12/29/5c273ec02db9e.png" alt="">
                    </div>
                    <form class="card" method="post" enctype="multipart/form-data" action="/user/register">
                        <div class="card-body p-6">
                            <div class="card-title">创建一个新账户</div>
                            <div class="form-group">
                                <label class="form-label">Email地址</label>
                                <input type="email" name="email" class="form-control" placeholder="Enter name">
                            </div>

                            <div class="form-group">
                                <label class="form-label">密码</label>
                                <input type="password" name="password" class="form-control" placeholder="Password">
                            </div>

                            <div class="form-group">
                                <label class="form-label">Blog名称</label>
                                <input type="text" name="blogName" class="form-control" placeholder="Enter email">
                            </div>

                            <div class="form-group">
                                <label class="form-label">请输入自我介绍</label>
                                <input type="text" name="aboutme" class="form-control" placeholder="Enter email">
                            </div>

                            <div class="form-group">
                                <div class="form-label">选择头像
                                </div>
                                <div class="custom-file">
                                    <input type="file" id="test-image-file" class="custom-file-input" name="avatarFile">
                                    <label class="custom-file-label">Choose file</label>
                                    <#--<div id="test-image-preview"-->
                                         <#--style="text-align: center;border: 1px solid #ccc;width: 50px; height: 50px; background-size: contain; background-repeat: no-repeat; background-position: center center;"></div>-->
                                </div>
                            </div>

                            <div class="form-group" style="text-align: center">
                            <span id="test-image-preview" class="avatar avatar-xl"></span>
                            </div>

                            <div class="form-footer">
                                <button type="submit" class="btn btn-primary btn-block">注册</button>
                            </div>

                        </div>
                    </form>
                    <div class="text-center text-muted">
                        已经有账户了？ <a href="/user/login">登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" >

    $(document).ready(function() {
        var errorMsg = getUrlParam("errorMsg");
        var successMsg = getUrlParam("successMsg");
        if(errorMsg){
            errormsg("error",errorMsg);
        }
        if(successMsg) {
        successmsg("success",successMsg);
        }
    });
    //检查是否支持FileReader对象
    var
        fileInput = document.getElementById('test-image-file'),
        preview = document.getElementById('test-image-preview');
    // 监听change事件:
    fileInput.addEventListener('change', function ()
    {
        // 清除背景图片:
        preview.style.backgroundImage = '';
        // 检查文件是否选择:
        if (!fileInput.value) {
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
        reader.onload = function (e)
        {
            var data = e.target.result; // 'data:image/jpeg;base64,/9j/4AAQSk...(base64编码)...'
            preview.style.backgroundImage = 'url(' + data + ')';
        };
        // 以DataURL的形式读取文件:
        reader.readAsDataURL(file);
    });



</script>
</body>
</html>