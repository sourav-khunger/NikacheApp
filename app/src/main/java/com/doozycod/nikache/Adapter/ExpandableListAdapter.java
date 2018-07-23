package com.doozycod.nikache.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.doozycod.nikache.R;

import java.util.HashMap;

/**
 * Created by sunilkumar on 08/06/18.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

//    ExpandableListView expandList;
    private Context _context;
    private String _listDataHeader[]; // header titles
    // child data in format of header title, child title
    private HashMap<String, String[]> _listDataChild;

    // Constructor
    public ExpandableListAdapter(Context context, String _listDataHeader[], HashMap<String, String[]> _listDataChild) {
        this._context = context;
        this._listDataHeader = _listDataHeader;
        this._listDataChild = _listDataChild;

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {

            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        //------*************------Setting the attribute of TextView that display as header of Navigation Drawer menu.------***************----------

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader);

            lblListHeader.setTypeface(null, Typeface.NORMAL);
            lblListHeader.setText(headerTitle);
            lblListHeader.setTextSize(16);
            lblListHeader.setTextColor(Color.parseColor("#1F4C23")); // Green color

        //---------------------*************------Showing and Hiding Up/Down Arrow on Header-------------***************------------------------------

            // changing the up/down arrow on expanded list view
            ImageView imgUpDownArrow = (ImageView) convertView
                    .findViewById(R.id.iv_plus_icon);

            if (groupPosition == 0) {
                if (isExpanded) {
                    imgUpDownArrow.setImageResource(R.drawable.ic_keyboard_arrow_up_green_24dp);
                } else {
                    imgUpDownArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_green_24dp);
                }
            }

            if (groupPosition == 1 || groupPosition == 2 || groupPosition == 3 || groupPosition == 4) {
                imgUpDownArrow.setImageResource(0);
            }

        //---------------------*************-------Not displaying text on header at 4th position index------------***************----------------------

            if (groupPosition == 4) {
                TextView tvLbListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
                tvLbListHeader.setText("");


            } // position 4 closed i.e - header containing social icon

        //---------------------*************-------Hiding Social site icon on header from index 0 to 3------------***************------------------------

            if (groupPosition == 0 || groupPosition == 1 || groupPosition == 2 || groupPosition == 3) {

                ImageView ivFacebook = (ImageView) convertView
                        .findViewById(R.id.iv_facebook);
                ivFacebook.setImageResource(0);
                ivFacebook.setBackground(null);

                ImageView ivTwitter = (ImageView) convertView
                        .findViewById(R.id.iv_twitter);
                ivTwitter.setImageResource(0);
                ivTwitter.setBackground(null);

                ImageView ivYoutube = (ImageView) convertView
                        .findViewById(R.id.iv_youtube);
                ivYoutube.setImageResource(0);
                ivYoutube.setBackground(null);
            }

        //---------------------*************-------***************************************************------------***************------------------------

        return convertView;
    } // getGroupView method close here.

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
//            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        txtListChild.setTextSize(14);
        txtListChild.setTypeface(null, Typeface.NORMAL);
        txtListChild.setTextColor(Color.parseColor("#808080")); // color code grey

//        if (groupPosition == 7 && childPosition == 0) {
//            iconOnLeftSideOfSubItem.setImageResource(R.drawable.fb_icon_wo_bg_green_15);
//        } else if (groupPosition == 7 && childPosition == 1) {
//            iconOnLeftSideOfSubItem.setImageResource(R.drawable.ig_icon_wo_bg_green_15);
//        } else if (groupPosition == 7 && childPosition == 2) {
//            iconOnLeftSideOfSubItem.setImageResource(R.drawable.t_n_c_wo_bg_15dp);
//        } else {
//            iconOnLeftSideOfSubItem.setImageResource(0);
//        }

        return convertView;
    } // getChildView method close here.


    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader[groupPosition])[childPosititon];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader[groupPosition]).length;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

//    public interface setOnChildClickListener {
//        public void OnChildClickListener(View v, int ItemPosition, int SubItemPosition);
//    }

}
