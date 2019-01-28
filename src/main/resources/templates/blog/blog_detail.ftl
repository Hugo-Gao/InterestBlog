<!doctype html>
<html lang="en" dir="ltr">
<@common.header_md/>

<body>
<div class="page">
    <div class="page-main">
        <@common.head_tab tab="home"/>
        <div class="my-3 my-md-5" id="blog">
            <div class="container col-lg-10">
                <div class="card">
                    <div style="text-align: center;margin-bottom: 10dp">
                        <h1 class="card-title">${blog.title}</h1>
                        <#list tagList as tag>
                            <button onclick="window.location.href='../tags/${tag.id}'" class="btn btn-primary btn-sm ${tag.style}">${tag.tag}</button>
                        </#list>
                        <div class="leading-loose" style="margin-top: 7px">${blog.createTime?string('yyyy-MM-dd HH:mm')}
                            <a href="javascript:void(0)" class="icon" style="margin-left: 10px">
                                <i class="fe fe-eye mr-1"></i> 112
                            </a>
                        </div>

                    </div>
                    <div class="card-body" style="margin-left: 30px;margin-right: 30px">
                        ${blog.mdContent}
                    </div>
                </div>
            </div>
        </div>
    </div>
    <@common.footer/>

</div>
</body>
</html>