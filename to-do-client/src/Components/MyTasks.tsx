import React from 'react';
import Task from '../Types/TaskInterface';

import './ComponentStyles/MyTasks.scss';
import SingleTask from './SingleTask';

type Props = {
  username: string;
  token: string;
  tasks: Array<Task> | undefined;
  rerenderComponent?: any;
  headerText: string;
};

const MyTasks: React.FC<Props> = ({ username, token, tasks, rerenderComponent, headerText }): React.ReactElement => {
  return (
    <div className='my-tasks-container'>
      <header>{headerText}</header>
      <div className='my-tasks-subcontainer'>
        {tasks &&
          tasks.map(
            (element, index) =>
              !element.done && (
                <SingleTask
                  key={`single-task-no${index}`}
                  id={element.id || 0}
                  name={element.name}
                  description={element.description}
                  important={element.important}
                  done={element.done}
                  subtasks={element.subtasks}
                  date={element.date}
                  startTime={element.startTime || undefined}
                  endTime={element.endTime || undefined}
                  username={username}
                  token={token}
                  rerenderComponent={rerenderComponent}
                />
              )
          )}
      </div>
    </div>
  );
};

export default MyTasks;
