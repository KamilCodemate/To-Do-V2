import React, { useState, FormEvent } from 'react';
import './PageStyles/Register.scss';
import axios from 'axios';
import UserData from '../Types/UserData';
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

  const [registerData, setRegisterData] = useState<RegisterData>({
    firstName: '',
    lastName: '',
    username: '',
    password: '',
    confirmPassword: '',
  });

  const handleInput = (e: React.ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;

    setRegisterData({
      ...registerData,
      [name]: value,
    });
  };
  const registerUser = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const registerAPIObject = {
        ...registerData,
        role: 'USER',
      };
      const response: any = await axios.post('/api/register', registerAPIObject);
      console.log(response);
      if (response.data.token && localStorage) {
        const userData: UserData = {
          accessToken: response.data.token,
          firstName: registerData.firstName,
          lastName: registerData.lastName,
          username: registerData.username,
        };
        localStorage.setItem('userData', JSON.stringify(userData));
        navigate('/panel');
      } else {
        console.log(
          'Your browser does not support localstorage, which is required for the application to function properly. Plase update your browser to use this application.'
        );
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
            <h2>Register new account</h2>
          </header>
          <form className='inputs' onSubmit={registerUser}>
            <label>First name </label>
            <input type='text' name='firstName' value={registerData.firstName} onChange={handleInput} /> <br />
            <label>Last name </label>
            <input type='text' name='lastName' value={registerData.lastName} onChange={handleInput} /> <br />
            <label>Username </label>
            <input type='text' name='username' value={registerData.username} onChange={handleInput} /> <br />
            <label>Password </label>
            <input type='password' name='password' value={registerData.password} onChange={handleInput} /> <br />
            <label>Repeat password </label>
            <input type='password' name='confirmPassword' value={registerData.confirmPassword} onChange={handleInput} /> <br />
            <div className='buttons'>
              <input type='submit' value='Continue >>>' />
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
