import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './PageStyles/UserPanel.scss';
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

  const [panelData, setPanelData] = useState<PanelData>();

  return (
    <div className='user-panel'>
      <div className='left-panel'>
        <div className='hello'>
          <h1>Hello, {panelData?.firstName}</h1>
        </div>
      </div>
    </div>
  );
};

export default UserPanel;
