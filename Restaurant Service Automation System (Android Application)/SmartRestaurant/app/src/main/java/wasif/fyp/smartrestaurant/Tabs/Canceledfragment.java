package wasif.fyp.smartrestaurant.Tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import wasif.fyp.smartrestaurant.CanceledOrderProjectBox;
import wasif.fyp.smartrestaurant.MyApp;
import wasif.fyp.smartrestaurant.R;


public class Canceledfragment extends Fragment {
    RecyclerView mRecyclerView;
    SlimAdapter slimAdapter;
    List<CanceledOrderProjectBox> pendingOrderProjectBoxes = new ArrayList<>();

    Box<CanceledOrderProjectBox> canecledbox;
    CanceledOrderProjectBox canceledOrderProjectBox;

    public Canceledfragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canecledbox = ((MyApp) getActivity().getApplication()).getBoxStore().boxFor(CanceledOrderProjectBox.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_canceledfragment, container, false);


        mRecyclerView = view.findViewById(R.id.recycler_view);
        pendingOrderProjectBoxes = canecledbox.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        slimAdapter = SlimAdapter.create()
                .register(R.layout.categories_item_pending, new SlimInjector<CanceledOrderProjectBox>() {
                    @Override
                    public void onInject(final CanceledOrderProjectBox data, IViewInjector injector) {
                        injector.text(R.id.category_name, data.getName() + " ")
                                ;


                    }
                })

                .attachTo(mRecyclerView).updateData(pendingOrderProjectBoxes);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
