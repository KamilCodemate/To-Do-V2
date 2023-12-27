import React, { useState, FormEvent } from 'react';
import './PageStyles/Register.scss';

interface RegisterData {
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  confirmPassword: string;
}

const Login: React.FC<{}> = (): React.ReactElement => {
  return (
    <div className='register-container'>
      <h1>Log in Page</h1>
    </div>
  );
};

export default Login;
