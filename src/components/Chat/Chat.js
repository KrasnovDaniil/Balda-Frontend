import React, { useState, useEffect } from "react";
import queryString from 'query-string';
import SockJS from 'sockjs-client';
import Stomp from '@stomp/stompjs';

// For now here will be commented code, because more likely I will use it in future
// for properly interacting with websockets

import Messages from '../Messages/Messages';
import InfoBar from '../InfoBar/InfoBar';
import Input from '../Input/Input';
import {sendMessageToServer, connect, setMessageHandler, addHandler} from '../../util/ws';

import './Chat.css';

export const Chat = ({ location }) => {
  // here are 'react useState hooks' it's kind of a class properties but for functional components 
  const [name, setName] = useState('');
  const [room, setRoom] = useState('');
  const [users, setUsers] = useState('');

  // messageObj represents message data namely text and sender name 
  const [messageObj, setMessageObj] = useState({ text:'qwe', sender:'' });
  const [message, setMessage] = useState(''); // just text of current typed message
  const [messages, setMessages] = useState([]);
  const ENDPOINT = "http://localhost:8080";

  useEffect(() => {
    const { name, room } = queryString.parse(location.search);  // parse specified data from URL to pair (tuple)
    setRoom(room); // set user name and his room
    setName(name);
    connect(name, room); // connect to WebSocket
    // const greetingMsg = {text: `Hello ${name}`, sender: 'admin'}; // just a greeting mesage

    // displayMessages();

    addHandler( curMessage => {
      if (curMessage.content === null) return;
      setMessages(messages => [...messages, {text:curMessage.content, sender:curMessage.sender}]);     
      console.log(messages);
    }); 

  }, [ENDPOINT, location.search]);


  function displayMessages(actual_messages){

  }


  // method of sending message
  function sendMessage(event) {  
    event.preventDefault();
    let calc = message.match(/[\s]*/); // check message on non-space text
    if (message && calc[0] != message) {
      sendMessageToServer(message);
      setMessage('');
    }
  };


  return (
    <div className="outerContainer">
      <div className="container">
        <InfoBar room={room} />
        <Messages messages={messages} name={name} />
        <Input message={message} sendMessage={sendMessage} saveBoth={setMessage} />
        
      </div>
    </div>
  );
}

export default Chat;
