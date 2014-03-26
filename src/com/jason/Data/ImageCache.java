/*
 * ImageCache
 *
 * Version 1.0
 *
 * 2014-03-25
 *
 * Copyright notice
 */

package com.jason.Data;

import java.util.HashMap;
import java.util.HashSet;

import android.graphics.Bitmap;

/**
 * ͼƬ������
 * 
 * @author Jason
 *
 */
public class ImageCache {
	
	//�洢ͼƬ�����ݽṹ
	private HashMap<String, Bitmap> images;
	
	//�洢�Ѿ���ʼ�����˵�ͼƬ���ӣ���ֹ�ظ����أ�
	private HashSet<String> isDownloading;
	
	public ImageCache(){
		images = new HashMap<String, Bitmap>();
		isDownloading = new HashSet<String>();
	}
	
	/**
	 * ȡ��ͼƬ
	 * @param key String ͼƬ�ؼ���
	 * @return Bitmap ͼƬ
	 */
	public Bitmap getImage(String key){
		return images.get(key);
	}
	
	/**
	 * ����ͼƬ
	 * @param key String ͼƬ�ؼ���
	 * @param value Bitmap ͼƬ
	 */
	public void putImage(String key, Bitmap value){
		images.put(key, value);
	}
	
	/**
	 * �����Ѿ���ʼ���ص�ͼƬ����
	 * @param key ͼƬURI
	 */
	public void putDownloading(String key){
		isDownloading.add(key);
	}
	
	/**
	 * �Ƿ�����˴�ͼƬ����
	 * @param key ͼƬURI
	 * @return ��ͼƬ�Ƿ���������
	 */
	public boolean getDownloading(String key){
		return isDownloading.contains(key);
	}
	
	/**
	 * ɾ��ͼƬ���ӣ�ͼƬ����ʧ��ʱ��
	 * @param key ͼƬURI
	 */
	public void removeDownloading(String key){
		isDownloading.remove(key);
	}
}
