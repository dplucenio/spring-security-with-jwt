import {createGlobalStyle} from 'styled-components';
import MinecraftiaRegularWoff from './resources/Minecraftia-Regular.woff'
import MinecraftiaRegularWoff2 from './resources/Minecraftia-Regular.woff2'

export default createGlobalStyle`
  * {
    margin: 0;
    padding: 0;
    outline: none;
    box-sizing: border-box;

    @font-face {
    font-family: 'Minecraftia';
    src: url(${MinecraftiaRegularWoff2}) format('woff2'),
        url(${MinecraftiaRegularWoff}) format('woff');
    font-weight: normal;
    font-style: normal;
}
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-rendering: optimizeLegibility;
    text-shadow: rgba(0,0,0,.01) 0 0 1px;
  }

  html, body, #app {
    height: 100%;
  }

  h1, h2, h3, p {
    color: #eee;
    font-family: 'Minecraftia'
  }
`;