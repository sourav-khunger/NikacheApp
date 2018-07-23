package com.doozycod.nikache.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doozycod.nikache.Adapter.CategoryAdapter;
import com.doozycod.nikache.Adapter.CustomerFeedbackAdapter;
import com.doozycod.nikache.Adapter.HomeFirstRecyclerViewAdapter;
import com.doozycod.nikache.Adapter.HomeSecondRecyclerViewAdapter;
import com.doozycod.nikache.HomePageItemDecoration;
import com.doozycod.nikache.MainActivity;
import com.doozycod.nikache.MySpacingItemDecoration;
import com.doozycod.nikache.OurLatestProducts;
import com.doozycod.nikache.PojoClasses.Category;
import com.doozycod.nikache.PojoClasses.CustomerFeedback;
import com.doozycod.nikache.PojoClasses.HomeFirstRecyclerView;
import com.doozycod.nikache.PojoClasses.HomeSecondRecyclerView;
import com.doozycod.nikache.R;
import com.doozycod.nikache.RecyclerTouchListener;
import com.doozycod.nikache.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilkumar on 08/06/18.
 */

public class Home extends Fragment {

    Fragment categoryFragment;
    Fragment hotDealFragment;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;


    // toolbar title respected to selected nav menu item
    private String[] homeFragmentSubTitle;
    // index to identify current nav menu item
    public static int navItemIndex = 0;
    View nikacheIconOnActionBar;
    ActionBar actionBar;

    private static final String TAG_BANNER1 = "ad_banner_1";
    private static final String TAG_BANNER2 = "ad_banner_2";
    private static final String TAG_BANNER3 = "ad_banner_3";
    private static final String TAG_BANNER4 = "TAG_HOT_DEAL_OF_DAY";
    Fragment adFragment1;
    Fragment adFragment2;
    Fragment adFragment3;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    TextView tvViewAllHotDeals;
    TextView tvViewAll2OurLatestProducts;
    private static final String TAG_HOT_DEAL = "hot deal";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //--------------------------------------------

        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        LayoutInflater inflator = (LayoutInflater) getContext() .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nikacheIconOnActionBar = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(nikacheIconOnActionBar);
        actionBar.setTitle("");

//        actionBar.setDisplayShowTitleEnabled(false);
//        nikacheIconOnActionBar.setVisibility(View.VISIBLE);

        nikacheIconOnActionBar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                double x = v.getX();
                double logoImageWidth = v.getWidth();
                // int logoPosition = actionBarWidth / 2 - logoImageWidth / 2;
                double logoPosition = MainActivity.actionBarWidth/4.5  - logoImageWidth /3;
                if (x != logoPosition) {
                    v.setX((float) logoPosition);
                    v.requestLayout();
                } else {
                    v.removeOnLayoutChangeListener(this);
                }
            }

        });

        //-------- setting data into the textview declared at the bottom of home page

        TextView tvFreeDelivery = view.findViewById(R.id.tv_free_delivery);
        tvFreeDelivery.setText("free\ndelivery");
        TextView tvEasyReturns = view.findViewById(R.id.tv_easy_returns);
        tvEasyReturns.setText("easy\nreturns");
        TextView tvCashOnDelivery = view.findViewById(R.id.tv_cash_on_delivery);
        tvCashOnDelivery.setText("cash on\ndelivery");

        //---------------------Calling Home Page's Recycler View-----------

        recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_view1);
        HomePageFirstRecyclerView homerv1 = new HomePageFirstRecyclerView();
        homerv1.recyclerViewFirstMethod(recyclerView1);

        recyclerView2 = (RecyclerView) view.findViewById(R.id.recycler_view2);
        HomePageSecondRecyclerView homerv2 = new HomePageSecondRecyclerView();
        homerv2.recyclerViewSecondMethod(recyclerView2);

        recyclerView3 = (RecyclerView) view.findViewById(R.id.recycler_view);
        HomePageThirdRecyclerView homerv3 = new HomePageThirdRecyclerView();
        homerv3.recyclerViewThirdMethod(recyclerView3);

        recyclerView4 = (RecyclerView) view.findViewById(R.id.recycler_view4);
        HomePageFourthRecyclerView homerv4 = new HomePageFourthRecyclerView();
        homerv4.recyclerViewFourthMethod(recyclerView4);

        //--------------------------------------------

        //To display ad banner
        adFragment1 = new ViewPager1();
        FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.add(R.id.content_frame1, adFragment1, TAG_BANNER1);
        fragmentTransaction1.commit();


//        adFragment2 = new ViewPager2();
//        FragmentManager fragmentManager2 = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
//        fragmentTransaction2.add(R.id.content_frame2, adFragment2, TAG_BANNER2);
//        fragmentTransaction2.commit();


