import React, { useState, useEffect, MouseEventHandler } from 'react';
import axios from 'axios';
import { BsFillSunFill, BsExclamationDiamondFill, BsPlusSquareFill } from 'react-icons/bs';
import { GrSchedule } from 'react-icons/gr';
import { IoCheckmarkDoneSharp } from 'react-icons/io5';
import { AiFillHome } from 'react-icons/ai';
import { MiddlePanelMode } from '../Types/MiddlePanelMode';
type Props = {
  username: string;
  token: string;
  handleAddTask: () => void;
  handleIconClick: (panelToChange: MiddlePanelMode) => void;
};

const LeftPanel: React.FC<Props> = ({ handleAddTask, username, token, handleIconClick }): React.ReactElement => {
  const getAllTasks = async () => {
    const requestData = {
      username: username,
      token: token,
    };
    try {
      const response = await axios.post('/api/userpanel/getalltasks');
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className='left-panel'>
      <div className='side-nav-menu-container'>
        <div className='side-nav-menu'>
          <ul>
            <li className='firstLi'>
              <AiFillHome size={45} color='black' onClick={() => handleIconClick(MiddlePanelMode.HelloPanel)} />
            </li>
            <li>
              <BsFillSunFill size={30} color='orange' onClick={() => handleIconClick(MiddlePanelMode.MyDayPanel)} />
            </li>
            <li>
              <BsExclamationDiamondFill size={30} color='brown' onClick={() => handleIconClick(MiddlePanelMode.ImportantPanel)} />
            </li>
            <li>
              <GrSchedule size={30} color='blueviolet' />
            </li>
            <li>
              <IoCheckmarkDoneSharp size={30} color='blueviolet' onClick={() => handleIconClick(MiddlePanelMode.CompletedPanel)} />
            </li>
          </ul>
          <div className='add-task-container'>
            <BsPlusSquareFill size={60} color={'#171717'} onClick={handleAddTask} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default LeftPanel;
