package gtsi.sdp.windowsos.models;

import java.util.ArrayList;
import java.util.List;

import gtsi.sdp.windowsos.MyApplication;

public class TaskManager {
    private static TaskManager instance;
    private List<Task> taskList;
    private List<History> historyList;

    private TaskManager() {
        taskList = new ArrayList<>();
        historyList = new ArrayList<>();
    }

    public static synchronized TaskManager getInstance() {
        if(instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public List<Task> getTaskList() {
        return taskList;
    }
    public List<History> getHistoryList() { return historyList; }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) { taskList.remove(task); }

    public void addGoodHistory(Task task) {
        // if task is completed in defined time
        historyList.add(new History("Classroom" + task.getRoomNumber(), task.getHint(), true));
    }

    public void addBadHistory(Task task) {
        // if task not finished in defined time, or cancel by user
        historyList.add(new History("Classroom" + task.getRoomNumber(), task.getHint(), false));
    }

    public void initialize(MyApplication myApplication) {
        // get the task & history list from db
    }
}
