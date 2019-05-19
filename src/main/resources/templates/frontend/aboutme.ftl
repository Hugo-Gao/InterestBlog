<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>

<body>
<div class="page">
    <div class="page-main">
        <@common.head_tab tab='aboutme'/>
        <div class="my-3 my-md-5" id="blog">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2"></div>
                    <div class="col-lg-8">
                        <div class="card card-profile">
                            <div class="card-header"
                                 style="background-image: url(https://uploadbeta.com/api/pictures/random/);height: 300px"></div>
                            <div class="card-body text-center">
                                <img class="card-profile-img" src="${(user.avaterPath)!}">
                                <h3 class="mb-3">${(user.blogName)!}</h3>
                                <p class="mb-4">
                                    ${(user.slogan)!}
                                </p>
                                <button class="btn btn-outline-primary btn-sm"
                                        onclick="window.location.href='${(user.githubUrl)!}'">
                                    <span class="fa fa-github"></span> Watch
                                </button>
                                <button class="btn btn-outline-primary btn-sm"
                                <span class="fa fa-envelope"></span> ${(user.email)!}
                                </button>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-body">
                                <div class="media">
                                        <span class="avatar avatar-xxl mr-5"
                                              style="background-image: url(${user.avaterPath})"></span>
                                    <div class="media-body">
                                        <h4 class="m-0">${(user.nickName)!}</h4>
                                        <p class="text-muted mb-0"
                                           style="margin-top: 5px">${(user.city)!}  ${(user.company)!}</p>
                                        <p class="text-muted mb-0">${(user.aboutme)!}</p>

                                        <ul class="social-links list-inline mb-0 mt-2">
                                            <#if user.githubUrl?? >
                                                <li class="list-inline-item">
                                                    <a href="${user.githubUrl}" title="" data-toggle="tooltip"
                                                       data-original-title="Github"><i class="fa fa-github"></i></a>
                                                </li>
                                            </#if>
                                            <#if user.qq?? >
                                                <li class="list-inline-item">
                                                    <a href="${user.qq}" title="" data-toggle="tooltip"
                                                       data-original-title="QQ"><i class="fa fa-qq"></i></a>
                                                </li>
                                            </#if>

                                            <#if user.sinaUrl?? >
                                                <li class="list-inline-item">
                                                    <a href="${user.sinaUrl}" title="" data-toggle="tooltip"
                                                       data-original-title="weibo"><i class="fa fa-weibo"></i></a>
                                                </li>
                                            </#if>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-2"></div>
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