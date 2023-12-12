import React, { useState, useEffect } from 'react';
import './ComponentStyles/HelloPanel.scss';
import axios from 'axios';
import Task from '../Types/TaskInterface';
import MyTasks from './MyTasks';

type Props = {
  username: string;
  token: string;
};

const MyDay: React.FC<Props> = ({ username, token }): React.ReactElement => {
  const [tasks, setTasks] = useState<Array<Task>>();
  const [rerenderEmitter, callRerenderEmitter] = useState<boolean>(false);
  const retTodayDate = () => {
    const date = new Date();
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
  };
  const rerenderMyDayComponent = () => {
    const actualEmitterValue = rerenderEmitter;
    callRerenderEmitter(!actualEmitterValue);
  };
  useEffect(() => {
    getAllTasks();
  }, []);

  const getAllTasks = async () => {
    const requestData = {
      username: username,
      date: retTodayDate(),
    };
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };

    try {
      const response = await axios.post('/api/user-panel/get-tasks-from-current-date', requestData, config);
      setTasks(response.data);
      console.log(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className='my-day-container'>
      <MyTasks username={username} token={token} tasks={tasks} rerenderComponent={rerenderMyDayComponent} />
    </div>
  );
};

export default MyDay;
