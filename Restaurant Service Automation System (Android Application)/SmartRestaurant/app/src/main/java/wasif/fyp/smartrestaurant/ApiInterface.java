package wasif.fyp.smartrestaurant;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import wasif.fyp.smartrestaurant.Tabs.BeautyCategoryFragment;
import wasif.fyp.smartrestaurant.Tabs.ItCategoryFragment;
import wasif.fyp.smartrestaurant.Tabs.PendingFragment;

public interface ApiInterface {
    //    @GET("products")
//    Call<List<HomeFragment.ProductsModel>> getAllCategory();
//
    @Multipart
    @POST("get_food_items")
    Call<ItCategoryFragment.AllFoodModel> getcategories(@Part("restaurantID") RequestBody res,
                                                        @Part("categoryID") RequestBody c);
//
//    @Multipart
//    @POST("saveOrder")
//    Call<ResponseBody> checkout(@Part("items") RequestBody requestBody, @Part("user_id") RequestBody requestBody1);

    @Multipart
    @POST("restaurant_customer_login")
    Call<IntroLocationActivity.LoginModel> login(@Part("firstName") RequestBody email,

                                                 @Part("contactNumber") RequestBody pass,
                                                 @Part("restaurantID") RequestBody id);

    @Multipart
    @POST("get_order_food_items")
    Call<PendingFragment.GetOrderModel> getorders(
            @Part("orderID") RequestBody orderID);

    @Multipart
    @POST("remove_from_order")
    Call<BeautyCategoryFragment.GeneralModel> removesingle(
            @Part("orderID") RequestBody orderID,
            @Part("foodItemID") RequestBody foodItemID);
   @Multipart
    @POST("remove_all_food_items")
    Call<BeautyCategoryFragment.GeneralModel> removesallitem(
            @Part("orderID") RequestBody orderID);
   @Multipart
    @POST("confirm_restaurant_order")
    Call<BeautyCategoryFragment.GeneralModel> confirm(
            @Part("orderID") RequestBody orderID);

    @Multipart
    @POST("create_restaurant_order")
    Call<BeautyCategoryFragment.CreateOrderModel> createorder(@Part("restaurantID") RequestBody restaurantID,

                                                              @Part("userID") RequestBody userID,
                                                              @Part("tableNumber") RequestBody tableNumber);

    @Multipart
    @POST("add_to_order")
    Call<BeautyCategoryFragment.GeneralModel> addorder(@Part("orderID") RequestBody orderID,
                                                       @Part("foodItemID") RequestBody foodItemID,

                                                       @Part("quantityOrdered") RequestBody quantityOrdered,
                                                       @Part("priceEach") RequestBody priceEach);

//
//    @POST("register")
//    @Multipart
//    Call<RegisterActivity.RegisterModel> register(@Part("name") RequestBody name,
//                                                  @Part("email") RequestBody email,
//                                                  @Part("pssword") RequestBody pssword,
//                                                  @Part("address") RequestBody address,
//                                                  @Part MultipartBody.Part photo,
//                                                  @Part("photo") RequestBody photo_name);
//
//    @POST("updateProfile")
//    @Multipart
//    Call<AddCustomerFragment.UPDATEMODEL> update(@Part("name") RequestBody name,
//                                                 @Part("user_id") RequestBody email,
//                                                 @Part("pssword") RequestBody pssword,
//                                                 @Part("address") RequestBody address,
//                                                 @Part MultipartBody.Part photo,
//                                                 @Part("photo") RequestBody photo_name);


}
