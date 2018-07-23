package com.doozycod.nikache.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doozycod.nikache.Adapter.UtilityItemsAdapter;
import com.doozycod.nikache.PojoClasses.UtilityItems;
import com.doozycod.nikache.R;
import com.doozycod.nikache.RecyclerTouchListener;
import com.doozycod.nikache.UtilityItemsProductDetails;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class UtilityItemsFragment extends Fragment {

        private RecyclerView recyclerView;
        private UtilityItemsAdapter adapter;
        private ArrayList<UtilityItems> utilityItemsList;
        public static final String UTILITY_ITEMS_KEY = "utilityItems";
        public static final String UTILITY_ITEMS_CURRENT_POSITION = "futilityItemsCurrPos";
        public static final String UTILITY_ITEMS_LIST_DATA = "utilityItemsListData";


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_utility_items, container, false);


//            EditText etSearchBar = (EditText)view.findViewById(R.id.et_search_bar);
//            etSearchBar.setVisibility(View.INVISIBLE);
//            etSearchBar.setVisibility(View.GONE);

            recyclerView =(RecyclerView) view.findViewById(R.id.recycler_view);

            utilityItemsList = new ArrayList<>();
            adapter = new UtilityItemsAdapter(getContext(), utilityItemsList);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));

            recyclerView.setAdapter(adapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    UtilityItems currentUtilityItems = utilityItemsList.get(position);
                    Intent intent = new Intent(getContext(), UtilityItemsProductDetails.class);
                    intent.putExtra(UTILITY_ITEMS_KEY, currentUtilityItems);
                    intent.putExtra(UTILITY_ITEMS_CURRENT_POSITION, position);
                    intent.putExtra(UTILITY_ITEMS_LIST_DATA, utilityItemsList);
                    startActivity(intent);
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

            prepareAlbums();

            return view;
        }


        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
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

            UtilityItems a = new UtilityItems("300","Utensils", "4300", covers[0],
                    "UtilityItems0 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "940cm" , "UtilityItems 0" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 0" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 0");
            utilityItemsList.add(a);

            a = new UtilityItems("301","Yellow Box", "480", covers[1],
                    "UtilityItems1 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "941cm" , "UtilityItems 1" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 1" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 1");
            utilityItemsList.add(a);

            a = new UtilityItems("302","Themosteel", "1250", covers[2],
                    "UtilityItems2 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "942cm" , "UtilityItems 2" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 2" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 2");
            utilityItemsList.add(a);

            a = new UtilityItems("303","Small Box", "630", covers[3],
                    "UtilityItems3 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "943cm" , "UtilityItems 3" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 3" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 3");
            utilityItemsList.add(a);

            a = new UtilityItems("304","Plastic Sheet", "270", covers[4],
                    "UtilityItems0 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "944cm" , "UtilityItems 4" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 4" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 4");
            utilityItemsList.add(a);

            a = new UtilityItems("305","Campher", "850", covers[5],
                    "UtilityItems5 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "945cm" , "UtilityItems 5" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 5" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 5");
            utilityItemsList.add(a);

            a = new UtilityItems("306","Juicer", "749", covers[6],
                    "UtilityItems6 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "946cm" , "UtilityItems 6" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 6" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 6");
            utilityItemsList.add(a);

            a = new UtilityItems("307","Campers & Hikers", "1100", covers[7],
                    "UtilityItems7 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "947cm" , "UtilityItems 7" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 7" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 7");
            utilityItemsList.add(a);

            a = new UtilityItems("308","Thermostat", "600", covers[8],
                    "UtilityItems8 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "948cm" , "UtilityItems 8" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 8" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 8");
            utilityItemsList.add(a);

            a = new UtilityItems("309","Basket", "450", covers[9],
                    "UtilityItems9 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "949cm" , "UtilityItems 9" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: UtilityItems 9" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* UtilityItems 9");
            utilityItemsList.add(a);

            adapter.notifyDataSetChanged();
        }

        /**
         * RecyclerView item decoration - give equal margin around grid item
         */

        public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

            private int spanCount;
            private int spacing;
            private boolean includeEdge;

            public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge){
                this.spanCount = spanCount;
                this.spacing = spacing;
                this.includeEdge = includeEdge;
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);    // item position
                int column = position % spanCount; // item column

                if(includeEdge){
                    outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount;  // (column + 1) * ((1f / spanCount) * spacing)

                    if(position < spanCount) {   // top edge
                        outRect.top = spacing;
                    }
                    outRect.bottom = spacing; // item bottom
                } else {
                    outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                    outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)

                    if(position >= spanCount){
                        outRect.top = spacing; // item top
                    }
                }

            }
        }

        /**
         * Converting dp to pixel
         */
        private int dpToPx(int dp){
            Resources r = getResources();
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
        }

    }