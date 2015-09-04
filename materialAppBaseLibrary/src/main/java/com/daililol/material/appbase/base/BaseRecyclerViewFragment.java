package com.daililol.material.appbase.base;

import com.daililol.material.appbase.R;
import com.daililol.material.appbase.widget.MaterialRaisedButton;
import com.daililol.material.appbase.widget.MultiSwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public abstract class BaseRecyclerViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

	private MultiSwipeRefreshLayout refreshLayout;
	private ProgressBar progressBar;
	private RecyclerView recyclerView;
	
	private LinearLayout emptyView;
	private TextView emptyViewEmptyMessage;
	private MaterialRaisedButton emptyViewRefreshButton;
	
	
	/**
	 * first step
	 */
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.base_recyclerview_fragment, container, false);
		
		refreshLayout = (MultiSwipeRefreshLayout)rootView.findViewById(R.id.refreshLayout);
		progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
		recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
		emptyView = (LinearLayout)rootView.findViewById(R.id.emptyView);
		emptyViewRefreshButton = (MaterialRaisedButton) rootView.findViewById(R.id.emptyViewRefreshButton);
		emptyViewEmptyMessage = (TextView)rootView.findViewById(R.id.emptyViewEmptyMessage);
		getRecyclerView().setLayoutManager(setupRecyclerViewLayoutManager());
		getRefreshLayout().setOnRefreshListener(this);
        getRefreshLayout().setSwipeableChildren(R.id.recyclerView, R.id.emptyView);
		return rootView;
	}
	
	
	
	
	/**
	 * Second step
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		setupRecyclerView(getRecyclerView());
        onFragmentCreated(savedInstanceState);
	}
	
	protected void setEmptyMessage(CharSequence message, boolean sholdShowRefreshButton){
		getProgressBar().setVisibility(View.GONE);
		getRecyclerView().setVisibility(View.GONE);
		getEmptyView().setVisibility(View.VISIBLE);
		setEmptyText(message.toString());
		getRefreshButton().setVisibility(sholdShowRefreshButton ? View.VISIBLE : View.GONE);
	}

	protected abstract void onFragmentCreated(Bundle savedInstanceState);
	
	protected abstract void setupRecyclerView(RecyclerView recyclerView);


    /**
     * LinearLayoutManager - When you want the recyclerView layout like a ListVew
     * GridLayoutManager - When you want the recyclerView layout like a GridView
     * StaggeredGridLayoutManager -  Will layout like a waterfall grid view
     * @return LinearLayoutManager, GridLayoutManager, StaggeredGridLayoutManager
     */
	protected abstract LayoutManager setupRecyclerViewLayoutManager();


    /**
     * The pull to refresh layout.
     * @return refreshLayout
     */
	protected MultiSwipeRefreshLayout getRefreshLayout(){
		return refreshLayout;
	}


    /**
     *
     * @return RecyclerView
     */
	protected RecyclerView getRecyclerView(){
		return recyclerView;
	}

	protected ProgressBar getProgressBar(){
		return progressBar;
	}
	

	protected View getEmptyView(){
		return emptyView;
	}
	
	private void setEmptyText(String emptyText){
		emptyViewEmptyMessage.setText(emptyText);
	}
	
	protected void setEmptyText(int resourceId){
		emptyViewEmptyMessage.setText(resourceId);
	}
	
	
	protected CharSequence getEmptyText(){
		return emptyViewEmptyMessage.getText();
	}
	
	protected MaterialRaisedButton getRefreshButton(){
		return emptyViewRefreshButton;
	}
	
	

}
