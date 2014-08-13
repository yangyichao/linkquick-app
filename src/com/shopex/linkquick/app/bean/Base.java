package com.shopex.linkquick.app.bean;

import java.io.Serializable;


/**
 * 实体基类：实现序列化
 */
public abstract class Base implements Serializable {

	public final static String UTF8 = "UTF-8";
	public final static String NODE_ROOT = "oschina";
	
	protected Notice notice;

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

}
