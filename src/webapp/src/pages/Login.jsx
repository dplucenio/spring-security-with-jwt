import React, { useEffect, useState } from 'react';
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
  let [loginLabel, setLoginLabel] = useState('login');
  let { handleSubmit: doHandleSubmit } = props;

  useEffect(() => {
    if (status === LOGGING_IN) {
      let id = setInterval(() => {
        setLoginLabel(loginLabel.slice(-3) === '...' ? 'login' : loginLabel + '.');
      }, 400);
      return () => { clearInterval(id); };
    }

  }, [status, loginLabel])

  let handleUsernameChange = e => {
    setUsername(e.target.value);
    console.log(username);
  }

  let handlePassword = e => {
    setPassword(e.target.value);
  }

  let handleSubmit = e => {
    setStatus(LOGGING_IN);
    setLoginLabel('login.')
    doHandleSubmit(e);
  }

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <input type="text" name="" id="" placeholder="username" value={username} onChange={handleUsernameChange} />
        <input type="password" name="" id="" placeholder="password" value={password} onChange={handlePassword} />
        <input type="submit" value={loginLabel} />
      </Form>
    </Container>
  );
}

export default Login;
