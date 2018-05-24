package ro.irian.well.well_delivery.view.tasks;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.domain.Task;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {

    private List<Task> mValues;
    private Context context;

    public TaskRecyclerViewAdapter(List<Task> mValues, Context context) {
        this.mValues = mValues;
        this.context = context;
    }

    @Override
    public TaskRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_item, parent, false);
        return new TaskRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mIdView.setText(mValues.get(position).getId());
        holder.mDestinationAddressView.setText(mValues.get(position).getPickupAddress() + " -> " + mValues.get(position).getDeliveryAddress());
        holder.mView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TaskDetailActivity.class);
            intent.putExtra("taskID", holder.mItem.getId());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return mValues != null ? mValues.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public Task mItem;

        @BindView(R.id.taskID)
        TextView mIdView;

        @BindView(R.id.taskDestinationAddress)
        TextView mDestinationAddressView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}

