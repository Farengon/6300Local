package gtsi.sdp.windowsos.models;

import java.sql.Time;

public class Task {
    private String classroom;
    private String hint;
    private String time; // should be Time

    public Task(String classroom, String hint, String time) {
        this.classroom = classroom;
        this.hint = hint;
        this.time = time;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getHint() {
        return hint;
    }

    public String getTime() {
        return time;
    }
}
