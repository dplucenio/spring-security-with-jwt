import React from 'react';
import styled from 'styled-components'
import userICon from '../resources/user.png'


const CardContainer = styled.div`
  width:100%;
  padding: 0.2rem 0.5rem;
  box-shadow: 0px 4px 6px 2px rgba(0,0,0,0.4);
  border-radius: 0.5rem;
  border: 2px solid #FFF;
  cursor: pointer;
  display: flex;
  align-items: center;

  &+& {
    margin-top: 0.5rem;
  }

  &:hover {
    background-color: #eee;
    transition: background-color 300ms ease-out;
    h3, p {
      color: #121212;
      transition: color 500ms ease-out;
    }
  }
`;

const UserImage = styled.div`
  height: 64px;
  width: 64px;
  border-radius: 64px;
  background-image: url(${userICon});
  background-size: cover;
  image-rendering: pixelated;
  margin: 0.4rem 0;
  border: 2px solid #eeeeee;
`;

const UserDetails = styled.div`
  display: flex;  
  justify-content: center;
  margin: 0.4rem 1rem;
  flex-direction: column;  
  flex: 1;
`;

function Card(props) {
  return (
    <CardContainer>
    <UserImage />
    <UserDetails>
      <h3>{props.name}</h3>
      <p>{props.username}</p>
    </UserDetails>
  </CardContainer>
  );
}

export default Card;