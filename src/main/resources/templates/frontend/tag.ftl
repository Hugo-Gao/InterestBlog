<!doctype html>
<html lang="en" dir="ltr">
<@common.header/>

<body>
<div class="page">
    <div class="page-main">
        <@common.head_tab tab='tags'/>
        <div class="my-3 my-md-5" id="blog">
            <div class="container">
                <div class="row">
                    <div class="card">
                        <div class="card-body">
                            <#list tagList as tag>
                                <button style="margin-left: 10px;margin-right: 10px"
                                        onclick="window.location.href='../tags/${tag.id}'"
                                        class="btn btn-primary ${tag.style}">${tag.tag}</button>
                            </#list>
                        </div>
                    </div>
                </div>
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
            },200)

        }
    )
</script>
</body>
</html>