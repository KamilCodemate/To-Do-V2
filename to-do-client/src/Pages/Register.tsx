import React, { useState, useEffect } from 'react';
import './PageStyles/Register.scss';
import img1 from '../images/register-photo.jpg';
type inputHandleStateType = Record<string, string>;

const Register: React.FC<{}> = (): React.ReactElement => {
  const [registerData, setRegisterData] = useState<inputHandleStateType>({});

  return (
    <div className='register-container'>
      <div className='register-area'>
        <div className='input-area'></div>
        <div className='photo-area'>
          <div className='afterglow'></div>
        </div>
      </div>
    </div>
  );
};

export default Register;