//        adFragment3 = new ViewPager3();
//        FragmentManager fragmentManager3 = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
//        fragmentTransaction3.add(R.id.content_frame3, adFragment3, TAG_BANNER3);
//        fragmentTransaction3.commit();

        //--------------------------------------------

        tvViewAllHotDeals = (TextView) view.findViewById(R.id.tv_view_all);
        tvViewAll2OurLatestProducts = (TextView) view.findViewById(R.id.tv_view_all_2);
        tvViewAllHotDeals.setOnClickListener(tvClickListner);
        tvViewAll2OurLatestProducts.setOnClickListener(tvClickListner);

        //------------------------------------------

        // load toolbar title from string resources
        homeFragmentSubTitle = getResources().getStringArray(R.array.nav_menu_items_fragment_title);

        //---------------------------------------------

        EditText edSearchBar = (EditText)view.findViewById(R.id.et_search_bar);
        edSearchBar.setOnClickListener(new
                                               View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       Intent intent = new Intent(getActivity(), SearchActivity.class);
                                                       startActivity(intent);
                                                   }
                                               });
        //---------------------------------------------

        return view;
    }

    View.OnClickListener tvClickListner = new View.OnClickListener()
    {
        private Handler mHandler;

        @Override
        public void onClick( View v )
        {

            // TODO Auto-generated method stub
            if( tvViewAllHotDeals.getId() == v.getId() )
            {
                //******-------***** On Clicking @ViewAll,opens HotDeal of day Fragment *****---------------------------------

                hotDealFragment = null;
                hotDealFragment = new HotDealOfDayFragment();

                    // Sometimes, when fragment has huge data, screen seems hanging
                    // when switching between navigation menus
                    // So using runnable, the fragment is loaded with cross fade effect
                    // This effect can be seen in GMail app
                    Runnable mPendingRunnable = new Runnable() {
                        @Override
                        public void run() {

                            navItemIndex = 7;
                            MainActivity.CURRENT_TAG = TAG_HOT_DEAL;
                            setToolbarTitle();
                            fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.content_frame, hotDealFragment,MainActivity.CURRENT_TAG);
                            fragmentTransaction.addToBackStack(MainActivity.CURRENT_TAG);
                            fragmentTransaction.commit();

                        }
                    };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                    mHandler = new Handler();
                    mHandler.post(mPendingRunnable);
                }

                //******------------------------**********---------------------------------

            }
            else if( tvViewAll2OurLatestProducts.getId() == v.getId() )
            {
                // Do tvViewAll2OurLatestProducts click operations here

                Intent intent = new Intent(getActivity(), OurLatestProducts.class);
                startActivity(intent);

            }
        }
    };


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Toolbar title changes, according to the item choosed
     * from the expandable listview under navigation drawer.
     */
    private void setToolbarTitle()
    {

        actionBar.setTitle(homeFragmentSubTitle[navItemIndex]);
        nikacheIconOnActionBar.setVisibility(View.GONE);

        // To change the color of labels on ActionBar.

        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
    }







    public class HomePageFirstRecyclerView  {

        private ArrayList<HomeFirstRecyclerView> homeFirstRecyclerViewList = new ArrayList<>();
        private HomeFirstRecyclerViewAdapter adapter;
        RecyclerView recyclerView1;

        public HomePageFirstRecyclerView(){

        }

        public  void recyclerViewFirstMethod(RecyclerView recyclerView1){




            adapter = new HomeFirstRecyclerViewAdapter(getContext(), homeFirstRecyclerViewList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

            recyclerView1.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView1.addItemDecoration(new HomePageItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));

            recyclerView1.setAdapter(adapter);

            prepareAlbums();
        }

        private void prepareAlbums(){
            int[] covers = new int[]{
                    R.drawable.discussion_table_300,
                    R.drawable.furniture_11_300,
                    R.drawable.furniture_12_300,
                    R.drawable.furniture_13_300,
                    R.drawable.jersey_dining_chair_300,
                    R.drawable.easy_chair_300,
                    R.drawable.u_shaped_couch_300,
                    R.drawable.tray_cum_table_300,
                    R.drawable.wingback_bed_300,
                    R.drawable.single_sofa_300,

            };

            HomeFirstRecyclerView a = new HomeFirstRecyclerView("700","Discussion Table", "1300", covers[0]);
            homeFirstRecyclerViewList.add(a);

            a = new HomeFirstRecyclerView("701","Cupboard", "8000", covers[1]);
            homeFirstRecyclerViewList.add(a);

            a = new HomeFirstRecyclerView("702","Brown Sofa", "10100", covers[2]);
            homeFirstRecyclerViewList.add(a);

            a = new HomeFirstRecyclerView("703","Wooden Table", "10200", covers[3]);
            homeFirstRecyclerViewList.add(a);

            a = new HomeFirstRecyclerView("704","Jersy Dining Chair", "14000", covers[4]);
            homeFirstRecyclerViewList.add(a);

            a = new HomeFirstRecyclerView("705","Easy Chair", "10000", covers[5]);
            homeFirstRecyclerViewList.add(a);

            a = new HomeFirstRecyclerView("706","U Shaped Couch", "1800", covers[6]);
            homeFirstRecyclerViewList.add(a);

            a = new HomeFirstRecyclerView("707","Tray Cum Table", "3450", covers[7]);
            homeFirstRecyclerViewList.add(a);

            a = new HomeFirstRecyclerView("708","Wingback Bed", "11065", covers[8]);
            homeFirstRecyclerViewList.add(a);

            a = new HomeFirstRecyclerView("709","Single Sofa", "31799", covers[9]);
            homeFirstRecyclerViewList.add(a);
            adapter.notifyDataSetChanged();
        }

    }


    public class HomePageSecondRecyclerView  {

        private List<HomeSecondRecyclerView> homeSecondRecyclerViewList = new ArrayList<>();
        private HomeSecondRecyclerViewAdapter adapter;
        RecyclerView recyclerView2;

        public HomePageSecondRecyclerView(){

        }

        public  void recyclerViewSecondMethod(RecyclerView recyclerView2){




            adapter = new HomeSecondRecyclerViewAdapter(getContext(), homeSecondRecyclerViewList);


            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

            recyclerView2.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView2.addItemDecoration(new HomePageItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));

            recyclerView2.setAdapter(adapter);

            prepareAlbums();
        }

        private void prepareAlbums(){
            int[] covers = new int[]{
                    R.drawable.utility_items_1_300,
                    R.drawable.utility_items_2_300,
                    R.drawable.utility_items_3_300,
                    R.drawable.utility_items_4_300,
                    R.drawable.utility_items_5_300,
                    R.drawable.utility_items_6_300,
                    R.drawable.utility_items_7_300,
                    R.drawable.campers_and_hikers_8_300,
                    R.drawable.thermostat_9_300,
                    R.drawable.basket_10_300,

            };

            HomeSecondRecyclerView a = new HomeSecondRecyclerView("800","Utensils", 4300, covers[0]);
            homeSecondRecyclerViewList.add(a);

            a = new HomeSecondRecyclerView("801","Yellow Box", 480, covers[1]);
            homeSecondRecyclerViewList.add(a);

            a = new HomeSecondRecyclerView("802","Themosteel", 1250, covers[2]);
            homeSecondRecyclerViewList.add(a);

            a = new HomeSecondRecyclerView("803","Small Box", 630, covers[3]);
            homeSecondRecyclerViewList.add(a);

            a = new HomeSecondRecyclerView("804","Plastic Sheet", 270, covers[4]);
            homeSecondRecyclerViewList.add(a);

            a = new HomeSecondRecyclerView("805","Campher", 850, covers[5]);
            homeSecondRecyclerViewList.add(a);

            a = new HomeSecondRecyclerView("806","Juicer", 749, covers[6]);
            homeSecondRecyclerViewList.add(a);

            a = new HomeSecondRecyclerView("807","Campers and Hikers", 1100, covers[7]);
            homeSecondRecyclerViewList.add(a);

            a = new HomeSecondRecyclerView("808","Thermostat", 600, covers[8]);
            homeSecondRecyclerViewList.add(a);

            a = new HomeSecondRecyclerView("809","Basket", 450, covers[9]);
            homeSecondRecyclerViewList.add(a);

            adapter.notifyDataSetChanged();
        }

    }

    public class HomePageThirdRecyclerView  {

        private List<Category> categoryList = new ArrayList<>();
        private CategoryAdapter adapter;
        private Handler mHandler;

        public HomePageThirdRecyclerView(){

        }

        public  void recyclerViewThirdMethod(RecyclerView recyclerView3){

            adapter = new CategoryAdapter(getContext(), categoryList);

            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

            recyclerView3.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView3.addItemDecoration(new MySpacingItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));

            recyclerView3.setAdapter(adapter);

            // row click listener
            recyclerView3.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView3, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {

                    categoryFragment = null;
                    Category category = categoryList.get(position);
                    String categoryTitle = category.getTitle();
                    Toast.makeText(getContext(), categoryTitle + " is selected!", Toast.LENGTH_SHORT).show();
                    Intent intent;

                    switch (categoryTitle) {

                        case "Wall Decor":
                            navItemIndex = 1;
                            categoryFragment = new WallDecorFragment();
                            setToolbarTitle();
                            break;
                        case "Furniture":
                            navItemIndex = 2;
                            categoryFragment = new FurnitureFragment();
                            setToolbarTitle();
                            break;

                        case "Utility Items":
                            navItemIndex = 3;
                            categoryFragment = new UtilityItemsFragment();
                            setToolbarTitle();
                            break;


                        case "Bras Decor":
                            navItemIndex = 4;
                            categoryFragment = new BrasDecorFragment();
                            setToolbarTitle();
                            break;

                        case "Clocks":
                            navItemIndex = 5;
                            categoryFragment = new ClocksFragment();
                            setToolbarTitle();
                            break;

                        case "Antiques":
                            navItemIndex = 6;
                            categoryFragment = new AntiquesFragment();
                            setToolbarTitle();
                            break;

                    }

                    Runnable mPendingRunnable = new Runnable() {
                        @Override
                        public void run() {


                            fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                                    android.R.anim.fade_out);
                            fragmentTransaction.replace(R.id.content_frame, categoryFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
//                            fragmentTransaction.commitAllowingStateLoss();


                        }
                    };

                    // If mPendingRunnable is not null, then add to the message queue
                    if (mPendingRunnable != null) {
                        mHandler = new Handler();
                        mHandler.post(mPendingRunnable);
                    }

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));


            prepareAlbums();
        }

        /**
         * Adding few albums for testing
         */

        private void prepareAlbums() {

            categoryList.clear();
            int[] covers = new int[]{
                    R.drawable.wall_decor,
                    R.drawable.furniture,
                    R.drawable.utility_items,
                    R.drawable.bras_decor,
                    R.drawable.clocks,
                    R.drawable.antiques,
            };

            Category a = new Category(covers[0], "Wall Decor");
            categoryList.add(a);

            a = new Category(covers[1], "Furniture");
            categoryList.add(a);

            a = new Category(covers[2], "Utility Items");
            categoryList.add(a);

            a = new Category(covers[3], "Bras Decor");
            categoryList.add(a);

            a = new Category(covers[4], "Clocks");
            categoryList.add(a);

            a = new Category(covers[5], "Antiques");
            categoryList.add(a);


            adapter.notifyDataSetChanged();
        }

    }

    public class HomePageFourthRecyclerView  {

        private List<CustomerFeedback> customerFeedbackList = new ArrayList<>();
        private CustomerFeedbackAdapter adapter;

        public HomePageFourthRecyclerView(){

        }

        public  void recyclerViewFourthMethod(RecyclerView recyclerView4){

            adapter = new CustomerFeedbackAdapter(getContext(), customerFeedbackList);

            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

            recyclerView4.setLayoutManager(mLayoutManager);

            // adding custom divider line with padding 16dp
            recyclerView4.addItemDecoration(new MySpacingItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));

            recyclerView4.setAdapter(adapter);

            prepareAlbums();
        }

        /**
         * Adding few albums for testing
         */

        private void prepareAlbums() {

            customerFeedbackList.clear();

            String[] customerFeedbackArray = new String[]{
                    "Nunc molestie vulputate" +
                            "\nlibero, a aliquam ante rutrum" +
                            "\neu. In sollicitudin vitae ipsum" +
                            "\net commodo. Maecenas auctor" +
                            "\ncursus sem sed varius. ",
                    "Lorem Ipsum has been the" +
                            "\nindustry's standard dummy" +
                            "\ntext ever since the 1500s, when" +
                            "\nan unknown printer took a" +
                            "\ngalley of type and scrambled it" +
                            "\nto",
                    "Aliquam facilisis turpis id" +
                            "\nmaximus luctus. Sed augue" +
                            "\ntortor, gravida id accumsan" +
                            "\nvitae, tempor nec nibh. Proin" +
                            "\nelementum lobortis sodales. ",
                    "Every" +
                            "\nproducts is" +
                            "\nvery" +
                            "\naffordable" +
                            "\nand stylish",
            };

            String[] customerNameArray = new String[]{
                    "LONG PHAM",
                    "STEPHEN NGO",
                    "FRANK NGUYEN",
                    "RAVIKANT",
            };

            CustomerFeedback a = new CustomerFeedback(customerFeedbackArray[0], customerNameArray[0]);
            customerFeedbackList.add(a);

            a = new CustomerFeedback(customerFeedbackArray[1], customerNameArray[1]);
            customerFeedbackList.add(a);

            a = new CustomerFeedback(customerFeedbackArray[2], customerNameArray[2]);
            customerFeedbackList.add(a);

            a = new CustomerFeedback(customerFeedbackArray[3], customerNameArray[3]);
            customerFeedbackList.add(a);

            adapter.notifyDataSetChanged();
        }

    }



}