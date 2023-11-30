package gtsi.sdp.windowsos;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gtsi.sdp.windowsos.models.Classroom;
import gtsi.sdp.windowsos.models.ClassroomManager;
import gtsi.sdp.windowsos.models.Task;
import gtsi.sdp.windowsos.models.TaskManager;

public class ClassroomItemAdapter extends RecyclerView.Adapter<ClassroomItemAdapter.ViewHolder> {
    private View.OnClickListener itemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView roomNumberTextView;
        private TextView hintTextView;
        private CardView card_view;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            roomNumberTextView = view.findViewById(R.id.classroom);
            hintTextView = view.findViewById(R.id.reminder);
            card_view = (CardView) view.findViewById(R.id.cardview);
        }

        public void bind(Classroom classroom, View.OnClickListener clickListener) {
            roomNumberTextView.setText("Classroom " + String.valueOf(classroom.getRoomNumber()));
            hintTextView.setText(classroom.getHint());
            itemView.setClickable(classroom.getIsClickable());

            if (classroom.getIsClickable()) {
                itemView.setOnClickListener(clickListener);
                itemView.setBackgroundColor(Color.WHITE);
            } else {
                itemView.setOnClickListener(null);
                itemView.setBackgroundColor(Color.GRAY);
            }
        }
    }

    public ClassroomItemAdapter() {

        // Initialize click listener
        itemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TaskManager.getInstance().getTaskList().size() > 2) {
                    Toast.makeText(view.getContext(), "You are only allowed to have a maximum of three tasks at the same time", Toast.LENGTH_SHORT).show();
                } else {
                    int position = ((ViewHolder) view.getTag()).getAdapterPosition();
                    Classroom clickedClassroom = ClassroomManager.getInstance().getClassroomList().get(position);

                    if (clickedClassroom.getIsClickable()) {
                        // Create new task, add it to the task list
                        Task newTask = new Task(clickedClassroom.getRoomNumber(), clickedClassroom.getHint());
                        TaskManager.getInstance().addTask(newTask);

                        // clicked classroom is not allowed to be clicked again
                        clickedClassroom.setClickable(false);
                        notifyItemChanged(position);
                }

                }
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.classroom_state_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Classroom classroom = ClassroomManager.getInstance().getClassroomList().get(position);
        viewHolder.bind(classroom, itemClickListener);
        // Set the tag to ViewHolder for later retrieval in the click listener
        viewHolder.itemView.setTag(viewHolder);
    }

    @Override
    public int getItemCount() {
        return ClassroomManager.getInstance().getClassroomList().size();
    }

    public void add_item(Classroom classroom) {
        ClassroomManager.getInstance().getClassroomList().add(classroom);
//        notifyItemInserted(0);
        notifyDataSetChanged();
    }

//    public void remove() {
//        classroom_list.remove(0);
////        notifyItemRemoved(0);
//        notifyDataSetChanged();
//    }

    public void remove(int classroom_number, String hint) {
        for (int i = 0; i < ClassroomManager.getInstance().getClassroomList().size(); i++) {
            Classroom c = ClassroomManager.getInstance().getClassroomList().get(i);
            if (c.getRoomNumber() == classroom_number && c.getHint().equals(hint)) {
                ClassroomManager.getInstance().getClassroomList().remove(i);
                notifyDataSetChanged();
                break;
            }
        }
    }

    public boolean inList(int classroom_number, String hint) {
        for (Classroom c: ClassroomManager.getInstance().getClassroomList()) {
            if (c.getRoomNumber() == classroom_number && c.getHint().equals(hint)) {
                return true;
            }
        }
        return false;
    }
}
