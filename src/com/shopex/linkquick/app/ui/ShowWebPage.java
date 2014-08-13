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
	private AppContext appContext;// ȫ��Context
	private WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//ʵ����WebView����
		webview = new WebView(this);
		//����WebView���ԣ��ܹ�ִ��Javascript�ű�
		webview.getSettings().setJavaScriptEnabled(true);
		//������Ҫ��ʾ����ҳ
		webview.loadUrl("http://www.baidu.com/");
		//����Web��ͼ
		setContentView(webview);
	}
	
	@Override
	//���û���
	//����Activity���onKeyDown(int keyCoder,KeyEvent event)����
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
			webview.goBack(); //goBack()��ʾ����WebView����һҳ��
			return true;
		}
		return false;
	}

}
