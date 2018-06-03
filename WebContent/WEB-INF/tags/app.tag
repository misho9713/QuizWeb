<%@tag description="Main App tag" pageEncoding="utf-8" %>
<%@attribute name="menu" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="main" fragment="true" %>
<%@attribute name="right" fragment="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="header_left" fragment="true" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
  <link rel="stylesheet" href="styles/reset.css"/>
</head>

<jsp:invoke fragment="head"/>
<body>
<div class="container">
  <div class="header">
    <a href="/index"><img class="logo" src="${pageContext.request.contextPath}/images/logo.png"></a>
    <ul>
      <li><a href="/index">Home</a></li>
      <jsp:invoke fragment="header_left"/>
    </ul>
    <ul class="right_header">
      <%
        boolean logged = false;
        if (logged) {%>
      <li><a href="#">Username</a></li>
      <li><a href="#">Logout</a></li>
      <%} else {%>
      <li><a href="#">Register</a></li>
      <li><a href="#">Log in</a></li>
      <%}%>
    </ul>
  </div>
  <div class="menu">
    <jsp:invoke fragment="menu"/>
  </div>
  <div class="main">
    <jsp:invoke fragment="main"/>

  </div>
  <div class="right">
    <jsp:invoke fragment="right"/>
  </div>
  <div class="footer">
    <jsp:invoke fragment="footer"/>
  </div>
</div>
</body>

</html>