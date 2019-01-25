<#macro header>
    <head>
        <title>InterestBlog</title>
        <link rel="shortcut icon" type="image/x-icon" href="/img/logo.ico" media="screen" />
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta http-equiv="Content-Language" content="en"/>
        <meta name="msapplication-TileColor" content="#2d89ef">
        <meta name="theme-color" content="#4188c9">
        <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="HandheldFriendly" content="True">
        <meta name="MobileOptimized" content="320">
        <!-- Generated: 2018-04-16 09:29:05 +0200 -->
        <title>Register - tabler.github.io - a responsive, flat and full featured admin template</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">
        <script src="/tabler/js/require.min.js"></script>
        <script src="/layui/layui.js"></script>
        <script>
            requirejs.config({
                baseUrl: '.'
            });
        </script>
        <!-- Dashboard Core -->
        <link href="/tabler/css/dashboard.css" rel="stylesheet"/>
        <script src="/tabler/js/dashboard.js"></script>
        <!-- c3.js Charts Plugin -->
        <link href="/tabler/plugins/charts-c3/plugin.css" rel="stylesheet"/>
        <script src="/tabler/plugins/charts-c3/plugin.js"></script>
        <!-- Google Maps Plugin -->
        <link href="/tabler/plugins/maps-google/plugin.css" rel="stylesheet"/>
        <script src="/tabler/plugins/maps-google/plugin.js"></script>
        <!-- Input Mask Plugin -->
        <script src="/tabler/plugins/input-mask/plugin.js"></script>
        <script src="/js/common.js"></script>
        <script src="/js/jquery-3.3.1.min.js"></script>

    </head>
</#macro>

<#macro header_md>
    <head>
        <title>InterestBlog</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta http-equiv="Content-Language" content="en"/>
        <meta name="msapplication-TileColor" content="#2d89ef">
        <meta name="theme-color" content="#4188c9">
        <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="HandheldFriendly" content="True">
        <meta name="MobileOptimized" content="320">
        <!-- Generated: 2018-04-16 09:29:05 +0200 -->
        <title>Register - tabler.github.io - a responsive, flat and full featured admin template</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">
        <script src="/tabler/js/require.min.js"></script>
        <script src="/layui/layui.js"></script>
        <script>
            requirejs.config({
                baseUrl: '.'
            });
        </script>
        <!-- Dashboard Core -->
        <link href="/tabler/css/dashboard.css" rel="stylesheet"/>
        <script src="/tabler/js/dashboard.js"></script>
        <link href="/css/markdown.css" rel="stylesheet"/>

        <!-- c3.js Charts Plugin -->
        <link href="/tabler/plugins/charts-c3/plugin.css" rel="stylesheet"/>
        <script src="/tabler/plugins/charts-c3/plugin.js"></script>
        <!-- Google Maps Plugin -->
        <link href="/tabler/plugins/maps-google/plugin.css" rel="stylesheet"/>
        <script src="/tabler/plugins/maps-google/plugin.js"></script>
        <!-- Input Mask Plugin -->
        <script src="/tabler/plugins/input-mask/plugin.js"></script>
        <script src="/js/common.js"></script>
        <script src="/js/jquery-3.3.1.min.js"></script>

    </head>
</#macro>


<#macro head_tab>
    <div class="header py-4">
        <div class="container">
            <div class="d-flex">
                <a class="header-brand" href="../">
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
                            <a href="../" class="nav-link active"><i class="fe fe-home"></i> 首页</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a href="../tags" class="nav-link" data-toggle="dropdown"><i
                                        class="fe fe-tag"></i> Tags</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a href="../aboutme" class="nav-link" data-toggle="dropdown"><i
                                        class="fe fe-user"></i> 关于我</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</#macro>