<%@tag description="Main App tag" pageEncoding="utf-8" %>
<%@attribute name="menu" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="main" fragment="true" %>
<%@attribute name="right" fragment="true" %>
<%@attribute name="head" fragment="true" %>
<head>

</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
<jsp:invoke fragment="head"/>
<body>
<div class="container">
  <div class="header">
    <a href="/index"><img class="logo" src="${pageContext.request.contextPath}/images/logo.png"></a>
    <ul>
      <li>Home</li>
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