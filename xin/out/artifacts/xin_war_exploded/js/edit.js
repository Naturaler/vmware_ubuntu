var xmlhttp;
function addArticle() {
    var content = CKEDITOR.instances.editor.getData();
    alert("content:" + content);
    initXmlhttp();
    /* 参数解释：请求方式 请求url 是否异步请求*/
    xmlhttp.open("POST","/xin/article/add", true);
    //设置发送数据的请求格式
    xmlhttp.setRequestHeader('content-type', 'application/json');
    xmlhttp.onreadystatechange = function () {
        if(xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var responseText = xmlhttp.responseText;
            var response = JSON.parse(responseText);
            var code = response.code;
            if(code === 200) {
                var data = response.data;
            }else {
                var msg = response.msg;
                alert(msg);
            }
        }
    };
    // var content = "<ttttt>";
    // remove head or tail new line
    // content = content.replace(/^\s+|\s+$/g,'');
    // remove all new line space
    content = content.replace(/[\r\n]/g, "");
    var title = document.getElementById("article_title").value;
    var type = document.getElementById("article_type").value;
    var author = "xin";
    var json = "{" +
            "\"title\":\"" + title + "\"," +
            "\"author\":\"" + author + "\"," +
            "\"type\":\"" + type + "\"," +
            "\"content\":\"" + content + "\"" +
        "}";
    alert("json:" + json);
    // alert("json:" + JSON.stringify(json));
    xmlhttp.send(json);
}