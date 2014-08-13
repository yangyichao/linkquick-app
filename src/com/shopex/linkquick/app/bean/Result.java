package com.shopex.linkquick.app.bean;

import java.io.IOException;
import java.io.InputStream;

import com.shopex.linkquick.app.AppException;
import com.shopex.linkquick.app.bean.Comment.Refer;
import com.shopex.linkquick.app.bean.Comment.Reply;
import com.shopex.linkquick.app.common.StringUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

/**
 * ���ݲ������ʵ����
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class Result extends Base {

	private int errorCode;
	private String errorMessage;
	
	private Comment comment;
	
	public boolean OK() {
		return errorCode == 1;
	}

	/**
	 * �������ý��
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static Result parse(InputStream stream) throws IOException, AppException {
		Result res = null;
		Reply reply = null;
		Refer refer = null;        
		// ���XmlPullParser������
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(stream, Base.UTF8);
			// ��ý��������¼���������п�ʼ�ĵ��������ĵ�����ʼ��ǩ��������ǩ���ı��ȵ��¼���
			int evtType = xmlParser.getEventType();
			// һֱѭ����ֱ���ĵ�����
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {

				case XmlPullParser.START_TAG:
					// ����Ǳ�ǩ��ʼ����˵����Ҫʵ����������
					if (tag.equalsIgnoreCase("result")) 
					{
						res = new Result();
					} 
					else if (res != null) 
					{ 
						if (tag.equalsIgnoreCase("errorCode")) 
						{
							res.errorCode = StringUtils.toInt(xmlParser.nextText(), -1);
						} 
						else if (tag.equalsIgnoreCase("errorMessage")) 
						{
							res.errorMessage = xmlParser.nextText().trim();
						}
						else if(tag.equalsIgnoreCase("comment"))
			    		{
							res.comment = new Comment();
			    		}
			            else if(res.comment != null)
			    		{
				            if(tag.equalsIgnoreCase("id"))
				            {			      
				            	res.comment.id = StringUtils.toInt(xmlParser.nextText(),0);
				            }
				            else if(tag.equalsIgnoreCase("portrait"))
				            {			            	
				            	res.comment.setFace(xmlParser.nextText());
				            }
				            else if(tag.equalsIgnoreCase("author"))
				            {			            	
				            	res.comment.setAuthor(xmlParser.nextText());		            	
				            }
				            else if(tag.equalsIgnoreCase("authorid"))
				            {			            	
				            	res.comment.setAuthorId(StringUtils.toInt(xmlParser.nextText(),0));		            	
				            }
				            else if(tag.equalsIgnoreCase("content"))
				            {			            	
				            	res.comment.setContent(xmlParser.nextText());
				            }
				            else if(tag.equalsIgnoreCase("pubDate"))
				            {			            	
				            	res.comment.setPubDate(xmlParser.nextText());	
				            }
				            else if(tag.equalsIgnoreCase("appclient"))
				            {			            	
				            	res.comment.setAppClient(StringUtils.toInt(xmlParser.nextText(),0));			            	
				            }
				            else if(tag.equalsIgnoreCase("reply"))
				            {			            	
				            	reply = new Reply();
				            }
				            else if(reply!=null && tag.equalsIgnoreCase("rauthor"))
				            {
				            	reply.rauthor = xmlParser.nextText();
				            }
				            else if(reply!=null && tag.equalsIgnoreCase("rpubDate"))
				            {
				            	reply.rpubDate = xmlParser.nextText();
				            }
				            else if(reply!=null && tag.equalsIgnoreCase("rcontent"))
				            {
				            	reply.rcontent = xmlParser.nextText();
				            }
				            else if(tag.equalsIgnoreCase("refer"))
				            {			            	
				            	refer = new Refer();         	
				            }
				            else if(refer!=null && tag.equalsIgnoreCase("refertitle"))
				            {
				            	refer.refertitle = xmlParser.nextText();
				            }
				            else if(refer!=null && tag.equalsIgnoreCase("referbody"))
				            {
				            	refer.referbody = xmlParser.nextText();
				            }
			    		}
			            //֪ͨ��Ϣ
			            else if(tag.equalsIgnoreCase("notice"))
			    		{
			            	res.setNotice(new Notice());
			    		}
			            else if(res.getNotice() != null)
			    		{
			    			if(tag.equalsIgnoreCase("atmeCount"))
				            {			      
			    				res.getNotice().setAtmeCount(StringUtils.toInt(xmlParser.nextText(),0));
				            }
				            else if(tag.equalsIgnoreCase("msgCount"))
				            {			            	
				            	res.getNotice().setMsgCount(StringUtils.toInt(xmlParser.nextText(),0));
				            }
				            else if(tag.equalsIgnoreCase("reviewCount"))
				            {			            	
				            	res.getNotice().setReviewCount(StringUtils.toInt(xmlParser.nextText(),0));
				            }
				            else if(tag.equalsIgnoreCase("newFansCount"))
				            {			            	
				            	res.getNotice().setNewFansCount(StringUtils.toInt(xmlParser.nextText(),0));
				            }
			    		}
					}
					break;
				case XmlPullParser.END_TAG:
					//���������ǩ��������Ѷ�����ӽ�������
			       	if (tag.equalsIgnoreCase("reply") && res.comment!=null && reply!=null) { 
			       		res.comment.getReplies().add(reply);
			       		reply = null; 
			       	}
			       	else if(tag.equalsIgnoreCase("refer") && res.comment!=null && refer!=null) {
			       		res.comment.getRefers().add(refer);
			       		refer = null;
			       	}
					break;
				}
				// ���xmlû�н������򵼺�����һ���ڵ�
				evtType = xmlParser.next();
			}

		} catch (XmlPullParserException e) {
			throw AppException.xml(e);
		} finally {
			stream.close();
		}
		return res;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Override
	public String toString(){
		return String.format("RESULT: CODE:%d,MSG:%s", errorCode, errorMessage);
	}
}
