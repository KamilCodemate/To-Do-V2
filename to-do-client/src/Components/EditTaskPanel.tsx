import React, { useState } from 'react';
import './ComponentStyles/AddTaskPanel.scss';
import { BsPlusSquareFill } from 'react-icons/bs';
import styled from 'styled-components';
import Task from '../Types/TaskInterface';
import axios from 'axios';

type Props = {
  username: string;
  token: string;
  rerenderHelloComponent: any;
  editTaskName?: string;
  editTaskDescription?: string;
  editTaskDate?: Date;
  editTaskStartTime?: any;
  editTaskEndTime?: any;
  editTaskSubtasks?: Array<{
    id?: number;
    name: string;
    done: boolean;
  }>;
  editTaskDone?: boolean;
  editTaskImportant?: boolean;
};

const StyledIcon = styled(BsPlusSquareFill)`
  transition: fill 0.6s;
  &:hover {
    cursor: pointer;
    fill: rgb(66, 66, 66);
  }
`;

const EditTaskPanel: React.FC<Props> = ({
  username,
  token,
  rerenderHelloComponent,
  editTaskName,
  editTaskDescription,
  editTaskDate,
  editTaskStartTime,
  editTaskEndTime,
  editTaskSubtasks,
  editTaskDone,
  editTaskImportant,
}): React.ReactElement => {
  const [iconColor, setIconColor] = useState('rgb(23,23,23)');
  const [taskData, setTaskData] = useState<Task>({
    name: editTaskName ? editTaskName : '',
    description: editTaskDescription ? editTaskDescription : '',
    date: editTaskDate ? editTaskDate : new Date(),
    startTime: editTaskStartTime ? editTaskStartTime : '',
    endTime: editTaskEndTime ? editTaskEndTime : '',
    subtasks: editTaskSubtasks ? editTaskSubtasks : [{ name: '', done: false }],
    done: editTaskDone ? editTaskDone : false,
    important: editTaskImportant ? editTaskImportant : false,
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setTaskData({
      ...taskData,
      [e.target.name]: e.target.value,
    });
    console.log(taskData);
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const requestData = {
      username: username,
      name: taskData.name,
      date: taskData.date,
      startTime: `${taskData.startTime}:00`,
      endTime: `${taskData.endTime}:00`,
      description: taskData.description,
      subtasks: taskData.subtasks.slice(0, -1),
    };
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    try {
      const response = await axios.post('/api/userpanel/editTask', requestData, config);
      rerenderHelloComponent();
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubtaskChange = (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
    const newSubtasks = [...taskData.subtasks];
    newSubtasks[index] = { name: e.target.value, done: false };
    setTaskData({ ...taskData, subtasks: newSubtasks });

    if (e.target.value === '' && index < newSubtasks.length - 1) {
      newSubtasks.splice(index + 1, 1);
      setTaskData({ ...taskData, subtasks: newSubtasks });
    } else if (index === taskData.subtasks.length - 1 && e.target.value !== '') {
      setTaskData({ ...taskData, subtasks: [...newSubtasks, { name: '', done: false }] });
    }
  };

  return (
    <div className='add-task-panel-container'>
      <header>Edit Task</header>
      <div className='add-task-area-container'>
        <div className='add-task-panel'>
          <form className='add-task-form' onSubmit={handleSubmit}>
            <div className='add-task-section-left'>
              <div className='label-input-section'>
                <label>Name: </label>
                <input type='text' name='name' className='add-task-input' onChange={handleChange} value={taskData.name} />
              </div>
              <div className='label-input-section'>
                <label>Description:</label>
                <textarea name='description' className='textarea-desc' onChange={handleChange} value={taskData.description}></textarea>
              </div>
              <div className='label-input-section'>
                <label>Date: </label>
                <input type='date' name='date' className='add-task-datetime' onChange={handleChange} value={taskData.date.toString()} />
              </div>
              <div className='label-input-section'>
                <label>Start time:</label>
                <input type='time' name='startTime' className='add-task-datetime' onChange={handleChange} value={taskData.startTime} />
              </div>
              <div className='label-input-section'>
                <label>End time:</label>
                <input type='time' name='endTime' className='add-task-datetime' onChange={handleChange} value={taskData.endTime} />
              </div>
            </div>
            <div className='add-task-section-right'>
              <header>Substeps</header>
              <div className='add-subtask'>
                {taskData.subtasks.map((subtask, index) => (
                  <div className='label-input-subtask' key={`label-input-subtask-no${index}`}>
                    <label>{`${index + 1}. `}</label>
                    <input className='subtask-form' key={index} type='text' value={subtask.name} onChange={(e) => handleSubtaskChange(e, index)} />
                  </div>
                ))}
              </div>
              <div className='add-task-submit-button-container'>
                <button type='submit'>
                  <StyledIcon
                    size={60}
                    fill={iconColor}
                    onMouseEnter={() => setIconColor('rgb(66,66,66)')}
                    onMouseLeave={() => setIconColor('rgb(23,23,23)')}
                  />
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default EditTaskPanel;
