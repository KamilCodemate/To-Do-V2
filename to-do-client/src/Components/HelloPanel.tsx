import React, { useState, useEffect } from 'react';
import './ComponentStyles/HelloPanel.scss';
import MyTasks from './MyTasks';
import axios from 'axios';
import Task from '../Types/TaskInterface';
import CustomCalendar from './Calendar';
import { RightPanelMode } from '../Types/RightPanelMode';
import AddTaskPanel from './AddTaskPanel';

type Props = {
  firstName: string;
  username: string;
  token: string;
  rightPanelMode: RightPanelMode;
};

const HelloPanel: React.FC<Props> = ({ firstName, username, token, rightPanelMode }): React.ReactElement => {
  const [welcomeText, setWelcomeText] = useState<string>('');
  const [formattedDate, setFormattedDate] = useState<string>('');
  const [tasks, setTasks] = useState<Array<Task>>();

  const returnRightPanelElement = (modeType: RightPanelMode): React.ReactElement => {
    switch (modeType) {
      case RightPanelMode.Calendar:
        return <CustomCalendar tasks={tasks} />;
      case RightPanelMode.CreateTask:
        return <AddTaskPanel username={username} token={token} />;
      default:
        return <CustomCalendar tasks={tasks} />;
    }
  };
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
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    setInterval(() => {
      const actualTime = new Date();
      const actualHour = actualTime.getHours();
      const actualSecond = actualTime.getSeconds();

      const day = String(actualTime.getDate()).padStart(2, '0');
      const month = (actualTime.getMonth() + 1).toString().padStart(2, '0');
      const year = actualTime.getFullYear();
      const hour = actualTime.getHours();
      const minute = actualTime.getMinutes();
      const ampm = hour >= 12 ? 'pm' : 'am';

      let hour12 = hour % 12;
      if (hour12 === 0) hour12 = 12;

      const formattedTime = `${day}.${month}.${year} ${hour12}:${minute.toString().padStart(2, '0')}:${actualSecond
        .toString()
        .padStart(2, '0')} ${ampm}`;

      if (actualHour >= 5 && actualHour <= 12) setWelcomeText(`Good Morning, ${firstName}`);
      if (actualHour > 12 && actualHour <= 18) setWelcomeText(`Good Evening, ${firstName}`);
      if (actualHour > 18 || actualHour < 5) setWelcomeText(`Good Afternoon, ${firstName}`);

      setFormattedDate(formattedTime);
    }, 1000);

    getAllTasks();
  });

  return (
    <div className='hello-container'>
      <div className='left-column'>
        <div className='welcome-first'>{welcomeText}</div>
        <div className='welcome-second'>{`\n${formattedDate}`}</div>
        <MyTasks username={username} token={token} tasks={tasks} />
      </div>
      {returnRightPanelElement(rightPanelMode)}
    </div>
  );
};

export default HelloPanel;
