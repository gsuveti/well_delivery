package ro.irian.well.well_delivery.view.tasks;

import android.content.Context;
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
import ro.irian.well.well_delivery.view.tasks.TaskListFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Task} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter<TaskListRecyclerViewAdapter.ViewHolder> {
    private final OnListFragmentInteractionListener mListener;
    private List<Task> mValues;
    private Context context;


    public TaskListRecyclerViewAdapter(List<Task> mValues, OnListFragmentInteractionListener mListener, Context context) {
        this.mValues = mValues;
        this.mListener = mListener;
        this.context = context;
    }

    @Override
    public TaskListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task_list_item, parent, false);
        return new TaskListRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mIdView.setText(mValues.get(position).getId());
        holder.mDestinationAddressView.setText(mValues.get(position).getPickupAddress() + " -> " + mValues.get(position).getDeliveryAddress());
        holder.mView.setOnClickListener(v -> {

            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
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
