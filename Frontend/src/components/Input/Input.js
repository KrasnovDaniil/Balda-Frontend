import React from 'react';

import './Input.css';

const Input = ({sendMessage, message, saveBoth} ) => (
  <form name="inputForm" className="form">
    <input
      className="input"
      type="text"
      placeholder="Type a message..."
      value={message}
      onChange={({ target: { value } }) => (saveBoth(value))} 
      onKeyPress={event => (event.key === 'Enter' ? sendMessage(event) : null)}
      />
    <button className="sendButton" onClick={event => sendMessage(event)}>Send</button>
  </form>
)

export default Input;