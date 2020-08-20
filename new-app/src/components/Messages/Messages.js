import React from 'react';

import ScrollToBottom from 'react-scroll-to-bottom';

import Message from './Message/Message';

import './Messages.css';

//  displaying messages on screen

const Messages = ({messages, name}) => (
  <ScrollToBottom className="messages">
    {console.log(messages)}
    {messages.map((message, i) => 
      <div key={i}>
        <Message message={message} name={name} lol={name}/>
      </div>)}
  </ScrollToBottom>
);

export default Messages;