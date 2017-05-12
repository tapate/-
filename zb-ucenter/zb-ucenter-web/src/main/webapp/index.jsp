<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<!DOCTYPE html>

<html>
<head>
<title>欢迎登录系统</title>
<script type="text/javascript">
    window.location.href = "<c:url value='/index'/>";
</script>
</head>
<body>
    <%-- <a href="<c:url value="/login/toLogin"/>">去登录，测试shiro权限认证吧</a> --%>
</body>
</html>