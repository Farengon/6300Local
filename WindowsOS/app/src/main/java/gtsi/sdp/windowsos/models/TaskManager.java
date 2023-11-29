package gtsi.sdp.windowsos.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import gtsi.sdp.windowsos.MyApplication;

public class TaskManager {
    private static TaskManager instance;
    private List<Task> taskList;
    private List<History> historyList;
    private Rank rank;
    private List<Rank> rankingList;

    private TaskManager() {
        rank = new Rank("Ava", 0, 1);
        taskList = new ArrayList<>();
        historyList = new ArrayList<>();
        rankingList = new ArrayList<>();
        initHistory(historyList);
        initRankingList(rankingList);

        rank = getRank();
        rankingList.add(rank);
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
    public Rank getRank() {
        rank.setScore(rank.calculateScore(numBadHistory(), numGoodHistory()));
        calculateRankings(rankingList);
        return rank;
    }

    public List<Rank> getRankingList() { return rankingList; }


    // Task Operation
    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) { taskList.remove(task); }

    // History Operation

    public void addGoodHistory(Task task) {
        // if task is completed in predefined time
        historyList.add(new History("Classroom " + task.getRoomNumber(), task.getHint(), true));
    }

    public void addBadHistory(Task task) {
        // if task not finished in predefined time, or cancel by user
        historyList.add(new History("Classroom " + task.getRoomNumber(), task.getHint(), false));
    }

    private int numGoodHistory() {
        int i = 0;
        for(History history : historyList) {
            if (history.getDone() == true) {
                i++;
            }
        }
        return i;
    }

    private int numBadHistory() {
        int i = 0;
        for(History history : historyList) {
            if(history.getDone() == false) { i++; }
        }
        return i;
    }

    private static void initHistory(List<History> historyList) {
        historyList.add(new History("Classroom 408", "window not close", true));
        historyList.add(new History("Classroom 401", "window not close", true));
        historyList.add(new History("Classroom 406", "window not close", true));
        historyList.add(new History("Classroom 407", "window not close", true));
        historyList.add(new History("Classroom 412", "window not close", false));
        historyList.add(new History("Classroom 405", "window not close", true));
        historyList.add(new History("Classroom 408", "window not close", true));
        historyList.add(new History("Classroom 402", "window not close", true));
    }

    // Ranking

    public static void calculateRankings(List<Rank> rankingList) {
        // Apply Comparator and Collections.sort() to sort rankingList
        Collections.sort(rankingList, Comparator.comparingDouble(Rank::getScore).reversed());

        // update ranking
        for (int i = 0; i < rankingList.size(); i++) {
            rankingList.get(i).setRanking(i + 1);
        }
    }

    private static void initRankingList(List<Rank> rankingList) {
        rankingList.add(new Rank("Emma", 3, 1));
        rankingList.add(new Rank("Liam", 6, 2));
        rankingList.add(new Rank("Olivia", 0, 1));
        rankingList.add(new Rank("Noah", 1, 2));
        rankingList.add(new Rank("Sophia", 7, 1));
        rankingList.add(new Rank("Jackson", 4, 2));

    }

    public void initialize(MyApplication myApplication) {
        // get the task & history list from db
    }

}
