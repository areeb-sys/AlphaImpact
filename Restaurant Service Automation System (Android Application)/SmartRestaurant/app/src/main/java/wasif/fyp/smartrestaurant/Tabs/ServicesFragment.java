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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import wasif.fyp.smartrestaurant.R;


public class ServicesFragment extends Fragment {

    public ServicesFragment() {
    }

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    List<Category> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);
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
        adapter.addFragment(new ItCategoryFragment(), "Starters");
        adapter.addFragment(new HomeCategoryFragment(), "Fast Food");
        adapter.addFragment(new EventCategoryFragment(), "Soups");
        adapter.addFragment(new ElectricalCategoryFragment(), "Bar B.Q");
        adapter.addFragment(new CoachingCategoryFragment(), "Deserts");
        adapter.addFragment(new BuisnessCategoryFragment(), "Burger");
        adapter.addFragment(new BeautyCategoryFragment(), "Shwarma");
        adapter.addFragment(new AdvertismentCategoryFragment(), "Rice");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(8);

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


    public class AllCategoriesModel {

        @SerializedName("error")
        @Expose
        private Boolean error;
        @SerializedName("categories")
        @Expose
        private List<Category> categories = new ArrayList<>();

        public Boolean getError() {
            return error;
        }

        public void setError(Boolean error) {
            this.error = error;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

    }

    public class Category {

        @SerializedName("categoryID")
        @Expose
        private Integer categoryID;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;

        public Integer getCategoryID() {
            return categoryID;
        }

        public void setCategoryID(Integer categoryID) {
            this.categoryID = categoryID;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

    }

}
