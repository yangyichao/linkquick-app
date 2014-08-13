package com.shopex.linkquick.app.ui;


import com.shopex.linkquick.app.AppConfig;
import com.shopex.linkquick.app.AppContext;
import com.shopex.linkquick.app.AppException;
import com.shopex.linkquick.app.AppManager;
import com.shopex.linkquick.app.R;

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
import android.view.KeyEvent;
import android.webkit.WebView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class ShowWebPage extends BaseActivity {
	private static final String TAG  = "ShowWebPage";
	private AppContext appContext;// 全局Context
	private WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//实例化WebView对象
		webview = new WebView(this);
		//设置WebView属性，能够执行Javascript脚本
		webview.getSettings().setJavaScriptEnabled(true);
		//加载需要显示的网页
		webview.loadUrl("http://www.baidu.com/");
		//设置Web视图
		setContentView(webview);
	}
	
	@Override
	//设置回退
	//覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
			webview.goBack(); //goBack()表示返回WebView的上一页面
			return true;
		}
		return false;
	}

}
