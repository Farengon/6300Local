package gtsi.sdp.windowsos;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gtsi.sdp.windowsos.models.History;

public class RankingItemAdapter extends RecyclerView.Adapter<RankingItemAdapter.ViewHolder> {
    private List<String> ranking_list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ranking;
        private TextView nickname;
        private CardView card_view;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            ranking = (TextView) view.findViewById(R.id.ranking);
            nickname = (TextView) view.findViewById(R.id.nickname);
            card_view = (CardView) view.findViewById(R.id.cardview);
        }
    }

    public RankingItemAdapter (List<String> arg) {
        this.ranking_list = arg;
    }

    @Override
    public RankingItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ranking_item, viewGroup, false);

        RankingItemAdapter.ViewHolder holder = new RankingItemAdapter.ViewHolder(view);

        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(RankingItemAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.ranking.setText(Integer.toString(position+1));
        viewHolder.nickname.setText(ranking_list.get(position));

        if (position == 0) {
            viewHolder.card_view.setBackgroundColor(Color.parseColor("#CCCCCC"));
        }
        else if (position == 1) {
            viewHolder.card_view.setBackgroundColor(Color.parseColor("#DDDDDD"));
        }
        else if (position == 2) {
            viewHolder.card_view.setBackgroundColor(Color.parseColor("#EEEEEE"));
        }
        // TODO: Set color (highlight) for current user in the ranking list
    }

    @Override
    public int getItemCount() {
        return ranking_list.size();
    }

    public void add_item(String r) {
        ranking_list.add(0, r);
//        notifyItemInserted(0);
        notifyDataSetChanged();
    }

    public void remove() {
        ranking_list.remove(0);
//        notifyItemRemoved(0);
        notifyDataSetChanged();
    }
}
