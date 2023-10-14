import React, { useState, useEffect } from 'react';
import { RightPanelMode } from '../Types/RightPanelMode';
import Task from '../Types/TaskInterface';
import './ComponentStyles/RightPanel.scss';
type Props = {
  mode: RightPanelMode;
  operatingTask: Task | null;
};

const RightPanel: React.FC<Props> = ({ mode, operatingTask }): React.ReactElement => {
  return (
    <div className='right-panel'>
      <header>{mode}</header>
    </div>
  );
};
export default RightPanel;
