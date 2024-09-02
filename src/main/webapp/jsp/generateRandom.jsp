<%@ page import="example.utils.RandomNumberGenerator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Random Number Generator</title>
</head>
<body>
<h2>Generate a Random Number</h2>
<form method="POST" action="">
  <br> Enter Range: <input type="number" name="start" required>
  <br> Enter Range: <input type="number" name="end" required>
  <br> <input type="submit" value="Generate">
</form>

<%
  String start = request.getParameter("start");
  String end = request.getParameter("end");

  if (start != null && end != null) {
    int range1 = Integer.parseInt(start);
    int range2 = Integer.parseInt(end);
    RandomNumberGenerator rng = new RandomNumberGenerator();
    int randomNumber = rng.generateRandomNumber(range1, range2);

    out.println("<div>");
    out.println("<h3>Generated Random Number: " + randomNumber + "</h3>");
    out.println("</div>");
  }
%>
</body>
</html>