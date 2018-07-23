package com.doozycod.nikache.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

public class HintAdapter   extends ArrayAdapter<String> {



    public HintAdapter(Context theContext, String[] objects, int theLayoutResId) {
        super(theContext, theLayoutResId, objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}