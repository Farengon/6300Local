package gtsi.sdp.windowsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import gtsi.sdp.windowsos.models.Classroom;
import gtsi.sdp.windowsos.models.ClassroomManager;
import gtsi.sdp.windowsos.models.Task;

public class MainActivity extends AppCompatActivity {

    private RecyclerView classroom_list_view;
    private ClassroomItemAdapter adapter;
    private ImageView icon_view;

    private RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            if (adapter.getItemCount() == 0) {
                icon_view.setImageResource(R.drawable.all_done);
            }
            else {
                icon_view.setImageResource(R.drawable.reminder);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classroom_list_view = findViewById(R.id.classroom_list);
        icon_view = findViewById(R.id.icon_state);

        adapter = new ClassroomItemAdapter();
        adapter.registerAdapterDataObserver(observer);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        classroom_list_view.setLayoutManager(manager);
        classroom_list_view.setAdapter(adapter);

        Button add_button = findViewById(R.id.add_button);
        FloatingActionButton profile_button = findViewById(R.id.user_profile);

        // if use sensor, change code below.
        // Do not delete
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Classroom classroom = new Classroom(1 + adapter.getItemCount(), "window not close", true);
                classroom.setCompleted(false);
                adapter.add_item(classroom);
            }
        });

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data or perform any other actions needed when the activity is resumed
        adapter.notifyDataSetChanged();
    }
}