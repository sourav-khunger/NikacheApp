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

import com.doozycod.nikache.Adapter.AntiquesAdapter;
import com.doozycod.nikache.AntiquesProductDetails;
import com.doozycod.nikache.PojoClasses.Antiques;
import com.doozycod.nikache.R;
import com.doozycod.nikache.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AntiquesFragment extends Fragment {

        private RecyclerView recyclerView;
        private AntiquesAdapter adapter;
        private ArrayList<Antiques> antiquesList;
        public static final String ANTIQUES_KEY = "antiques";
        public static final String ANTIQUES_CURRENT_POSITION = "antiquesCurrPos";
        public static final String ANTIQUES_LIST_DATA = "antiquesListData";

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_antiques, container, false);


//            EditText etSearchBar = (EditText)view.findViewById(R.id.et_search_bar);
//            etSearchBar.setVisibility(View.INVISIBLE);
//            etSearchBar.setVisibility(View.GONE);

            recyclerView =(RecyclerView) view.findViewById(R.id.recycler_view);

            antiquesList = new ArrayList<>();
            adapter = new AntiquesAdapter(getContext(), antiquesList);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));

            recyclerView.setAdapter(adapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Antiques currentantiques = antiquesList.get(position);
                    Intent intent = new Intent(getContext(), AntiquesProductDetails.class);
                    intent.putExtra(ANTIQUES_KEY, currentantiques);
                    intent.putExtra(ANTIQUES_CURRENT_POSITION, position);
                    intent.putExtra(ANTIQUES_LIST_DATA, antiquesList);
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
                    R.drawable.glided_mirror_300,
                    R.drawable.marble_top_bistro_300,
                    R.drawable.schumacher_300,
                    R.drawable.balthazar_martinot_300,
                    R.drawable.table_5_300,
                    R.drawable.cabinet_300,
                    R.drawable.drum_table_7_300,
                    R.drawable.tea_kettle_8_300,
                    R.drawable.calvin_300,
                    R.drawable.xv_style_bed_300,

            };

            Antiques a = new Antiques("600","Glided Mirror", "4300", covers[0],
                    "Antiques0 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1240cm" , "Antiques 0" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 0" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 0");
            antiquesList.add(a);

            a = new Antiques("601","Marble Top Bistro", "480", covers[1],
                    "Antiques1 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1241cm" , "Antiques 1" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 1" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 1");
            antiquesList.add(a);

            a = new Antiques("602","Schumacher", "1250", covers[2],
                    "Antiques2 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1242cm" , "Antiques 2" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 2" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 2");
            antiquesList.add(a);

            a = new Antiques("603","Balthazar Martinot", "630", covers[3],
                    "Antiques3 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1243cm" , "Antiques 3" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 3" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 3");
            antiquesList.add(a);

            a = new Antiques("604","Table", "270", covers[4],
                    "Antiques4 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1244cm" , "Antiques 4" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 4" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 4");
            antiquesList.add(a);

            a = new Antiques("605","Cabinet", "850", covers[5],
                    "Antiques5 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1245cm" , "Antiques 5" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 5" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 5");
            antiquesList.add(a);

            a = new Antiques("606","Drum table", "749", covers[6],
                    "Antiques6 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1246cm" , "Antiques 6" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 6" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 6");
            antiquesList.add(a);

            a = new Antiques("607","Tea Kettle", "1100", covers[7],
                    "Antiques7 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1247cm" , "Antiques 7" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 7" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 7");
            antiquesList.add(a);

            a = new Antiques("608","Calvin", "600", covers[8],
                    "Antiques8 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1248cm" , "Antiques 8" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 8" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 8");
            antiquesList.add(a);

            a = new Antiques("609","XV Style Bed", "450", covers[9],
                    "Antiques9 congue dolor, id placerat lorem. Vestibulum\n" +
                            "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                            "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                            "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                            "a aliquam ante rutrum eu." , "1249cm" , "Antiques 9" ,
                    "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Antiques 9" ,
                    "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Antiques 9");
            antiquesList.add(a);

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