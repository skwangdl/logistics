<%@page contentType="text/html; charset=MS932" pageEncoding="MS932"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href='<%=request.getContextPath()%>/css/bootstrap/3.3.4/css/bootstrap.css' rel="stylesheet">
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<script src='<%=request.getContextPath()%>/css/bootstrap/3.3.4/js/bootstrap.js'></script>
<tr style="background:#337AB7">
	<td class="labelValue">
		<span id="userName">ユーザー</span>
		<span class="noPrint" id="welcomeLabel" style="border: 1px solid transparent;">zhu00001(朱)</span>&nbsp;&nbsp;
		<span id="userName">所属</span>
		<span class="noPrint" id="welcomeLabel" style="border: 1px solid transparent;">Bizex本部(2101)</span>&nbsp;&nbsp;
		<span id="workDate">2019/01/09 15:29</span>&nbsp;&nbsp;
		<a title="Top Page" id="topPage" href="${pageContext.request.contextPath}/c0130/doInit" shape="rect">
			<span class="glyphicon glyphicon-home" style="color:#fff"></span>
		</a>
		&nbsp;
		<button type="button" class="btn btn-primary dropdown-toggle btn-xs" data-toggle="dropdown">
			<span class="glyphicon glyphicon-bell"></span>
			<span class="caret"></span>
		</button>
		<c:if test="${errorsFlag eq '1'}">
			<ul class="dropdown-menu" role="menu" style="left: 1000px; top: 15px;">
				<c:forEach items="${errors }" var="error" varStatus="errorStatus">
					<li style = "color: black;">${error }</li>
				</c:forEach>
			</ul>
		</c:if>
		&nbsp;&nbsp;
		<a style="cursor: pointer;" onclick="doRefresh();" shape="rect" href="javascript:location.reload();">
			<span class="glyphicon glyphicon-refresh" style="color:#fff"></span>
		</a>
		&nbsp;&nbsp;
		<a title="Logout" id="logoffLabel" onclick="return showLogOutConfirm()" href="${pageContext.request.contextPath}/Login/toLogin" shape="rect">
			<span class="glyphicon glyphicon-log-out" style="color:#fff"></span>
		</a>
		&nbsp;
	</td>
</tr>