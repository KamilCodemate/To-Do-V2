import React, { useState, useEffect } from 'react';
import './ComponentStyles/HelloPanel.scss';
import MyTasks from './MyTasks';
import axios from 'axios';
import Task from '../Types/TaskInterface';
import CustomCalendar from './Calendar';
import { RightPanelMode } from '../Types/RightPanelMode';
import AddTaskPanel from './AddTaskPanel';
import { CalendarView } from '../Types/CalendarView';
import EditTaskPanel from './EditTaskPanel';
type Props = {
  firstName: string;
  username: string;
  token: string;
  rightPanelMode: RightPanelMode;
  handleEditTaskClicked: any;
  editTaskData: Task | null;
};

const MyDay: React.FC<Props> = ({ firstName, username, token, rightPanelMode, handleEditTaskClicked, editTaskData }): React.ReactElement => {
  const [welcomeText, setWelcomeText] = useState<string>('');
  const [formattedDate, setFormattedDate] = useState<string>('');
  const [tasks, setTasks] = useState<Array<Task>>();
  const [rerenderEmitter, callRerenderEmitter] = useState<boolean>(false);

  const rerenderHelloPanelComponent = () => {
    const actualEmitterValue = rerenderEmitter;
    callRerenderEmitter(!actualEmitterValue);
  };
  const returnRightPanelElement = (modeType: RightPanelMode): React.ReactElement => {
    switch (modeType) {
      case RightPanelMode.Calendar:
        return <CustomCalendar tasks={tasks} view={CalendarView.Week} />;
      case RightPanelMode.CreateTask:
        return <AddTaskPanel username={username} token={token} rerenderHelloComponent={rerenderHelloPanelComponent} />;
      case RightPanelMode.EditTask:
        return (
          <EditTaskPanel
            //@ts-expect-error
            id={editTaskData?.id}
            username={username}
            token={token}
            rerenderHelloComponent={rerenderHelloPanelComponent}
            editTaskName={editTaskData?.name}
            editTaskDescription={editTaskData?.description}
            editTaskDate={editTaskData?.date}
            editTaskStartTime={editTaskData?.startTime}
            editTaskEndTime={editTaskData?.endTime}
            editTaskSubtasks={editTaskData?.subtasks}
            editTaskDone={editTaskData?.done}
            editTaskImportant={editTaskData?.important}
          />
        );
      default:
        return <CustomCalendar tasks={tasks} view={CalendarView.Week} />;
    }
  };
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
      const response = await axios.post('/api/userpanel/get-tasks-from-current-date', requestData, config);
      setTasks(response.data);
      console.log(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  const retTodayDate = () => {
    const date = new Date();
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
  };

  const welcomeTextFunction = () => {
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
  };
  useEffect(() => {
    welcomeTextFunction();
    setInterval(() => {
      welcomeTextFunction();
    }, 1000);

    getAllTasks();
  }, [rerenderEmitter]);

  return (
    <div className='hello-container'>
      <div className='left-column'>
        <div className='welcome-first'>{welcomeText}</div>
        <div className='welcome-second'>{`\n${formattedDate}`}</div>
        <MyTasks
          username={username}
          token={token}
          tasks={tasks}
          rerenderComponent={rerenderHelloPanelComponent}
          headerText='Tasks For Today'
          showCompleted={false}
          handleEditTaskClicked={handleEditTaskClicked}
        />
      </div>
      {returnRightPanelElement(rightPanelMode)}
    </div>
  );
};

export default MyDay;
