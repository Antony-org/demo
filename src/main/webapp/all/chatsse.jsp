<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat Application</title>
    <style>
        #chatBox {
            border: 1px solid black;
            height: 300px;
            overflow-y: scroll;
            margin-bottom: 10px;
            padding: 10px;
        }
        #onlineUsers {
            border: 1px solid black;
            margin-bottom: 10px;
            padding: 10px;
        }
        #container {
            display: flex;
            justify-content: space-between;
        }
        #chatWindow {
            width: 70%;
        }
        #userList {
            width: 25%;
        }
        #messages {
            border: 1px solid #ddd;
            height: 300px;
            overflow-y: scroll;
            padding: 10px;
            white-space: pre-line;  /* Ensure newlines are respected */
        }
    </style>
    <script src="chat.js"></script>
</head>

<body onload="receiveMessages();">
<h2>WebSocket Chat</h2>
<div id="container">
    <div id="chatWindow">
        <div id="messages"></div>
        <div style="display: flex;">
            <input type="text" id="messageInput" placeholder="Type your message here..."/>
            <button id="sendButton" onclick="sendMessage();">Send</button>
        </div>
        <button id="logoutButton">Logout</button>
    </div>
    <div id="userList">
        <h3>Online Users</h3>
        <ul id="onlineUsers"></ul>
    </div>
</div>
</body>

</html>
