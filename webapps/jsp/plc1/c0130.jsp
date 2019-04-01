<%@page contentType="text/html; charset=MS932" pageEncoding="MS932"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/css/common.css" type="text/css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/bootstrap/3.3.4/css/bootstrap-custom.css" rel="stylesheet">
<style>
body{margin:0}
#gview_resultListJqGrid{
	overflow-x:hidden;
}
</style>
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>

<script type="text/javascript">
function onloadgo(){
	if ($("#searchFlg").val()=="1") {
		$.jgrid.gridUnload("resultListJqGrid");
		defaultObj.url = "<%=request.getContextPath()%>/c0130/doSearch";
		$("#resultListJqGrid").jqGrid($.extend(defaultObj,{datatype : "json"}));
		return false;
	}
}

/**
 * –¾×ƒŠƒ“ƒN‰Ÿ‰º
 */
function linkSubmit(rowdata){
	console.log(rowdata);
	document.forms[0].action = '<%=request.getContextPath()%>/c0130/doShousai';
	document.forms[0].submit();
}

	var defaultObj = {
		caption : "ˆê——",
		datatype : "json",
		mtype : "POST",
		styleUI : 'Bootstrap',
		colNames:[
		          '‘I‘ğ',
		          '‰c‹ÆŠ‚b‚c',
		          '‰c‹ÆŠ',
		          'ƒfƒ|‚b‚c',
		          'ƒfƒ|',
		          '“d˜b”Ô†',
		          'ŠÇ—Œ³',
		          'Š‘®’nˆæ',
		          'ƒfƒ|‘gD',
		          ],
		colModel : [ 
			{label : '‘I‘ğ',name : 'eigyoushoCd',width : 50, 
				formatter: function(cellvalue, options, rowdata) {
					return "<a name='link' href='#' onclick='linkSubmit(\""+rowdata + "\")'; >Ú×</a>";
				}
			},  
			{label : '‰c‹ÆŠ‚b‚c',name : 'eigyoushoCd',width : 80},
			{label : '‰c‹ÆŠ',name : 'eigyoushoNm',width : 120},
			{label : 'ƒfƒ|‚b‚c',name : 'depoCd',width : 60},
			{label : 'ƒfƒ|',name : 'depoNm',width : 150},
			{label : '“d˜b”Ô†',name : 'denwaNo',width : 100},
			{label : 'ŠÇ—Œ³',name : 'kanrimotoKaisyaKb',width : 80},
			{label : 'Š‘®’nˆæ',name : 'syozokuchiikiKb',width : 80},
			{label : 'ƒfƒ|‘gD',name : 'depoSoshikiKb',width : 80},
			/* {label : 'SS•”–åĞ‰î',name : 'departmentCont',width : 270, 
				formatter: function(cellvalue, options, rowdata) {
					return '<div class="formatDiv" style="overflow: hidden;"> '+ rowdata.departmentCont + '</div>';
				}
			}, */
		],
		jsonReader : {
			root : "gridModel",
			records : "record",
			repeatitems : false,
		},
		postData :{
			"eigyoushoCd": function(){
				return $("#eigyoushoCd").val();
			},"kanrimotoKaisyaKb": function(){
				return $("#kanrimotoKaisyaKb").val();
			},"syozokuchiikiKb": function(){
				return $("#syozokuchiikiKb").val();
			},"depoCd": function(){
				return $("#depoCd").val();
			},"sakujoKb": function changeval(){
		        var check = document.getElementById("sakujoKb");
		         if(check.checked == true){
		              document.getElementById("sakujoKb").value = "1";
		              return $("#sakujoKb").val();
		         }else{
		              document.getElementById("sakujoKb").value = "0";
		              return $("#sakujoKb").val();
		          }
		 }
		},
		 prmNames : {
			page : "page",		//
			rows : "rows", 		//
			sort : "sort_key",  //
			order : "asc_desc"  //
		},
		viewrecords : true,
		rowList : [ 10, 20, 30 ],
		width: 1000,
		height:250,
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
			$(".ui-pg-button").css("position","relative");
			$(".ui-pg-button").css("left","-150px");
			$(".ui-pg-selbox").css("position","relative");
			$(".ui-pg-selbox").css("left","-120px");
		}
	};
	
	$(document).ready(function() {
		$("#BtnQuery").on("click", function() {
			//
			//trimLRAll();
			$("#searchFlg").val("1");
			$.jgrid.gridUnload("resultListJqGrid");
			defaultObj.url = "<%=request.getContextPath()%>/c0130/doSearch";
			
			$("#resultListJqGrid").jqGrid($.extend(defaultObj,{datatype : "json"}));
			return false;
		});
	});
	function processSelection(button){
		if (button == "Btninsert") {
		//	depo_cd = $("#eigyoushoCd").val();
		//	alert(depo_cd);
		//	if(depo_cd==""){
		//		alert("insert null");
		//		return false;
		//	}
			form = document.forms[0];
			form.action = '<%=request.getContextPath()%>/c0130/doInsert';
			form.submit();
		}else if (button == "BtnClear"){
			form = document.forms[0];
			form.action = '<%=request.getContextPath()%>/c0130/doInit';
			form.submit();
		}
	}
	
