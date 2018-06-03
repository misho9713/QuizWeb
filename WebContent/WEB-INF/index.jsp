<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:app>
  <jsp:attribute name="head">
    <title>Quiz Website</title>
  </jsp:attribute>
  <jsp:attribute name="header_left">
  </jsp:attribute>

  <jsp:attribute name="menu">
  </jsp:attribute>
  <jsp:attribute name="main">
   <div class="login-form">
     <div class="empty-left"></div>
     <div class="form-content">
       <form action="#" method="POST">

         You are not logged in
         <br>
         <label>
           Username
           <input name="username">
         </label>
         <br>
         <label>Password
           <input type="password" type="password">
         </label>
         <input type="submit" value="log in">
       </form>
     </div>
     <div class="empty-right"></div>
   </div>
  </jsp:attribute>
  <jsp:attribute name="right"> this is the right bar</jsp:attribute>
  <jsp:attribute name="footer"> this is jigar footer</jsp:attribute>
</t:app>