package gtsi.sdp.windowsos.models;

public class Classroom{
    private int roomNumber;
    private String hint;
    private boolean isClickable;
    private boolean isCompleted;

    public Classroom(int roomNumber, String hint, boolean isClickable) {
        this.roomNumber = roomNumber;
        this.hint = hint;
        this.isClickable = isClickable;
        this.isCompleted = false;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getHint() { return hint; }
    public boolean getIsClickable() { return isClickable; }
    public boolean getIsCompleted() { return isCompleted; }

    public void setClickable(boolean isClickable) { this.isClickable = isClickable; }
    public void setCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

}
