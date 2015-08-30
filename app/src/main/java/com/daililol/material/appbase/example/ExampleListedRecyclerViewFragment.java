package com.daililol.material.appbase.example;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daililol.material.appbase.base.BaseRecyclerViewFragment;
import com.daililol.material.appbase.component.BaseRecyclerViewOnItemClickListener;

/**
 * Created by DennyShum on 8/21/15.
 */
public class ExampleListedRecyclerViewFragment extends BaseRecyclerViewFragment
        implements BaseRecyclerViewOnItemClickListener.ItemOnClickListener{


    @Override
    protected Object onDoTaskInBackground(Object... params) {
        return null;
    }

    @Override
    protected void onFragmentCreated(Bundle savedInstanceState) {
        this.getProgressBar().setVisibility(View.GONE);
        this.getRefreshLayout().setEnabled(false);
    }

    @Override
    protected void setupRecyclerView(RecyclerView recyclerView) {
        ExampleRecyclerViewAdapter adapter = new ExampleRecyclerViewAdapter(null, recyclerView.getLayoutManager());

        adapter.addItem("Item 0");
        adapter.addItem("Item 1");
        adapter.addItem("Item 2");
        adapter.addItem("Item 3");
        adapter.addItem("Item 4");
        adapter.addItem("Item 5");
        adapter.addItem("Item 6");
        adapter.addItem("Item 7");
        adapter.addItem("Item 8");
        adapter.addItem("Item 9");
        adapter.addItem("Item 10");
        adapter.addItem("Item 11");
        adapter.addItem("Item 12");
        adapter.addItem("Item 13");
        adapter.addItem("Item 14");

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new BaseRecyclerViewOnItemClickListener(getActivity(), this));
    }

    @Override
    protected RecyclerView.LayoutManager setupRecyclerViewLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        return linearLayoutManager;
    }

    @Override
    protected void handleUiMessage(Message msg) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(View childView, int position) {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
        }
    }

    @Override
    public void onItemLongClick(View childView, int position) {

    }


}
