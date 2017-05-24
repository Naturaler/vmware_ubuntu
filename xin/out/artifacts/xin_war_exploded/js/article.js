var xmlhttp;
function getArticle() {
    // 获取url
    var url = document.URL;
    var param = url.split('?')[1];
    var id = param.split('=')[1];
    initXmlhttp();
    xmlhttp.onreadystatechange = function () {
        if(xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var responseText = xmlhttp.responseText;
            var response = JSON.parse(responseText);
            var code = response.code;
            if(code === 200) {
                var data = response.data;
                showArticle(data[0].object);
            }else {
                var msg = response.msg;
                alert(msg);
            }
        }
    };
    /* 参数解释：请求方式 请求url 是否异步请求*/
    xmlhttp.open("GET","/xin/article/getArticleById?id=" + id, false);
    xmlhttp.send();
}
function showArticle(articleInfo) {
    var br = document.createElement("br");
    var title = document.createElement("h1");
    title.innerHTML = articleInfo.title;
    var br1 = document.createElement("br");
    var info = document.createElement("p");
    info.setAttribute("class", "article_info");
    var infoTime = document.createElement("span");
    infoTime.innerHTML = "发表于 " + articleInfo.createDatetime;
    var space = document.createElement("p");
    space.innerHTML = "&nbsp;|&nbsp;";
    var infoType = document.createElement("span");
    infoType.innerHTML = "分类于 " + articleInfo.type;
    info.appendChild(infoTime);
    info.appendChild(space);
    info.appendChild(infoType);
    var br2 = document.createElement("br");
    var content = document.createElement("p");
    content.innerHTML = articleInfo.content;
    var br3 = document.createElement("br");
    var article = document.getElementById("article");
    article.appendChild(br);
    article.appendChild(title);
    article.appendChild(br1);
    article.appendChild(info);
    article.appendChild(br2);
    article.appendChild(content);
    article.appendChild(br3);
}