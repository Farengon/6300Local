package gtsi.sdp.windowsos.models;

import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.List;

public class Task{
    private int roomNumber;
    private String hint;
    private long countdownMillis; // Store remaining countdown time
    private CountDownTimer countDownTimer;

    private boolean isCompleted;
    private List<TaskObserver> observers = new ArrayList<>();

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (TaskObserver observer : observers) {
            observer.onTaskUpdated(this);
        }
    }

    public Task(int roomNumber, String hint) {
        this.roomNumber = roomNumber;
        this.hint = hint;
        this.countdownMillis = 2 * 60 * 1000; // 2 min
        this.isCompleted = false;
//        this is for test
//        this.countdownMillis = 10000; // 10s
        startCountdown();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getHint() {
        return hint;
    }

    public long getCountdownMillis() {
        return countdownMillis;
    }

    public void setCountdownMillis(long countdownMillis) {
        this.countdownMillis = countdownMillis;
    }

    public void startCountdown() {
        countDownTimer = new CountDownTimer(countdownMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownMillis = millisUntilFinished;
                // Notify observers
                // or update UI if needed
                notifyObservers();
            }

            @Override
            public void onFinish() {
                // Countdown finished, perform any required actions
                countdownMillis = 0;
                removeTask();
                setClassroomClickable();
                addBadHistory();
                // Notify observers
                // or update UI if needed
                notifyObservers();
            }
        }.start();
    }

    private void removeTask() {
        TaskManager.getInstance().removeTask(this);
    }

    private void setClassroomClickable() {
        ClassroomManager.getInstance().setClassroomClickable(this.getRoomNumber());
    }

    private void addBadHistory() {
        TaskManager.getInstance().addBadHistory(this);
    }

}
