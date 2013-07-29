package com.osunx.scrollpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	String[] myList = { "a", "b", "c", "d", "e", "f", "g", "h" };
	ViewPager pager;
	GridView gridview;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ac_chartbar);

		LinearLayout chart = (LinearLayout) this.findViewById(R.id.chart1);

		// for (int i = 0; i < 4; i++) {
		// final View child = (View)
		// getLayoutInflater().inflate(R.layout.ac_chartbar, null);
		// final TextView t1 = (TextView) this.findViewById(R.id.chart_name);
		// t1.setText("K");
		// View c1 = (View) this.findViewById(R.id.chart1);

		// chart.addView(child);
		// }

		// chart.setLayoutParams(new
		// LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
		// LayoutParams.WRAP_CONTENT, 2.0f));
		// LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
		// 60,
		// 60);

		// chart.setLayoutParams(new LinearLayout.LayoutParams(30,60));
		// chart.addView(child);

		Log.i("kelvin", chart + "xxx");
		siftSystemShare();
	}

	private void siftSystemShare() {
		String contentDetails = "";
		String contentBrief = "";
		String shareUrl = "";
		Intent it = new Intent(Intent.ACTION_SEND);
		it.setType("text/plain");
		List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(it, 0);
		if (!resInfo.isEmpty()) {
			List<Intent> targetedShareIntents = new ArrayList<Intent>();
			for (ResolveInfo info : resInfo) {
				Intent targeted = new Intent(Intent.ACTION_SEND);
				targeted.setType("text/plain");
				ActivityInfo activityInfo = info.activityInfo;

				// judgments : activityInfo.packageName, activityInfo.name, etc.
				if (!activityInfo.packageName.contains("app")) {
					continue;
				}
				// if (activityInfo.packageName.contains("gm") ||
				// activityInfo.name.contains("mail")) {
				// targeted.putExtra(Intent.EXTRA_TEXT, contentDetails);
				// } else if (activityInfo.packageName.contains("zxing")) {
				// targeted.putExtra(Intent.EXTRA_TEXT, shareUrl);
				// } else {
				// targeted.putExtra(Intent.EXTRA_TEXT, contentBrief);
				// }
				targeted.setPackage(activityInfo.packageName);
				targetedShareIntents.add(targeted);
			}
			Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Select app to share");
			if (chooserIntent == null) {
				return;
			}
			// A Parcelable[] of Intent or LabeledIntent objects as set with
			// putExtra(String, Parcelable[]) of additional activities to place
			// a the front of the list of choices, when shown to the user with a
			// ACTION_CHOOSER.
			chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[] {}));
			try {
				startActivity(chooserIntent);
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(this, "Can't find share component to share", Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void onCreate2() {
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
				layout = getLayoutInflater().inflate(R.layout.ac_grid, parent, false);
			} else {
				layout = (View) convertView;
			}

			TextView tv = (TextView) layout.findViewById(R.id.textview2);
			tv.setText("grid" + myList[position]);

			return layout;
		}

	}

}