<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
	<h2><span id="real-time"></span>
	<script type="text/javascript">
function Time() {
   var realTime = new Date();
   var hour = realTime.getHours();
   var minutes  = realTime.getMinutes();
   var seconds  = realTime.getSeconds();
   var text = hour + ":" + minutes + ":" + seconds;
   document.getElementById("real-time").innerHTML = text;
}
setInterval('Time()',1000);
  </script>
  	</h2>

</c:import>