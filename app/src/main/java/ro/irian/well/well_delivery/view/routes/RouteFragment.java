package ro.irian.well.well_delivery.view.routes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.domain.Route;
import ro.irian.well.well_delivery.viewmodel.RouteViewModel;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class RouteFragment extends Fragment implements Injectable {

    private static final String TAG = "RouteFragment";
    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    RouteViewModel routeViewModel;

    @Inject
    EventBus eventbus;

    @Inject
    SharedPreferences sharedPreferences;

    private OnListFragmentInteractionListener mListener;
    private RouteRecyclerViewAdapter mAdapter;
    private List<Route> routes = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RouteFragment() {
    }

    public static RouteFragment newInstance(int columnCount) {
        RouteFragment fragment = new RouteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Observer<List<Route>> routeObserver = routeList -> {
            if (routeList != null) {
                routes.clear();
                routes.addAll(routeList);
                mAdapter.notifyDataSetChanged();
            }
        };

        String uid = sharedPreferences.getString("uid", null);

        routeViewModel = ViewModelProviders.of(this, viewModelFactory).get(RouteViewModel.class);
        routeViewModel.getRouteLiveData(uid).observe(this, routeObserver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route_list, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new RouteRecyclerViewAdapter(routes, mListener);

        Context context = view.getContext();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Route item);
    }
}
