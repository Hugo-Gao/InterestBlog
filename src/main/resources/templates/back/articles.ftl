<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>

<body>
<div class="page">
    <div class="page-main">
        <@common.backend_tab tab='articles'/>
        <div class="my-3 my-md-5">
            <div class="container">
                <div class="page-header">
                    <h1 class="page-title">
                    </h1>
                </div>
                <div class="row row-cards row-deck">
                    <div class="col-12">
                        <div class="card">
                            <div class="table-responsive">
                                <table class="table table-hover table-outline table-vcenter text-nowrap card-table">
                                    <thead>
                                    <tr>
                                        <th class="text-center">文章名</th>
                                        <th class="text-center">点击数量</th>
                                        <th class="text-center">评论数</th>
                                        <th class="text-center">Tags</th>
                                        <th class="text-center">创建日期</th>
                                        <th class="text-center"><i class="icon-settings"></i></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list blogList as blog>
                                        <tr>
                                            <td>
                                                <div class="text-center">
                                                <a href="../blog/${blog.id}" style="color: #0a0c0d">${blog.title}</a>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="text-center">${blog.views}</div>
                                            </td>
                                            <td class="text-center">
                                                <div class="text-center">
                                                    <#if blog.comments??>
                                                        ${blog.comments?size}
                                                    <#else>
                                                        0
                                                    </#if>

                                                </div>
                                            </td>
                                            <td class="text-center">
                                                <#list blog.tagList as tag>
                                                    <button onclick="window.location.href='../tags/${tag.id}'" class="btn btn-primary btn-sm ${tag.style}">${tag.tag}</button>
                                                </#list>
                                            </td>
                                            <td class="text-center">
                                                <div class="text-center">${blog.createTime?string('yyyy-MM-dd HH:mm')}</div>
                                            </td>

                                            <td class="text-center">
                                                <a href="/backend/to_edit_page?blogId=${blog.id}" class="card-options-remove" style="margin: 5px"><i class="fe fe-edit-3"></i></a>

                                                <a href="#" class="card-options-remove" onclick="deleteBlog(${blog.id})"><i class="fe fe-x"></i></a>
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="text-align: center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item page-prev <#if page==1> disabled </#if>">
                            <a class="page-link" href="./articles?page=${page-1}" tabindex="-1">
                                Prev
                            </a>
                        </li>
                        <#if pageSum gt page+5>
                            <#assign max=page+5>
                        </#if>
                        <#if pageSum lt page+5>
                            <#assign max=pageSum>
                        </#if>
                        <#if page gt 1>
                            <li class="page-item"><a class="page-link" href="./articles?page=${page-1}">${page-1}</a></li>
                        </#if>
                        <#list  page..max as i>
                            <li class="page-item <#if i==page>active</#if>"><a class="page-link" href="./articles?page=${i}">${i}</a></li>
                        </#list>
                        <li class="page-item page-next <#if page==pageSum> disabled </#if>">
                            <a class="page-link" href="./articles?page=${page+1}">
                                Next
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

        </div>

    </div>

    <@common.footer/>
</div>
<script type="text/javascript">

function deleteBlog(blogId){
    blogData={
        blogId:blogId
    };
    $.alert({
        title: '警告',
        content: '您确定要删除这篇文章吗',
        buttons: {
            cancel: {
                text: '取消',
                keys: ['esc'],

            },
            confirm: {
                text: '确定',
                btnClass: 'btn-red',
                keys: ['enter', 'shift'],
                action: function(){
                    $.ajax({
                        type: 'POST',
                        url: "./deleteBlog",
                        data: blogData,
                        success: function ()
                        {
                            window.location.href="./articles"
                        },
                        error:function ()
                        {
                            $.alert({
                                    content: '服务端故障，删除失败'
                                })
                        }
                    });

                }
            }
        }
    })
}
</script>
</body>
</html>