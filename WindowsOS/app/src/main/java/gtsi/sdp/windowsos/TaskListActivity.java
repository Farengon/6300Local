package gtsi.sdp.windowsos;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gtsi.sdp.windowsos.models.Task;
import gtsi.sdp.windowsos.models.TaskManager;

public class TaskListActivity extends AppCompatActivity {

    private RecyclerView task_list_view;
    private TaskItemAdapter adapter;
    private TextView noTasksTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        task_list_view = findViewById(R.id.task_list);
        noTasksTextView = findViewById(R.id.no_tasks_text_view);
        adapter = new TaskItemAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        task_list_view.setLayoutManager(manager);
        task_list_view.setAdapter(adapter);

        updateNoTasksVisibility();
    }

    // Add this method to update visibility of "No tasks" text view
    private void updateNoTasksVisibility() {
        if (TaskManager.getInstance().getTaskList().size() == 0) {
            noTasksTextView.setVisibility(View.VISIBLE);
            task_list_view.setVisibility(View.GONE);
        } else {
            noTasksTextView.setVisibility(View.GONE);
            task_list_view.setVisibility(View.VISIBLE);
        }
    }

    private ArrayList<Task> initList() {
        //get data from database
        //add newly added tasks



        ArrayList<Task> tl = new ArrayList<Task>();
//        tl.add(new Task("classroom0", "window opening", "05:00"));
//        tl.add(new Task("classroom1", "window opening", "05:00"));
//        tl.add(new Task("classroom2", "window opening", "05:00"));


        return tl;
    }
}
