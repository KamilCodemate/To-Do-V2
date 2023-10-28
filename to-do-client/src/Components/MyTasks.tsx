import React, { useEffect, useState } from 'react';
import Task from '../Types/TaskInterface';

import './ComponentStyles/MyTasks.scss';
import SingleTask from './SingleTask';
import axios from 'axios';
type Props = {
  username: string;
  token: string;
};

const MyTasks: React.FC<Props> = ({ username, token }): React.ReactElement => {
  const [tasks, setTasks] = useState<Array<Task>>();

  const getAllTasks = async () => {
    const requestData = {
      username: username,
    };
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };

    try {
      const response = await axios.post('/api/userpanel/getalltasks', requestData, config);
      setTasks(response.data);
      console.log(response);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getAllTasks();
  }, []);
  return (
    <div className='my-tasks-container'>
      <header>My tasks</header>
      {tasks &&
        tasks.map((element) => (
          <SingleTask
            name={element.name}
            description={element.description}
            important={element.important}
            done={element.isDone}
            subtasks={element.subtasks}
            date={element.date}
            time={element.time}
          />
        ))}
    </div>
  );
};

export default MyTasks;
