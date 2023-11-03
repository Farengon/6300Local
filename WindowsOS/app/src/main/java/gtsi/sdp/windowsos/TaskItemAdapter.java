package gtsi.sdp.windowsos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gtsi.sdp.windowsos.models.Task;

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder> {
    private ArrayList<Task> task_list;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView classroom;
        private TextView hint;
        private TextView time;
        private ImageView cancel;
        private CardView card_view;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            classroom = (TextView) view.findViewById(R.id.classroom);
            hint = (TextView) view.findViewById(R.id.hint);
            time = (TextView) view.findViewById(R.id.time);
            cancel = view.findViewById(R.id.cancel);
            card_view = (CardView) view.findViewById(R.id.cardview);
        }
    }

    public TaskItemAdapter (ArrayList<Task> arg) {
        this.task_list = arg;
    }

    @Override
    public TaskItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_item, viewGroup, false);

        TaskItemAdapter.ViewHolder holder = new TaskItemAdapter.ViewHolder(view);

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task_list.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(TaskItemAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.classroom.setText(task_list.get(position).getClassroom());
        viewHolder.hint.setText(task_list.get(position).getHint());
        viewHolder.time.setText(task_list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return task_list.size();
    }

    public void add_item(Task t) {
        task_list.add(0, t);
//        notifyItemInserted(0);
        notifyDataSetChanged();
    }

    public void remove() {
        task_list.remove(0);
//        notifyItemRemoved(0);
        notifyDataSetChanged();
    }
}
