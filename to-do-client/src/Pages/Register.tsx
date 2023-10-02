import React, { useState, useEffect } from 'react';
import './PageStyles/Register.scss';
import img1 from '../images/register-photo.jpg';
type inputHandleStateType = Record<string, string>;

const Register: React.FC<{}> = (): React.ReactElement => {
  const [registerData, setRegisterData] = useState<inputHandleStateType>({});

  return (
    <div className='register-container'>
      <div className='register-area'>
        <div className='input-area'>
          <header>
            <h2>Register new account</h2>
          </header>
          <form className='inputs'>
            <input type='text' placeholder='First name...' /> <br />
            <input type='text' placeholder='Last name...' /> <br />
            <input type='text' placeholder='Username...' /> <br />
            <input type='password' placeholder='Password...' /> <br />
            <input type='password' placeholder='Repeat password...' /> <br />
            <div className='buttons'>
              <input type='submit' value='Register' />
              <input type='reset' value='Clear' />
            </div>
          </form>
        </div>
        <div className='photo-area'>
          <div className='afterglow'></div>
        </div>
      </div>
    </div>
  );
};

export default Register;
