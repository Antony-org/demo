<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Combined AJAX Example</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
    <script src="js/script.js"></script>
</head>
<body onload="setInterval('updateSchedule()', '3000')">

<div id="ajaxButtonSection">
    <h2>1st Assignment: AJAX Button</h2>
    <button onclick="submitForm()">Submit</button>
    <div id="response"></div>
</div>

<div id="validationForm">
    <h2>2nd Assignment: AJAX Form Validation</h2>
    <form onsubmit="return false;">
        Enter Your Name: <input type="text" id="userName" onblur="validateName()"><span id="nameStatus"></span><br>
        Enter Your Password: <input type="password" id="password" disabled><br>
        <input type="submit" value="Submit">
    </form>
</div>

<div id="scheduleTable">
    <h2>3rd Assignment: Dynamic Schedule Table</h2>
    <table border="1">
        <thead>
        <tr>
            <th>Activity Name</th>
            <th>Location</th>
            <th>Time</th>
        </tr>
        </thead>
        <tbody id="schedule">
        <!-- get table.jsp -->
        </tbody>
    </table>
</div>

</body>
</html>
