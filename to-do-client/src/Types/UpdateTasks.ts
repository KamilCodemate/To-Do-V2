import Task from './TaskInterface';

export default interface UpdateTasks {
  username: string;
  token: string;
  important: boolean;
  taskId: number;
}
