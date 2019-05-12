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
                                    <small class="text-muted">博客</small>

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
                                    <small class="text-muted">点击量</small>
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
                                    <small class="text-muted">评论</small>
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
                                    <small class="text-muted">Tags</small>
                                </div>
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