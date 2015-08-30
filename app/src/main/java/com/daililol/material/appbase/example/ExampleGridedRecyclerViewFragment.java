package com.daililol.material.appbase.example;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daililol.material.appbase.base.BaseRecyclerViewFragment;
import com.daililol.material.appbase.component.BaseGridedRecyclerViewDivider;
import com.daililol.material.appbase.component.BaseRecyclerViewOnItemClickListener;
import com.daililol.material.appbase.utility.Converter;

import java.util.Vector;

/**
 * Created by DennyShum on 8/21/15.
 */
public class ExampleGridedRecyclerViewFragment extends BaseRecyclerViewFragment
        implements BaseRecyclerViewOnItemClickListener.ItemOnClickListener{


    private Vector<String> dataSet;
    ExampleRecyclerViewAdapter adapter;

    @Override
    protected Object onDoTaskInBackground(Object... params) {

        //Simulating a non-ui-thread

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 60; i++){
            dataSet.add("Item " + i);
        }

        sendUiMessage(0);
        return null;
    }

    @Override
    protected void onFragmentCreated(Bundle savedInstanceState) {
        this.getRefreshLayout().setEnabled(false);
        this.doTaskInBackground();

    }

    @Override
    protected void setupRecyclerView(RecyclerView recyclerView) {

        //Initialize the dataSet and adapter.
        dataSet = new Vector<String>();
        adapter = new ExampleRecyclerViewAdapter(dataSet, recyclerView.getLayoutManager());

        //Adding a divider to the recyclerView
        recyclerView.addItemDecoration(new BaseGridedRecyclerViewDivider(getActivity(), Color.WHITE,
                Converter.dp2px(this.getActivity(), 1)));

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new BaseRecyclerViewOnItemClickListener(getActivity(), this));
    }

    @Override
    protected RecyclerView.LayoutManager setupRecyclerViewLayoutManager() {
        //I will create a 2-column GridView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        return gridLayoutManager;
    }

    @Override
    protected void handleUiMessage(Message msg) {
        adapter.notifyDataSetChanged();
        getProgressBar().setVisibility(View.GONE);
        this.getRefreshLayout().setEnabled(true);
    }

    @Override
    public void onRefresh() {
        getRefreshLayout().setRefreshing(false);
    }

    @Override
    public void onItemClick(View childView, int position) {

    }

    @Override
    public void onItemLongClick(View childView, int position) {

    }


}
