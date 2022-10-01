package wasif.fyp.smartrestaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wasif.fyp.smartrestaurant.Tabs.BeautyCategoryFragment;

public class IntroLocationActivity extends AppCompatActivity {
    private LinearLayout location;
    GPSTracker gpsTracker;

    public static final String PREF_USERNAME = "username";
    public static final String PREF_USERID = "id";
    public static final String PREF_ORDERID = "orderid";
    public static final String PREF_PHONE = "zone";
    SharedPreferences mSharedPreferences;
    ProgressBar progressBar;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_location);
        location = findViewById(R.id.intro_location);
        //intialize gos tracker

        gpsTracker = new GPSTracker(this);
        final EditText Name = findViewById(R.id.name);
        final EditText Numbner = findViewById(R.id.no);
        progressBar = findViewById(R.id.progress);
        Password = findViewById(R.id.password);

        mSharedPreferences = getSharedPreferences(SignInActivity.PREFERENCE, Context.MODE_PRIVATE);
//        Name.setText("Sarim Khan");
//        Numbner.setText("03211234567");
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(IntroLocationActivity.this, "LATITUDE: " + gpsTracker.getLatitude() + "  LONGITUDE :" + gpsTracker.getLongitude(), Toast.LENGTH_SHORT).show();
                Log.e("TGED", "LATITUDE: " + gpsTracker.getLatitude() + "  LONGITUDE :" + gpsTracker.getLongitude());
                if (!Name.getText().toString().isEmpty() && !Numbner.getText().toString().isEmpty()) {
                    Intent i = new Intent(IntroLocationActivity.this, MainActivity.class);
                    mSharedPreferences.edit().putString(PREF_USERNAME, Name.getText().
                            toString()).commit();
                    mSharedPreferences.edit().putString(PREF_PHONE, Numbner.getText().toString()).commit();
                    Retrofit retrofit1 = new Retrofit.Builder()
                            .baseUrl(getResources().getString(R.string.URL))
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RequestBody pass = RequestBody.create(MediaType.parse("text/plain"), Name.getText().toString());
                    RequestBody adre = RequestBody.create(MediaType.parse("text/plain"), Numbner.getText().toString());
                    RequestBody id_res = RequestBody.create(MediaType.parse("text/plain"), "1");

                    ApiInterface service1 = retrofit1.create(ApiInterface.class);
                    Call<LoginModel> user1 = service1.login(pass, adre, id_res);
                    user1.enqueue(new Callback<LoginModel>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                        @Override
                        public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {

                            if (response.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                assert response.body() != null;
                                mSharedPreferences.edit().putString(PREF_USERID, String.valueOf(response.body().getUserID())).commit();
                                func();
                                Toast.makeText(IntroLocationActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(LoginActivity.this, "Something went wrong Please Try Again!", Toast.LENGTH_SHORT).show();
//
//                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(IntroLocationActivity.this, "Something went wrong Please Try Again!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                        @Override
                        public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(IntroLocationActivity.this, "Internet Issue try Again!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(IntroLocationActivity.this, "Please Fill out the above", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class LoginModel {

        @SerializedName("error")
        @Expose
        private Boolean error;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("userID")
        @Expose
        private Integer userID;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("contactNumber")
        @Expose
        private String contactNumber;
        @SerializedName("numberOfVisits")
        @Expose
        private Object numberOfVisits;
        @SerializedName("created_at")
        @Expose
        private String createdAt;

        public Boolean getError() {
            return error;
        }

        public void setError(Boolean error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getUserID() {
            return userID;
        }

        public void setUserID(Integer userID) {
            this.userID = userID;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public Object getNumberOfVisits() {
            return numberOfVisits;
        }

        public void setNumberOfVisits(Object numberOfVisits) {
            this.numberOfVisits = numberOfVisits;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

    }

    public void func() {

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestBody pass = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody adre = RequestBody.create(MediaType.parse("text/plain"), mSharedPreferences.getString(PREF_USERID, ""));
        RequestBody id_res = RequestBody.create(MediaType.parse("text/plain"), "1");

        ApiInterface service1 = retrofit1.create(ApiInterface.class);
        Call<BeautyCategoryFragment.CreateOrderModel> user1 = service1.createorder(pass, adre, id_res);
        user1.enqueue(new Callback<BeautyCategoryFragment.CreateOrderModel>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(@NonNull Call<BeautyCategoryFragment.CreateOrderModel> call, @NonNull Response<BeautyCategoryFragment.CreateOrderModel> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(IntroLocationActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    mSharedPreferences.edit().putString(PREF_ORDERID, String.valueOf(response.body().getOrderID())).commit();

                    Toast.makeText(IntroLocationActivity.this, "" + mSharedPreferences.getString(PREF_ORDERID,
                            ""), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(IntroLocationActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(IntroLocationActivity.this, "Something went wrong Please Try Again!", Toast.LENGTH_SHORT).show();
                }

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onFailure(@NonNull Call<BeautyCategoryFragment.CreateOrderModel> call, @NonNull Throwable t) {

                Toast.makeText(IntroLocationActivity.this, "Internet Issue try Again!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

