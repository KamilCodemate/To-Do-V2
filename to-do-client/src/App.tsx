import React from 'react';
import Register from './Pages/Register';
import UserPanel from './Pages/UserPanel';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <div className='App'>
      <BrowserRouter>
        <Routes>
          <Route path='/register' element={<Register />} />
          <Route path='/panel' element={<UserPanel />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
