package gtsi.sdp.windowsos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gtsi.sdp.windowsos.models.History;
import gtsi.sdp.windowsos.models.TaskManager;

public class RankingActivity extends AppCompatActivity {
    private RecyclerView ranking_list_view;
    private RankingItemAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        ranking_list_view = findViewById(R.id.ranking_list);

        adapter = new RankingItemAdapter(initList());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        ranking_list_view.setLayoutManager(manager);
        ranking_list_view.setAdapter(adapter);
    }

    private ArrayList<String> initList() {
        ArrayList<String> rl = new ArrayList<>();
        rl.add("Place Holder");
        rl.add("Second!");
        rl.add("...");
        rl.add("NULL");
        rl.add("!@#$%^&*");
        return rl;
    }
}
