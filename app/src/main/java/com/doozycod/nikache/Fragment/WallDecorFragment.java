package com.doozycod.nikache.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doozycod.nikache.Adapter.WallDecorsAdapter;
import com.doozycod.nikache.PojoClasses.WallDecor;
import com.doozycod.nikache.R;
import com.doozycod.nikache.RecyclerTouchListener;
import com.doozycod.nikache.WallDecorProductDetails;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 */
public class WallDecorFragment extends Fragment  {

    private RecyclerView recyclerView;
    private WallDecorsAdapter adapter;
    private ArrayList<WallDecor> wallDecorList;
    private static final int CURSOR_LOADER_ID = 4;
    ActionBar actionBar;
    public static final String WALL_DECOR_KEY = "wallDecor";
    public static final String WALL_DECOR_CURRENT_POSITION = "wallDecorCurrPos";
    public static final String WALL_DECOR_LIST_DATA = "wallDecorListData";



        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_wall_decor, container, false);

//            actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//            // Setting the color of title on action bar
//            Spannable text = new SpannableString(actionBar.getTitle());
//            text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//            actionBar.setTitle(text);
//            //-----------------------------



//            EditText etSearchBar = (EditText)view.findViewById(R.id.et_search_bar);
//            etSearchBar.setVisibility(View.INVISIBLE);
//            etSearchBar.setVisibility(View.GONE);

            recyclerView =(RecyclerView) view.findViewById(R.id.recycler_view);

            wallDecorList = new ArrayList<>();
//            name = wallDecorList.get(0).getProductId();
            adapter = new WallDecorsAdapter(getContext(), wallDecorList);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
