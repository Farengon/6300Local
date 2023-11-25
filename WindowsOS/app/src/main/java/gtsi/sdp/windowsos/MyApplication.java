package gtsi.sdp.windowsos;

import android.app.Application;

import gtsi.sdp.windowsos.models.ClassroomManager;
import gtsi.sdp.windowsos.models.TaskManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TaskManager.getInstance().initialize(this);
        ClassroomManager.getInstance().initialize(this);
    }
}
