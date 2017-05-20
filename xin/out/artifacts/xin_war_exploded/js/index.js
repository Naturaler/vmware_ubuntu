function loadArticles() {
    var xmlhttp;
    if(window.XMLHttpRequest) {
        // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function() {
        /*判断请求结果状态*/
        if(xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            /* 请求返回值：string类型 */
            var responseText = xmlhttp.responseText;
            /* 将string类型的json数据转换为json对象 */
            var response = JSON.parse(responseText);
            var code = response.code;
            if(code === 200) {
                var data = response.data;
                /* 插入到页面中 */
                var articles = document.getElementById("articles");
                data.forEach(function (value) {
                    var article = document.createElement("div");
                    article.setAttribute("class", "index_article");
                    /* 创建标题 */
                    var title = document.createElement("p");
                    title.setAttribute("class", "article_title");
                    title.innerHTML = value.title;
                    /* 创建br */
                    var br = document.createElement("br");
                    var br1 = document.createElement("br");
                    var br2 = document.createElement("br");
                    var br3 = document.createElement("br");
                    var br4 = document.createElement("br");
                    /* 文章信息 */
                    var info = document.createElement("p");
                    info.setAttribute("class", "article_info");
                    /* 创建时间 */
                    var spanCreateDatetime = document.createElement("span");
                    spanCreateDatetime.innerHTML = "发表于 " + value.createDatetime + "&nbsp;|&nbsp;";
                    /* 文章类型 */
                    var spanType = document.createElement("span");
                    spanType.innerHTML = "分类于 " + value.type;
                    info.appendChild(spanCreateDatetime);
                    info.appendChild(spanType);
                    /* 文章内热 */
                    var content = document.createElement("p");
                    content.setAttribute("class", "article_content");
                    content.innerHTML = value.content;
                    /* 阅读全文 */
                    var readMore = document.createElement("p");
                    readMore.setAttribute("class", "article_readmore");
                    readMore.innerHTML = "阅读全文>>";

                    article.appendChild(br)
                    article.appendChild(title);
                    article.appendChild(br1);
                    article.appendChild(info);
                    article.appendChild(br2);
                    article.appendChild(content);
                    article.appendChild(br3);
                    article.appendChild(readMore);
                    article.appendChild(br4);

                    articles.appendChild(article);
                });
            }else {
                var msg = response.msg;
                alert(msg);
            }
        }
    };
    xmlhttp.open("GET","article/list", true);
    xmlhttp.send();
}