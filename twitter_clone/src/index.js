import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { GoogleOAuthProvider } from '@react-oauth/google';
import { store } from './Store/Store';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
    <Provider store={store}>
      <GoogleOAuthProvider clientId="1085287461632-r8s069iqsb8c8p4dtv95g9rd7mchoiak.apps.googleusercontent.com">
        <App />
      </GoogleOAuthProvider>
    
    </Provider>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
