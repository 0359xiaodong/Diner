/*
 * NavActivity
 *
 * Version 1.0
 *
 * 2014-03-26
 *
 * Copyright notice
 */
package com.jason.diner;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * ����ҳ��Ӧ�õ�һ������ʱ���֣�
 * @author Jason
 *
 */
public class NavActivity extends Activity {

	private ViewPager mViewPager;				//ViewPager����
	private int currIndex = 0;					//��ǰҳ������
	private ImageView mPage0, mPage1, mPage2;	//����ͼƬ����

	private final int COUNT = 3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nav_activity);

		SharedPreferences preferences = getSharedPreferences("app_conf",
				MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean("isFirstIn", false);
		editor.commit();

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());

		mPage0 = (ImageView) findViewById(R.id.page0);
		mPage1 = (ImageView) findViewById(R.id.page1);
		mPage2 = (ImageView) findViewById(R.id.page2);

		int[] welcomImg = { R.drawable.welcome0, R.drawable.welcome1,
				R.drawable.welcome2};

		LayoutInflater mLi = LayoutInflater.from(this);
		final ArrayList<View> views = new ArrayList<View>();
		final ArrayList<String> titles = new ArrayList<String>();
		for (int i = 0; i < COUNT; i++) {
			View view = mLi.inflate(R.layout.nav_activity_image, null);
			LinearLayout lay = (LinearLayout) view
					.findViewById(R.id.welcomeImg);
			lay.setBackgroundResource(welcomImg[i]);
			views.add(view);
			titles.add("" + i);
		}

		//��дViewPager��������
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container,
					int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return titles.get(position);
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mViewPager.setAdapter(mPagerAdapter);
	}

	/**
	 * ҳ�滬��������
	 * @author Jason
	 *
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		/**
		 * ѡ��ĳҳ�ǵĴ�����
		 */
		public void onPageSelected(int arg0) {

			Animation animation = null;		//������������
			switch (arg0) {
			case 0: //ҳ��0
				//�����һ������ҳ�棬СԲ��Ϊѡ��״̬����һ��ҳ���СԲ����δѡ��״̬��
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.point_dark));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.point_light));
				if (currIndex == arg0 + 1) {
					//Բ���ƶ�Ч���������ӵ�ǰView�ƶ�����һ��View
					animation = new TranslateAnimation(arg0 + 1, arg0, 0, 0);
				}
				break;
			case 1: //ҳ��1
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.point_dark));		//��ǰView
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.point_light));		//��һ��View
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.point_light));		//��һ��View
				
				
				if (currIndex == arg0 - 1) {//�����������һ��View
					//Բ���ƶ�Ч���������ӵ�ǰView�ƶ�����һ��View
					animation = new TranslateAnimation(arg0 - 1, arg0, 0, 0); 

				} else if (currIndex == arg0 + 1) {//�����������һ��View
					//Բ���ƶ�Ч���������ӵ�ǰView�ƶ�����һ��View
					animation = new TranslateAnimation(arg0 + 1, arg0, 0, 0);
				}
				break;
			case 2: //ҳ��2
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.point_dark));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.point_light));

				if (currIndex == arg0 - 1) {
					animation = new TranslateAnimation(arg0 - 1, arg0, 0, 0);
				}
				break;
			}
			currIndex = arg0;				//���õ�ǰView
			animation.setFillAfter(true);	//True:����ͼƬͣ�ڶ�������λ��
			animation.setDuration(300);		//���ö�������ʱ��
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

	}

	public void startbutton(View v) {
		Intent mainIntent = new Intent(NavActivity.this, MainActivity.class);
		NavActivity.this.startActivity(mainIntent);
		NavActivity.this.finish();

	}
}