// import React, { useState, useEffect } from "react";
// import SockJS from 'sockjs-client';
// import {Stomp} from '@stomp/stompjs';

// const SocketConnection = () => {
//     // const [connected, setConnected] = useState(false);
//     // const [connected1, setConnected1] = useState('');
//     let stompClient = null;
//     let connected = false;

//     const connect = () => {
        
//         let socket = new SockJS('/ownsite');
//         stompClient = Stomp.over(socket);
//         stompClient.connect({}, function (frame) {
//             connected = true;
//             console.log('Connected: ' + frame);

//             stompClient.subscribe('/topic/greetings', function (greeting) {
//                 // showGreeting(JSON.parse(greeting.body).content);
//                 console.log('Subscribed');
//             });
//         });
//     }

//     const disconnect = () => {
//         if(stompClient !== null){
//             stompClient.disconnect();
//         }
//         connected = false;
//         console.log('Disconnected');
//     }

//     const sendMsg = (text) => {
//         stompClient.send("app/hello", {}, JSON.stringify({'content': text}));
//     }



// }

// export default SocketConnection;