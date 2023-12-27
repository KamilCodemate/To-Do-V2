import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import Task from '../Types/TaskInterface';
import { CalendarView } from '../Types/CalendarView';

type Props = {
  tasks: Array<Task> | undefined;
  view: CalendarView;
};
const CustomCalendar: React.FC<Props> = ({ tasks, view }): React.ReactElement => {
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
        defaultView={view}
      />
    </div>
  );
};

export default CustomCalendar;
