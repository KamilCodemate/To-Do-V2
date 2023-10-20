import React from 'react';

type Props = {
  name: String;
  description: String;
  important: boolean;
  done: boolean;
  date: Date;
  subtasks: Array<{
    name: String;
    isDone: boolean;
  }>;
};

const SingleTask: React.FC<Props> = ({ name, description, important, done, subtasks, date }): React.ReactElement => {
  return (
    <div className='task'>
      <div className='main-col'>
        <div className='name-col'>{name}</div>
        <div className='date-col'>{}</div>
      </div>
    </div>
  );
};

export default SingleTask;
