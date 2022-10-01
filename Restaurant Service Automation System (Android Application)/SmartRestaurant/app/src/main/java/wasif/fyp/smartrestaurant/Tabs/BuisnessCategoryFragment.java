package wasif.fyp.smartrestaurant.Tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wasif.fyp.smartrestaurant.ApiInterface;
import wasif.fyp.smartrestaurant.R;
import wasif.fyp.smartrestaurant.SignInActivity;

import static wasif.fyp.smartrestaurant.IntroLocationActivity.PREF_ORDERID;


public class BuisnessCategoryFragment extends Fragment {
    List<ItCategoryFragment.FoodItem> arrayList = new ArrayList<>();
    RecyclerView mRecyclerView;
    ElegantNumberButton button;

    SlimAdapter slimAdapter;
    Integer quantity;
    SharedPreferences mSharedPreferences;

    public BuisnessCategoryFragment() {
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
        View view = inflater.inflate(R.layout.fragment_buisness_category, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mSharedPreferences = getActivity().getSharedPreferences(SignInActivity.PREFERENCE, Context.MODE_PRIVATE);

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody id_res = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody id_a = RequestBody.create(MediaType.parse("text/plain"), "8");

        ApiInterface service1 = retrofit1.create(ApiInterface.class);
        Call<ItCategoryFragment.AllFoodModel> user1 = service1.getcategories(id_res, id_a);
        user1.enqueue(new Callback<ItCategoryFragment.AllFoodModel>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(@NonNull Call<ItCategoryFragment.AllFoodModel> call, @NonNull Response<ItCategoryFragment.AllFoodModel> response) {

                if (response.isSuccessful()) {
                    arrayList = response.body().getFoodItems();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    slimAdapter = SlimAdapter.create()
                            .register(R.layout.categories_item_services, new SlimInjector<ItCategoryFragment.FoodItem>() {
                                @Override
                                public void onInject(final ItCategoryFragment.FoodItem data, IViewInjector injector) {
                                    injector.text(R.id.category_name, data.getFoodItemName() + "")
                                            .text(R.id.category_usage, "" + data.getPrice());
                                    injector.with(R.id.number_button, new IViewInjector.Action() {
                                        @Override
                                        public void action(View view) {
                                            button = (ElegantNumberButton) view.findViewById(R.id.number_button);

                                        }
                                    });
                                    button.setNumber("1");
                                    quantity = 1;
                                    button.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                                        @Override
                                        public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                                            quantity = newValue;
                                        }
                                    });


                                    injector.clicked(R.id.add, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                                            String s = mSharedPreferences.getString(PREF_ORDERID, "");
                                            Toast.makeText(getActivity(), "Clicked INT: " + Integer.valueOf(s), Toast.LENGTH_SHORT).show();

                                            nextcall(Integer.valueOf(s), data.getPrice(), data.getFoodItemID(), quantity);

                                        }
                                    });


                                }
                            })

                            .attachTo(mRecyclerView).updateData(arrayList);

                } else {

                    Toast.makeText(getActivity(), "Something went wrong Please Try Again!", Toast.LENGTH_SHORT).show();
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onFailure(@NonNull Call<ItCategoryFragment.AllFoodModel> call, @NonNull Throwable t) {

                Toast.makeText(getActivity(), "Internet Issue try Again!", Toast.LENGTH_SHORT).show();
            }
        });


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

    public void nextcall(int i, int j, int k, int l) {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestBody pass = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(i));
        RequestBody adre = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(j));
        RequestBody id_res = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(k));
        RequestBody aa = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(l));

        ApiInterface service1 = retrofit1.create(ApiInterface.class);
        Call<BeautyCategoryFragment.GeneralModel> user1 = service1.addorder(pass, id_res, aa, adre);
        user1.enqueue(new Callback<BeautyCategoryFragment.GeneralModel>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(@NonNull Call<BeautyCategoryFragment.GeneralModel> call, @NonNull Response<BeautyCategoryFragment.GeneralModel> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Something went wrong Please Try Again!", Toast.LENGTH_SHORT).show();
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onFailure(@NonNull Call<BeautyCategoryFragment.GeneralModel> call, @NonNull Throwable t) {

                Toast.makeText(getActivity(), "Internet Issue try Again!", Toast.LENGTH_SHORT).show();
            }
        });
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
