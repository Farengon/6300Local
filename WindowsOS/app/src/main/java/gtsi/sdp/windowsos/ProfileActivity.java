package gtsi.sdp.windowsos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import gtsi.sdp.windowsos.models.Classroom;
import gtsi.sdp.windowsos.models.TaskManager;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        LinearLayout task_button = findViewById(R.id.tasks);
        LinearLayout history_button = findViewById(R.id.history);
        LinearLayout ranking_button = findViewById(R.id.ranking);

        updateTaskAndHistoryCount();

        task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, TaskListActivity.class);

                // receive the specified classroom list sent from MainActivity
                // pass selected classroom list to TaskListActivity
                //intent.putParcelableArrayListExtra("selectedClassrooms", getIntent().getParcelableArrayListExtra("selectedClassrooms"));
                startActivity(intent);
            }
        });

        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        ranking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, RankingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTaskAndHistoryCount();
    }

    private void updateTaskAndHistoryCount() {
        // # of tasks, history
        TextView task_num = findViewById(R.id.num_tasks);
        TextView history_num = findViewById(R.id.num_history);
        task_num.setText(String.valueOf(TaskManager.getInstance().getTaskList().size()));
        history_num.setText(String.valueOf(TaskManager.getInstance().getHistoryList().size()));

        // ranking
        TextView rank = findViewById(R.id.num_ranking);
        int ranking = TaskManager.getInstance().getRank().getRanking();
        rank.setText(String.valueOf(ranking));
    }

}
