import React, { useState, useEffect } from "react";
import queryString from 'query-string';

import Messages from '../Messages/Messages';
import InfoBar from '../InfoBar/InfoBar';
import Input from '../Input/Input';

import './Chat.css';


const Chat = ({ location }) => {
  const [name, setName] = useState('');
  const [room, setRoom] = useState('');
  const [messageObj, setMessageObj] = useState({ text:'qwe', user:'' });
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState([]);

  const setterM = (msg) => {
    setMessages((messages) => [...messages, msg]);    
  };

  useEffect(() => {
    const { name, room } = queryString.parse(location.search);    
    setRoom(room);
    setName(name);
    const greetingMsg = {text: `Hello ${name}`, user: 'admin'};
    setterM(greetingMsg);

  },[]);

  useEffect(() => {
  }, []);

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
      setterM(messageObj);
      setMessage(()=>'')
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
