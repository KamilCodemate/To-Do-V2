import React, { useState, FormEvent, ReactEventHandler } from 'react';
import './PageStyles/Register.scss';
import axios from 'axios';
import LogInData from '../Types/LogInData';
import { useNavigate } from 'react-router-dom';

interface RegisterData {
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  confirmPassword: string;
}

const Register: React.FC<{}> = (): React.ReactElement => {
  const navigate = useNavigate();

  const [loginData, setLoginData] = useState<LogInData>({
    username: '',
    password: '',
  });

  const handleInput = (e: React.ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;

    setLoginData({
      ...loginData,
      [name]: value,
    });
  };

  const loginUser = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const requestData = {
      username: loginData.username,
      password: loginData.password,
    };
    try {
      const response: any = await axios.post('/api/userpanel/login', requestData);
      const data = response.data;
      if (data) {
        localStorage.removeItem('userData');
        localStorage.setItem(
          'userData',
          JSON.stringify({ accessToken: data.token, firstName: data.user.firstName, lastName: data.user.lastName, username: data.user.username })
        );
        navigate('/panel');
      }
    } catch (err) {
      console.log(err);
    }
  };
  return (
    <div className='register-container'>
      <div className='register-area'>
        <div className='input-area'>
          <header>
            <h2>Log in to your account</h2>
          </header>
          <form className='inputs' onSubmit={(e) => loginUser(e)}>
            <label>Username </label>
            <input type='text' name='username' value={loginData.username} onChange={handleInput} /> <br />
            <label>Password </label>
            <input type='password' name='password' value={loginData.password} onChange={handleInput} /> <br />
            <div className='buttons'>
              <input type='submit' value='Continue >>>' />
            </div>
          </form>
          <span className='redirectInfo'>
            You dont have an account yet?{' '}
            <span
              className='redirectLink'
              onClick={() => {
                navigate('/register');
              }}
            >
              {' '}
              Register new account
            </span>{' '}
          </span>
        </div>

        <div className='photo-area'>
          <div className='afterglow'></div>
        </div>
      </div>
    </div>
  );
};

export default Register;
