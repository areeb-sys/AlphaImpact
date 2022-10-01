package wasif.fyp.smartrestaurant.Tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wasif.fyp.smartrestaurant.R;


public class ProjectsFragment extends Fragment {

    public ProjectsFragment() {
    }

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.categories_view_pager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.categories_tab_layout);
        tabLayout.removeAllTabs();
        tabLayout.setupWithViewPager(viewPager);
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

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
//        adapter.addFragment(new AwadedFragment(), "AWARDED");
//        adapter.addFragment(new InProgressFragment(), "IN PROGRESS");
        adapter.addFragment(new PendingFragment(), " In Progress");

//        adapter.addFragment(new CompletedFragment(), "COMPLETED");
        adapter.addFragment(new Canceledfragment(), "Previous");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
