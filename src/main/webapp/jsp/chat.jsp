<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebSocket Chat</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h2>WebSocket Chat</h2>
<div id="chatWindow">
    <div id="messages"></div>
</div>
<input type="text" id="messageInput" placeholder="Type your message here..." />
<button id="sendButton">Send</button>
<button id="logoutButton">Logout</button>

<script>
    var websocket;
    $(document).ready(function() {
        var username = decodeURIComponent(window.location.search.split('=')[1]);

        // Open WebSocket connection
        websocket = new WebSocket("ws://localhost:8080/testServer");

        websocket.onopen = function(event) {
            $('#messages').append('<p>Connected as ' + username + '</p>');
        };

        websocket.onmessage = function(event) {
            $('#messages').append('<p>' + event.data + '</p>');
        };

        websocket.onclose = function(event) {
            $('#messages').append('<p>Disconnected</p>');
        };

        $('#sendButton').click(function() {
            var message = $('#messageInput').val();
            if (message && websocket.readyState === WebSocket.OPEN) {
                websocket.send(username + ": " + message);
                $('#messageInput').val(''); // Clear input after sending
            }
        });

        $('#logoutButton').click(function() {
            websocket.close(); // Close the WebSocket connection
            window.location.href = 'login.html'; // Redirect to login page
        });
    });
</script>
</body>
</html>