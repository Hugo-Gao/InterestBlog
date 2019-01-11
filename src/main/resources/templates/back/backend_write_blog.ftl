<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>InterestBlog控制台</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="/css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="/css/style.min.css" rel="stylesheet">
    <link href="/css/style.min.css" rel="stylesheet">
    <style>

        .map-container {
            overflow: hidden;
            padding-bottom: 56.25%;
            position: relative;
            height: 0;
        }

        .map-container iframe {
            left: 0;
            top: 0;
            height: 100%;
            width: 100%;
            position: absolute;
        }
    </style>
</head>

<body class="grey lighten-3">

<!--Main Navigation-->
<header>

    <!-- Navbar -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
        <div class="container-fluid">

            <!-- Brand -->
            <a class="navbar-brand waves-effect" href="/backend/" target="_blank">
                <strong class="blue-text">InterestBlog</strong>
            </a>

            <!-- Collapse -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- 头部标题 -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <!-- Left -->
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link waves-effect" href="/backend/">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link waves-effect" href="" target="_blank">查看博客</a>
                    </li>
                </ul>

                <!-- Right -->
                <ul class="navbar-nav nav-flex-icons">
                    <li class="nav-item">
                        <a href="https://www.sina.com" class="nav-link waves-effect" target="_blank">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="https://twitter.com/" class="nav-link waves-effect" target="_blank">
                            <i class="fab fa-twitter"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="https://github.com/Hugo-Gao" class="nav-link border border-light rounded waves-effect"
                           target="_blank">
                            <i class="fab fa-github mr-2"></i>GitHub
                        </a>
                    </li>
                </ul>

            </div>

        </div>
    </nav>
    <!-- Navbar -->

    <!-- Sidebar -->
    <div class="sidebar-fixed position-fixed">

        <a class="logo-wrapper waves-effect" style="margin-right: 50px">
            <img src="https://i.loli.net/2018/12/29/5c273ec02db9e.png" alt="">
        </a>

        <div class="list-group list-group-flush">
            <a href="#" class="list-group-item waves-effect" s>
                <i class="fas fa-chart-pie mr-3"></i>控制台
            </a>
            <a href="#" class="list-group-item list-group-item-action waves-effect">
                <i class="fas fa-user mr-3"></i>个人设置</a>
            <a href="#" class="list-group-item active list-group-item-action waves-effect">
                <i class="fas fa-table mr-3"></i>写博客</a>

        </div>

    </div>
    <!-- Sidebar -->

</header>
<!--Main Navigation-->

<!--Main layout-->
<main class="pt-5 mx-lg-5">
    <div class="container-fluid mt-5">

        <div class="row wow fadeIn">

            <!--Grid column-->
            <div class="col-md-12 mb-4">
                <!--Card-->
                <div class="card">

                    <!--Card content-->
                    <div class="card-body">
                        <form method="post" action="/backend/write_blog">

                            <h3 class="section-heading mb-5  h2 mt-0" style="text-align: center">
                                请在此输入博客文章
                            </h3>
                            <div style="width: 500px">
                                <label for="exampleForm2">文章标题</label>
                                <input type="text" name="title" class="form-control">
                            </div>
                            <div style="width: 500px;margin-top: 20px">
                                <label for="exampleForm2">文章tag(以;分隔)</label>
                                <input type="text" name="tagString" class="form-control">
                            </div>
                            <div style="margin-top: 20px">
                                <label for="textarea-char-counter">请输入文章</label>
                                <textarea name="content" class="form-control md-textarea"
                                          rows="20"></textarea>
                            </div>
                            <div style="margin-top: 20px">
                                <button type="submit" class="btn btn-primary">提交</button>
                            </div>
                        </form>
                    </div>

                </div>
                <!--/.Card-->

            </div>
            <!--Grid column-->


        </div>
        <!--Grid column-->

    </div>
    <!--Grid row-->


    <!--Grid row-->

</main>
<!--Main layout-->

<!--Footer-->
<footer class="page-footer text-center font-small primary-color-dark darken-2 mt-4 wow fadeIn">

    <hr class="my-4">

    <!--Copyright-->
    <div class="footer-copyright py-3">
        © 2019 Copyright:
        <a href="" target="_blank"> Hugo-Gao </a>
    </div>
    <!--/.Copyright-->

</footer>
<!--/.Footer-->

<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="/js/mdb.min.js"></script>
<!-- Initializations -->
<script type="text/javascript">
    // Animations initialization
    new WOW().init();

</script>

</body>

</html>
