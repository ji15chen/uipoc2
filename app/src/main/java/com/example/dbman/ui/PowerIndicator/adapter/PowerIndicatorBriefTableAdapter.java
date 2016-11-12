package com.example.dbman.ui.PowerIndicator.adapter;

import android.content.Context;
import android.content.res.Resources;

import com.example.dbman.ui.R;

public class PowerIndicatorBriefTableAdapter extends SampleTableAdapter {

	private final int width;
	private final int height;

		public PowerIndicatorBriefTableAdapter(Context context) {
			super(context);

			Resources resources = context.getResources();

			width = resources.getDimensionPixelSize(R.dimen.table_width);
			height = resources.getDimensionPixelSize(R.dimen.table_height);
		}

		@Override
		public int getRowCount() {
			return 10;
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public int getWidth(int column) {
			return width;
		}

		@Override
		public int getHeight(int row) {
			return height;
		}

		@Override
		public String getCellString(int row, int column) {
			return "Lorem (" + row + ", " + column + ")";
		}

		@Override
		public int getLayoutResource(int row, int column) {
			final int layoutResource;
			switch (getItemViewType(row, column)) {
				case 0:
					layoutResource = R.layout.powerindicator_table_header;
				break;
				case 1:
					layoutResource = R.layout.powerindicator_table_body;
				break;
				default:
					throw new RuntimeException("wtf?");
			}
			return layoutResource;
		}

		@Override
		public int getItemViewType(int row, int column) {
			if (row < 0) {
				return 0;
			} else {
				return 1;
			}
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}
	}