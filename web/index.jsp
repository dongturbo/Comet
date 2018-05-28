<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*" %>
<%


  // 检测网页是否由新的访问用户
    String uuid = (String)session.getAttribute("uuid");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>测试</title>
</head>

<head>
  <script type='text/javascript' src='/comet/dwr/engine.js'></script>
  <script type='text/javascript' src='/comet/dwr/util.js'></script>
  <script type='text/javascript' src='/comet/dwr/interface/Listener.js'></script>
  <script type="text/javascript">
      function sendMessage() {
          Listener.onListener(dwr.util.getValue("text"));
      }
      function clientFunction(data){
          console.log(data);
      }
  </script>

</head>
<body onload="dwr.engine.setActiveReverseAjax(true);">
<p>输入信息:
  <input id="text" onkeypress="dwr.util.onReturn(event)" />
  <input type="button" value="Send" onclick="sendMessage()" /></p>

  <h2><% out.print(uuid); %></h2>



</body>
</html>
