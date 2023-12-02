export default interface Task {
  id?: number;
  name: string;
  description?: string;
  date: Date;
  startTime?: string;
  endTime?: string;
  subtasks: Array<{
    id?: number;
    name: string;
    done: boolean;
  }>;
  done: boolean;
  important: boolean;
}
