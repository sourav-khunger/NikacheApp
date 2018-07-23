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

import com.doozycod.nikache.Adapter.FurnitureAdapter;
import com.doozycod.nikache.FurnitureProductDetails;
import com.doozycod.nikache.PojoClasses.Furniture;
import com.doozycod.nikache.R;
import com.doozycod.nikache.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class FurnitureFragment extends Fragment {

        private RecyclerView recyclerView;
        private FurnitureAdapter adapter;
        private ArrayList<Furniture> furnitureList;
        public static final String FURNITURE_KEY = "furniture";
        public static final String FURNITURE_CURRENT_POSITION = "furnitureCurrPos";
        public static final String FURNITURE_LIST_DATA = "furnitureListData";


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_furniture, container, false);




//            EditText etSearchBar = (EditText)view.findViewById(R.id.et_search_bar);
//            etSearchBar.setVisibility(View.INVISIBLE);
//            etSearchBar.setVisibility(View.GONE);

            recyclerView =(RecyclerView) view.findViewById(R.id.recycler_view);

            furnitureList = new ArrayList<>();
            adapter = new FurnitureAdapter(getContext(), furnitureList);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));

            recyclerView.setAdapter(adapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {

                    Furniture currentFurniture = furnitureList.get(position);
                    Intent intent = new Intent(getContext(), FurnitureProductDetails.class);
                    intent.putExtra(FURNITURE_KEY, currentFurniture);
                    intent.putExtra(FURNITURE_CURRENT_POSITION, position);
                    intent.putExtra(FURNITURE_LIST_DATA, furnitureList);
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

            Furniture a = new Furniture("200","Discussion Table", "1300", covers[0],
                    "Furniture1 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "841cm" , "Furniture 1" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture 1" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture 1");
            furnitureList.add(a);

            a = new Furniture("201","Cupboard", "8000", covers[1],
                    "Furniture2 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "Furniture2" , "Furniture2" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture2" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture2");
            furnitureList.add(a);

            a = new Furniture("202","Brown Sofa", "10100", covers[2],
                    "Furniture3 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "Furniture3" , "Furniture3" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture3" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture3");
            furnitureList.add(a);

            a = new Furniture("203","Wooden Table", "10200", covers[3],
                    "Furniture4 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "Furniture4" , "Furniture4" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture4" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture4");
            furnitureList.add(a);

            a = new Furniture("204","Jersy Dining Chair", "14000", covers[4],
                    "Furniture5 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "Furniture5" , "Furniture5" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture5" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture5");
            furnitureList.add(a);

            a = new Furniture("205","Easy Chair", "10000", covers[5],
                    "Furniture6 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "Furniture6" , "Furniture6" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture6" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture6");
            furnitureList.add(a);

            a = new Furniture("206","U Shaped Couch", "1800", covers[6],
                    "Furniture7 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "Furniture7" , "Furniture7" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture7" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture7");
            furnitureList.add(a);

            a = new Furniture("207","Tray Cum Table", "3450", covers[7],
                    "Furniture8 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "Furniture8" , "Furniture8" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture8" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture8");
            furnitureList.add(a);

            a = new Furniture("208","Wingback Bed", "11065", covers[8],
                    "Furniture9 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "Furniture9" , "Furniture9" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture9" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture9");
            furnitureList.add(a);

            a = new Furniture("209","Single Sofa", "31799", covers[9],
                    "Furniture10 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "Furniture10" , "Furniture10" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Furniture10" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Furniture10");
            furnitureList.add(a);

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