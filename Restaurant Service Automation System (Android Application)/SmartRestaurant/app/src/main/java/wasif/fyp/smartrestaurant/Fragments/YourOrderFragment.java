package wasif.fyp.smartrestaurant.Fragments;

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

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;
import wasif.fyp.smartrestaurant.R;


public class YourOrderFragment extends Fragment {

    public YourOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ArrayList<ItemObject> itemObjects = new ArrayList<>();

    SlimAdapter slimAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_order, container, false);


        BannerSlider bannerSlider = view.findViewById(R.id.banner10);


        List<Banner> banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.bn1));
        banners.add(new DrawableBanner(R.drawable.bn2));
        banners.add(new DrawableBanner(R.drawable.bn3));
        bannerSlider.setBanners(banners);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        RecyclerView rView1 = (RecyclerView) view.findViewById(R.id.recycler_view01);
        rView1.setHasFixedSize(false);
        rView1.setLayoutManager(linearLayoutManager);
        ItemObject itemObject = new ItemObject("Nido", "250 PKR", "Name");
        itemObjects.add(itemObject);
        itemObject = new ItemObject("Nido", "250 PKR", "Name");
        itemObjects.add(itemObject);
        itemObject = new ItemObject("Nido", "250 PKR", "Name");
        itemObjects.add(itemObject);
        itemObject = new ItemObject("Nido", "250 PKR", "Name");
        itemObjects.add(itemObject);
        itemObject = new ItemObject("Nido", "250 PKR", "Name");
        itemObjects.add(itemObject);
        itemObject = new ItemObject("Nido", "250 PKR", "Name");
        itemObjects.add(itemObject);
        itemObject = new ItemObject("Nido", "250 PKR", "Name");
        itemObjects.add(itemObject);
        itemObject = new ItemObject("Nido", "250 PKR", "Name");
        itemObjects.add(itemObject);
        itemObject = new ItemObject("Nido", "250 PKR", "Name");
        itemObjects.add(itemObject);
        slimAdapter = SlimAdapter.create()
                .register(R.layout.categories_item_special, new SlimInjector<ItemObject>() {
                    @Override
                    public void onInject(final ItemObject data, IViewInjector injector) {

                    }
                })

                .attachTo(rView1).updateData(itemObjects);


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
