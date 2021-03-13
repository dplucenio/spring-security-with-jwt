import React from 'react';
import GlobalStyle from './GlobalStyle';
import Login from './pages/Login';
import Users from './pages/Users'

import { Route, Switch, useHistory } from 'react-router-dom';

export default function App() {
  const history = useHistory();
  console.log(`history is ${history}`)

  function handleSubmit(e) {
    console.log(history);
    e.preventDefault();
    // history.push("/users");
  }

  return (
    <>
      <GlobalStyle />
      <Switch>
        <Route exact path='/'>
          <h1>main</h1>
        </Route>
        <Route exact path='/login'>
          <Login handleSubmit={handleSubmit} />
        </Route>
        <Route exact path='/users' component={Users} />
      </Switch>
    </>
  );
}