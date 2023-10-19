import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './PageStyles/UserPanel.scss';
import LeftPanel from '../Components/LeftPanel';
import Task from '../Types/TaskInterface';
import UserData from '../Types/UserData';
import TodayTasks from '../Components/TodayTasks';
import RightPanel from '../Components/RightPanel';
import { RightPanelMode } from '../Types/RightPanelMode';
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
  const [panelData, setPanelData] = useState<PanelData>({
    firstName: 'Name',
    lastName: 'Surname',
    username: 'Username',
    tasks: [],
  });

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
        <LeftPanel extended={rightPanelMode !== RightPanelMode.None} handleAddTask={handleAddTask} />

        <TodayTasks
          username={userData.username}
          accessToken={userData.accessToken}
          taskClickHandler={handleTaskSelect}
          extended={rightPanelMode !== RightPanelMode.None}
        />

        {rightPanelMode !== RightPanelMode.None && <RightPanel mode={rightPanelMode} operatingTask={selectedTask} />}
      </div>
    </div>
  );
};

export default UserPanel;
