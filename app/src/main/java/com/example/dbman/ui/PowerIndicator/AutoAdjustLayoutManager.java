package com.example.dbman.ui.PowerIndicator;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class AutoAdjustLayoutManager extends LinearLayoutManager {

	public AutoAdjustLayoutManager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
		try {
			View view = recycler.getViewForPosition(0);
			if (view != null) {
				measureChild(view, widthSpec, heightSpec);
				int measuredWidth = View.MeasureSpec.getSize(widthSpec);
				int measuredHeight = view.getMeasuredHeight();
				setMeasuredDimension(measuredWidth, measuredHeight);
			}
		}catch (Exception e){
			super.onMeasure(recycler,state,widthSpec,heightSpec);
		}
	}
}