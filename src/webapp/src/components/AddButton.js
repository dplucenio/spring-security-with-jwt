import styled from 'styled-components'
import plusIcon from '../resources/plus.png'

const PlusContainer = styled.div`
  height: 64px;
  width: 64px;
  border-radius: 8px;
  position: fixed;
  bottom: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: none;
  cursor: pointer;
  background-image: url(${plusIcon});
  background-size: cover;
  image-rendering: pixelated;

  &:hover {
    background-color: #121212 ;
  }
`;

export default PlusContainer;