export default interface Task {
  name: string;
  date: Date;
  time?: string;
  subtasks: Array<{
    name: string;
    isDone: boolean;
  }>;
  isDone: boolean;
  isImportant: boolean;
}
