package ro.irian.well.well_delivery.view.tasks;

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

    public TaskRecyclerViewAdapter(List<Task> mValues) {
        this.mValues = mValues;
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
//            if (null != mListener) {
//                 Notify the active callbacks interface (the activity, if the
//                 fragment is attached to one) that an item has been selected.
//                mListener.onListFragmentInteraction(holder.mItem);
//            }
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

