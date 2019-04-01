
<%@page contentType="text/html; charset=MS932" pageEncoding="MS932"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/css/common.css" type="text/css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/bootstrap/3.3.4/css/bootstrap-custom.css" rel="stylesheet">
<%-- <link href="<%=request.getContextPath()%>/css/bootstrap/3.3.4/css/bootstrap.css" rel="stylesheet"> --%>
<style>
body{margin:0}
</style>
<%-- <script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>
<script src='<%=request.getContextPath()%>/css/bootstrap/3.3.4/js/bootstrap.js'></script> --%>
<script src='<%=request.getContextPath()%>/js/cmHelp.js'></script>
<script type="text/ecmascript" src="<%=request.getContextPath()%>/css/jqGrid/jquery.jqGrid.js"></script>
<script type="text/ecmascript" src="<%=request.getContextPath()%>/css/jqGrid/grid.locale-ja.js"></script>
<script type="text/javascript">

	function processSelection(button){
		if (button == "Btninsert") {
		//	depo_cd = $("#depoCd").val();
		//	bumon_cd = $("#bumonCd").val();;
		//	if(depo_cd=="" && bumon_cd==""){
		//		alert("insert null");
		//		return false;
		//	}
		if($("#syoriFlg").val()=="0"){
			if($("#depoCd").val()==""){
				alert("デポコード必ず入力");
			}else{
				form = document.forms[0];
				form.action = '<%=request.getContextPath()%>/c0120/doInsert';
				form.submit();
			}
		}else{
			form = document.forms[0];
			form.action = '<%=request.getContextPath()%>/c0120/doUpdate';
			form.submit();
		}
		} else if (button == "BtnBack") {
			form = document.forms[0];
			form.action = '<%=request.getContextPath()%>/c0120/doBack';
			form.submit();
		} else if (button == "Delete") {
			form = document.forms[0];
			form.action = '<%=request.getContextPath()%>/c0120/doDelete';
			form.submit();
		}
	}
	
	// 業者検索ヘルプ使用ＪＡＶＡＳＣＲＩＰＴ(PLC1H0020) 
	function PLC1H0020_call(){
		var retData = new Array(7);
		retData = PLC1H0020_sel($("#help2_itakuGyoushaCd").val());	
		if (retData==null || retData.length==0){
			//戻り値が空の場合は閉じるで戻った場合。
		} else {

			$("#itakuGyoushaCd").val(retData[0]);
			$("#help2_itakuGyoushaCd").val(retData[0]);
			$("#gyousyaNm").val(retData[1]);

		}

	}
	function PLC1H0020_del(){
		//ドライバー情報をクリアする。
		document.forms[0].help2_itakuGyoushaCd.value = "";
		document.forms[0].gyousyaNm.value = "";
		document.forms[0].itakuGyoushaCd.value = "";
	}
	function aaa(){
		form = document.forms[0];
		form.action = '<%=request.getContextPath()%>/h0020/doInit';
	    form.submit();
	}
	
</script>
</head>

<body>
<table class="header" style="width:1244px;">
<tr>
<%@include file="../menuTitle.jsp"%>
</tr>
</table>
<table>
<tr>
<td style="width:20%">
<%@include file="../menuTest.jsp"%>
</td>
<td style="width:80%;" valign="top">
<form:form name="searchForm" action="/c0120" method="post" modelAttribute="c0120model">
<input type="hidden" id="syoriFlg" name="syoriFlg" value="${c0120model.syoriFlg}" />
<TABLE class="main" >
	<TR>
		<TD class="title" style = "text-align:left;height:30px">９１．デポ情報詳細</TD>
	</TR>
</TABLE>
	<br>
	<input type="hidden" name="dumShoriKbn" value="${c0120model.syoriFlg}" />
	<input type="hidden" name="hidEigyoushoCd" value="${c0120model.hidEigyoushoCd}" />
	<INPUT type="hidden" name="dumDepoSoshikiKb" value="2" />
	<INPUT type="hidden" name="dumDepoSoshikiKb2" value="1" />

	<TABLE class="main">
		<colgroup>
			<col class="labelField char24Col"/>
			<col class="valueField char36Col"/>
		</colgroup>
	<TR>
	<c:if test="${c0120model.syoriFlg eq '0'}">
		<TD>デポコード</TD>
		<TD><form:input type="text" path="depoCd" size="12" maxlength="4" class="txtInp4N" style="ime-mode: disabled;background-color: rgb(255, 228, 196);" value="${c0120model.depoCd}" /></TD>
	</c:if>	
	
	<c:if test="${c0120model.syoriFlg ne '0'}">
		<TD>デポコード</TD>
		<TD><form:input type="text" path="depoCd" size="12" maxlength="4" class="txtInp4N" style="background-color:#e6e6fa;" readonly="true" value="${c0120model.depoCd}" /></TD>
	</c:if>
	</TR>	
		<TR>
			<TD>デポ名</TD>
			<TD><form:input type="text" path="depoNm" size="12" maxlength="8" class="txtInp4N" style="width:120px;ime-mode: disabled" value="${c0120model.depoNm}" /></TD>
		</TR>	
		
		<TR>
			<TD>デポ組織</TD>
			<TD>
				<form:select path="depoSoshikiKb"  onchange='changeDp()'>
					<form:option value="" label="" />
					<form:options items="${c0120model.depoSoshikiKbItems}" itemLabel="fullName" itemValue="codeId"/>
				</form:select>
			</TD>
		</TR>
		<TR>
			<TD>委託業者</TD>
			<TD>
				<form:input type="text" path="help2_itakuGyoushaCd" size="12" maxlength="4" class="txtInp4N" style="ime-mode: disabled;width:120px;" value="${c0120model.help2_itakuGyoushaCd}" />
				<input id="btn_PLC1H0020_sel" name="btn_PLC1H0020_sel" type="button" class="freeButton" value="選択" onclick="PLC1H0020_call();"/>
				<input id="btn_PLC1H0020_del" name="btn_PLC1H0020_del" type="button" class="freeButton" value="クリア" onclick="PLC1H0020_del();"/>
				<form:input type="text" path="gyousyaNm" size="12" class="txtInp4N" readonly="true" style="ime-mode: disabled;background-color:#e6e6fa;width:120px;width:300px" value="${c0120model.gyousyaNm}"  />
				<input type="hidden" name="itakuGyoushaCd" value="${c0120model.itakuGyoushaCd}" />
			</TD>
		</TR>
	</TABLE>
	<br>
	<table >
		<tr>
			<td>
				<input id="BtnBack" name="BtnBack" type="button" class="button" value="戻る" onclick="processSelection(this.name);"/>
				<input id="Btninsert" name="Btninsert" type="button" class="button" value="登録" onclick="processSelection(this.name);"/>
				<input id="Delete" name="Delete" type="button" class="button" value="削除" onclick="processSelection(this.name);"/>
			</td>
		</tr>
	</table>
</form:form>
</td>
</table>
</body>
</html>