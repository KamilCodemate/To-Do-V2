export default interface Task {
  id: number;
  name: string;
  description?: string;
  date: Date;
  startTime?: string;
  endTime?: string;
  subtasks: Array<{
    name: string;
    isDone: boolean;
  }>;
  done: boolean;
  important: boolean;
}
