package com.daililol.material.appbase.base;

import com.daililol.material.appbase.R;
import com.daililol.material.appbase.widget.ExtendableListView;
import com.daililol.material.appbase.widget.MaterialRaisedButton;
import com.daililol.material.appbase.widget.MultiSwipeRefreshLayout;
import com.daililol.material.appbase.widget.RetriableListFooter;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public abstract class BaseListViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{



	private MultiSwipeRefreshLayout refreshLayout;
	private ProgressBar progressBar;
	private RetriableListFooter retriableListFooter;
	
	private ExtendableListView listView;
	private LinearLayout emptyView;
	private TextView emptyViewEmptyMessage;
	private MaterialRaisedButton emptyViewRefreshButton;
	
	/**
	 * First step
	 */
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.base_listview_fragment, container, false);
		refreshLayout = (MultiSwipeRefreshLayout)rootView.findViewById(R.id.refreshLayout);
		progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
		listView = (ExtendableListView)rootView.findViewById(R.id.listView);
		emptyView = (LinearLayout)rootView.findViewById(R.id.emptyView);
		emptyViewRefreshButton = (MaterialRaisedButton) rootView.findViewById(R.id.emptyViewRefreshButton);
		emptyViewEmptyMessage = (TextView)rootView.findViewById(R.id.emptyViewEmptyMessage);
		
		listView.setEmptyView(emptyView);
		refreshLayout.setSwipeableChildren(R.id.listView, R.id.emptyView);
		refreshLayout.setOnRefreshListener(this);
		setupFooter();
		
		if (Build.VERSION.SDK_INT < 21){
			listView.setSelector(R.drawable.touch_feedback_holo_light);
		}
		
        return rootView;
    }
	
	
	/**
	 * Second step
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		setupListView(listView);
        onFragmentCreated(savedInstanceState);
	}

	/**
	 * this method will be called when the fragment is created and is attached to the mother activity
	 * @param savedInstanceState
	 */
	abstract protected void onFragmentCreated(Bundle savedInstanceState);
	
	/**
	 * this method will be called after onFragmentCreated(Bundle savedInstanceState) is done
	 * @param listView
	 */
	abstract protected void setupListView(ExtendableListView listView);

	

	
	
	protected View getEmptyView(){
		return emptyView;
	}
	
	protected void setEmptyText(String emptyText){
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
	
	protected MultiSwipeRefreshLayout getRefreshLayout(){
		return refreshLayout;
	}
	
	protected ProgressBar getProgressBar(){
		return progressBar;
	}
	
	protected ExtendableListView getListView(){
		return listView;
	}
	

	
	protected RetriableListFooter getListViewFooter(){
		return retriableListFooter;
	}
	
	private void setupFooter(){
		retriableListFooter = new RetriableListFooter(getActivity());
		retriableListFooter.showView(RetriableListFooter.ViewType.LOADING);
		retriableListFooter.setVisibility(View.GONE);
		listView.addFooterView(retriableListFooter, "", false);
	}
	

	
	
}
