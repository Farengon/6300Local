package gtsi.sdp.windowsos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gtsi.sdp.windowsos.models.History;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView history_list_view;
    private HistoryItemAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        history_list_view = findViewById(R.id.history_list);

        adapter = new HistoryItemAdapter(initList());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        history_list_view.setLayoutManager(manager);
        history_list_view.setAdapter(adapter);
    }

    private ArrayList<History> initList() {
        ArrayList<History> hl = new ArrayList<>();
        hl.add(new History("Classroom0", "Opening window", Boolean.TRUE));
        hl.add(new History("Classroom0", "Opening window", Boolean.FALSE));
        hl.add(new History("Classroom0", "Opening window", Boolean.TRUE));
        return hl;
    }
}
