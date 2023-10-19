export default interface Task {
  id: number;
  name: string;
  date: Date;
  time?: string;
  subtasks: Array<{
    name: string;
    isDone: boolean;
  }>;
  isDone: boolean;
  important: boolean;
}