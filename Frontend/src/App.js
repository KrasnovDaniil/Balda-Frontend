import React from 'react';

import Chat from './components/Chat/Chat';
import Join from './components/Join/Join';
import {connect} from './util/ws'

import { BrowserRouter as Router, Route } from "react-router-dom";

let stompClient = null;

const App = () => {
  connect()
  return (
    <Router>
       <Route path="/" exact component={Join} />
       <Route path="/chat" component={Chat} />
    </Router>
  );
  
}

export default App;
