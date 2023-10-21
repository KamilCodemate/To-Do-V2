import React, { useState } from 'react';
import { AiFillCalendar } from 'react-icons/ai';
import { HiOutlinePencilSquare } from 'react-icons/hi2';
import { FaTasks, FaGreaterThan } from 'react-icons/fa';
import Task from '../Types/TaskInterface';
type Props = {
  name: String;
  description?: String;
  important: boolean;
  done: boolean;
  date: Date;
  time?: String;
  subtasks: Array<{
    name: String;
    isDone: boolean;
  }>;
};

const SingleTask: React.FC<Props> = ({ name, description, important, done, subtasks, date, time }): React.ReactElement => {
  const [showSubtasks, setShowSubtasks] = useState<boolean>(false);

  const retDoneCount = (subtasksCopy: Array<{ name: String; isDone: boolean }>) => {
    return subtasksCopy.reduce(function (count, element) {
      if (element.isDone) return ++count;
      else return count;
    }, 0);
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
                    {element.name}
                  </li>
                );
              })}
            </ul>
          </div>
        )}
      </div>
    </div>
  );
};

export default SingleTask;
