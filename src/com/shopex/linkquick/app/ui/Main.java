package com.shopex.linkquick.app.ui;

import greendroid.widget.MyQuickAction;
import greendroid.widget.QuickActionWidget;
//import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;

import com.shopex.linkquick.app.AppConfig;
import com.shopex.linkquick.app.AppContext;
import com.shopex.linkquick.app.AppException;
import com.shopex.linkquick.app.AppManager;
import com.shopex.linkquick.app.R;
import com.shopex.linkquick.app.common.UIHelper;
import com.shopex.linkquick.app.common.FileUtils;
import com.shopex.linkquick.app.common.StringUtils;

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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class Main extends BaseActivity {
	private static final String TAG  = "Main";
	private AppContext appContext;// 全局Context
//	private QuickActionWidget mGrid;// 快捷栏控件
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		appContext = (AppContext)getApplication();
//		// 网络连接判断
		if (!appContext.isNetworkConnected()){
			UIHelper.ToastMessage(this, R.string.network_not_connected);
		}
		
//		redirectTo();
			
//		String cookie = appContext.getProperty("cookie");
//		if(StringUtils.isEmpty(cookie)) {
//			appContext.setProperty("website", "sss");
//			String website = appContext.getProperty("website");
//			Log.i(this.TAG,website);
//		}
		
		
			
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_start, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) { 
		case R.id.sign_in:
//			redirectTo();
			setTitle(R.string.sign_in);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private void redirectTo(){ 
        Intent intent = new Intent(this, ShowWebPage.class);
        startActivity(intent);
        finish();
    }

}
