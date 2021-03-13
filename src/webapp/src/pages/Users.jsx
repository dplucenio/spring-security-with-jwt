import React from 'react';
import styled from 'styled-components'
import Card from '../components/Card';
import AddButton from '../components/AddButton';

const OuterContainer = styled.div`
  min-height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #121212;
`;

const InnerContainer = styled.div`
  width: 100%;
  max-width: 500px;  
  display: flex;
  flex-direction: column;
  align-items: center;
`;


export default function Users() {
  return (
    <OuterContainer>
      <InnerContainer>
        <Card name="Daniel Plucenio" username = "dplucenio@gmail.com"/>
        <Card name="Priscilla Bernardelli" username = "pbernardelli@gmail.com"/>
        <Card name="Clarice Plucenio" username = "cplucenio@gmail.com"/>
      </InnerContainer>
      <AddButton onClick={e => alert('hi')} />
    </OuterContainer>
  )
}