package com.daililol.material.appbase.component;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static android.support.v7.widget.RecyclerView.OnItemTouchListener;


public class BaseRecyclerViewOnItemClickListener implements
OnItemTouchListener,
GestureDetector.OnGestureListener,
View.OnClickListener{

	private View currentView;
	private GestureDetector gustureDetetor;
    private ItemOnClickListener itemOnClickListener;
	
	public BaseRecyclerViewOnItemClickListener(Context context, ItemOnClickListener itemOnClickListener){

        this.itemOnClickListener = itemOnClickListener;
		gustureDetetor = new GestureDetector(context, this);
		gustureDetetor.setIsLongpressEnabled(true);
		
	}
	

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		if (currentView != null && itemOnClickListener != null){
            itemOnClickListener.onItemClick(currentView, (Integer)currentView.getTag());
		}
		
		return false;
	}
	
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
        if (currentView != null && itemOnClickListener != null){
            itemOnClickListener.onItemLongClick(currentView, (Integer) currentView.getTag());
        }
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
		// TODO Auto-generated method stub
		currentView = recyclerView.findChildViewUnder(e.getX(), e.getY());
		if (currentView == null) return false;
		
		int position = recyclerView.getChildAdapterPosition(currentView);
		currentView.setTag(position);
		currentView.setOnClickListener(this);
		
		
		gustureDetetor.onTouchEvent(e);
		return false;
	}



	@Override
	public void onTouchEvent(RecyclerView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    public static interface ItemOnClickListener{
        public void onItemClick(View childView, int position);
        public void onItemLongClick(View childView, int position);
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	
	

}
