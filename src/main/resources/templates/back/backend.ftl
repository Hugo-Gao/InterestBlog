<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>

<body>
<div class="page">
    <div class="page-main">
        <@common.backend_tab tab='home'/>
        <div class="my-3 my-md-5">
            <div class="container">
                <div class="page-header">
                    <h1 class="page-title">
                        控制台
                    </h1>
                </div>
                <div class="row row-cards">
                    <div class="col-sm-6 col-lg-3">
                        <div class="card p-3">
                            <div class="d-flex align-items-center">
                    <span class="stamp stamp-md bg-blue mr-3">
                      <i class="fe fe-book-open"></i>
                    </span>
                                <div>
                                    <h4 class="m-0">${blogNum}</h4>
                                    <small class="text-muted">总博客数量</small>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-3">
                        <div class="card p-3">
                            <div class="d-flex align-items-center">
                    <span class="stamp stamp-md bg-green mr-3">
                      <i class="fe fe-users"></i>
                    </span>
                                <div>
                                    <h4 class="m-0">${blogViews}
                                    </h4>
                                    <small class="text-muted">总点击量</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-3">
                        <div class="card p-3">
                            <div class="d-flex align-items-center">
                    <span class="stamp stamp-md bg-yellow mr-3">
                      <i class="fe fe-message-square"></i>
                    </span>
                                <div>
                                    <h4 class="m-0">${commentNum}
                                    </h4>
                                    <small class="text-muted">总评论数</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-3">
                        <div class="card p-3">
                            <div class="d-flex align-items-center">
                    <span class="stamp stamp-md bg-azure mr-3">
                      <i class="fe fe-filter"></i>
                    </span>
                                <div>
                                    <h4 class="m-0">${tagNum}</h4>
                                    <small class="text-muted">Tags数</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row row-cards row-deck">
                    <div class="col-sm-6 col-lg-4">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">浏览最多的文章</h4>
                            </div>
                            <table class="table card-table">
                                <tbody>
                                <#list blogListByView as blog>
                                    <tr>
                                        <td>
                                            <a style="color: #0a0c0d" href="/blog/${blog.id}"> ${blog.title}</a>
                                        </td>

                                        <td class="text-right"><span class="text-muted">${blog.views}</span></td>
                                    </tr>
                                </#list>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-4">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">评论最多的文章</h4>
                            </div>
                            <table class="table card-table">
                                <tbody>
                                <#list blogListByComment as blog>
                                    <tr>
                                        <td><a href="/blog/${blog.id}">${blog.title}</a></td>
                                        <td class="text-right"><span class="text-muted">${blog.commentsView}条</span>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-sm-6 col-lg-4">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">最新评论</h4>
                            </div>
                            <div class="card-body o-auto" style="height: 15rem">
                                <ul class="list-unstyled list-separated">

                                    <#list newestComments as comment>
                                        <li class="list-separated-item">
                                            <div class="row align-items-center">
                                                <div class="col">
                                                    <div>
                                                        <a href="/blog/${comment.blogId}"
                                                           class="text-inherit">在《${comment.blogTitle}》评论:</a>
                                                    </div>
                                                    <div >
                                                        <h4 style="margin: 5px">${comment.content}</h4>
                                                    </div>
                                                    <small class="d-block item-except text-sm text-muted h-1x">
                                                        ${comment.nickname}
                                                    </small>
                                                    <small class="d-block item-except text-sm text-muted h-1x">
                                                        ${comment.createTime?string('yyyy-MM-dd HH:mm')}
                                                    </small>

                                                </div>
                                            </div>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

    <@common.footer/>
</div>
</body>
</html>