<!DOCTYPE html>
<html>
<head lang="en">
    <link rel="shortcut icon" href="/static/favicon.ico"/>
    <link rel="bookmark" href="/static/favicon.ico"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <title>主页</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-11 table-responsive"
                 style="text-align:center;padding-top:50px; padding-right:50px;padding-bottom:50px">
                <center>
                    <h2>商品信息</h2>
                </center>
                <form id="goods_info_form" method="post" enctype="multipart/form-data">
                    <table class="table table-bordered table-hover">
                        <tr>
                            <td>名称：</td>
                            <td><input type="text" name="name" class="form-control" id="article_title"></td>
                        </tr>
                        <tr>
                            <td>单价：</td>
                            <td><input type="text" name="price" class="form-control" id="article_ftitle"></td>
                        </tr>
                        <tr>
                            <td>插图：</td>
                            <td><input type="file" name="file" class="form-control"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div id="edti" name="content"></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <button class="btn btn-success" id="add_article_submit">确定添加</button>
                                <button type="reset" class="btn btn-warning">重置信息</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/release/wangEditor.js"></script>
    <script type="text/javascript">
        var E = window.wangEditor;
        var editor = new E('#edti');
        editor.create();
        $("#add_article_submit").click(function () {
            var articleData = new FormData($("#goods_info_form")[0]);
            articleData.append('abstracts',editor.txt.html());
            $.ajax({
                url:"/submitGoodsInfo",
                type:"POST",
                data:articleData,
                async:false,
                cache:false,
                contentType:false,
                processData:false,
                success:function (result) {
                    alert(result.msg);
                }
            });
        });
    </script>
</body>
</html>