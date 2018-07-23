package com.doozycod.nikache.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doozycod.nikache.PojoClasses.Category;
import com.doozycod.nikache.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<Category> categoryList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Category category = categoryList.get(position);

        holder.footer.setText(category.getTitle());

        Glide.with(mContext).load(category.getThumbnail()).into(holder.thumbnail);

    }





    @Override
    public int getItemCount() {
        int categorySize = categoryList.size();
        String categorySizeInString = String.valueOf(categoryList.size());
//        Toast.makeText(mContext,categorySizeInString,Toast.LENGTH_LONG).show();
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView footer;
        public ImageView thumbnail, foreground;


        public MyViewHolder(View itemView) {
            super(itemView);

            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            foreground = (ImageView) itemView.findViewById(R.id.foreground);
            footer = (TextView) itemView.findViewById(R.id.tv_title);


        }
    }

    public CategoryAdapter(Context mContext, List<Category> categoryList){
        this.mContext = mContext;
        this.categoryList = categoryList;
    }


}
