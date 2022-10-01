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
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wasif.fyp.smartrestaurant.ApiInterface;
import wasif.fyp.smartrestaurant.CanceledOrderProjectBox;
import wasif.fyp.smartrestaurant.PendingOrderProjectBox;
import wasif.fyp.smartrestaurant.R;
import wasif.fyp.smartrestaurant.SignInActivity;

import static wasif.fyp.smartrestaurant.IntroLocationActivity.PREF_ORDERID;

public class PendingFragment extends Fragment {
    RecyclerView mRecyclerView;
    SlimAdapter slimAdapter;
    List<FoodItem> pendingOrderProjectBoxes = new ArrayList<>();
    Box<PendingOrderProjectBox> wishListBox;
    Box<CanceledOrderProjectBox> canecledbox;
    CanceledOrderProjectBox canceledOrderProjectBox;
    SharedPreferences mSharedPreferences;

    public PendingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        wishListBox = ((MyApp) getActivity().getApplication()).getBoxStore().boxFor(PendingOrderProjectBox.class);
//        canecledbox = ((MyApp) getActivity().getApplication()).getBoxStore().boxFor(CanceledOrderProjectBox.class);


    }

    Button Rem, Confirm;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        mSharedPreferences = getActivity().getSharedPreferences(SignInActivity.PREFERENCE, Context.MODE_PRIVATE);
        Rem = view.findViewById(R.id.remove_all);
        Confirm = view.findViewById(R.id.confirm);

        mRecyclerView = view.findViewById(R.id.recycler_view);
//        pendingOrderProjectBoxes = wishListBox.getAll();
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody id_a = RequestBody.create(MediaType.parse("text/plain"), mSharedPreferences.getString(PREF_ORDERID, ""));

        ApiInterface service1 = retrofit1.create(ApiInterface.class);
        Call<GetOrderModel> user1 = service1.getorders(id_a);
        user1.enqueue(new Callback<GetOrderModel>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(@NonNull Call<GetOrderModel> call, @NonNull Response<GetOrderModel> response) {

                if (response.isSuccessful()) {
                    pendingOrderProjectBoxes = response.body().getFoodItems();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    slimAdapter = SlimAdapter.create()
                            .register(R.layout.categories_item_pending, new SlimInjector<FoodItem>() {
                                @Override
                                public void onInject(final FoodItem data, IViewInjector injector) {
                                    injector.text(R.id.category_name, data.getFoodItemName() + " ");
                                    injector.text(R.id.number_button, "X " + data.getQuantityOrdered());
                                    injector.text(R.id.category_usage, data.getPriceEach() + " ");
                                    injector.clicked(R.id.remove, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                                            Retrofit retrofit1 = new Retrofit.Builder()
                                                    .baseUrl(getResources().getString(R.string.URL))
                                                    .addConverterFactory(GsonConverterFactory.create())
                                                    .build();
                                            RequestBody pass = RequestBody.create(MediaType.parse("text/plain"), mSharedPreferences.getString(PREF_ORDERID, ""));
                                            RequestBody adre = RequestBody.create(MediaType.parse("text/plain"), data.getFoodItemID() + "");
//                                   RequestBody id_res = RequestBody.create(MediaType.parse("text/plain"), "1");

                                            ApiInterface service1 = retrofit1.create(ApiInterface.class);
                                            Call<BeautyCategoryFragment.GeneralModel> user1 = service1.removesingle(pass, adre);
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
                                    });


                                }
                            })

                            .attachTo(mRecyclerView).updateData(pendingOrderProjectBoxes);


                }
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onFailure(@NonNull Call<GetOrderModel> call, @NonNull Throwable t) {

                Toast.makeText(getActivity(), "Internet Issue try Again!", Toast.LENGTH_SHORT).show();
            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.URL))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RequestBody id_a = RequestBody.create(MediaType.parse("text/plain"), mSharedPreferences.getString(PREF_ORDERID, ""));

                ApiInterface service1 = retrofit1.create(ApiInterface.class);
                Call<BeautyCategoryFragment.GeneralModel> user1 = service1.confirm(id_a);
                user1.enqueue(new Callback<BeautyCategoryFragment.GeneralModel>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(@NonNull Call<BeautyCategoryFragment.GeneralModel> call, @NonNull Response<BeautyCategoryFragment.GeneralModel> response) {

                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            if (response.body().getMessage().equalsIgnoreCase("")) {
//                                }
                        }
                    }

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onFailure(@NonNull Call<BeautyCategoryFragment.GeneralModel> call, @NonNull Throwable t) {

                        Toast.makeText(getActivity(), "Internet Issue try Again!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.URL))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RequestBody id_a = RequestBody.create(MediaType.parse("text/plain"), mSharedPreferences.getString(PREF_ORDERID, ""));

                ApiInterface service1 = retrofit1.create(ApiInterface.class);
                Call<BeautyCategoryFragment.GeneralModel> user1 = service1.removesallitem(id_a);
                user1.enqueue(new Callback<BeautyCategoryFragment.GeneralModel>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onResponse(@NonNull Call<BeautyCategoryFragment.GeneralModel> call, @NonNull Response<BeautyCategoryFragment.GeneralModel> response) {

                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            pendingOrderProjectBoxes.clear();
                            slimAdapter.updateData(pendingOrderProjectBoxes);
                        }
                    }

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onFailure(@NonNull Call<BeautyCategoryFragment.GeneralModel> call, @NonNull Throwable t) {

                        Toast.makeText(getActivity(), "Internet Issue try Again!", Toast.LENGTH_SHORT).show();
                    }
                });
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

    public class FoodItem {

        @SerializedName("foodItemID")
        @Expose
        private Integer foodItemID;
        @SerializedName("foodItemName")
        @Expose
        private String foodItemName;
        @SerializedName("quantityOrdered")
        @Expose
        private Integer quantityOrdered;
        @SerializedName("priceEach")
        @Expose
        private Integer priceEach;

        public Integer getFoodItemID() {
            return foodItemID;
        }

        public void setFoodItemID(Integer foodItemID) {
            this.foodItemID = foodItemID;
        }

        public String getFoodItemName() {
            return foodItemName;
        }

        public void setFoodItemName(String foodItemName) {
            this.foodItemName = foodItemName;
        }

        public Integer getQuantityOrdered() {
            return quantityOrdered;
        }

        public void setQuantityOrdered(Integer quantityOrdered) {
            this.quantityOrdered = quantityOrdered;
        }

        public Integer getPriceEach() {
            return priceEach;
        }

        public void setPriceEach(Integer priceEach) {
            this.priceEach = priceEach;
        }

    }

    public class GetOrderModel {

        @SerializedName("error")
        @Expose
        private Boolean error;
        @SerializedName("foodItems")
        @Expose
        private List<FoodItem> foodItems = null;

        public Boolean getError() {
            return error;
        }

        public void setError(Boolean error) {
            this.error = error;
        }

        public List<FoodItem> getFoodItems() {
            return foodItems;
        }

        public void setFoodItems(List<FoodItem> foodItems) {
            this.foodItems = foodItems;
        }

    }
}