//            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));

            recyclerView.setAdapter(adapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    WallDecor currentWallDecor = wallDecorList.get(position);
                    Intent intent = new Intent(getContext(), WallDecorProductDetails.class);
                    intent.putExtra(WALL_DECOR_KEY, currentWallDecor);
                    intent.putExtra(WALL_DECOR_CURRENT_POSITION, position);
                    intent.putExtra(WALL_DECOR_LIST_DATA, wallDecorList);
                    startActivity(intent);
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

            prepareAlbums();

//            getLoaderManager().initLoader(CURSOR_LOADER_ID, null, favoriteLoaderManager);


            return view;
        }


        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }

    private void prepareAlbums(){
        int[] covers = new int[]{
                R.drawable.welcome,
                R.drawable.satin_matt_textured,
                R.drawable.key_hanger,
                R.drawable.buddha_face,
                R.drawable.wood_key_holder_300,
                R.drawable.sun_face_300,
                R.drawable.sun_wall,
                R.drawable.frame_300,
                R.drawable.krishna_key_holder,
                R.drawable.tree_mirror,

        };

        WallDecor a = new WallDecor("100","Welcome", "1300", covers[0],
                "Fusce et congue dolor, id placerat lorem. Vestibulum\n" +
                "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                "a aliquam ante rutrum eu." , "74cm" , "Regular Fit" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: 100% Acetate" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* Centre-back vent");
        wallDecorList.add(a);

        a = new WallDecor("101","Satin Matt Textured", "800", covers[1],
                "Wall Decor2 congue dolor, id placerat lorem. Vestibulum\n" +
                        "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                        "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                        "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                        "a aliquam ante rutrum eu." , "Wall Decor2" , "Wall Decor2" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Wall Decor2" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* CentreWall Decor2");
        wallDecorList.add(a);

        a = new WallDecor("102","Key Hanger", "1100", covers[2],
                "Wall Decor3 congue dolor, id placerat lorem. Vestibulum\n" +
                        "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                        "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                        "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                        "a aliquam ante rutrum eu." , "Wall Decor3" , "Wall Decor3" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Wall Decor3" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* CentreWall Decor3");
        wallDecorList.add(a);

        a = new WallDecor("103","Buddha Face", "1200", covers[3],
                "Wall Decor4 congue dolor, id placerat lorem. Vestibulum\n" +
                        "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                        "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                        "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                        "a aliquam ante rutrum eu." , "Wall Decor4" , "Wall Decor4" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Wall Decor4" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* CentreWall Decor4");
        wallDecorList.add(a);

        a = new WallDecor("104","Wood Key Holder", "1400", covers[4],
                "Wall Decor5 congue dolor, id placerat lorem. Vestibulum\n" +
                        "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                        "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                        "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                        "a aliquam ante rutrum eu." , "Wall Decor5" , "Wall Decor5" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Wall Decor5" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* CentreWall Decor5");
        wallDecorList.add(a);

        a = new WallDecor("105","Sun Face", "1000", covers[5],
                "Wall Decor6 congue dolor, id placerat lorem. Vestibulum\n" +
                        "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                        "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                        "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                        "a aliquam ante rutrum eu." , "Wall Decor6" , "Wall Decor6" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Wall Decor6" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* CentreWall Decor6");
        wallDecorList.add(a);

        a = new WallDecor("106","Sun Wall", "1100", covers[6],
                "Wall Decor7 congue dolor, id placerat lorem. Vestibulum\n" +
                        "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                        "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                        "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                        "a aliquam ante rutrum eu." , "Wall Decor7" , "Wall Decor7" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Wall Decor7" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* CentreWall Decor7");
        wallDecorList.add(a);

        a = new WallDecor("107","Frame", "1450", covers[7],
                "Wall Decor8 congue dolor, id placerat lorem. Vestibulum\n" +
                        "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                        "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                        "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                        "a aliquam ante rutrum eu." , "Wall Decor8" , "Wall Decor8" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Wall Decor9" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* CentreWall Decor9");
        wallDecorList.add(a);

        a = new WallDecor("108","Krishna Key Holder", "1165", covers[8],
                "Wall Decor10 congue dolor, id placerat lorem. Vestibulum\n" +
                        "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                        "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                        "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                        "a aliquam ante rutrum eu." , "Wall Decor10" , "Wall Decor10" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Wall Decor10" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* CentreWall Decor10");
        wallDecorList.add(a);

        a = new WallDecor("109","Tree Mirror", "1799", covers[9],
                "Wall Decor10 congue dolor, id placerat lorem. Vestibulum\n" +
                        "et ultrices arcu. Aliquam facilisis turpis id maximus luctus.\n" +
                        "Sed augue tortor, gravida id accumsan vitae, tempor nec nibh.\n" +
                        "Proin elementum lobortis sodales. Nunc molestie vulputate libero,\n" +
                        "a aliquam ante rutrum eu." , "Wall Decor10" , "Wall Decor10" ,
                "50% Linen &amp; 50% Polyamide;\nBody Lining: 100% Cotton;\nLining: Wall Decor10" ,
                "* Notched lapels\n* Twin button front fastening\n* Front patch pockets; chest pocket\n* Internal pockets\n* CentreWall Decor10");
        wallDecorList.add(a);

        adapter.notifyDataSetChanged();
    }

//    @Override
//    public void wallDecorInfo(int position) {
//
//            WallDecor itemView = wallDecorList.get(position);
//
////        int itemPosition = recyclerView.getChildLayoutPosition(view);
////            String item = mList.get(itemPosition);
////            Toast.makeText(mContext,"onItemClicked",Toast.LENGTH_LONG).show();
//
//        Toast.makeText(getContext(),"onItemClicked",Toast.LENGTH_LONG).show();
//
////        Intent intent = new Intent(getContext(), WallDecorProductDetails.class);
////        intent.putExtra("WallDecorProduct", wallDecor);
////        startActivity(intent);
//    }


//    private  void updateFavoriteButtons() {
//        new AsyncTask<Void, Void, Boolean>() {
//            @Override
//            protected Boolean doInBackground(Void... params) {
//                return isFavorite();
//            }
//
//            @Override
//            protected void onPostExecute(Boolean isFavorite) {
//
//                Log.i(ISFAVOURITE,isFavorite.toString());
//
//                if (isFavorite) {
//                    lUnFavourite.setVisibility(View.GONE);
//                    lFavourite.setVisibility(View.VISIBLE);
//                } else {
//                    lFavourite.setVisibility(View.GONE);
//                    lUnFavourite.setVisibility(View.VISIBLE);
//
//                }
//            }
//        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//
//    }


//    private android.app.LoaderManager.LoaderCallbacks<Cursor> favoriteLoaderManager = new android.app.LoaderManager.LoaderCallbacks<Cursor>() {
//        @Override
//        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//            return new CursorLoader(getContext(), NikacheContract.ProductEntry.CONTENT_URI, NikacheContract.ProductEntry.PRODUCT_COLUMNS, null, null, null);
//        }
//
//        @Override
//        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//            adapter.add(data);
//        }
//
//        @Override
//        public void onLoaderReset(Loader<Cursor> loader) {
//            wallDecorList.clear();
//
//        }
//    };

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