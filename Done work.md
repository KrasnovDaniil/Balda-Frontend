I work on properly interaction with chat rooms: user can create `chat-room`, other user can enter in any `chat-rooms` without inviting just typing name of deserved room. Also all users can see messages sent by any user in `chat-room`.


### The cycle of message sending:
User enter in the `chat-room` -->  
User send message to chat -->   
This message process by `sendMessage()` method in `Chat.js` class -->  
If it's accepted then it send to the server (backend) -->   
On backent it saved in DB and send to other users -->  
Other user get that message from server and it handle by arrow-function in `addHandle()` method -->   
If that message accepted then it displayed on user's screen -->   
Cycle end here and app wait other messages. -->


#### ENTERING IN ROOM

When user created or entered in room then he will connect to chat websocket for listening messages from other users.  

*The code below run every time when user connect to `chat-room`*  
`Chat.js`
```javascript
  useEffect(() => {
    const { name, room } = queryString.parse(location.search);  // parse specified data from URL to pair (tuple)
    setRoom(room); // set user name and his room
    setName(name);
    connect(name, room); // connect to WebSocket
    // const greetingMsg = {text: `Hello ${name}`, sender: 'admin'}; // just a greeting mesage

    addHandler( curMessage => {
      if (curMessage.sender === name || curMessage.content === null) return;
      setMessages(messages => [...messages, {text:curMessage.content, sender:curMessage.sender}]);     
      console.log(messages);
    }); 

  }, [ENDPOINT, location.search]);
```

Describe `connect()` method:
``` javascript
export function connect(userName, roomID) {
    username = userName;
    roomId = roomID;
        
    const socket = new SockJS('http://localhost:8080/ownsite') // connecting to the Spring server
    stompClient = Stomp.over(socket)
    stompClient.debug = () => {}
    // stompClient.connect({}, frame => {
    //     stompClient.subscribe('/topic/method', message => {
    //         handlers.forEach(handler => handler(JSON.parse(message.body)))
    //         console.log(message)
    //     })
    // })

    stompClient.connect({}, onConnected, onError);
    console.log('Connected')
}
```

In that line I attached 2 listeners: `onConnected` and `onError` for handle appropriate events 
```javascript
stompClient.connect({}, onConnected, onError);
```

`onConnected()`:
```javascript
export function onConnected() {
    enterRoom(roomId);
}
```
this method only call `enterRoom()` function for entering in room

`enterRoom()`:
```javascript
// Leave the current room and enter a new one.
function enterRoom(newRoomId) {
    roomId = newRoomId;
    topic = `/app/chat/${newRoomId}`; // form an address to send for new room

    if (currentSubscription) {
        currentSubscription.unsubscribe();
    }
    // establish subscription on specified address "/chat-channel/${roomId}"
    // and attach handler onMessageReceived()
    // It means this handler will process received messages at that address
    currentSubscription = stompClient.subscribe(`/chat-channel/${roomId}`, onMessageReceived);

    // send request as join message for joining current user in chatroom 
    stompClient.send(`${topic}/addUser`,
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    );
}
```


#### SENDING MESSAGE FROM USER TO CHAT

This method check and send message to the server and add that in message list  
```javascript
  // method of sending message
  function sendMessage(event) {  
    event.preventDefault();
    let calc = message.match(/[\s]*/); // check message on non-space text
    if (message && calc[0] != message) {
      sendMessageToServer(message);
      setMessages((messages) => [...messages, {text:message, sender:name}]); 
      setMessage('');
    }
  };
```

#### PROCESS THE SENT MESSAGE FROM THE SERVER

```javascript
// handle received messages from backend
// here I will display  received message on messageBox 
export function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    curHandler(message);
}
```
This method only call current handler for process received message but at first it insert text from given massage object (payload). And curHandler is handler which I added in `addHandler()` at the beginning.

`addHandler()`:
As you can see in first code piece of code in this article (at the top of this page) here is `addHandler()` method, it add handler of received messages from backend to get messages from other users.

```javascript
  useEffect(() => {
    const { name, room } = queryString.parse(location.search);  // parse specified data from URL to pair (tuple)
    setRoom(room); // set user name and his room
    setName(name);
    connect(name, room); // connect to WebSocket
    // const greetingMsg = {text: `Hello ${name}`, sender: 'admin'}; // just a greeting mesage

    addHandler( curMessage => {
      if (curMessage.sender === name || curMessage.content === null) return;
      setMessages(messages => [...messages, {text:curMessage.content, sender:curMessage.sender}]);     
      console.log(messages);
    }); 

  }, [ENDPOINT, location.search]);
```

