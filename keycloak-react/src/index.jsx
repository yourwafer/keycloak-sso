import React from 'react';
import { render } from 'react-dom';
import LoginComponent from './components/LoginComponent';

const Login = () => <LoginComponent />;

render(<Login />, document.getElementById('app'));
