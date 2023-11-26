package gtsi.sdp.windowsos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import gtsi.sdp.windowsos.models.ClassroomManager;
import gtsi.sdp.windowsos.models.Task;
import gtsi.sdp.windowsos.models.TaskManager;
import gtsi.sdp.windowsos.models.TaskObserver;

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder implements TaskObserver{
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

        public void bind(Task task) {
            classroom.setText(String.valueOf("Classroom" + task.getRoomNumber()));
            hint.setText(task.getHint());
            updateCountdown(task.getCountdownMillis());
        }

        private void updateCountdown(long millisUntilFinished) {
            // Update the countdown TextView with the formatted time
            long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                    TimeUnit.MINUTES.toSeconds(minutes);
            time.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
        }

        @Override
        public void onTaskUpdated(Task task) {
            updateCountdown(task.getCountdownMillis());
        }
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
                Task cancelTask = TaskManager.getInstance().getTaskList().get(holder.getAdapterPosition());
                TaskManager.getInstance().addBadHistory(cancelTask);
                TaskManager.getInstance().getTaskList().remove(holder.getAdapterPosition());

                // make the Classroom clickable again
                ClassroomManager.getInstance().setClassroomClickable(cancelTask.getRoomNumber());

                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(TaskItemAdapter.ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Task task = TaskManager.getInstance().getTaskList().get(position);
        viewHolder.bind(task);

        // Register Observer
        task.addObserver(viewHolder);
    }

    @Override
    public int getItemCount() {
        return TaskManager.getInstance().getTaskList().size();
    }



    public void add_item(Task t) {
        TaskManager.getInstance().getTaskList().add(0, t);
//        notifyItemInserted(0);
        notifyDataSetChanged();
    }

    public void remove() {
        TaskManager.getInstance().getTaskList().remove(0);
//        notifyItemRemoved(0);
        notifyDataSetChanged();
    }
}
