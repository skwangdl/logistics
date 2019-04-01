<!DOCTYPE HTML>
<!-- saved from url=(0028)https://www.linkworldpms.jp/ -->
<HTML lang="en">
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">
<%@page contentType="text/html; charset=MS932" pageEncoding="MS932"%>
<TITLE>login</TITLE>
<!-- Meta -->
<META name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"> <!-- Favicon --> 
<LINK href="/favicon.ico" rel="shortcut icon"> 
<!-- CSS JS --> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/bootstrap.min.css" rel="stylesheet" type="text/css"> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/font-awesome.min.css" rel="stylesheet" type="text/css"> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/alertify.core.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath()%>/css/loginCSS/alertify.bootstrap.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath()%>/css/loginCSS/bootstrap-switch.min.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath()%>/css/loginCSS/bootstrap-multiselect.css" rel="stylesheet" type="text/css"> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/bootstrap-editable.css" rel="stylesheet" type="text/css"> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/style.min.css" rel="stylesheet" type="text/css"> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/fullcalendar.css" rel="stylesheet" type="text/css"> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/main.css" rel="stylesheet" type="text/css" media="screen"> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/style.css" rel="stylesheet" type="text/css" media="screen"> 
<LINK href="<%=request.getContextPath()%>/css/loginCSS/custom.css" rel="stylesheet" type="text/css" media="screen"> 
  <LINK href="<%=request.getContextPath()%>/css/loginCSS/login.css" rel="stylesheet"> 
<META name="GENERATOR" content="MSHTML 11.00.9600.17842">
<style>
	#web_bg{
  position:fixed;
  top: 0;
  left: 0;
  width:100%;
  height:100%;
  
  z-index:-10;
  zoom: 1;
  background-color: #fff;
  background-repeat: no-repeat;
  background-size: 100% 100%;
  -webkit-background-size: cover;
  -o-background-size: cover;
  background-position: center 0;
}
</style>
</HEAD> 
<BODY style='background: url("/static/images/bg2.jpg") no-repeat 0px 0px / cover;'>
<%@include file="../messageDiv.jsp"%>
<DIV class="container"><INPUT name="_csrf" id="_csrf" type="hidden">     
<DIV style="width: 100%; text-align: center; margin-top: 100px;">
      <BR><BR><BR><BR>
     </DIV>
<DIV class="row" style="margin-top: 20px;">
<DIV class="col-md-6 col-md-offset-3">
<DIV class="panel panel-login radius-border">
<DIV class="panel-heading" style="border-color: transparent; background-color: transparent;">
<DIV class="row">
<HR>
</DIV>
<DIV class="panel-body">
<DIV class="row">
<DIV class="col-lg-8 col-md-offset-2">
<FORM id="login-form" role="form" style="display: block;" method="post">
<DIV class="form-group"><INPUT name="userId" tabindex="1" class="form-control radius-border" id="userId" type="text" placeholder="ユーザ名" value=""></DIV>
<DIV class="form-group"><INPUT name="password" tabindex="2" class="form-control radius-border" id="password" type="password" placeholder="パスワード" value=""></DIV>
<DIV class="form-group">

<DIV>
<BUTTON name="login-submit" class="form-control btn btn-login radius-border" 
id="login-submit" type="button" onclick="dologin()">ログイン</BUTTON> 
<BUTTON name="login-submit2222" class="form-control btn btn-login radius-border" 
id="login-submit" type="button" onclick="toMenu()">toMenu</BUTTON>                           
</DIV></DIV></FORM>
</DIV></DIV></DIV></DIV></DIV></DIV></DIV><!-- JS Global Compulsory --> 
<div id="web_bg" style="background-image: url(<%=request.getContextPath()%>/css/loginCSS/loginbackground.png);"></div>
</DIV>
<SCRIPT type="text/javascript">
//Menu
function toMenu(){
	form = document.forms[0];
	form.action = '<%=request.getContextPath()%>/menu/toMenu';
	form.submit();
}
  function dologin(){
	form = document.forms[0];
	form.action = '<%=request.getContextPath()%>/c0130/doInit';
	form.submit();
  }
</SCRIPT>
   
<SCRIPT src="<%=request.getContextPath()%>/css/loginCSS/login.js" type="text/javascript"></SCRIPT>
 </BODY></HTML>
