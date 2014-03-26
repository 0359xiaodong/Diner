/*
 * ImageLoadTask
 *
 * Version 1.0
 *
 * 2014-03-25
 *
 * Copyright notice
 */
package com.jason.Task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.BaseAdapter;

import com.jason.Network.Httper;
import com.jason.diner.Document;

/**
 * ͼƬ��̨������
 * @author Jason
 *
 */
public class ImageLoadTask extends AsyncTask<Object, Void, Bitmap> {   
	
	/** ��ַ */
    private String address;
    
    /** ������ */
    private BaseAdapter adapter;
    
    @Override  
    protected Bitmap doInBackground(Object... params) {  
        String url = (String) params[0];
        address = (String) params[1];
        adapter = (BaseAdapter) params[2];
        Bitmap drawable = Httper.loadImage(url + address);//��ȡ����ͼƬ  
        return drawable;  
    }  

    @Override  
    protected void onPostExecute(Bitmap result) {  
        if (result == null) { 
        	Document.MainDoc().imageCache.removeDownloading(address);
            return;  
        }  
        Document.MainDoc().imageCache.putImage(address, result);//���뻺��  
        adapter.notifyDataSetChanged();
    }

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	} 
    
    
} 