package com.shopex.linkquick.app;

import java.io.File;
import java.util.List;

import com.shopex.linkquick.app.common.FileUtils;
import com.shopex.linkquick.app.common.StringUtils;
import com.shopex.linkquick.app.ui.Main;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;


public class AppStart extends Activity {
	private static final String TAG  = "AppStart";
//	private TextView mHeadTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.activity_app_start, null);
//		LinearLayout wellcome = (LinearLayout) view.findViewById(R.id.app_start_view);
		setContentView(view);
		
		//渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationStart(Animation animation) {}
			
		});

//		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
//		startMainAvtivity();
		
		//兼容低版本cookie（1.5版本以下，包括1.5.0,1.5.1）
		AppContext appContext = (AppContext)getApplication();
		String cookie = appContext.getProperty("cookie");
		if(StringUtils.isEmpty(cookie)) {
			String cookie_name = appContext.getProperty("cookie_name");
			String cookie_value = appContext.getProperty("cookie_value");
			if(!StringUtils.isEmpty(cookie_name) && !StringUtils.isEmpty(cookie_value)) {
				cookie = cookie_name + "=" + cookie_value;
				appContext.setProperty("cookie", cookie);
				appContext.removeProperty("cookie_domain","cookie_name","cookie_value","cookie_version","cookie_path");
			}
		}
//		appContext.setProperty("website", "linkquick.cn");
//		String website = appContext.getProperty("website");
//		mHeadTitle = (TextView) findViewById(R.id.textView1);
//		mHeadTitle.setText(website);
//		Log.i(this.TAG, "android log");
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.app_start, menu);
//		
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) { 
//		case R.id.sign_in:
//			redirectTo();
////			setTitle(R.string.sign_in);
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_app_start,
//					container, false);
//			return rootView;
//		}
//	}
	
	
	
	private void redirectTo(){ 
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
        finish();
    }
	

}
