<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
확인된 글개수는 <span id="count"> </span> <br>
<button type="button" onclick="aaa()">글개수 갱신</button> <br>

<button onclick="gogo();">그려라</button> <br>

pie차트
<div id="piechart" style="width: 900px; height: 500px;"></div>
 
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script src="/resources/js/pi.js?ver=1"></script>



<script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>


<script>
function aaa(){
	$.ajax({
		url:"/api/count",
		type:"get",
		cache:false,
		success:function(data){
			$("#count").text(data);
		}
	});
	
}
</script>
</body>
</html>






