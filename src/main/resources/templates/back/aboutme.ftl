<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>

<body>
<div class="page">
    <div class="page-main">
        <@common.backend_tab tab='aboutme'/>
        <div class="my-3 my-md-5">
            <div class="container">
                <#if successMsg??>
                    <div class="alert alert-success alert-dismissible test">
                        <button  type="button" class="close" data-dismiss="alert"></button>
                        ${successMsg}
                    </div>
                </#if>
                <#if errorMsg??>
                    <div class="alert alert-error alert-dismissible test">
                        <button type="button" class="close" data-dismiss="alert"></button>
                        ${errorMsg}
                    </div>
                </#if>
                <div class="row">

                    <div class="col-lg-4">
                        <script>
                            require(['input-mask']);
                        </script>
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">我的资料(均可选填哦)</h3>
                            </div>
                            <div class="card-body">
                                <form method="post" enctype="multipart/form-data" action="/backend/aboutme">
                                    <div class="row">
                                        <div class="col-auto">
                                            <span class="avatar avatar-xl" id="avatar" style="background-image: url(${user.avaterPath})">
                                                <input type="file" id="test-image-file" class="custom-file-input" name="avatarFile">
                                            </span>
                                        </div>
                                        <div class="col">
                                            <div class="form-group">
                                                <label class="form-label">邮箱地址</label>
                                                <input class="form-control" placeholder="your-email@domain.com" value="${user.email}" readonly>
                                                <input class="form-control"  name="email" placeholder="your-email@domain.com" value="${user.email}" hidden>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">个性签名</label>
                                        <input class="form-control" name="slogan" placeholder="请一句话展示自己" value="${user.slogan!}">

                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">密码</label>
                                        <input type="password" class="form-control" name="password" value="${user.password!}">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Github地址</label>
                                        <input type="text" class="form-control" name="githubUrl" value="${user.githubUrl!}">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">微博地址</label>
                                        <input type="text" class="form-control" name="sinaUrl" value="${user.sinaUrl!}" >
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">QQ</label>
                                        <input type="text" class="form-control" name="qq" value="${user.qq!}">
                                    </div>
                                    <div class="form-footer">
                                        <button type="submit" class="btn btn-primary btn-block">保存</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <form class="card" method="post" enctype="multipart/form-data" action="/backend/aboutme">
                            <div class="card-body">
                                <h3 class="card-title">编辑</h3>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="form-label">昵称</label>
                                            <input type="text" class="form-control" placeholder="name" name="nickName" value="${user.nickName!}">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="form-label">博客名称</label>
                                            <input type="text" class="form-control" placeholder="Blog Name" name="blogName" value="${user.blogName!}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">公司及职位</label>
                                            <input type="text" class="form-control" placeholder="Company" name="company" value="${user.company!}">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label class="form-label">城市</label>
                                            <input type="text" class="form-control" placeholder="City" name="city" value="${user.city!}">
                                        </div>
                                    </div>
                                    <input  name="email" type="hidden" value="${user.email}">


                                    <div class="col-md-12">
                                        <div class="form-group mb-0">
                                            <label class="form-label">关于我</label>
                                            <textarea rows="5" class="form-control" name="aboutme" placeholder="请详细介绍自己吧">
                                                <#lt>${user.aboutme!?trim}<#rt>
                                            </textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer text-right">
                                <button type="submit" class="btn btn-primary">更新资料</button>
                            </div>
                        </form>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <@common.footer/>
</div>
<script type="text/javascript">
    $(".close").click(function ()
    {
        $(".test").hide();
    });

    var
        fileInput = document.getElementById('test-image-file'),
        preview = document.getElementById('avatar');
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