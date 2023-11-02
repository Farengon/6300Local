package gtsi.sdp.windowsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView classroom_list_view;
    private ClassroomItemAdaper adapter;
    private ArrayList<String> classroom_list = new ArrayList<>();
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

        adapter = new ClassroomItemAdaper(classroom_list);
        adapter.registerAdapterDataObserver(observer);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        classroom_list_view.setLayoutManager(manager);
        classroom_list_view.setAdapter(adapter);

        Button add_button = findViewById(R.id.add_button);
        Button remove_button = findViewById(R.id.remove_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add_item("classroom" + adapter.getItemCount());
            }
        });

        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.remove();
            }
        });
    }
}