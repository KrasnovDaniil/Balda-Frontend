import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'
import React, { useState, useEffect } from "react";
import {Chat, refreshMessages} from '../components/Chat/Chat';
// Here is code responsible for websockets functionality
// like send message, handle websocket, connect and disconnect

let stompClient = null
const handlers = []
let currentSubscription;
let messageHandler = null;
let curHandler = null;
let topic = null;
let username = null;
let roomId = null;
  

export function connect(userName, roomID) {
    username = userName;
    roomId = roomID;
        
    const socket = new SockJS('https://balda-play.herokuapp.com/ownsite') // connecting to the Spring server
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

export function addHandler(handler){
    curHandler = handler;
    // handlers.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
    console.log("Disconnected")
}


export function onConnected() {
    enterRoom(roomId);
}


export function onError(error) {
  console.log("Error occured!");
}

  
// Leave the current room and enter a new one.
function enterRoom(newRoomId) {
    roomId = newRoomId;
    topic = `/app/chat/${newRoomId}`; // form a address to send for new room

    if (currentSubscription) {
        currentSubscription.unsubscribe();
    }
    // establish subscription on specified address "/chat-channel/${roomId}"
    // and attach handler onMessageReceived()
    // It means this handler will process received messages at that address
    currentSubscription = stompClient.subscribe(`/chat-channel/${roomId}`, onMessageReceived);

    // send request for joining current user in chatroom 
    stompClient.send(`${topic}/addUser`,
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    );
}



// send messages to server (backend)
export function sendMessageToServer(message) {
    let messageContent = message;

      // if it's just conversation message, then create response and send it to backend
    if (messageContent && stompClient) {
      var chatMessage = {
        sender: username,
        content: messageContent,
        type: 'CHAT'
      };
      // send response to backend through websocket using STOMP
      stompClient.send(`${topic}/sendMessage`, {}, JSON.stringify(chatMessage));
    }
  }


  
// handle received messages from backend
// here I will display  received message on messageBox 
export function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    curHandler(message);
    // handlers.forEach(handler => handler(JSON.parse(payload.body)))
    console.log(message);
 
}
  
