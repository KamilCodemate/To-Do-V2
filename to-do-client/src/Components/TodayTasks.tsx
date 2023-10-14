import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { BsFillCalendarWeekFill } from 'react-icons/bs';
import axios from 'axios';
import Task from '../Types/TaskInterface';
type Props = {
  username: string;
  accessToken: string;
};
const TodayTasks: React.FC<Props> = ({ username, accessToken }): React.ReactElement => {
  const navigate = useNavigate();
  const [tasks, setTasks] = useState<Array<Task> | null>(null);
  const [mappedTasks, setMappedTasks] = useState();
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

    const requestPath: string = '/api/user-panel/get-tasks-from-current-date';
    try {
      const response = await axios.post(requestPath, requestData);
      console.log(response);
      setTasks([...response.data]);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getAllTasksToday();
  }, []);

  return (
    <div className='today-tasks-container'>
      {tasks &&
        tasks.map((element) => {
          return (
            <div className='singleTask'>
              <div className='first-row'>
                <h3>{element.name}</h3>
              </div>
              <div className='second-row'>
                <div className='task-date'>
                  <BsFillCalendarWeekFill /> {element.date.toString()}
                </div>
                {element.subtasks.length > 0 ? (
                  <div className='task-other'>
                    <div className='task-subtask'>
                      {element.subtasks.map((subtask) => {
                        return (
                          <div className='subtask'>
                            <h4>{subtask.name}</h4>
                          </div>
                        );
                      })}
                    </div>
                  </div>
                ) : null}
              </div>
            </div>
          );
        })}
    </div>
  );
};

export default TodayTasks;
