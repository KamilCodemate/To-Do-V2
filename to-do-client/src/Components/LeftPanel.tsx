import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { BsFillSunFill, BsExclamationDiamondFill } from 'react-icons/bs';
import { GrSchedule } from 'react-icons/gr';
import { IoCheckmarkDoneSharp } from 'react-icons/io5';
import axios from 'axios';
import Task from '../Types/TaskInterface';

const LeftPanel: React.FC<{}> = (): React.ReactElement => {
  return (
    <div className='left-panel'>
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
        </div>
      </div>
    </div>
  );
};

export default LeftPanel;
