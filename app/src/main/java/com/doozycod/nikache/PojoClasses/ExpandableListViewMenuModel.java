package com.doozycod.nikache.PojoClasses;

import java.util.HashMap;

public  class ExpandableListViewMenuModel {

    public static String listDataHeader[];
    public static HashMap<String, String[]> listDataChild;

    /*
     * Preparing the list data
     */
    public static void prepareListData() {

        listDataHeader = new String[5];
        listDataChild = new HashMap<String, String[]>(); // key (listDataHeader) , value (listDataChild)

        /**
         * Adding the Title of Navigation drawer's
         * menu item in the "listDataHeader" array.
         */
        listDataHeader[0] = "Shop by category";
        listDataHeader[1] = "Hot deal of day";
        listDataHeader[2] = "My Account";
        listDataHeader[3] = "Contact Us";
        listDataHeader[4] = " ";


        /**
         * Adding the Title of Navigation drawer's
         * Sub-menu item in the "Sub-Menu" array.
         */
        String shopByCategory[] = new String[6];
        shopByCategory[0] = "Wall Decor";
        shopByCategory[1] = "Furniture";
        shopByCategory[2] = "Utility Items";
        shopByCategory[3] = "Bras Decor";
        shopByCategory[4] = "Clocks";
        shopByCategory[5] = "Antiques";

        String hotDealOfDay[] = new String[0];

        String myAccount[] = new String[0];

        String contactUs[] = new String[0];

        String socialMedia[] = new String[0];


        /**
         * Here "listDataChild" is using the HashMap
         * Data Structure to store the data. It's
         * first parameter is the "index position" of
         * listDataHeader's Array and second parameter is
         * the sub-menu array name.
         */
        listDataChild.put(listDataHeader[0], shopByCategory); // Header, sub-menu array
        listDataChild.put(listDataHeader[1], hotDealOfDay);
        listDataChild.put(listDataHeader[2], myAccount);
        listDataChild.put(listDataHeader[3], contactUs);
        listDataChild.put(listDataHeader[4], socialMedia);

    }


}
