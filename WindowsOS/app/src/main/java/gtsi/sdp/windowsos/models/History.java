package gtsi.sdp.windowsos.models;

public class History {
    private String classroom;
    private String hint;
    private Boolean done;

    public History(String classroom, String hint, Boolean done) {
        this.classroom = classroom;
        this.hint = hint;
        this.done = done;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getHint() {
        return hint;
    }

    public Boolean getDone() {
        return done;
    }
}
