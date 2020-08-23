import React, { useState, useEffect } from "react";
import queryString from 'query-string';
import io from "socket.io-client";

import TextContainer from '../TextContainer/TextContainer';
import Messages from '../Messages/Messages';
import InfoBar from '../InfoBar/InfoBar';
import Input from '../Input/Input';

import './Chat.css';

let socket;


const Chat = ({ location }) => {
  const [name, setName] = useState('');
  const [room, setRoom] = useState('');
  const [text, setText] = useState('');
  const [users, setUsers] = useState('');
  const [messageObj, setMessageObj] = useState({ text:'qwe', user:'' });
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState([]);
  // const ENDPOINT = 'https://project-chat-application.herokuapp.com/';

  const setterM = (msg) => {
    setMessages((messages) => [...messages, msg]);    
  };

  useEffect(() => {
    const { name, room } = queryString.parse(location.search);
    // console.log(name); // works
    // socket = io(ENDPOINT);
    
    setRoom(room);
    setName(name);
    const greetingMsg = {text: `Hello ${name}`, user: 'admin'};
    setterM(greetingMsg);

    // socket.emit('join', { name, room }, (error) => {
      // if(error) {
        // alert(error);
      // }
    // });
  },[]);
  // }, [ENDPOINT, location.search]);

  useEffect(() => {
    // socket.on('message', message => {
    // setMessages(messages => [ ...messages, message ]);
    // });

    // socket.on("roomData", ({ users }) => {
    //   setUsers(users);
    // });
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
      // console.log(message);
      // console.log('before ' + messageObj.text);
      // setMessageObj((messageObj) =>({
      //   ...messageObj,
      //   user: name,
      // }));
      setterM(messageObj);
      // socket.emit('sendMessage', message, () => setMessage(''));
      // console.log('after ' + messageObj.text);
      // console.log(messageObj);
      // console.log(messages);
      setMessage(()=>'')
    }
  };

  return (
    <div className="outerContainer">
      <div className="container">
        {/* {console.log("Asdasd")} */}
        <InfoBar room={room} />
        <Messages messages={messages} name={name} />
        <Input message={message} setMessage={setMessage} sendMessage={sendMessage} saveBoth={saveBoth} />
      </div>
      {/* <TextContainer users={users} /> */}
    </div>
  );
}

export default Chat;
