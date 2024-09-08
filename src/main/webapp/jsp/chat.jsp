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

<!-- Connection Status -->
<div id="connectionStatus">Disconnected</div>

<!-- Chat Window -->
<div id="chatWindow" style="border: 1px solid #000; width: 300px; height: 200px; overflow-y: scroll;"></div>

<!-- Message Input -->
<input type="text" id="messageInput" placeholder="Type your message here..." style="width: 250px;" />
<button id="sendButton">Send</button>
<button id="logoutButton">Logout</button>

<script>
    $(document).ready(function() {
        var username = new URLSearchParams(window.location.search).get('username');
        var websocket;

        // logs
        console.log("Username: " + username);

        // Update connection status
        function setConnectionStatus(status) {
            $('#connectionStatus').text(status);
        }

        // Initialize WebSocket connection
        function connectWebSocket() {
            websocket = new WebSocket('ws://localhost:8080/myApp/websocket'); // Update WebSocket URL based on your server

            websocket.onopen = function() {
                setConnectionStatus("Connected as " + username);
                websocket.send(username + " has joined the chat.");
            };

            websocket.onmessage = function(event) {
                $('#chatWindow').append('<div>' + event.data + '</div>');
            };

            websocket.onclose = function() {
                setConnectionStatus("Disconnected");
            };

            websocket.onerror = function() {
                setConnectionStatus("Error in WebSocket connection");
            };
        }

        // Send message
        $('#sendButton').click(function() {
            var message = $('#messageInput').val();
            if (message && websocket) {
                websocket.send(username + ": " + message);
                $('#messageInput').val('');  // Clear input field
            }
        });

        // Logout button to disconnect WebSocket
        $('#logoutButton').click(function() {
            if (websocket) {
                websocket.send(username + " has left the chat.");
                websocket.close();
            }
            window.location.href = '/myApp/index.jsp'; // Redirect to login page
        });

        // Connect WebSocket when the page loads
        connectWebSocket();
    });
</script>
</body>
</html>