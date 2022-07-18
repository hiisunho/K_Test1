var bb= [['작성자', '글개수']];

function gogo(){
	$.ajax({
		url:"/api/rank",
		type:"get",
		cache:false,
		success:function(data){
			 bb= [['작성자', '글개수']];
			 data.forEach((rank)=>{
				 bb.push([rank.writer,rank.cn])
			 })
			rankdraw();
		}
	});
	
}


function rankdraw(){
  google.charts.load('current', {'packages':['corechart']});
  google.charts.setOnLoadCallback(drawChart);

  function drawChart() {

	//배열로된 데이터다  bb가 필요
	
    var data = google.visualization.arrayToDataTable(bb);

    var options = {
      title: '랭킹'
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
  }
}