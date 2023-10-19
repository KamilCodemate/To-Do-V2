import React, { useState, useEffect } from 'react';
import './ComponentStyles/HelloPanel.scss';
const requestPath: string = '/api/user-panel/get-tasks-from-current-date';

type Props = {
  firstName: string;
};

const HelloPanel: React.FC<Props> = ({ firstName }): React.ReactElement => {
  const [welcomeText, setWelcomeText] = useState<string>('');
  const [formattedDate, setFormattedDate] = useState<string>('');

  useEffect(() => {
    setInterval(() => {
      const actualTime = new Date();
      const actualHour = actualTime.getHours();
      const actualMinute = actualTime.getMinutes();
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
  }, []);

  return (
    <div className='hello-container'>
      <div className='welcome-text'>
        <div className='welcome-first'>{welcomeText}</div>
        <div className='welcome-second'>{`\n${formattedDate}`}</div>
      </div>
    </div>
  );
};

export default HelloPanel;
