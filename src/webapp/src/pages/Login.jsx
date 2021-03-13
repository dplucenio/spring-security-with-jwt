import React, { useState } from 'react';
import styled from 'styled-components';

const Container = styled.div`
  min-height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #121212;
`;

const Form = styled.form`
  width: 100%;
  max-width: 200px;
  display: flex;
  flex-direction: column;
  padding: 1.2rem 1rem 1.6rem 1rem;
  border-radius: 0.5rem;

  input {
    padding: 0.4rem;
    margin-bottom: 0.2rem;
    font-family: 'Minecraftia';
    color: #eee;
    outline: none;
    box-shadow: none;
    border: none;
    font-size: 1rem;
    text-align: center;
    cursor: pointer;
    border: 2px solid #eee;
    border-radius: 0.5rem;
    background-color: #121212;
  }

  input[type="submit"]:hover {
    background-color: #eee;
    color: #121212;
    transition: background-color 200ms ease, color 200ms ease;
  }
`;

const IDLE = 'idle';
const LOGGING_IN = 'loggingIn';

function Login(props) {
  let [status, setStatus] = useState(IDLE);
  let [username, setUsername] = useState('');
  let [password, setPassword] = useState('');
  let {handleSubmit: doHandleSubmit} = props;

  let handleUsernameChange = e => {
    setUsername(e.target.value);
    console.log(username);
  }

  let handlePassword = e => {
    setPassword(e.target.value);
    console.log(password);
  }

  let handleSubmit = e => {
    setStatus(LOGGING_IN);
    doHandleSubmit(e);
  }

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <input type="text" name="" id="" placeholder="username" value={username} onChange={handleUsernameChange} />
        <input type="password" name="" id="" placeholder="password" value={password} onChange={handlePassword} />
        <input type="submit" value={status===IDLE ?"login":"loggingIn"}/>
      </Form>
    </Container>
  );
}

export default Login;
