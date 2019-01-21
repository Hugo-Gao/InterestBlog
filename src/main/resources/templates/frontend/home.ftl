<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>

<body>
<div class="page">
    <div class="page-main">
        <div class="header py-4">
            <div class="container">
                <div class="d-flex">
                    <a class="header-brand" href="./">
                        <img src="https://i.loli.net/2018/12/29/5c273ec02db9e.png" class="header-brand-img"
                             alt="InterestBlog logo">
                    </a>
                    <div class="d-flex order-lg-2 ml-auto">
                        <div class="dropdown">
                            <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown">
                                <span class="avatar" style="background-image: url(${user.avaterPath})"></span>
                                <span class="ml-2 d-none d-lg-block">
                                     <span class="text-default">${user.email}</span>
                                     <small class="text-muted d-block mt-1">博主</small>
                                </span>
                            </a>
                        </div>
                    </div>
                    <a href="#" class="header-toggler d-lg-none ml-3 ml-lg-0" data-toggle="collapse"
                       data-target="#headerMenuCollapse">
                        <span class="header-toggler-icon"></span>
                    </a>
                </div>
            </div>
        </div>
        <div class="header collapse d-lg-flex p-0" id="headerMenuCollapse">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-3 ml-auto">
                        <form class="input-icon my-3 my-lg-0">
                            <input type="search" class="form-control header-search" placeholder="Search&hellip;"
                                   tabindex="1">
                            <div class="input-icon-addon">
                                <i class="fe fe-search"></i>
                            </div>
                        </form>
                    </div>
                    <div class="col-lg order-lg-first">
                        <ul class="nav nav-tabs border-0 flex-column flex-lg-row">
                            <li class="nav-item">
                                <a href="./" class="nav-link active"><i class="fe fe-home"></i> 首页</a>
                            </li>
                            <li class="nav-item">
                                <a href="./archive" class="nav-link" data-toggle="dropdown"><i
                                            class="fe fe-box"></i> 归档</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a href="./tags" class="nav-link" data-toggle="dropdown"><i
                                            class="fe fe-tag"></i> Tags</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a href="./aboutme" class="nav-link" data-toggle="dropdown"><i
                                            class="fe fe-user"></i> 关于我</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="my-3 my-md-5">
            <div class="container">
                <div class="page-header">
                    <h1 class="page-title">
                        全部博客
                    </h1>
                </div>
                <div class="row row-cards row-deck">

                    <#list blogList as blog>
                        <div class="col-lg-6">
                            <div class="card card-aside">
                                <a href="./blog/${blog.id}" class="card-aside-column"
                                   style="background-image: url(${blog.picUrl})"></a>
                                <div class="card-body d-flex flex-column">
                                    <h4><a href="./blog/${blog.id}">${blog.title}</a></h4>
                                    <div class="text-muted">${blog.digest}
                                    </div>
                                    <div class="d-flex align-items-center pt-5 mt-auto">
                                        <div>
                                            <small class="d-block text-muted">${blog.createTime?string('yyyy-MM-dd HH:mm')}</small>
                                        </div>
                                        <div class="ml-auto text-muted">
                                            <a href="javascript:void(0)" class="icon d-none d-md-inline-block ml-3"><i
                                                        class="fe fe-heart mr-1"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>

                </div>

            </div>

        </div>

    </div>
    <div style="text-align: center">
        <ul class="pagination justify-content-center">
            <li class="page-item page-prev <#if page==1> disabled </#if>">
                <a class="page-link" href=".?page=${page-1}" tabindex="-1">
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
                <li class="page-item"><a class="page-link" href=".?page=${page-1}">${page-1}</a></li>
            </#if>
            <#list  page..max as i>
                <li class="page-item <#if i==page>active</#if>"><a class="page-link" href=".?page=${i}">${i}</a></li>
            </#list>
            <li class="page-item page-next <#if page==pageSum> disabled </#if>">
                <a class="page-link" href=".?page=${page+1}">
                    Next
                </a>
            </li>
        </ul>
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