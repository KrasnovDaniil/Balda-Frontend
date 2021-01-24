import React from 'react';

import './Message.css';

import ReactEmoji from 'react-emoji';


// should add timestamp
const Message = ({ message: { text, sender }, name }) => {
  let isSentByCurrentUser = false;
  let hrs = new Date().getHours();
  let mins = new Date().getMinutes();
  if (sender === name) {
    isSentByCurrentUser = true;
  }

  return (
    isSentByCurrentUser
      ? (
        <div className="messageContainer justifyEnd">
          <div>
            <p className="sentText pr-10 senderName">You</p>
            {/* <p className="sentTime pr-10">{hrs}:{mins}</p> */}
          </div>
          <div className="messageBox backgroundBlue">
            <p className="messageText colorWhite">{ReactEmoji.emojify(text)}</p>
          </div>
        </div>
        )
        : (
          <div className="messageContainer justifyStart">
            <div className="messageBox backgroundLight">
              <p className="messageText colorDark">{ReactEmoji.emojify(text)}</p>
            </div>
            <p className="sentText pl-10 ">{sender}</p>
          </div>
        )
  );
}

export default Message;