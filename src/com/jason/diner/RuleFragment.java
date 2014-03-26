/*
 * RuleFragment
 *
 * Version 1.0
 *
 * 2014-03-25
 *
 * Copyright notice
 */
package com.jason.diner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.jason.Interface.IUpdate;
import com.jason.Task.FragmentLoadTask;

/**
 * �������
 * @author Jason
 *
 */
public class RuleFragment extends Fragment implements IUpdate {

	private Button submit;			//�ύ���򣨴����˵���
	private View rootView ;
	
	//�洢�û�ѡ��Ĺ�����Ϣ
	private HashMap<String, ArrayList<ViewPair>> checkBoxList;
	
	private ProgressDialog progressbar;
	
	//���缸�ؼ��� ��NumberPicker
	private NumberPicker picker1, picker2, picker3;
	
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	rootView = inflater.inflate(R.layout.rule_fragment, container, false);
    	ShopInfo shop = Document.MainDoc().shop;
		if(shop.shopId == null && shop.shopName == null){
			return rootView;
		}
    	
		//����NumberPicker
		checkBoxList = new HashMap<String, ArrayList<ViewPair>>();
		picker1 = (NumberPicker) rootView.findViewById(R.id.ruleNumberPicker1);
		picker1.setMaxValue(8);
		picker1.setMinValue(0);
		picker1.setValue(3);
		picker2 = (NumberPicker) rootView.findViewById(R.id.ruleNumberPicker2);
		picker2.setMaxValue(8);
		picker2.setMinValue(0);
		picker2.setValue(2);
		picker3 = (NumberPicker) rootView.findViewById(R.id.ruleNumberPicker3);
		picker3.setMaxValue(3);
		picker3.setMinValue(0);
		picker3.setValue(1);
    	
		submit = (Button)rootView.findViewById(R.id.createShow);
		
