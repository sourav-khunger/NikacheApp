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

import com.doozycod.nikache.Adapter.ClocksAdapter;
import com.doozycod.nikache.ClocksProductDetails;
import com.doozycod.nikache.PojoClasses.Clocks;
import com.doozycod.nikache.R;
import com.doozycod.nikache.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClocksFragment extends Fragment {

        private RecyclerView recyclerView;
        private ClocksAdapter adapter;
        private ArrayList<Clocks> clocksList;
        public static final String CLOCKS_KEY = "clocks";
        public static final String CLOCKS_CURRENT_POSITION = "clocksCurrPos";
        public static final String CLOCKS_LIST_DATA = "clocksListData";

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_clocks, container, false);


//            EditText etSearchBar = (EditText)view.findViewById(R.id.et_search_bar);
//            etSearchBar.setVisibility(View.INVISIBLE);
//            etSearchBar.setVisibility(View.GONE);

            recyclerView =(RecyclerView) view.findViewById(R.id.recycler_view);

            clocksList = new ArrayList<>();
            adapter = new ClocksAdapter(getContext(), clocksList);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));

            recyclerView.setAdapter(adapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Clocks currentClocks = clocksList.get(position);
                    Intent intent = new Intent(getContext(), ClocksProductDetails.class);
                    intent.putExtra(CLOCKS_KEY, currentClocks);
                    intent.putExtra(CLOCKS_CURRENT_POSITION, position);
                    intent.putExtra(CLOCKS_LIST_DATA, clocksList);
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
                    R.drawable.grey_wall_clock_1_300,
                    R.drawable.large_modern_wall_clock_2_300,
                    R.drawable.sapling_front_dial_300,
                    R.drawable.charlie_twin_bell_4_300,
                    R.drawable.radio_controlled_5_300,
                    R.drawable.sapling_analog_round_6_300,
                    R.drawable.white_ross_clock_300,
                    R.drawable.block_in_between_8_300,
                    R.drawable.flor_clock_blue_9_300,
                    R.drawable.reloj_pared_madera_umbra_10_300,

            };

            Clocks a = new Clocks("500","Grey Wall Clock", "4300", covers[0],
                    "Clocks0 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1140cm" , "Clocks 0" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 0" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 0");
            clocksList.add(a);

            a = new Clocks("501","Large Modern Clock", "480", covers[1],
                    "Clocks1 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1141cm" , "Clocks 1" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 1" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 1");
            clocksList.add(a);

            a = new Clocks("502","Sapling Front Dial", "1250", covers[2],
                    "Clocks2 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1142cm" , "Clocks 2" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 2" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 2");
            clocksList.add(a);

            a = new Clocks("503","Charlie Twin Bell", "630", covers[3],
                    "Clocks3 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1143cm" , "Clocks 3" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 3" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 3");
            clocksList.add(a);

            a = new Clocks("504","Radio Controlled", "270", covers[4],
                    "Clocks4 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1144cm" , "Clocks 4" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 4" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 4");
            clocksList.add(a);

            a = new Clocks("505","Sapling Analog Round", "850", covers[5],
                    "Clocks5 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1145cm" , "Clocks 5" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 5" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 5");
            clocksList.add(a);

            a = new Clocks("506","White Ross Clock", "749", covers[6],
                    "Clocks6 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1146cm" , "Clocks 6" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 6" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 6");
            clocksList.add(a);

            a = new Clocks("507","Block in between", "1100", covers[7],
                    "Clocks7 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1147cm" , "Clocks 7" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 7" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 7");
            clocksList.add(a);

            a = new Clocks("508","Flor Clock Blue", "600", covers[8],
                    "Clocks8 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1148cm" , "Clocks 8" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 8" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 8");
            clocksList.add(a);

            a = new Clocks("509","Reloj pared madera", "450", covers[9],
                    "Clocks9 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1149cm" , "Clocks 9" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Clocks 9" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Clocks 9");
            clocksList.add(a);

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