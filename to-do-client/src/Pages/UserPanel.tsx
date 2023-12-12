import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './PageStyles/UserPanel.scss';
import LeftPanel from '../Components/LeftPanel';
import Task from '../Types/TaskInterface';
import UserData from '../Types/UserData';

import { RightPanelMode } from '../Types/RightPanelMode';
import HelloPanel from '../Components/HelloPanel';
import { MiddlePanelMode } from '../Types/MiddlePanelMode';
import MyDay from '../Components/MyDay';
interface PanelData {
  firstName: string;
  lastName: string;
  username: string;
  tasks: Array<Task>;
}

const UserPanel: React.FC<{}> = (): React.ReactElement => {
  const navigate = useNavigate();
  const userData: UserData = JSON.parse(localStorage.getItem('userData') as string);
  const [rightPanelMode, setRightPanelMode] = useState<RightPanelMode>(RightPanelMode.Calendar);
  const [middlePanelMode, setMiddlePanelMode] = useState<MiddlePanelMode>(MiddlePanelMode.HelloPanel);

  const returnMiddlePanelByMode = (): React.ReactElement => {
    switch (middlePanelMode) {
      case MiddlePanelMode.HelloPanel:
        return (
          <HelloPanel firstName={userData.firstName} username={userData.username} token={userData.accessToken} rightPanelMode={rightPanelMode} />
        );
      case MiddlePanelMode.MyDayPanel:
        return <MyDay username={userData.username} token={userData.accessToken} />;
    }
    return <HelloPanel firstName={userData.firstName} username={userData.username} token={userData.accessToken} rightPanelMode={rightPanelMode} />;
  };

  const handleIconClick = (panelToChange: MiddlePanelMode) => {
    console.log('dziala');
    setMiddlePanelMode(panelToChange);
  };
  const handleAddTask = () => {
    console.log(rightPanelMode);
    if (rightPanelMode === RightPanelMode.Calendar || rightPanelMode === RightPanelMode.EditTask) setRightPanelMode(RightPanelMode.CreateTask);
    else setRightPanelMode(RightPanelMode.Calendar);
  };
  return (
    <div className='user-panel-container'>
      <div className='user-panel'>
        <LeftPanel handleAddTask={handleAddTask} username={userData.username} token={userData.accessToken} handleIconClick={handleIconClick} />

        <div className='right-panel'>
          {/* <TodayTasks
            username={userData.username}
            accessToken={userData.accessToken}
            taskClickHandler={handleTaskSelect}
            extended={rightPanelMode !== RightPanelMode.None}
          /> */}
          {returnMiddlePanelByMode()}
        </div>
      </div>
    </div>
  );
};

export default UserPanel;