		/**
		 * �����ύ��ť�ļ����¼�
		 */
		submit.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("rawtypes")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RuleInfo rule = Document.MainDoc().rule;
				rule.clear();
				rule.shopId = Document.MainDoc().shop.shopId;
				Iterator iter0 = Document.MainDoc().shop.selectedTopList.
									entrySet().iterator();
				while(iter0.hasNext()){
					@SuppressWarnings("unchecked")
					Map.Entry<String, Boolean> entry = 
						(Map.Entry<String, Boolean>) iter0.next();
					if(entry.getValue()){
						rule.staredDishList.add(entry.getKey());
					}
				}
				rule.categoryList.add("" + picker1.getValue());
				rule.categoryList.add("" + picker2.getValue());
				rule.categoryList.add("" + picker3.getValue());
				Iterator iter1 = checkBoxList.entrySet().iterator();
				while(iter1.hasNext()){
					Map.Entry<String, ArrayList<ViewPair>> entry = 
							(Map.Entry<String, ArrayList<ViewPair>>) 
							iter1.next();
					ArrayList<Boolean> tempList = new ArrayList<Boolean>();
					ArrayList<ViewPair> value = entry.getValue();
					for(int i = 0; i < value.size(); i++){
						ViewPair vp = (ViewPair)value.get(i);
						tempList.add(vp.checkBox.isChecked());
					}
					rule.conditionList.put(entry.getKey(), tempList);
				}
				Document.MainDoc().mainActivity.selectItem(2);
			}
		});
		
		//�ύ��������
		updateHttp();
		
		return rootView;
    }
    

    /**
     * �ύ��������
     */
    @Override
    public void updateHttp(){
		String param = "rule=rule";
		String oldParam = Document.MainDoc().server.paramRule;
		if(oldParam != null && oldParam.trim().equals(param)){
			updateUI();
		}else{
			Document.MainDoc().server.paramRule = param;
			FragmentLoadTask mTask = new FragmentLoadTask(this);
			mTask.execute(Document.MainDoc().server.getConditionUrl(null));
			
			//��ʾ������
			progressbar = ProgressDialog.show(
					Document.MainDoc().mainActivity, "Loading...",
					"Please wait...", true, false);
		}
    }
    
	@Override
	public void updateData(String json) {
		// TODO Auto-generated method stub
		if(!Helper.json2Condition(json, Document.MainDoc().condition)){
			Toast.makeText(Document.MainDoc().mainActivity, "���������쳣���������粢����",
				     Toast.LENGTH_SHORT).show();
			Document.MainDoc().server.clearParam();
		}
		
		//����������
		progressbar.dismiss();
	}

	/**
	 * ����UI
	 */
	@Override
	public void updateUI() {
		//��һ�㲼�֣�����㣩
		RelativeLayout ruleMain = 
				(RelativeLayout) rootView.findViewById(R.id.ruleMain);
		
		//��ȡ�ӷ������õ��Ĺ���������Ϣ
		ConditionInfo ruleInfo = Document.MainDoc().condition;
		int screemW = Document.MainDoc().screenWidth;
		Iterator iter = ruleInfo.conditions.entrySet().iterator();
		
		int belowId = R.id.ruleArea0;
		int idIndex = 1;
		
		//����������Ϣ���Զ�����UI����
		while(iter.hasNext()){
			Map.Entry<String, ArrayList<String>> entry = 
					(Map.Entry<String, ArrayList<String>>) iter.next();
			
			String key = entry.getKey();
			ArrayList<String> value = entry.getValue();
			ArrayList<ViewPair> pairList = new ArrayList<ViewPair>();
			
			//�ڶ��㲼��
			LinearLayout ruleArea = 
					new LinearLayout(Document.MainDoc().mainActivity);
			ruleArea.setBackgroundResource(R.color.background_gray_light);
			ruleArea.setId(idIndex);
			ruleArea.setOrientation(LinearLayout.VERTICAL);
			RelativeLayout.LayoutParams areaParam = 
					new RelativeLayout.LayoutParams(
							ViewGroup.LayoutParams.MATCH_PARENT,
							ViewGroup.LayoutParams.WRAP_CONTENT);
			areaParam.addRule(RelativeLayout.BELOW, belowId);
			belowId = idIndex;
			idIndex++;
			areaParam.topMargin = 10;
			ruleMain.addView(ruleArea, areaParam);

			//������/���򣩷������
			TextView ruleHead = new TextView(Document.MainDoc().mainActivity);
			
			Resources resource = Document.MainDoc().mainActivity.resources;
			ColorStateList csl = (ColorStateList) resource.getColorStateList(
							R.color.font_color_deep);  
			if (csl != null) {  
				ruleHead.setTextColor(csl);  
			}
			ruleHead.setTextSize(18);
			ruleHead.setText(key);
			LinearLayout.LayoutParams headParam = 
					new LinearLayout.LayoutParams(
							ViewGroup.LayoutParams.MATCH_PARENT,
							ViewGroup.LayoutParams.WRAP_CONTENT);
			headParam.leftMargin = 5;
			ruleArea.addView(ruleHead, headParam);
			
			//��������ݣ������CheckBox��TextView ÿ4��һ�У���ֹһ��̫ӵ����
			int size = value.size();
			int col = 4;
			int row = (int)Math.ceil((double) size / col);
			
			//�������ܵ�����
			for(int i = 0; i < row; i++){
				LinearLayout ruleZone = 
						new LinearLayout(Document.MainDoc().mainActivity);
				LinearLayout.LayoutParams zoneParam = 
						new LinearLayout.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT,
								ViewGroup.LayoutParams.WRAP_CONTENT);
				zoneParam.topMargin = 10;
				ruleZone.setOrientation(LinearLayout.HORIZONTAL);
				ruleArea.addView(ruleZone, zoneParam);
				
				//��ÿһ�е�4�У�����CheckBox��TextView�ԣ����һ�в���4�Ե����⴦��
				for(int j = 0; j < col; j++){
					int index = i*col + j;
					if(index >= size)
						break;
					
					CheckBox cb = new CheckBox(Document.MainDoc().mainActivity);

					LinearLayout.LayoutParams cbParam = 
							new LinearLayout.LayoutParams(
									screemW / col / 3,
									ViewGroup.LayoutParams.WRAP_CONTENT);
					TextView tv = new TextView(Document.MainDoc().mainActivity);
					LinearLayout.LayoutParams tvParam = 
							new LinearLayout.LayoutParams(
									screemW / col / 3 * 2,
									ViewGroup.LayoutParams.WRAP_CONTENT);
					tv.setText(value.get(index));
					tv.setTag(cb);
					tv.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							CheckBox cb = (CheckBox)v.getTag();
							cb.setChecked(!cb.isChecked());
						}
					});
					ColorStateList csl2 = 
							(ColorStateList) resource.getColorStateList(
									R.color.font_gray_normal);  
					if (csl != null) {  
						tv.setTextColor(csl2);  
					}
					
					pairList.add(new ViewPair(cb, tv));
					ruleZone.addView(cb, cbParam);
					ruleZone.addView(tv, tvParam);
					
				}
			}
			
			//UI�ϵķָ���
			View divider = new View(Document.MainDoc().mainActivity);
			LinearLayout.LayoutParams dividerParam = 
					new LinearLayout.LayoutParams(
							ViewGroup.LayoutParams.MATCH_PARENT, 1);
			divider.setBackgroundResource(R.color.divider_gray_normal);
			ruleArea.addView(divider, dividerParam);

			//�洢����������CheckBox��TextView
			checkBoxList.put(key, pairList);
			
		}

		
	}

	/**
	 * ��һ��CheckBox��һ��TextView��һ�����
	 * @author Jason
	 *
	 */
    class ViewPair{
    	public CheckBox checkBox;
    	public TextView textView;
    	ViewPair(CheckBox cb, TextView tv){
    		checkBox = cb;
    		textView = tv;
    	}
    	
    }
	
}
