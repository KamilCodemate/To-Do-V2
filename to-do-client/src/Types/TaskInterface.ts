export default interface Task {
  name: string;
  date: Date;
  subtasks: Array<{
    name: string;
    isDone: boolean;
  }>;
  isDone: boolean;
  isImportant: boolean;
}
