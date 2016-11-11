package com.example.dbman.ui.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.dbman.ui.R;

public class NavigationMenuAdapter extends ArrayAdapter {



    private static final int[] mIcons = {
            R.mipmap.ic_menu_home,
            R.mipmap.home_mbank_1_normal,
            R.mipmap.home_mbank_2_normal,
            R.mipmap.home_mbank_3_normal,
            R.mipmap.home_mbank_4_normal,
            R.mipmap.home_mbank_5_normal,
            R.mipmap.home_mbank_6_normal
    };
    private static final int[] m_nTitles = {
            R.string.title_home_0,
            R.string.title_home_1,
            R.string.title_home_2,
            R.string.title_home_3,
            R.string.title_home_4,
            R.string.title_home_5,
            R.string.title_home_6,
    };

    private Context mContext;
	private String[] mTitles;


	public NavigationMenuAdapter(Context context){
        super(context, R.layout.nav_menu_item);
        mContext = context;
        this.mTitles = new String[m_nTitles.length];
        for (int i=0;i<mTitles.length;i++){
            this.mTitles[i] = context.getResources().getString(m_nTitles[i]);
        }
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTitles.length;
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return mTitles[position];
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public int getIconId(int position) {
        if (position < mIcons.length){
            return mIcons[position];
        }else{
            return 0;
        }
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        TextView item = (TextView) convertView;
        if(item == null) {
            item = new TextView(mContext);
        }
        item.setText(getItem(position));
        item.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(getIconId(position), null),null,null,null);
        return item;
    }
}