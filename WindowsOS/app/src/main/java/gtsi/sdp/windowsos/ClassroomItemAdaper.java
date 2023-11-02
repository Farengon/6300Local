package gtsi.sdp.windowsos;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClassroomItemAdaper extends RecyclerView.Adapter<ClassroomItemAdaper.ViewHolder> {
    private ArrayList<String> classroom_list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView classroom;
        private TextView reminder;
        private CardView card_view;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            classroom = (TextView) view.findViewById(R.id.classroom);
            reminder = (TextView) view.findViewById(R.id.reminder);
            card_view = (CardView) view.findViewById(R.id.cardview);
        }
    }

    public ClassroomItemAdaper (ArrayList<String> arg) {
        this.classroom_list = arg;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.classroom_state_item, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.classroom.setText(classroom_list.get(position));
    }

    @Override
    public int getItemCount() {
        return classroom_list.size();
    }

    public void add_item(String classroom) {
        classroom_list.add(0, classroom);
//        notifyItemInserted(0);
        notifyDataSetChanged();
    }

    public void remove() {
        classroom_list.remove(0);
//        notifyItemRemoved(0);
        notifyDataSetChanged();
    }
}
