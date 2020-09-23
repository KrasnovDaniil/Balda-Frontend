import React, { useState, useEffect } from "react";
import queryString from 'query-string';
import SockJS from 'sockjs-client';
import Stomp from '@stomp/stompjs';

// For now here will be commented code, because more likely I will use it in future
// for properly interacting with websockets

import Messages from '../Messages/Messages';
import InfoBar from '../InfoBar/InfoBar';
import Input from '../Input/Input';
import {sendMsg, connect} from '../../util/ws'

import './Chat.css';

const Chat = ({ location }) => {
  // here are 'react useState hooks' it's kind of a class properties but for functional components 
  const [name, setName] = useState('');
  const [room, setRoom] = useState('');
  const [users, setUsers] = useState('');

  // messageObj represents message data namely text and sender name 
  const [messageObj, setMessageObj] = useState({ text:'qwe', user:'' });
  const [message, setMessage] = useState(''); // just text of current typed message
  const [messages, setMessages] = useState([]);
  const ENDPOINT = "http://localhost:8080";

  useEffect(() => {
    const { name, room } = queryString.parse(location.search);  // parse specified data from URL to pair (tuple)
    setRoom(room); // set user name and his room
    setName(name);
    connect();
    const greetingMsg = {text: `Hello ${name}`, user: 'admin'}; // just a greeting mesage
    setMessages((messages) => [...messages, greetingMsg]); // add greeting message from admin
  }, [ENDPOINT, location.search]);
  
  // Helping method for saving message and messageObj
  // I united two setters in one method, because, as I noticed, they don't work properly separately in Input.js
  // and I don't know why may be here are some problems with asynchronous. 
  const saveBoth = (value) => {
    setMessage(value);
    setMessageObj(() =>({
      text: value,
      user: name,
    }));
  }
  
  // method of sending message
  const sendMessage = (event) => {  
    event.preventDefault();
    if (message) {
      setMessages((messages) => [...messages, messageObj]); 
      sendMsg({text: message}) 
    }
  };

  return (
    <div className="outerContainer">
      <div className="container">
        <InfoBar room={room} />
        <Messages messages={messages} name={name} />
        <Input message={message} sendMessage={sendMessage} saveBoth={saveBoth} />
      </div>
    </div>
  );
}

export default Chat;
