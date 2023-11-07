import React, { useState, useEffect } from 'react';
import './ComponentStyles/HelloPanel.scss';
import MyTasks from './MyTasks';
import axios from 'axios';
import Task from '../Types/TaskInterface';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';

type Props = {
  firstName: string;
  username: string;
  token: string;
};

const HelloPanel: React.FC<Props> = ({ firstName, username, token }): React.ReactElement => {
  const [welcomeText, setWelcomeText] = useState<string>('');
  const [formattedDate, setFormattedDate] = useState<string>('');
  const [tasks, setTasks] = useState<Array<Task>>();

  const localizer = momentLocalizer(moment);

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

      console.log(actualHour);
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
      <div className='calendar-container'>
        <header> My Calendar</header>
        <Calendar
          localizer={localizer}
          events={tasks?.map((task) => ({
            title: task.name,
            start: task.startTime ? new Date(`${task.date}T${task.startTime}`) : new Date(task.date),
            end: task.endTime ? new Date(`${task.date}T${task.endTime}`) : new Date(task.date),
          }))}
          startAccessor='start'
          endAccessor='end'
        />
      </div>
    </div>
  );
};

export default HelloPanel;
