<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="${ctx}/resources/common/icon/msg.ico">
<link href="${ctx}/resources/common/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/resources/common/css/namoosocial.css" rel="stylesheet">
<script src="${ctx}/resources/common/js/jquery.js"></script>
<script src="${ctx}/resources/common/js/jquery.form.js"></script>
<script src="${ctx}/resources/common/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/common/js/namoosocial.js"></script>
<script type="text/javascript" src="${ctx}/resources/common/js/date.js"></script>
<script type="text/javascript">
	var nsjs = {};
	nsjs.ctx = "${ctx}";
	nsjs.loginId = "${login.userId}";
</script>