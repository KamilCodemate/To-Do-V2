import React, { useState, useEffect } from 'react';

import { BsFillSunFill, BsExclamationDiamondFill, BsPlusSquareFill } from 'react-icons/bs';
import { GrSchedule } from 'react-icons/gr';
import { IoCheckmarkDoneSharp } from 'react-icons/io5';
import { AiFillHome } from 'react-icons/ai';
type Props = {
  handleAddTask: () => void;
};

const LeftPanel: React.FC<Props> = ({ handleAddTask }): React.ReactElement => {
  return (
    <div className='left-panel'>
      <div className='side-nav-menu-container'>
        <div className='side-nav-menu'>
          <ul>
            <li className='firstLi'>
              <AiFillHome size={45} color='black' />
            </li>
            <li>
              <BsFillSunFill size={30} color='orange' />
            </li>
            <li>
              <BsExclamationDiamondFill size={30} color='brown' />
            </li>
            <li>
              <GrSchedule size={30} color='blueviolet' />
            </li>
            <li>
              <IoCheckmarkDoneSharp size={30} color='blueviolet' />
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
