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

import android.graphics.Bitmap;

/**
 * ͼƬ������
 * 
 * @author Jason
 *
 */
public class ImageCache {
	
	/** �洢ͼƬ�����ݽṹ */
	private HashMap<String, Bitmap> images;
	
	public ImageCache(){
		images = new HashMap<String, Bitmap>();
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
}
