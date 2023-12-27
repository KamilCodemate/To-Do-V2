import React from 'react';
import Register from './Pages/Register';
import UserPanel from './Pages/UserPanel';
import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Login from './Pages/Login';

function App() {
  return (
    <div className='App'>
      <BrowserRouter>
        <Routes>
          <Route path='/register' element={<Register />} />
          <Route path='/panel' element={<UserPanel />} />
          <Route path='/login' element={<Login />} />
          <Route path='*' element={<Navigate to='/panel' />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
