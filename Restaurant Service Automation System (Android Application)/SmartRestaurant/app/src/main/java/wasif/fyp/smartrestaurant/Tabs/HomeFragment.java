//package wasif.fyp.smartrestaurant.Tabs;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.squareup.picasso.Picasso;
//
//import net.idik.lib.slimadapter.SlimAdapter;
//import net.idik.lib.slimadapter.SlimInjector;
//import net.idik.lib.slimadapter.viewinjector.IViewInjector;
//
//import java.util.ArrayList;
//
//
//public class HomeFragment extends Fragment {
//    private RecyclerView mRecyclerView, mRecyclerView01, mRecyclerView02, mRecyclerView03;
//    SlimAdapter slimAdapter;
//    ArrayList<User> userArrayList = new ArrayList<>();
//    ArrayList<User> usersArrayList = new ArrayList<>();
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
////        BannerSlider bannerSlider = view.findViewById(R.id.banner);
//        mRecyclerView = view.findViewById(R.id.recycler_view);
//        mRecyclerView01 = view.findViewById(R.id.recycler_view01);
//        mRecyclerView02 = view.findViewById(R.id.recycler_view02);
//        mRecyclerView03 = view.findViewById(R.id.recycler_view03);
//        User user = new User("Home", "256 ", R.mipmap.home);
//        userArrayList.add(user);
//        User user1 = new User("Event", "256", R.mipmap.event);
//        userArrayList.add(user1);
//
//        User user2 = new User("Electrical", "256", R.mipmap.electrical);
//        userArrayList.add(user2);
//
//        User user3 = new User("Coaching", "256", R.mipmap.coaching);
//        userArrayList.add(user3);
//
//        User user4 = new User("Buisness", "256", R.mipmap.business);
//        userArrayList.add(user4);
//
//        User user5 = new User("Beauty&Fitness", "256", R.mipmap.beauty);
//        userArrayList.add(user5);
//
//        User user6 = new User("Advertisment", "256", R.mipmap.advertisement);
//        userArrayList.add(user6);
//
//        User user7 = new User("ITservices   ", "256", R.mipmap.itservices);
//        userArrayList.add(user7);
//
//        User user8 = new User("Laptop&Mobile", "256", R.mipmap.it);
//        userArrayList.add(user8);
//
//        User user9 = new User("Mechnical", "256", R.mipmap.me);
//        userArrayList.add(user9);
//
//        User user10 = new User("Travel", "256", R.mipmap.craft);
//        userArrayList.add(user10);
//
//        User user11 = new User("VehicleMaintainance", "256", R.mipmap.vehicle);
//        userArrayList.add(user11);
//
////        List<Banner> banners = new ArrayList<>();
////        banners.add(new DrawableBanner(R.mipmap.ad_1));
////        banners.add(new DrawableBanner(R.mipmap.ad_2));
////        banners.add(new DrawableBanner(R.mipmap.ad_3));
////        bannerSlider.setBanners(banners);
//        mRecyclerView.removeAllViews();
//        GridLayoutManager Layout = new GridLayoutManager(getActivity(), 3);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(Layout);
//        slimAdapter = SlimAdapter.create()
//                .register(R.layout.categories_item, new SlimInjector<User>() {
//                    @Override
//                    public void onInject(final User data, IViewInjector injector) {
//                        injector.text(R.id.category_name, data.getName() + "")
//                                .text(R.id.category_usage, "" + data.getNumber());
//
//                        injector.with(R.id.product_image, new IViewInjector.Action() {
//                            @Override
//                            public void action(View view) {
//                                Picasso.with(getActivity()).load(data.getImage()).into((ImageView) view);
//
//                            }
//                        });
//
//
//                    }
//                })
//
//                .attachTo(mRecyclerView).updateData(userArrayList);
//        User users = new User("Home", "256", R.mipmap.asdfasad);
//        usersArrayList.add(users);
//        User users1 = new User("Event", "256", R.mipmap.aqed);
//        usersArrayList.add(users1);
//
//        User users2 = new User("Electrical", "256", R.mipmap.ad_1);
//        usersArrayList.add(users2);
//
//        User users3 = new User("Coaching", "256", R.mipmap.ad_2);
//        usersArrayList.add(users3);
//
//        User users4 = new User("Buisness", "256", R.mipmap.ad_3);
//        usersArrayList.add(users4);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerView01.setHasFixedSize(true);
//        mRecyclerView01.setLayoutManager(linearLayoutManager);
//        slimAdapter = SlimAdapter.create()
//                .register(R.layout.categories_item_big, new SlimInjector<User>() {
//                    @Override
//                    public void onInject(final User data, IViewInjector injector) {
//                        injector.text(R.id.category_name, data.getName() )
//                                .text(R.id.category_usage, "" + data.getNumber()+ " Service Providers");
//
//                        injector.with(R.id.product_image, new IViewInjector.Action() {
//                            @Override
//                            public void action(View view) {
//                                Picasso.with(getActivity()).load(data.getImage()).into((ImageView) view);
//
//                            }
//                        });
//
//
//                    }
//                })
//
//                .attachTo(mRecyclerView01).updateData(usersArrayList);
//        LinearLayoutManager linearLayoutManager01 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//
//        mRecyclerView02.setHasFixedSize(true);
//        mRecyclerView02.setLayoutManager(linearLayoutManager01);
//        slimAdapter = SlimAdapter.create()
//                .register(R.layout.categories_item_big, new SlimInjector<User>() {
//                    @Override
//                    public void onInject(final User data, IViewInjector injector) {
//                        injector.text(R.id.category_name, data.getName() + "")
//                                .text(R.id.category_usage, "" + data.getNumber()+" Service Providers");
//
//                        injector.with(R.id.product_image, new IViewInjector.Action() {
//                            @Override
//                            public void action(View view) {
//                                Picasso.with(getActivity()).load(data.getImage()).into((ImageView) view);
//
//                            }
//                        });
//
//
//                    }
//                })
//
//                .attachTo(mRecyclerView02).updateData(usersArrayList);
//        LinearLayoutManager linearLayoutManager02 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//
//        mRecyclerView03.setHasFixedSize(true);
//        mRecyclerView03.setLayoutManager(linearLayoutManager02);
//
//        slimAdapter = SlimAdapter.create()
//                .register(R.layout.categories_item_big, new SlimInjector<User>() {
//                    @Override
//                    public void onInject(final User data, IViewInjector injector) {
//                        injector.text(R.id.category_name, data.getName() + "")
//                                .text(R.id.category_usage, "" + data.getNumber() +" Service Providers");
//
//                        injector.with(R.id.product_image, new IViewInjector.Action() {
//                            @Override
//                            public void action(View view) {
//                                Picasso.with(getActivity()).load(data.getImage()).into((ImageView) view);
//
//                            }
//                        });
//
//                    }
//                })
//
//                .attachTo(mRecyclerView03).updateData(usersArrayList);
//        return view;
//    }
//
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//    }
//
//    public static class User {
//
//        String name;
//        String Number;
//        int Image;
//
//        public User(String name, String number, int image) {
//            this.name = name;
//            Number = number;
//            Image = image;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getNumber() {
//            return Number;
//        }
//
//        public void setNumber(String number) {
//            Number = number;
//        }
//
//        public int getImage() {
//            return Image;
//        }
//
//        public void setImage(int image) {
//            Image = image;
//        }
//    }
//}
