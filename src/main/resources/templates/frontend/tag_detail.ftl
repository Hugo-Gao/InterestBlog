<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>

<body>
<div class="page">
    <div class="page-main">
        <@common.head_tab tab="tags"/>
        <div class="my-3 my-md-5" id="blog">

            <div class="container">
                <div class="page-header">
                    <h1 class="page-title">
                        ${tag}:
                    </h1>
                </div>
                <div class="row row-cards row-deck">
                    <#list blogList as blog>
                        <div class="col-lg-4">
                            <div class="card">
                                <div class="card-body d-flex flex-column">
                                    <h4><a href="../blog/${blog.id}">${blog.title}</a></h4>
                                    <div class="text-muted">${blog.digest}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>

            </div>
        </div>
    </div>
    <@common.footer/>

</div>
</body>
</html>