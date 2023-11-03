package gtsi.sdp.windowsos;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gtsi.sdp.windowsos.models.Task;

public class TaskListActivity extends AppCompatActivity {

    private RecyclerView task_list_view;
    private TaskItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        task_list_view = findViewById(R.id.task_list);

        adapter = new TaskItemAdapter(initList());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        task_list_view.setLayoutManager(manager);
        task_list_view.setAdapter(adapter);
    }

    private ArrayList<Task> initList() {
        ArrayList<Task> tl = new ArrayList<Task>();
        tl.add(new Task("classroom0", "window opening", "05:00"));
        tl.add(new Task("classroom1", "window opening", "05:00"));
        tl.add(new Task("classroom2", "window opening", "05:00"));
        return tl;
    }
}
