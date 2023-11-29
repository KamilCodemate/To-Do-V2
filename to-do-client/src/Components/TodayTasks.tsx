import React, { useState, useEffect } from 'react';

import { BsFillCalendarWeekFill } from 'react-icons/bs';
import { AiOutlineStar, AiTwotoneStar } from 'react-icons/ai';

import axios from 'axios';
import Task from '../Types/TaskInterface';
import './ComponentStyles/TodayTasks.scss';
import { IoCheckmarkCircle, IoCheckmarkCircleOutline } from 'react-icons/io5';
import UpdateTasks from '../Types/UpdateTaskImportance';
type Props = {
  username: string;
  accessToken: string;
  taskClickHandler: any;
  extended: boolean;
};
const requestPath: string = '/api/user-panel/get-tasks-from-current-date';
const TodayTasks: React.FC<Props> = ({ username, accessToken, taskClickHandler, extended }): React.ReactElement => {
  const [tasks, setTasks] = useState<Array<Task> | null>(null);

  const getAllTasksToday = async () => {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    const day = String(currentDate.getDate()).padStart(2, '0');
    const todayFormattedDate: string = `${year}-${month}-${day}`;

    const requestData = {
      token: accessToken,
      username: username,
      date: todayFormattedDate,
    };

    try {
      const response = await axios.post(requestPath, requestData);
      console.log(response);
      setTasks([...response.data]);
    } catch (err) {
      console.log(err);
    }
  };

  const updateTasksInDb = async (taskToUpdate: Task) => {
    const requestData: UpdateTasks = {
      username: username,

      important: taskToUpdate.important,
      taskId: taskToUpdate.id || 0,
      //TODO: Fix passing token
    };
    console.log(requestData);
    try {
      const resposne = await axios.put(requestPath, requestData);
      console.log(resposne);
      getAllTasksToday();
    } catch (err) {
      console.log(err);
    }
  };

  const handleTaskClick = (taskId: number | undefined): void => {
    if (!tasks) return;

    if (!tasks) return;
    const foundTask = tasks?.find((e) => e.id === taskId);
    if (!foundTask || typeof foundTask === 'undefined') return;
    // console.log(foundTask.important);
    foundTask.important = !foundTask?.important;
    // console.log(foundTask);
    updateTasksInDb(foundTask);
  };

  useEffect(() => {
    getAllTasksToday();
  });

  return (
    <div className='today-tasks-container' style={{ width: extended ? '50%' : '75%' }}>
      <header>Today</header>
      <div className='tasks'>
        {tasks &&
          tasks.map((element) => {
            return (
              <div className='single-task'>
                <div className='left-col'>
                  <div className='first-row'>
                    <h3>
                      {element.name}{' '}
                      {element.subtasks.length > 0 ? `,<span className = 'substep-count'> ${element.subtasks.length} substeps </span>` : null}
                    </h3>
                  </div>
                  <div className='second-row'>
                    {/* <div className='task-date'>
                      <BsFillCalendarWeekFill /> {'Today'}
                      {element.time ? `, ${element.time.substring(0, 5)}` : null}
                    </div> */}
                    {element.subtasks.length > 0 ? (
                      <div className='task-other'>
                        <div className='task-subtask'>
                          {element.subtasks.map((subtask) => {
                            if (!subtask.isDone)
                              return (
                                <div className='subtask'>
                                  <h4>{subtask.name}</h4>
                                </div>
                              );
                            else return null;
                          })}
                        </div>
                      </div>
                    ) : null}
                  </div>
                </div>
                <div className='right-col'>
                  {element.important ? (
                    <AiTwotoneStar onClick={() => handleTaskClick(element.id )} style={{ color: 'yellow' }} />
                  ) : (
                    <AiOutlineStar onClick={() => handleTaskClick(element.id )} />
                  )}
                  {element.done ? <IoCheckmarkCircle /> : <IoCheckmarkCircleOutline />}
                </div>
              </div>
            );
          })}
      </div>
    </div>
  );
};

export default TodayTasks;
