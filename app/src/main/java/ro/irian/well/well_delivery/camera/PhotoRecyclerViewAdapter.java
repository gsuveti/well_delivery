package ro.irian.well.well_delivery.camera;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ro.irian.well.well_delivery.R;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ImageData}.
 */

public class PhotoRecyclerViewAdapter extends RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder> {

    private final List<ImageData> mValues;

    public PhotoRecyclerViewAdapter(List<ImageData> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mImageView.setImageBitmap(mValues.get(position).getBitmap());

        holder.mRemoveButton.setOnClickListener(v -> {
            mValues.remove(holder.mItem);
            this.notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView mImageView;

        @BindView(R.id.removeButton)
        FloatingActionButton mRemoveButton;

        public final View mView;
        public ImageData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

    }
}
