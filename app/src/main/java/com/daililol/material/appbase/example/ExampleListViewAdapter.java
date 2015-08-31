package com.daililol.material.appbase.example;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daililol.material.appbase.utility.Converter;

import java.util.Vector;

/**
 * Created by DennyShum on 8/22/15.
 */
public class ExampleListViewAdapter extends BaseAdapter{

    private Vector<String> dataSet;

    public ExampleListViewAdapter(Vector<String> dataSet){
        this.dataSet = dataSet;
    }


    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View itemView, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (itemView == null){
            itemView = new LinearLayout(viewGroup.getContext());
            ((LinearLayout)itemView).setOrientation(LinearLayout.HORIZONTAL);
            AbsListView.LayoutParams layoutParams =
                    new AbsListView.LayoutParams(AdapterView.LayoutParams.MATCH_PARENT, Converter.dp2px(itemView.getContext(), 56));

            itemView.setPadding(Converter.dp2px(itemView.getContext(), 16), 0,
                    Converter.dp2px(itemView.getContext(), 16), 0);
            ((LinearLayout)itemView).setGravity(Gravity.LEFT | Gravity.START | Gravity.CENTER);
            itemView.setBackgroundResource(R.drawable.touch_feedback_holo_light);
            itemView.setLayoutParams(layoutParams);



            ImageView imageView = new ImageView(viewGroup.getContext());
            int imageSize = Converter.dp2px(viewGroup.getContext(), 64);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(imageSize, imageSize);
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setId(android.R.id.icon1);

            TextView textView = new TextView(viewGroup.getContext());
            textView.setTextSize(18);
            textView.setId(android.R.id.text1);

            ((LinearLayout)itemView).addView(imageView);
            ((LinearLayout)itemView).addView(textView);

            viewHolder = new ViewHolder(itemView);

        }else{
            viewHolder = (ViewHolder)itemView.getTag();
        }

        viewHolder.textView.setText(dataSet.get(i));
        if (i == 3) {
            viewHolder.imageView.setImageResource(R.drawable.collapse_slash);
            viewHolder.imageView.setVisibility(View.VISIBLE);
        }else{
            viewHolder.imageView.setVisibility(View.GONE);
        }

        return itemView;
    }

    class ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            textView = (TextView)itemView.findViewById(android.R.id.text1);
            imageView = (ImageView)itemView.findViewById(android.R.id.icon1);
            itemView.setTag(this);
        }
    }
}
