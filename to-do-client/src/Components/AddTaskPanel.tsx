import React, { useState } from 'react';
import './ComponentStyles/AddTaskPanel.scss';
import { BsPlusSquareFill } from 'react-icons/bs';
import styled from 'styled-components';
type Props = {
  username: string;
  token: string;
};
const StyledIcon = styled(BsPlusSquareFill)`
  transition: fill 0.6s;
  &:hover {
    cursor: pointer;
    fill: rgb(66, 66, 66);
  }
`;

const AddTaskPanel: React.FC<Props> = ({ username, token }): React.ReactElement => {
  const [subtasks, setSubtasks] = useState(['']);
  const [iconColor, setIconColor] = useState('rgb(23,23,23)');
  const handleSubtaskChange = (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
    const newSubtasks = [...subtasks];
    newSubtasks[index] = e.target.value;
    setSubtasks(newSubtasks);

    if (e.target.value === '' && index < newSubtasks.length - 1) {
      newSubtasks.splice(index + 1, 1);
      setSubtasks(newSubtasks);
    } else if (index === subtasks.length - 1 && e.target.value !== '') {
      setSubtasks([...newSubtasks, '']);
    }
  };

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
            <div className='add-task-section-right'>
              <header>Substeps</header>
              <div className='add-subtask'>
                {subtasks.map((subtask, index) => (
                  <div className='label-input-subtask'>
                    <label>{`${index + 1}. `}</label>
                    <input className='subtask-form' key={index} type='text' value={subtask} onChange={(e) => handleSubtaskChange(e, index)} />
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

export default AddTaskPanel;
