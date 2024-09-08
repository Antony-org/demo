<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebSocket Login</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h2>Login to WebSocket Chat</h2>
<form id="loginForm">
    <input type="text" id="username" placeholder="Enter your username" required />
    <button type="submit">Login</button>
</form>

<script>
    $(document).ready(function() {
        $('#loginForm').submit(function(event) {
            event.preventDefault();
            var username = $('#username').val();
            if(username) {
                window.location.href = 'jsp/chat.jsp?username=' + encodeURIComponent(username);
            }
        });
    });
</script>
</body>
</html>