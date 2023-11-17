import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ComponentStyles/AddTaskPanel.scss';
type Props = {
  username: string;
  token: string;
};

const AddTaskPanel: React.FC<Props> = ({ username, token }): React.ReactElement => {
  return (
    <div className='add-task-panel-container'>
      <header>Add New Task</header>
      <div className='add-task-area-container'>
        <div className='add-task-panel'>
          <form className='add-task-form'>
            <div className='add-task-section-left'>
              <div className='label-input-section'>
                <label>Name: </label> <input type='text' name='name' className='add-task-input' />
              </div>
              <div className='label-input-section'>
                <label>Description:</label>
                <textarea name='description' className='textarea-desc'></textarea>
              </div>
              <div className='label-input-section'>
                <label>Date: </label>
                <input type='date' name='date' className='add-task-datetime' />
              </div>
              <div className='label-input-section'>
                <label>Start time:</label>
                <input type='time' className='add-task-datetime' />
              </div>
              <div className='label-input-section'>
                <label>End time:</label>
                <input type='time' className='add-task-datetime' />
              </div>
            </div>
            <div className='add-task-section-right'></div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddTaskPanel;
