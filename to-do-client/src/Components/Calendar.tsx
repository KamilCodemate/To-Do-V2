import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import Task from '../Types/TaskInterface';

type Props = {
  tasks: Array<Task> | undefined;
};
const CustomCalendar: React.FC<Props> = ({ tasks }): React.ReactElement => {
  const localizer = momentLocalizer(moment);
  return (
    <div className='calendar-container'>
      <header> My Calendar</header>
      <Calendar
        localizer={localizer}
        events={tasks?.map((task) => ({
          title: task.name,
          start: task.startTime ? new Date(`${task.date}T${task.startTime}`) : new Date(task.date),
          end: task.endTime ? new Date(`${task.date}T${task.endTime}`) : new Date(task.date),
        }))}
        startAccessor='start'
        endAccessor='end'
      />
    </div>
  );
};

export default CustomCalendar;
