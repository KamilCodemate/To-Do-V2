import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './PageStyles/UserPanel.scss';
import LeftPanel from '../Components/LeftPanel';
import UserData from '../Types/UserData';
import axios from 'axios';
import { RightPanelMode } from '../Types/RightPanelMode';
import HelloPanel from '../Components/HelloPanel';
import { MiddlePanelMode } from '../Types/MiddlePanelMode';
import MyDay from '../Components/MyDay';
import ImportantTasks from '../Components/ImportantTasks';
import CompletedTasks from '../Components/CompletedTasks';
import Task from '../Types/TaskInterface';

const UserPanel: React.FC<{}> = (): React.ReactElement => {
  const navigate = useNavigate();
  const userData: UserData = JSON.parse(localStorage.getItem('userData') as string) || {};
  const [rightPanelMode, setRightPanelMode] = useState<RightPanelMode>(RightPanelMode.Calendar);
  const [middlePanelMode, setMiddlePanelMode] = useState<MiddlePanelMode>(MiddlePanelMode.HelloPanel);
  const [editTaskDataState, setEditTaskDataState] = useState<Task>();

  const handleEditTaskClicked = (editTaskData: Task) => {
    setEditTaskDataState(editTaskData);
    setRightPanelMode(RightPanelMode.EditTask);
  };
  const getAllTasks = async () => {
    const userData = JSON.parse(localStorage.getItem('userData') as string) || {};
    const [username, token] = [userData.username || null, userData.accessToken || null];

    if (!(username && token)) navigate('/login');
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

      if (response.status !== 200) navigate('/register');
      console.log(response);
    } catch (err) {
      console.log(err);
      navigate('/register');
    }
  };
  useEffect(() => {
    getAllTasks();
  });
  const returnMiddlePanelByMode = (): React.ReactElement => {
    switch (middlePanelMode) {
      case MiddlePanelMode.HelloPanel:
        return (
          <HelloPanel
            firstName={userData.firstName}
            username={userData.username}
            token={userData.accessToken}
            rightPanelMode={rightPanelMode}
            editTaskData={editTaskDataState ? editTaskDataState : null}
            handleEditTaskClicked={handleEditTaskClicked}
          />
        );
      case MiddlePanelMode.MyDayPanel:
        return (
          <MyDay
            firstName={userData.firstName}
            username={userData.username}
            token={userData.accessToken}
            rightPanelMode={rightPanelMode}
            handleEditTaskClicked={handleEditTaskClicked}
            editTaskData={editTaskDataState ? editTaskDataState : null}
          />
        );

      case MiddlePanelMode.ImportantPanel:
        return (
          <ImportantTasks
            firstName={userData.firstName}
            username={userData.username}
            token={userData.accessToken}
            rightPanelMode={rightPanelMode}
            handleEditTaskClicked={handleEditTaskClicked}
            editTaskData={editTaskDataState ? editTaskDataState : null}
          />
        );
      case MiddlePanelMode.CompletedPanel:
        return (
          <CompletedTasks
            firstName={userData.firstName}
            username={userData.username}
            token={userData.accessToken}
            rightPanelMode={rightPanelMode}
            handleEditTaskClicked={handleEditTaskClicked}
            editTaskData={editTaskDataState ? editTaskDataState : null}
          />
        );
    }
    return (
      <HelloPanel
        firstName={userData.firstName}
        username={userData.username}
        token={userData.accessToken}
        rightPanelMode={rightPanelMode}
        editTaskData={editTaskDataState ? editTaskDataState : null}
        handleEditTaskClicked={(editTaskData: Task) => handleEditTaskClicked(editTaskData)}
      />
    );
  };

  const handleIconClick = (panelToChange: MiddlePanelMode) => {
    setMiddlePanelMode(panelToChange);
  };
  const handleAddTask = () => {
    if (rightPanelMode === RightPanelMode.Calendar || rightPanelMode === RightPanelMode.EditTask) setRightPanelMode(RightPanelMode.CreateTask);
    else setRightPanelMode(RightPanelMode.Calendar);
  };
  return (
    <div className='user-panel-container'>
      <div className='user-panel'>
        <LeftPanel handleAddTask={handleAddTask} username={userData.username} token={userData.accessToken} handleIconClick={handleIconClick} />

        <div className='right-panel'>{returnMiddlePanelByMode()}</div>
      </div>
    </div>
  );
};

export default UserPanel;
