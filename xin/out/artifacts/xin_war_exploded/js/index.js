var xmlhttp;
var currentPagination;
var sumPagination;

function loadArticles() {
    currentPagination = 1;
    console.log("start set pagination");
    // 设置页码（不能放在请求的代码后）
    getSumPagination();
    initXmlhttp();
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
                appendArticle(data);
            }else {
                var msg = response.msg;
                alert(msg);
            }
        }
    };
    xmlhttp.open("GET","/xin/article/listByPagination?pagination=1", false);
    xmlhttp.send();
}
function loadArticlesByPagination(pagination) {
    currentPagination = pagination;
    // 设置页码
    getSumPagination();
    initXmlhttp();
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
                /* 先清空所有文章，再重新加载 */
                removeAllArticle();
                appendArticle(data);
            }else {
                var msg = response.msg;
                alert(msg);
            }
        }
    };
    xmlhttp.open("GET","/xin/article/listByPagination?pagination=" + pagination, false);
    xmlhttp.send();
}
function getSumPagination() {
    initXmlhttp();
    xmlhttp.onreadystatechange = function () {
        if(xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var responseText = xmlhttp.responseText;
            var response = JSON.parse(responseText);
            var code = response.code;
            if(code === 200) {
                var data = response.data;
                sumPagination = data[0].object;
                removePagination();
                showPagination();
            }else {
                var msg = response.msg;
                alert(msg);
            }
        }
    };
    /* 参数解释：请求方式 请求url 是否异步请求*/
    xmlhttp.open("GET","/xin/article/getSumPagination", false);
    xmlhttp.send();
}
function showPagination() {
    var paginationNums = document.createElement("div");
    paginationNums.setAttribute("id", "index_page_button_nums");
    // 最多只显示3个页码
    var minPagination = (currentPagination - 1) > 0 ? (currentPagination - 1) : 1;
    var maxPagination = (minPagination + 2) > sumPagination ? sumPagination : (minPagination + 2);
    for(var i = minPagination; i <= maxPagination; i++) {
        var paginationButton = document.createElement("button");
        paginationButton.setAttribute("class", "index_page_button_num");
        paginationButton.setAttribute("type", "button");
        paginationButton.setAttribute("onclick", "loadArticlesByPagination(" + i + ")");
        if(i === currentPagination) {
            paginationButton.setAttribute("style", "background-color: lightgray");
        }
        paginationButton.innerHTML = i;
        paginationNums.appendChild(paginationButton);
    }
    // 是否显示省略号
    if(sumPagination > (minPagination + 2)) {
        var ellipsis = document.createElement("button");
        ellipsis.setAttribute("class", "index_page_button_num");
        ellipsis.setAttribute("type", "button");
        ellipsis.innerHTML = "...";
        paginationNums.appendChild(ellipsis);
    }
    var preButton = document.getElementById("index_pre_button");
    var nextButton = document.getElementById("index_next_button");
    if(currentPagination === 1) {
        preButton.setAttribute("class", "index_page_button_num_disabled");
        preButton.setAttribute("disabled", "disabled");
    }else {
        preButton.setAttribute("class", "index_page_button");
        preButton.removeAttribute("disabled");
    }
    if(currentPagination === sumPagination) {
        nextButton.setAttribute("class", "index_page_button_num_disabled");
        nextButton.setAttribute("disabled", "disabled");
    }else {
        nextButton.setAttribute("class", "index_page_button");
        nextButton.removeAttribute("disabled");
    }
    var paginationButtons = document.getElementById("index_page_num");
    // 在“下一页”前添加页码按钮
    paginationButtons.insertBefore(paginationNums, nextButton);
}
/* 删除数字页码 */
function removePagination() {
    var paginationNums = document.getElementById("index_page_button_nums");
    paginationNums.parentNode.removeChild(paginationNums);
}
/* 删除文章列表 */
function removeAllArticle() {
    var articles = document.getElementById("articles");
    while(articles.hasChildNodes()) {
        articles.removeChild(articles.firstChild);
    }
}
function appendArticle(data) {
    /* 插入到页面中 */
    var articles = document.getElementById("articles");
    data.forEach(function (articleInfo) {
        var article = document.createElement("div");
        article.setAttribute("class", "index_article");
        /* 创建标题 */
        var title = document.createElement("p");
        title.setAttribute("class", "article_title");
        title.setAttribute("onclick", "readArticle(" + articleInfo.id +")");
        title.innerHTML = articleInfo.title;
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
        spanCreateDatetime.innerHTML = "发表于 " + articleInfo.createDatetime + "&nbsp;|&nbsp;";
        /* 文章类型 */
        var spanType = document.createElement("span");
        spanType.innerHTML = "分类于 " + articleInfo.type;
        info.appendChild(spanCreateDatetime);
        info.appendChild(spanType);
        /* 文章内容 */
        var content = document.createElement("p");
        content.setAttribute("class", "article_content");
        content.innerHTML = articleInfo.content;
        /* 阅读全文 */
        var readMore = document.createElement("p");
        readMore.setAttribute("class", "article_readmore");
        readMore.setAttribute("onclick", "readArticle(" + articleInfo.id +")");
        readMore.innerHTML = "阅读全文>>";

        article.appendChild(br);
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
}
function readArticle(id) {
    // 页面跳转
    window.location.href = "/xin/pages/article.html?id=" + id;
    /*initXmlhttp();
    xmlhttp.onreadystatechange = function () {
        if(xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var responseText = xmlhttp.responseText;
            var response = JSON.parse(responseText);
            var code = response.code;
            if(code === 200) {
                var data = response.data;
                sumPagination = data[0].object;
                removePagination();
                showPagination();
            }else {
                var msg = response.msg;
                alert(msg);
            }
        }
    };
    /!* 参数解释：请求方式 请求url 是否异步请求*!/
    xmlhttp.open("GET","/xin/article/getArticleById?id=" + id, false);
    xmlhttp.send();*/
}
function previousPage() {
    currentPagination -= 1;
    loadArticlesByPagination(currentPagination);
}
function nextPage() {
    currentPagination += 1;
    loadArticlesByPagination(currentPagination);
}
function initXmlhttp() {
    if(window.XMLHttpRequest) {
        // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
}