<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actAtt" value="${ForwardConst.ACT_ATT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
		<h2>勤怠 一覧</h2>
		<table id="attendance_all">
			<tbody>
				<tr>
					<td class="attendance_days">出勤日数</td>
					<td class="attendance_alltime">総労働時間</td>
				</tr>
			</tbody>

		</table>


		<table id="attendance_list">
            <tbody>
                <tr>
                    <td class="attendance_day">日付</td>
                    <td class="attendance_class">勤怠区分</td>
                    <td class="attendance_start">出勤時刻</td>
                    <td class="attendance_finish">退勤時刻</td>
                    <td class="attendance_alltime">労働時間</td>
                    <td class="attendance_rest">休憩時間</td>
                </tr>

                <c:forEach var="attendance" items="${attendances}" varStatus="status">
                    <fmt:parseDate value="${attendance.date}" pattern="yyyy-MM-dd" var="attendanceDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="attendance_day"><fmt:formatDate value='${attendanceDay}' pattern='MM-dd' /></td>
                        <td class="attendance_class">${attendance.attclass}</td>
                        <td class="attendance_start">${attendance.attstart_at}</td>
                        <td class="attendance_finish">${attendance.leaving_at}</td>
                        <td class="attendance_alltime">${attendance.alltime}</td>
                        <td class="attendance_rest">${attendance.rest}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${reports_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actRep}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actAtt}&command=${commNew}' />">新規勤怠の登録</a></p>

    </c:param>
</c:import>