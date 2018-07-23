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

import com.doozycod.nikache.Adapter.BrasDecorAdapter;
import com.doozycod.nikache.BrasDecorProductDetails;
import com.doozycod.nikache.PojoClasses.BrasDecor;
import com.doozycod.nikache.R;
import com.doozycod.nikache.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrasDecorFragment extends Fragment {

        private RecyclerView recyclerView;
        private BrasDecorAdapter adapter;
        private ArrayList<BrasDecor> brasDecorList;
        public static final String BRASS_DECOR_KEY = "brassdecor";
        public static final String BRASS_DECOR_CURRENT_POSITION = "brassdecorCurrPos";
        public static final String BRASS_DECOR_LIST_DATA = "brassdecorListData";

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_bras_decor, container, false);


//            EditText etSearchBar = (EditText)view.findViewById(R.id.et_search_bar);
//            etSearchBar.setVisibility(View.INVISIBLE);
//            etSearchBar.setVisibility(View.GONE);

            recyclerView =(RecyclerView) view.findViewById(R.id.recycler_view);

            brasDecorList = new ArrayList<>();
            adapter = new BrasDecorAdapter(getContext(), brasDecorList);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));

            recyclerView.setAdapter(adapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    BrasDecor currentBrassDecor = brasDecorList.get(position);
                    Intent intent = new Intent(getContext(), BrasDecorProductDetails.class);
                    intent.putExtra(BRASS_DECOR_KEY, currentBrassDecor);
                    intent.putExtra(BRASS_DECOR_CURRENT_POSITION, position);
                    intent.putExtra(BRASS_DECOR_LIST_DATA, brasDecorList);
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
                    R.drawable.brass_duck_hook_300,
                    R.drawable.brass_oil_lamp_300,
                    R.drawable.brass_wall_hook_300,
                    R.drawable.brass_wall_shelf_300,
                    R.drawable.brass_rope_clock_300,
                    R.drawable.bathroom_shelves_300,
                    R.drawable.crystal_glass_vase_300,
                    R.drawable.cold_water_dispenser_300,
                    R.drawable.brass_bell_300,
                    R.drawable.living_table_lamps_10_300,

            };

            BrasDecor a = new BrasDecor("400","Brass Duck Hook", "4300", covers[0],
                    "BrasDecor0 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1040cm" , "BrasDecor 0" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 0" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 0");
            brasDecorList.add(a);

            a = new BrasDecor("401","Brass Oil Lamp", "480", covers[1],
                    "BrasDecor1 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1041cm" , "BrasDecor 1" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 1" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 1");
            brasDecorList.add(a);

            a = new BrasDecor("402","Brass Wall Hook", "1250", covers[2],
                    "BrasDecor2 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1042cm" , "BrasDecor 2" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 2" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 2");
            brasDecorList.add(a);

            a = new BrasDecor("403","Brass Wall Shelf", "630", covers[3],
                    "BrasDecor3 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1043cm" , "BrasDecor 3" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 3" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 3");
            brasDecorList.add(a);

            a = new BrasDecor("404","Brass Rope Clock", "270", covers[4],
                    "BrasDecor4 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1044cm" , "BrasDecor 4" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 4" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 4");
            brasDecorList.add(a);

            a = new BrasDecor("405","Bathroom Shelves", "850", covers[5],
                    "BrasDecor5 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1045cm" , "BrasDecor 5" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 5" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 5");
            brasDecorList.add(a);

            a = new BrasDecor("406","Crystal Glass Vase", "749", covers[6],
                    "BrasDecor6 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1046cm" , "BrasDecor 6" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 6" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 6");
            brasDecorList.add(a);

            a = new BrasDecor("407","Cold Water Dispenser", "1100", covers[7],
                    "BrasDecor7 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1047cm" , "BrasDecor 7" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 7" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 7");
            brasDecorList.add(a);

            a = new BrasDecor("408","Brass Bell", "600", covers[8],
                    "BrasDecor8 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1048cm" , "BrasDecor 8" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 8" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 8");
            brasDecorList.add(a);

            a = new BrasDecor("409","Living Table Lamp", "450", covers[9],
                    "BrasDecor9 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1049cm" , "BrasDecor 9" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: BrasDecor 9" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* BrasDecor 9");
            brasDecorList.add(a);

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