</script>
</head>

<body onload="onloadgo()" >
<table class="header" style="width:1244px;">
<tr>
<%@include file="../menuTitle.jsp"%>
</tr>
</table>
<table>
<tr>
<td style="width:20%;" valign="top">
<%@include file="../menuTest.jsp"%>

</td>
<td style="width:80%;" valign="top">
<script type="text/ecmascript" src="<%=request.getContextPath()%>/css/jqGrid/jquery.jqGrid.js"></script>
<script type="text/ecmascript" src="<%=request.getContextPath()%>/css/jqGrid/grid.locale-ja.js"></script>
<form:form name="searchForm" action="/c0130" method="post" modelAttribute="c0130model">
<input type="hidden" id="searchFlg" name="searchFlg" value="${c0130model.searchFlg}" />
<TABLE class="main" >
	<TR>
		<TD class="title" style = "text-align:left;height:30px">‚X‚QDƒfƒ|î•ñˆê——</TD>
	</TR>
</TABLE>
<br>
	<TABLE class="main">
	<colgroup>
		<col class="labelField char24Col"/>
		<col class="valueField char36Col"/>
		<col class="labelField char24Col"/>
		<col class="valueField char36Col"/>
	</colgroup>
	<TR>
		<TD  >‰c‹ÆŠ</TD>
		<TD >
			<form:select path="eigyoushoCd">
				<form:option value="" label="" />
				<form:options items="${c0130model.eigyoushoCdItems}" itemLabel="depoNm" itemValue="depoCd"/>
			</form:select>
		</TD>		
		<TD  >ŠÇ—Œ³</TD>
		<TD >
			<form:select path="kanrimotoKaisyaKb">
				<form:option value="" label="" />
				<form:options items="${c0130model.kanrimotoKaisyaKbItems}" itemLabel="fullName" itemValue="codeId"/>
			</form:select>
		</TD>
	</TR>
	<TR>
		<TD  >Š‘®’nˆæ</TD>
		<TD >
			<form:select path="syozokuchiikiKb">
				<form:option value="" label="" />
				<form:options items="${c0130model.syozokuchiikiKbItems}" itemLabel="fullName" itemValue="codeId"/>
			</form:select>
		</TD>
		<TD  >ƒfƒ|‚b‚c</TD>
		<TD  >
			<form:input type="text" path="depoCd" size="12" maxlength="4" class="txtInp4N" style="ime-mode: disabled" value="${c0130model.depoCd}" />
		</TD>
	</TR>
	<TR>
		<TD  >íœ•\¦</TD>
		<TD  >
			<input type="checkbox" name="sakujoKb" id="sakujoKb" onchange="changeval()" />
		</TD>
	</TR>
	</TABLE>
	<BR>
	<TABLE>
		<TR >
	      <TD colspan="7" class="btnLine">
			<input id="BtnQuery" name="BtnQuery" type="button" class="button" value="ŒŸõ" />
			<input id="Btninsert" name="Btninsert" type="button" class="button" value="“o˜^" onclick="processSelection(this.name);"/>
			<input id="BtnClear" name="BtnClear" type="button" class="button" value="ƒNƒŠƒA" onclick="processSelection('BtnClear');" />
	      </TD>
	      <TD colspan="7" class="btnLine"></TD>
	    </TR>
	</TABLE>

	<div align="center">
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