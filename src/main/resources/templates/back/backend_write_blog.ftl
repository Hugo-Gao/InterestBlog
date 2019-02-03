<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>

<body>
<div class="page">
    <div class="page-main">
        <@common.backend_tab tab='write'/>
        <div class="my-3 my-md-5">
            <div class="container" >
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10">
                        <form class="card" method="post" action="/backend/write_blog">
                            <div class="card-body">
                                <h2>Write Blog</h2>
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
                                    <div class="col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label class="form-label">文章标题</label>
                                            <input type="text" name="title" class="form-control">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                    </div>
                                    <div class="col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label class="form-label">文章tag(以;分割)</label>
                                            <input type="text" name="tagString" class="form-control">
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group mb-0">
                                            <label class="form-label">博客内容</label>
                                            <textarea rows="15" name="content" class="form-control"
                                                      placeholder="以MarkDown格式输入文章"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer text-right">
                                <button type="submit" class="btn btn-primary">上传博客</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-1"></div>

                </div>

            </div>

        </div>

    </div>

    <@common.footer/>
</div>
<script type="text/javascript">
    $(".close").click(function(){
        $(".test").hide();
    })
</script>
</body>
</html>