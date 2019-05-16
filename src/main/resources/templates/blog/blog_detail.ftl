<!doctype html>
<html lang="en" dir="ltr">
<@common.header_md/>

<body>
<div class="page">
    <div class="page-main">
        <@common.head_tab tab="home"/>
        <div class="my-3 my-md-5" id="blog">
            <div class="container col-lg-9">
                <div class="card">
                    <div style="text-align: center">
                        <h1 class="card-title">${blog.title}</h1>
                        <#list tagList as tag>
                            <button onclick="window.location.href='../tags/${tag.id}'" class="btn btn-primary btn-sm ${tag.style}">${tag.tag}</button>
                        </#list>
                        <div class="leading-loose" style="margin-top: 7px">${blog.createTime?string('yyyy-MM-dd HH:mm')}
                            <a href="javascript:void(0)" class="icon" style="margin-left: 10px">
                                <i class="fe fe-eye mr-1"></i> ${views}
                            </a>
                        </div>
                    </div>
                    <div class="card-body" style="margin-left: 30px;margin-right: 30px">
                        ${blog.mdContent}
                    </div>
                </div>
            </div>

            <div class="container col-lg-9">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">请发表您的看法</h3>
                    </div>
                    <div class="card-body" >
                        <form method="post" action="/blog/${blogId}/comment">
                            <div class="form-group">
                                <label class="form-label">昵称</label>
                                <input name="nickname" class="form-control"  maxlength="8" />
                            </div>
                            <div class="form-group">
                                <label class="form-label">评论</label>
                                <input name="content" class="form-control"/>
                            </div>
                            <div class="form-footer" style="text-align: center">
                                <input type="submit" class="btn btn-primary" value="提交" />
                            </div>
                        </form>
                    </div>
                </div>
                <h2>评论</h2>

                <#list commentList as comment>
                <div class="card">
                    <div class="card-status bg-blue"></div>
                    <div class="card-header ">
                        <h3 class="card-title col-lg-6">${comment.nickname}</h3>
                        <h3 class="card-title col-lg-6" style="text-align: right">${comment.createTime?string('yyyy-MM-dd HH:mm')}</h3>
                    </div>
                    <div class="card-body"  style="margin-left: 30px;margin-right: 30px">
                        ${comment.content}
                    </div>
                </div>
                </#list>
            </div>

        </div>
    </div>
    <@common.footer/>

</div>
<script type="text/javascript">
    $("#searchInput").blur(function ()
        {
            console.log("失去焦点");
            setTimeout(function ()
            {
                $("#searchDialog").removeClass("show");
                $("#searchDialog").empty()
            }, 200)

        }
    )
</script>
</body>
</html>