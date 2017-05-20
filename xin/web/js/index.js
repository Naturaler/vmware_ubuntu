function textclick() {
    alert("just test ");
}

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
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            alert("just test ");
            var responseText = xmlhttp.responseText;
            var response = JSON.parse(responseText);
            // var response = xmlhttp.responseText;
            alert("response:" + response);
            console.log("responseText:" + responseText);
            console.log("responseText data:" + responseText.data);
            console.log("responseText data title:" + responseText.data[0].title);
        }
    }
    xmlhttp.open("GET","article/list", true);
    xmlhttp.send();
}