/*
 * Document
 *
 * Version 1.0
 *
 * 2014-03-25
 *
 * Copyright notice
 */
package com.jason.diner;

import android.support.v4.app.Fragment;

import com.jason.Data.ImageCache;

/**
 * ������
 * @author Jason
 *
 */
public class Document {
	
	private static Document mainDoc = null;		//ָ���Լ���̬��������ã���������ҳ��ʹ�ã�
	public MainActivity mainActivity = null;	//ָ��MainActivity������
	
	public int screenWidth;				//��Ļ��ȣ�ViewPager���õ���
	public ImageCache imageCache;		//ͼƬ����
	public Fragment currentFragment;	//��ǰ��ʾ��fragment
	
	public ServerInfo server;			//��������Ϣ
	public SearchInfo search;			//������Ϣ
	public ShopInfo shop;				//�͹���Ϣ
	public ConditionInfo condition;		//������Ϣ����¼��Ҫ�ӷ������õ������й���
	public RuleInfo rule;				//������Ϣ���û�ѡ��Ĺ���
	public OrderInfo order;				//�˵���Ϣ
	
	/**
	 * ���� Ψһ�ľ�̬�������
	 * @return
	 */
	public static Document MainDoc(){
		return mainDoc;
	}
	
	
	public Document(MainActivity act){
		mainDoc = this;
		mainActivity = act;

		screenWidth = 800;
		imageCache = new ImageCache();
		currentFragment = null;
		
		server = new ServerInfo();
		search = new SearchInfo();
		shop = new ShopInfo();
		rule = new RuleInfo();
		condition = new ConditionInfo();
		order = new OrderInfo();

	}
}
