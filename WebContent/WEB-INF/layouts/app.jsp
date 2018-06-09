<%--
  Created by IntelliJ IDEA.
  User: Misho
  Date: 06.06.2018
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css"/>
  <link rel="stylesheet" href="styles/reset.css"/>
  <%
    String head = getIncludePageName("head", request);
    if (head.length() > 0) {
  %>
  <jsp:include page='<%= head %>'/>
  <%}%>
</head>
<body>
<div class="container">
  <div class="header">
    <a href="/index"><img class="logo" src="${pageContext.request.contextPath}/images/logo.png"></a>
    <ul>
      <li><a href="/index">Home</a></li>
      <%
        String leftHeader = getIncludePageName("leftHeader", request);
        if (leftHeader.length() > 0) {
      %>
      <jsp:include page="<%=leftHeader%>"/>
      <%}%>
    </ul>
    <ul class="right_header">
      <%

        boolean
                logged
                =
                false;
        if
                (
                logged
                ) {%>
      <li><a href="#">Username</a></li>
      <li><a href="#">Logout</a></li>
      <%
      } else {
      %>
      <li><a href="#">Register</a></li>
      <li><a href="#">Log in</a></li>
      <%}%>
      <%
        String rightHeader = getIncludePageName("rightHeader", request);
        if (rightHeader.length() > 0) {
      %>
      <jsp:include page="<%=rightHeader%>"/>
      <%}%>
    </ul>
  </div>
  <div class="menu">
    <ul>
      <%
        for (int i = 0; i < 30; i++) {
      %>
      <li> list item <%=i%>
      </li>
      <%}%>
      <%
        String menu = getIncludePageName("menu", request);
        if (menu.length() > 0) {
      %>
      <jsp:include page="<%=menu%>"/>
      <%}%>
    </ul>

  </div>
  <div align="center" class="main">
    <%
      String main = getIncludePageName("main", request);
      if (main.length() > 0) {
    %>
    <jsp:include page="<%=main%>"/>
    <%}%>
  </div>
  <div class="right">
    <%
      String right = getIncludePageName("right", request);
      if (right.length() > 0) {
    %>
    <jsp:include page="<%=right%>"/>
    <%}%>
  </div>
  <div class="footer">
    <%
      String footer = getIncludePageName("footer", request);
      if (footer.length() > 0) {
    %>
    <jsp:include page="<%=footer%>"/>
    <%}%>
  </div>
</div>
</body>

</html>
<%!
  private String getIncludePageName(String attributeName, HttpServletRequest request) {
    String page = (String) request.getAttribute(attributeName);
    return page == null ? "" : page;
  }
%>