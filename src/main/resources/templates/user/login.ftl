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
                    <form class="card" method="post" enctype="multipart/form-data" action="/user/login">
                        <div class="card-body p-6">
                            <div class="card-title">请登录您的账户</div>
                            <div class="form-group">
                                <label class="form-label">邮箱</label>
                                <input type="email" name="email" class="form-control" id="exampleInputEmail1"
                                       aria-describedby="emailHelp" placeholder="Enter email">
                            </div>
                            <div class="form-group">
                                <label class="form-label">
                                    密码
                                </label>
                                <input type="password" name="password" class="form-control" id="exampleInputPassword1"
                                       placeholder="Password">
                            </div>
                            <div class="form-footer">
                                <button type="submit" class="btn btn-primary btn-block">登录</button>
                            </div>
                        </div>
                    </form>
                    <div class="text-center text-muted">
                        还没有账户? <a href="./register.html">注册</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    $(document).ready(function ()
    {

        var errorMsg = getUrlParam("errorMsg");
        var successMsg = getUrlParam("successMsg");

        if (errorMsg) {
            $.alert({
                content: errorMsg
            });
        }
        if (successMsg) {
            // successmsg("success", successMsg);
            $.alert({
                content: errorMsg
            });
        }
    });
</script>
</body>
</html>