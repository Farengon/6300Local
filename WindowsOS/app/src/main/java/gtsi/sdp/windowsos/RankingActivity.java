package gtsi.sdp.windowsos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import gtsi.sdp.windowsos.models.Rank;
import gtsi.sdp.windowsos.models.TaskManager;

public class RankingActivity extends AppCompatActivity {
    private RecyclerView ranking_list_view;
    private RankingItemAdapter adapter;
    private List<Rank> rankingList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ranking_list_view = findViewById(R.id.ranking_list);

        // get current rankingList
        rankingList = TaskManager.getInstance().getRankingList();
        TaskManager.calculateRankings(rankingList);
        adapter = new RankingItemAdapter(rankingList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        ranking_list_view.setLayoutManager(manager);
        ranking_list_view.setAdapter(adapter);
    }


}
