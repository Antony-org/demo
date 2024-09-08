<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebSocket Chat</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="styles.css"> <!-- Link to the external CSS file -->
</head>
<body>
<h2>WebSocket Chat</h2>

<!-- Connection Status -->
<div id="connectionStatus">Disconnected</div>

<!-- Online Users -->
<h3>Online Users</h3>
<ul id="onlineUsers" style="border: 1px solid #000; width: 200px; height: 300px; overflow-y: scroll;"></ul>

<!-- Chat Window -->
<div id="chatWindow" style="border: 1px solid #000; width: 500px; height: 300px; overflow-y: scroll;"></div>

<!-- Message Input -->
<input type="text" id="messageInput" placeholder="Type your message here..." style="width: 450px;" />
<button id="sendButton">Send</button>
<button id="logoutButton">Logout</button>

<script>
    $(document).ready(function() {
        var username = new URLSearchParams(window.location.search).get('username');
        var websocket;

        function setConnectionStatus(status) {
            $('#connectionStatus').text(status);
        }

        function updateOnlineUsers(usersString) {
            var userList = $('#onlineUsers');
            userList.empty();
            var users = usersString.split(',');
            users.forEach(function(user) {
                userList.append('<li>' + user + '</li>');
            });
        }

        function connectWebSocket() {
            websocket = new WebSocket('ws://localhost:8080/myApp/websocket'); // Update WebSocket URL based on your server

            websocket.onopen = function() {
                setConnectionStatus("Connected as " + username);
                websocket.send("USERNAME:" + username);
            };

            websocket.onmessage = function(event) {
                var data = event.data;
                if (data.startsWith("ONLINE_USERS:")) {
                    var onlineUsersString = data.substring("ONLINE_USERS:".length);
                    updateOnlineUsers(onlineUsersString);
                } else {
                    $('#chatWindow').append('<div>' + data + '</div>');
                }
            };

            websocket.onclose = function() {
                websocket.send("LOGOUT");
                setConnectionStatus("Disconnected");
            };

            websocket.onerror = function() {
                setConnectionStatus("Error in WebSocket connection");
            };
        }

        $('#sendButton').click(function() {
            var message = $('#messageInput').val();
            if (message && websocket) {
                websocket.send(username + ": " + message);
                $('#messageInput').val('');
            }
        });

        $('#logoutButton').click(function() {
            if (websocket) {
                websocket.send(username + " has left the chat.");
                websocket.close();
            }
            window.location.href = '/myApp/index.jsp'; // Redirect to login page
        });

        connectWebSocket();
    });
</script>
</body>
</html>