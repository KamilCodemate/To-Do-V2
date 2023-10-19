import React, { useState, useEffect } from 'react';

import { BsFillSunFill, BsExclamationDiamondFill, BsPlusSquareFill } from 'react-icons/bs';
import { GrSchedule } from 'react-icons/gr';
import { IoCheckmarkDoneSharp } from 'react-icons/io5';

type Props = {
  extended: boolean;
  handleAddTask: () => void;
};

const LeftPanel: React.FC<Props> = ({ extended, handleAddTask }): React.ReactElement => {
  return (
    <div className='left-panel' style={{ width: extended ? '20%' : '35%' }}>
      <header>Menu</header>

      <div className='side-nav-menu-container'>
        <div className='side-nav-menu'>
          <ul>
            <li>
              <span className='liFirst'>
                <BsFillSunFill size={22} /> My Day
              </span>
              <div className='liSecond'>5</div>
            </li>
            <li>
              <span className='liFirst'>
                <BsExclamationDiamondFill size={22} />
                Important
              </span>
              <div className='liSecond'>5</div>
            </li>
            <li>
              <span className='liFirst'>
                <GrSchedule size={22} />
                Scheduled
              </span>
              <div className='liSecond'>5</div>
            </li>
            <li>
              <span className='liFirst'>
                <IoCheckmarkDoneSharp size={22} />
                Completed
              </span>
              <div className='liSecond'>5</div>
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
