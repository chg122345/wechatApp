 $("#submit")
        .click(
            function() {
                //	alert("0001");
                var formData = new FormData($("#addcourse")[0]);
                var coursename = $("input#coursename").val();
                var link = $("input#link").val();
                var password = $("input#password").val();
               // var img=$("input#img").val();    //获取文件对象
                var score = $("input#score").val();
                var reg = /^((https?|ftp|news):\/\/)?([a-z]([a-z0-9\-]*[\.。])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\/[a-z0-9_\-\.~]+)*(\/([a-z0-9_\-\.]*)(\?[a-z0-9+_\-\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$/;
                if (coursename.length < 0) {
                    alert("请输入名称！");
                    return false;
                } else if (link.length < 0) {
                    alert("链接地址不能为空！");
                    return false;
                } else if (!reg.test(link)) {
                    alert("请输入有效的链接地址！");
                    return false;
                }

                $.ajax({
                        type : "POST",
                        url : "addcoursesubmit",
                        dateType : "text/plain",
                        data :formData, /*{
                            coursename : coursename,
                            link: link,
                            password : password,
                            file: file,
                            score : score,
                        },*/
                        async:false,
                        cache:false,
                        contentType:false,
                        processData:false,

                        success : function(msg) {
                            if (msg == "true") {

                                alert("添加成功！");
                               // window.location.href = "addcourse";
                            } else {
                                alert("添加失败！");
                            }
                        },
                        error : function(data) {
                            alert("操作异常：" + $("#id").val()
                                + data.responseText);
                        }
                    }

                );
            }

        );