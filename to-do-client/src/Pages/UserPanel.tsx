import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './PageStyles/UserPanel.scss';
import LeftPanel from '../Components/LeftPanel';
import Task from '../Types/TaskInterface';
import UserData from '../Types/UserData';
import TodayTasks from '../Components/TodayTasks';

import { RightPanelMode } from '../Types/RightPanelMode';
import HelloPanel from '../Components/HelloPanel';
interface PanelData {
  firstName: string;
  lastName: string;
  username: string;
  tasks: Array<Task>;
}

const UserPanel: React.FC<{}> = (): React.ReactElement => {
  const navigate = useNavigate();
  const userData: UserData = JSON.parse(localStorage.getItem('userData') as string);
  const [rightPanelMode, setRightPanelMode] = useState<RightPanelMode>(RightPanelMode.None);

  const [selectedTask, setSelectedTask] = useState<Task | null>(null);

  const handleTaskSelect: any = (taskData: Task) => {
    setSelectedTask(taskData);
  };

  const handleAddTask = () => {
    if (rightPanelMode === RightPanelMode.None || rightPanelMode === RightPanelMode.SeeTaskDetails) setRightPanelMode(RightPanelMode.CreateTask);
    else setRightPanelMode(RightPanelMode.None);
  };
  return (
    <div className='user-panel-container'>
      <div className='user-panel'>
        <LeftPanel handleAddTask={handleAddTask} username={userData.username} token={userData.accessToken} />
        <div className='middle-panel'>
          {/* <TodayTasks
            username={userData.username}
            accessToken={userData.accessToken}
            taskClickHandler={handleTaskSelect}
            extended={rightPanelMode !== RightPanelMode.None}
          /> */}
          <HelloPanel firstName={userData.firstName} username={userData.username} token={userData.accessToken} />
        </div>
      </div>
    </div>
  );
};

export default UserPanel;
