package com.example.dbman.ui.PowerIndicator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

/**
 * This class implements the main functionalities of the TableAdapter in
 * Mutuactivos.
 * 
 * 
 * @author Brais Gabín
 */
public abstract class FixedColTableAdapter extends SampleTableAdapter {
	private final Context context;
	private final LayoutInflater inflater;
	private int columns;
	private String [] headers;

	protected abstract  String [] getHeaders();

	/**
	 * Constructor
	 *
	 * @param context
	 *            The current context.
	 */
	public FixedColTableAdapter(Context context) {
		super(context);
		this.context = context;
		this.headers=getHeaders();
		this.columns = headers.length;
		inflater = LayoutInflater.from(context);
	}

	/**
	 * Returns the context associated with this array adapter. The context is
	 * used to create views from the resource passed to the constructor.
	 * 
	 * @return The Context associated with this adapter.
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * Quick access to the LayoutInflater instance that this Adapter retreived
	 * from its Context.
	 * 
	 * @return The shared LayoutInflater.
	 */
	public LayoutInflater getInflater() {
		return inflater;
	}

	@Override
	public View getView(int row, int column, View converView, ViewGroup parent) {
		if (converView == null) {
			if (row == 0) {
				converView = inflater.inflate(getHeaderViewResource(), parent, false);
			}else {
				converView = inflater.inflate(getBodyViewResource(), parent, false);
			}
		}
		setText(converView, getCellString(row, column));
		return converView;
	}

	@Override
	public  String getCellString(int row, int column){
		if (row == 0){
			return headers[column];
		}else{
			return getCellText(row-1,column);
		}
	}
	/**
	 * Sets the text to the view.
	 * 
	 * @param view
	 * @param text
	 */
	private void setText(View view, String text) {
		((TextView) view.findViewById(android.R.id.text1)).setText(text);
	}

	/**
	 * @param row
	 *            the title of the row of this header. If the column is -1
	 *            returns the title of the row header.
	 * @param column
	 *            the title of the column of this header. If the column is -1
	 *            returns the title of the column header.
	 * @return the string for the cell [row, column]
	 */
	public abstract String getCellText(int row, int column);
	protected abstract int getHeaderViewResource();
	public abstract int getBodyViewResource();
}
