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
            <label>First name </label>
            <input type='text' /> <br />
            <label>Last name </label>
            <input type='text' /> <br />
            <label>Username </label>
            <input type='text' /> <br />
            <label>Password </label>
            <input type='password' /> <br />
            <label>Repeat password </label>
            <input type='password' /> <br />
            <div className='buttons'>
              <input type='submit' value='Continue >>>' />
            </div>
          </form>
          <div className='haveAnAcc'>
            <h2>Already have and account? Log in</h2>
          </div>
        </div>

        <div className='photo-area'>
          <div className='afterglow'></div>
        </div>
      </div>
    </div>
  );
};

export default Register;
