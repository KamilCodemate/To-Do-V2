import React, { useState } from 'react';
import { AiFillCalendar } from 'react-icons/ai';
import { HiOutlinePencilSquare } from 'react-icons/hi2';
import { FaTasks } from 'react-icons/fa';
import { IoIosCheckmarkCircleOutline } from 'react-icons/io';
import { IoCheckmarkCircleSharp } from 'react-icons/io5';
import { AiOutlineStar, AiFillStar } from 'react-icons/ai';
import axios from 'axios';
import UpdateTaskImportance from '../Types/UpdateTaskImportance';
import UpdateTaskCompletion from '../Types/UpdateTaskCompletion';

const requestURL_update_importance = '/api/user-panel/updatetaskimportance';
const requestURL_update_completion = '/api/user-panel/updatetaskcompletion';
type Props = {
  id: number;
  name: string;
  description?: string;
  important: boolean;
  done: boolean;
  date: Date;
  time?: string;
  subtasks: Array<{
    name: string;
    isDone: boolean;
  }>;
  username: string;
  token: string;
};

const SingleTask: React.FC<Props> = ({ id, name, description, important, done, subtasks, date, time, username, token }): React.ReactElement => {
  const [showSubtasks, setShowSubtasks] = useState<boolean>(false);
  const [taskImportance, setTaskImportance] = useState<boolean>(important);
  const [taskDone, setTaskDone] = useState<boolean>(done);
  const retDoneCount = (subtasksCopy: Array<{ name: String; isDone: boolean }>) => {
    return subtasksCopy.reduce(function (count, element) {
      if (element.isDone) return ++count;
      else return count;
    }, 0);
  };

  const updateImportance = async () => {
    const requestData: UpdateTaskImportance = {
      username: username,
      important: !important,
      taskId: id,
    };

    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    try {
      const response = await axios.put(requestURL_update_importance, requestData, config);
      console.log(response);

      setTaskImportance(!taskImportance);
    } catch (err) {
      console.log(err);
    }
  };
  const updateCompletion = async () => {
    const requestData: UpdateTaskCompletion = {
      username: username,
      done: !taskDone,
      taskId: id,
    };
    console.log(requestData);
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    try {
      const response = await axios.put(requestURL_update_completion, requestData, config);
      console.log(response);
      setTaskDone(!taskDone);
    } catch (err) {
      console.log(err);
    }
  };
  return (
    <div className='task'>
      <div className='main-col'>
        <div className='name-col'>{name}</div>
        <div className='date-col'>
          <AiFillCalendar size={20} /> Until:
          {`  ${new Date(date).toLocaleDateString('pl-PL', { year: 'numeric', month: '2-digit', day: '2-digit' })}${
            time ? `, ${time.substring(0, 5)}` : ''
          }`}
        </div>
        <div className='desc-col'>
          <HiOutlinePencilSquare size={20} /> {description ? `Description: ${description}` : 'No description provided '}
        </div>
        <div className='subtask-col'>
          <FaTasks size={20} />{' '}
          {subtasks && subtasks.length > 0 ? ` Subtasks: ${retDoneCount(subtasks)} out of ${subtasks.length} done ` : ' No subtasks provided'}{' '}
          {subtasks.length > 0 && subtasks ? (
            <span
              className='show-subtasks'
              onClick={() => {
                setShowSubtasks(!showSubtasks);
              }}
            >
              {showSubtasks ? '[hide]' : '[show]'}
            </span>
          ) : (
            ''
          )}
        </div>
        {showSubtasks && (
          <div className='subtasks'>
            <ul>
              {subtasks.map((element, index) => {
                return (
                  <li key={`subtaskNO${index}`} style={{ textDecorationLine: element.isDone ? 'line-through' : 'none' }}>
                    {`${index + 1}. ${element.name}`}
                    <IoIosCheckmarkCircleOutline size={20} />
                  </li>
                );
              })}
            </ul>
          </div>
        )}
      </div>
      <div className='icons'>
        {taskDone ? (
          <IoCheckmarkCircleSharp size={25} className='checkmark filled' onClick={() => updateCompletion()} />
        ) : (
          <IoIosCheckmarkCircleOutline size={25} className='checkmark not-filled' onClick={() => updateCompletion()} />
        )}
        {taskImportance ? (
          <AiFillStar size={25} className='importance-star filled' onClick={() => updateImportance()} color={'yellow'} />
        ) : (
          <AiOutlineStar size={25} className='importance-star not-filled' onClick={() => updateImportance()} />
        )}
      </div>
    </div>
  );
};

export default SingleTask;
