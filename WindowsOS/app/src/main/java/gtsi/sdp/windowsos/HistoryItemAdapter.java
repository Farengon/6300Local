package gtsi.sdp.windowsos;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gtsi.sdp.windowsos.models.History;

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.ViewHolder> {
    private List<History> history_list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView classroom;
        private TextView hint;
        private CardView card_view;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            classroom = (TextView) view.findViewById(R.id.classroom);
            hint = (TextView) view.findViewById(R.id.hint);
            card_view = (CardView) view.findViewById(R.id.cardview);
        }
    }

    public HistoryItemAdapter (List<History> arg) {
        Collections.reverse(arg);
        this.history_list = arg;
    }

    @Override
    public HistoryItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_item, viewGroup, false);

        HistoryItemAdapter.ViewHolder holder = new HistoryItemAdapter.ViewHolder(view);

        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(HistoryItemAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.classroom.setText(history_list.get(position).getClassroom());
        viewHolder.hint.setText(history_list.get(position).getHint());
        if (history_list.get(position).getDone() == Boolean.TRUE) {
            viewHolder.card_view.setBackgroundColor(Color.parseColor("#56DF56"));
        }
        else {
            viewHolder.card_view.setBackgroundColor(Color.parseColor("#E4571E"));
        }
    }

    @Override
    public int getItemCount() {
        return history_list.size();
    }

    public void add_item(History t) {
        history_list.add(0, t);
//        notifyItemInserted(0);
        notifyDataSetChanged();
    }

    public void remove() {
        history_list.remove(0);
//        notifyItemRemoved(0);
        notifyDataSetChanged();
    }
}
