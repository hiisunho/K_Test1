<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../includes/header.jsp" %>    

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            게시글 읽기
                        <!-- /.panel-heading -->
                        <div class="panel-body">
 
 						
	 						
	 						<label>제목</label>
	 						<input class="form-control"  type="text" name="title" id="title"  readonly>	
	 						
	 						<br> 						
	 						<label>내용</label>
	 						<textarea class="form-control"  name="content" rows="3" id="content" readonly> </textarea>
	 						
	 						<br>
	 						<label>작성자</label>
	 						<input class="form-control"  type="text" name="writer" id="writer" readonly>
	 						
	 						<br>
	 					
	 						
	 				
 
 
 
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/dist/js/sb-admin-2.js"></script>



</body>
<script>
var num="${num}";
$.ajax({
	url:"/api/get/"+num,
	type:"get",
	cache:false,
	success:function(data){  //json 으로 보냈기 때문에 파싱되서 data 객체로 생성된다.
		$("#title").val(data.title);
		$("#content").text(data.content);
		$("#writer").val(data.writer);
	}
});


</script>



</html>















