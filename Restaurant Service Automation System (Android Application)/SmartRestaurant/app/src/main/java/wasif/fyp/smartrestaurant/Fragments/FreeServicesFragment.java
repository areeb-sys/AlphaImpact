package wasif.fyp.smartrestaurant.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;
import wasif.fyp.smartrestaurant.R;


public class FreeServicesFragment extends Fragment {

    public FreeServicesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_free_services, container, false);



        BannerSlider bannerSlider = view.findViewById(R.id.banner10);
        


        List<Banner> banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.bn1));
        banners.add(new DrawableBanner(R.drawable.bn2));
        banners.add(new DrawableBanner(R.drawable.bn3));
        bannerSlider.setBanners(banners);


        return  view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
     }

    @Override
    public void onDetach() {
        super.onDetach();
     }
 }
