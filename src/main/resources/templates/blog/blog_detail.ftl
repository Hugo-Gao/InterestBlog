<!doctype html>
<html lang="en" dir="ltr">
<@common.header_md/>

<body>
<div class="page">
    <div class="page-main">
        <@common.head_tab />
        <div class="my-3 my-md-5" id="blog">
            <div class="container col-lg-10">
                <div class="card">
                    <div style="text-align: center;margin-bottom: 10dp">
                        <h1 class="card-title">${blog.title}</h1>
                        <#list tagList as tag>
                            <span class="tag tag-blue">${tag}</span>
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
    <footer class="footer">
        <div class="container">
            <div style="text-align: center">
                <div class="col-12 col-lg-auto mt-3 mt-lg-0 text-center">
                    Copyright © 2019 <a href=".">Hugo-Gao</a>. Theme by <a href="https://tabler.io/" target="_blank">Tabler</a>
                </div>
            </div>
        </div>
    </footer>
</div>
</body>
</html>