import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { BsFillSunFill, BsExclamationDiamondFill, BsPlusSquareFill } from 'react-icons/bs';
import { GrSchedule } from 'react-icons/gr';
import { IoCheckmarkDoneSharp } from 'react-icons/io5';
import { AiFillHome } from 'react-icons/ai';
type Props = {
  username: string;
  token: string;
};

const AddTaskPanel: React.FC<Props> = ({ username, token }): React.ReactElement => {
  return (
    <div className='add-task-panel-container'>
      <div className='add-task-panel'></div>
    </div>
  );
};

export default AddTaskPanel;
