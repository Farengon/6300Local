package gtsi.sdp.windowsos.models;

import java.util.ArrayList;
import java.util.List;

import gtsi.sdp.windowsos.MyApplication;

public class ClassroomManager {

    private static ClassroomManager instance;
    private List<Classroom> classroomList;

    private ClassroomManager() {
        classroomList = new ArrayList<>();
    }

    public static synchronized ClassroomManager getInstance() {
        if(instance == null) {
            instance = new ClassroomManager();
        }
        return instance;
    }

    public List<Classroom> getClassroomList() {
        return classroomList;
    }

    public void addClassroom(Classroom classroom) {
        // if one classroom's status is detected to be wrong, add it
        classroomList.add(classroom);
    }

    public void setClassroomClickable(int roomNum) {
        for(Classroom classroom : classroomList) {
            if(classroom.getRoomNumber() == roomNum) {
                classroom.setClickable(true);
                break;
            }
        }
    }

    public void initialize(MyApplication myApplication) {
        // get the classroom list from sensor
    }
}
