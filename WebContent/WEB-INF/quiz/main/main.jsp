<%--
  Created by IntelliJ IDEA.
  User: Misho
  Date: 09.06.2018
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="container">
  <form>
    is this quiz no <%=request.getAttribute("id")%> page?
    <label>1) <input type="radio" name="quiz" value="YES">Yes</label>
    <label>2) <input type="radio" name="quiz" value="NO">No</label>
    <label>3) <input type="radio" name="quiz" value="Don't know">Don't know</label>
    <label>4) <input type="radio" name="quiz" value="None of above">None of above</label>
  </form>
  <div align="center">
    <a href="#prev" id="prevq" class="switch-question">
      <button>prev</button>
    </a>
    <a href="#next" id="nextq" class="switch-question">
      <button>next</button>
    </a>
  </div>
</div>