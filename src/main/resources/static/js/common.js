function successmsg(title, content)
{
    layui.use('layer', function ()
    {
        var layer = layui.layer;
        layer.msg(content, {icon: 6});
    });
    return true;
}

function errormsg(title, content)
{
    layui.use('layer', function ()
    {
        var layer = layui.layer;
        layer.msg(content, {icon: 5});
    });
    return false;
}

function getUrlParam(key)
{
    // 获取参数
    var url = window.location.search;
    // 正则筛选地址栏
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    // 匹配目标参数
    var result = url.substr(1).match(reg);
    //返回参数值
    return result ? decodeURIComponent(result[2]) : null;
}



function searchForBlog()
{
    var keyWord = $("#searchInput").val();
    if (keyWord != null && keyWord !== '') {
        $.ajax({
            url: "/search?keyWord=" + keyWord,
            type: "GET",
            dataType: "json",
            success: function (data)
            {
                $("#searchDialog").empty()
                if (data.code === 0) {
                    var blogList = data.result;
                    for (i=0;i<blogList.length;i++) {
                        $("#searchDialog").append("<div id=\"searchResult\"  class=\"dropdown-item \" style=\"width: 300px\">\n" +
                            "            <div>\n" +
                            "                <a href=\"/blog/"+blogList[i].id+"\" class=\"\">"+blogList[i].title+"</a>\n" +
                            "            </div>\n" +
                            "            <div>\n" +
                            "                <p class=\"\" style=\"overflow: scroll;\">"+blogList[i].content+"</p>\n" +
                            "            </div>\n" +
                            "        </div>");
                    }
                    $("#searchDialog").addClass("show");
                }
                if(data.code===2)
                {
                    $("#searchDialog").append("<div id=\"searchResult\"  class=\"dropdown-item \" style=\"width: 300px\">\n" +
                        "            <div>\n" +
                        "                <p href=\"\" class=\"\">没有找到相关内容</p>\n" +
                        "            </div>\n" +
                        "        </div>");
                    $("#searchDialog").addClass("show");

                }
            }
        });
    }

}

