import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './PageStyles/UserPanel.scss';
import { BsFillSunFill, BsExclamationDiamondFill } from 'react-icons/bs';
import { GrSchedule } from 'react-icons/gr';
import LeftPanel from '../Components/LeftPanel';
interface Task {
  name: string;
  date: Date;
  subtasks: Array<{
    name: string;
    isDone: boolean;
  }>;
  isDone: boolean;
  isImportant: boolean;
}

interface PanelData {
  firstName: string;
  lastName: string;
  username: string;
  tasks: Array<Task>;
}

const UserPanel: React.FC<{}> = (): React.ReactElement => {
  const navigate = useNavigate();

  const [panelData, setPanelData] = useState<PanelData>({
    firstName: 'Name',
    lastName: 'Surname',
    username: 'Username',
    tasks: [],
  });

  return (
    <div className='user-panel-container'>
      <div className='user-panel'>
        <LeftPanel />
        <div className='middle-panel'></div>
        <div className='right-panel'></div>
      </div>
    </div>
  );
};

export default UserPanel;
