package com.osunx.scrollpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {
	String[] myList = { "a", "b", "c", "d", "e", "f", "g", "h" };
	ViewPager pager;
	GridView gridview;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int pagerPosition = 0;

		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new ImagePagerAdapter(myList));
		pager.setCurrentItem(pagerPosition);

		gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter());
		gridview.setVerticalScrollBarEnabled(false);
		gridview.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					return true;
				}
				gridview.setSelected(false);
				return false;
			}

		});
		gridview.setSelected(false);

	}

	private class ImagePagerAdapter extends PagerAdapter {

		private String[] list;
		private LayoutInflater inflater;

		ImagePagerAdapter(String[] list) {
			this.list = list;
			inflater = getLayoutInflater();
		}

		@Override
		public int getCount() {
			return list.length;
		}

		public Object instantiateItem(ViewGroup view, int position) {
			View layout = inflater.inflate(R.layout.ac_textview, view, false);
			TextView tv = (TextView) layout.findViewById(R.id.textview1);
			tv.setText("textview" + position + list[position]);

			((ViewPager) view).addView(layout, 0);
			return layout;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public void finishUpdate(View container) {
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View container) {
		}

	}

	public class ImageAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return myList.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final View layout;
			if (convertView == null) {
				// imageView = (TextView)
				// getLayoutInflater().inflate(R.layout.ac_textview, parent,
				// false);
				layout = getLayoutInflater().inflate(R.layout.ac_grid, parent,
						false);
			} else {
				layout = (View) convertView;
			}

			TextView tv = (TextView) layout.findViewById(R.id.textview2);
			tv.setText("grid" + myList[position]);

			return layout;
		}

	}

}