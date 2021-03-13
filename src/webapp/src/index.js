import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import dotenv from 'dotenv';
import { BrowserRouter as Router } from 'react-router-dom';

dotenv.config();

ReactDOM.render(
  // Router component is used here, because if its is used in the same component
  // where useHistory is sued we get undefined history.
  <Router>  
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </Router>,
  document.getElementById('app')
);