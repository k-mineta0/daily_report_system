<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.AttributeConst"%>

<c:if test="${errors != null}">
	<div id="flush_error">
		入力内容にエラーがあります。<br />
		<c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
			<br />
		</c:forEach>
	</div>
</c:if>

<body>
	<div id="Clock2" style="display: inline"></div>
	<br />
	<div id="Clock1" style="display: inline"></div>
</body>

<br />
<div class="flex">
  <div class="btn-group">
    <button type="button" id="btn-01">出勤</button>
    <button type="button" id="btn-02">退勤</button>
  </div>
  <div class="text-group">
    <div class="text-item">

    <label for="${AttributeConst.ATT_START_AT.getValue()}">出勤時間</label>
      		<span class="time" id="text-01"></span>
    </div>

    <div class="text-item">
      <label for="${AttributeConst.ATT_START_AT.getValue()}">退勤時間</label>
            <span class="time" id="text-02"></span>
    </div>
  </div>
</div>



<script type="text/javascript">

// 出勤ボタンをクリックしたときの処理
document.getElementById('btn-01').addEventListener('click', event => {
  // 現在の日時を取得
  var now = new Date();
  var month = now.getMonth() + 1;
  var day = now.getDate();
  var time = now.getHours() + ':' + now.getMinutes();
  // テキストの内容を更新
  document.getElementById('text-01').innerText = month + '/' + day + ' ' + time;
});


	function set0(num) {
		var ret;
		if (num < 10) {
			ret = "0" + num;
		} else {
			ret = num;
		}
		return ret;
	}
</script>