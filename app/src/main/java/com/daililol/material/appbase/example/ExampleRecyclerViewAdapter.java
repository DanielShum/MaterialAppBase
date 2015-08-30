package com.daililol.material.appbase.example;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daililol.material.appbase.utility.Converter;
import com.daililol.material.appbase.utility.DeviceUtil;

import java.util.Vector;

/**
 * Created by DennyShum on 8/21/15.
 */
public class ExampleRecyclerViewAdapter extends RecyclerView.Adapter<ExampleRecyclerViewAdapter.ViewHolder>{

    private Vector<String> listItems;
    private RecyclerView.LayoutManager layoutManager;

    public ExampleRecyclerViewAdapter(Vector<String> dataSet, RecyclerView.LayoutManager layoutManager){

        this.layoutManager = layoutManager;
        listItems = dataSet;
        if (listItems == null) listItems = new Vector<String>();
    }

    public void addItem(String itemText){
        listItems.add(itemText);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout itemView = new LinearLayout(parent.getContext());


        TextView textView = new TextView(parent.getContext());
        textView.setTextSize(18);
        textView.setId(android.R.id.text1);


        RecyclerView.LayoutParams layoutParams =
                new RecyclerView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Converter.dp2px(itemView.getContext(), 56));

        itemView.setPadding(Converter.dp2px(itemView.getContext(), 16), 0,
                Converter.dp2px(itemView.getContext(), 16), 0);
        itemView.setGravity(Gravity.LEFT | Gravity.START | Gravity.CENTER);
        itemView.setBackgroundResource(R.drawable.touch_feedback_holo_light);
        itemView.addView(textView);
        itemView.setLayoutParams(layoutParams);
        if (this.layoutManager instanceof GridLayoutManager){
            calculateGridItemSize(itemView);
        }

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(listItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    private int calculateGridItemSize(View itemView){
        int size = (DeviceUtil.getScreenSize(itemView.getContext()).x - Converter.dp2px(itemView.getContext(), 20)) / 2;
        itemView.getLayoutParams().height = size;
        itemView.getLayoutParams().width = size;
        return size;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(android.R.id.text1);

        }
    }
}
