<%@page contentType="text/html; charset=MS932" pageEncoding="MS932"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/css/common.css" type="text/css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/bootstrap/3.3.4/css/bootstrap-custom.css" rel="stylesheet">
<link href='<%=request.getContextPath()%>/css/bootstrap/3.3.4/css/bootstrap.css' rel="stylesheet">
<style>
body{margin:0}
#gview_resultListJqGrid{
	overflow-x:hidden;
}
</style>
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>

<script type="text/javascript">
function onloadgo(){
	$("#gyousyaCd").focus();
	if ($("#searchFlg").val()=="1") {
		$.jgrid.gridUnload("resultListJqGrid");
		defaultObj.url = "<%=request.getContextPath()%>/h0020/doSearch";
		$("#resultListJqGrid").jqGrid($.extend(defaultObj,{datatype : "json"}));
		return false;
	}
}

/**
 * 明細リンク押下
 */
function linkSubmit(rowdata){
	console.log(rowdata);
	document.forms[0].action = '<%=request.getContextPath()%>/c0130/doShousai';
	document.forms[0].submit();
}
//選択リンクをクリックしたとき
function dlgOK(outtext)
{
	// 2017/08/07 NTS_朱 【№B309】 ドライバーメンテナンス機能の改修 UPD Start
//	window.returnValue=outtext;
	window.parent.returnValue=outtext;
	// 2017/08/07 NTS_朱 【№B309】 ドライバーメンテナンス機能の改修 UPD End
    //returnValueは呼び出し元に返す値(数値、文字列、その他の値をとる)
	window.close();
}
function dlgCancel(){
	window.returnValue="";
	window.close();
   //[×]ボタンを押した場合は null を返すようである
}
var defaultObj = {
		caption : "一覧",
		datatype : "json",
		mtype : "POST",
		styleUI : 'Bootstrap',
		colModel : [ 
			{label : '選択',name : 'listKey',width : 20, 
				formatter: function(cellvalue, options, rowdata) {
					return "<a name='link' href='#' onclick='dlgOK(\""+rowdata.listKey + "\")'; >選択</a>";
				}
			},  
			{label : '業者ＣＤ',name : 'gyousyaCd',width : 20},
			{label : '業者名',name : 'gyousyaNm',width : 20},
			{label : '管理元',name : 'kanrimotoKaisyaNm',width : 20},
		],
		jsonReader : {
			root : "gridModel",
			records : "record",
			repeatitems : false,
		},
		 prmNames : {
			page : "page",		//
			rows : "rows", 		//
			sort : "sort_key",  //
			order : "asc_desc"  //?
		},
		viewrecords : true,
		rowList : [ 10, 20, 30 ],
		width: 860,
		height:360,
		rowNum : 10,
		pager : "#resultListJqGridPager",
		beforeRequest:function(){
			$("#searchRowNum").val($("#resultListJqGrid").jqGrid('getGridParam','rowNum'));
		},
		gridComplete:function(){
			 document.getElementById("input_resultListJqGridPager").innerHTML="";
			$(".ui-pg-button").css("padding-left","10px");
			$(".ui-th-ltr").css("background","#CEE7F7");
			$(".ui-pg-selbox").css("height","30px");
			$(".ui-jqgrid-titlebar").css("text-align","left");
			$(".ui-pg-button").css("position","relative");
			$(".ui-pg-button").css("left","-130px");
			$(".ui-pg-selbox").css("position","relative");
			$(".ui-pg-selbox").css("left","-100px");
			$(".ui-paging-info").css("position","relative");
			$(".ui-paging-info").css("left","-20px");
			$(".ui-th-column").css("height","5px");
		}
	};
	$(document).ready(function() {
		$("#BtnQuery").on("click", function() {
			//
			//trimLRAll();
			$.jgrid.gridUnload("resultListJqGrid");
			defaultObj.url = "<%=request.getContextPath()%>/h0020/doSearch";
			
			$("#resultListJqGrid").jqGrid($.extend(defaultObj,{datatype : "json"}));
			return false;
		});
	});
</script>
</head>

<body onload="onloadgo()" >
<table>
<tr>
<td>
<script type="text/ecmascript" src="<%=request.getContextPath()%>/css/jqGrid/jquery.jqGrid.js"></script>
<script type="text/ecmascript" src="<%=request.getContextPath()%>/css/jqGrid/grid.locale-ja.js"></script>
<form:form name="searchForm" action="/h0020" method="post" modelAttribute="h0020model">
<input type="hidden" id="searchFlg" name="searchFlg" value="${h0020model.searchFlg}" />
<div style="width:860px;height:20px;background:#337AB7"></div>
<br>
<TABLE class="main">
	<colgroup>
		<col class="labelField char07Col"/>
		<col class="valueField char18Col"/>
		<col class="labelField char07Col"/>
		<col class="valueField char18Col"/>
		<col class="labelField char07Col"/>
		<col class="valueField char06Col"/>
	</colgroup>
    <TR>
      <TD>業者ＣＤ</TD>
      <TD>
      <form:input type="text" path="gyousyaCd" maxlength="6"  class="txtInp10N" style="ime-mode: disabled" value="${h0020model.gyousyaCd}" /></TD>
      <TD>業者名</TD>
      <TD>
      <form:input type="text" path="gyousyaNm" maxlength="15" class="txtInp10N" style="ime-mode: disabled" value="${h0020model.gyousyaNm}" /></TD>
      <TD>管理元</TD>
	  <TD>
	   <c:if test="${h0020model.kanrimotoFlg ne '0'}">
	  <form:select path="kanrimotoFlg"  onchange='changeDp()'>
	  		<form:option value="" label="" />
			<form:options items="${h0020model.kanrimotoFlgItems}" itemLabel="fullName" itemValue="codeId" attributes="tabindex='3'  disabled='true' style='background-color: #ffffff'"/>
	  </form:select>
	  </c:if>
	  <c:if test="${h0020model.kanrimotoFlg eq '0'}">
	  <form:select path="kanrimotoFlg"  onchange='changeDp()'>
	  		<form:option value="" label="" />
			<form:options items="${h0020model.kanrimotoFlgItems}" itemLabel="fullName" itemValue="codeId"/>
	  </form:select>
	  </c:if>	
	  </TD>
    </TR>
</TABLE>
<br>
<TABLE>
     <TR>
      <TD colspan="7" class="btnLine">
      	<input id="BtnQuery" name="BtnQuery" type="button" class="button" value="検索"  />
      	<input id="Btn01" name="Btn01" type="button" class="button" value="閉じる" onclick="dlgCancel()"/>
      </TD>
    </TR>
</TABLE>
<br>
	<div align="left">
	<table id="resultListJqGrid"></table>
	<div id="resultListJqGridPager"></div>
	<%-- list:${list} --%>
	</div>

</form:form>
</td>
</tr>
</table>
</body>
</html>