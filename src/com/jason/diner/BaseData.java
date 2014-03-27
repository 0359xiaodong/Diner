/*
 * BaseData
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

public class BaseData {}

/**
 * fragment tag ö��
 * @author Jason
 *
 */
enum FRAGMENT_TAG{
	GUIDE, SEARCH, MAIN, SHOP, RULE, SHOW, SETTING, ABOUT
}



/**
 * ��������Ϣ��
 * @author Jason
 *
 */
class ServerInfo{
	
	//������URL
	public String url;
	
	//���������������һ�εĲ�������ֹ�ظ�����
	public String paramSearch;
	public String paramShop;
	public String paramRule;
	public String paramOrder;
	
	//�����ؼ��֣�������һ�εĹؼ��֣���ֹ�ظ�����
	public String prompt;
	
	//���ҳ���ַ
	private String searchAddress;
	private String shopAddress;
	private String conditionAddress;
	private String orderAddress;
	
	ServerInfo(){
		url = "http://mqstreetball.51a.net222-2.net";
		searchAddress = "/search.php";
		shopAddress = "/selectedShop.php";
		conditionAddress = "/getRule.php";
		orderAddress = "/getList.php";
		
		paramSearch = null;
		paramShop = null;
		paramRule = null;
		paramOrder = null;
	}
	
	/**
	 * ���ñ���Ĳ��������������쳣ʱִ��
	 */
	public void clearParam(){
		paramSearch = null;
		paramShop = null;
		paramRule = null;
		paramOrder = null;
	}
	
	/**
	 * ��������URL
	 * @param url���Ӳ��� String
	 * @return ������URL
	 */
	public String getSearchUrl(String param){
		String getUrl = url + searchAddress;
		if(param != null){
			getUrl += "?" + param;
		}
		return getUrl;
	}
	
	/**
	 * ���ز͹�URL
	 * @param url���Ӳ��� String
	 * @return ������URL
	 */
	public String getShopUrl(String param){
		String getUrl = url + shopAddress;
		if(param != null){
			getUrl += "?" + param;
		}
		return getUrl;
	}
	
	/**
	 * ��������URL
	 * @param url���Ӳ��� String
	 * @return ������URL
	 */
	public String getConditionUrl(String param){
		String getUrl = url + conditionAddress;
		if(param != null){
			getUrl += "?" + param;
		}
		return getUrl;
	}
	
	/**
	 * ���ز˵�URL
	 * @param url���Ӳ��� String
	 * @return ������URL
	 */
	public String getOrderUrl(String param){
		String getUrl = url + orderAddress;
		if(param != null){
			getUrl += "?" + param;
		}
		return getUrl;
	}
	
}

/**
 * ���������Ϣ��
 * @author Jason
 *
 */
class SearchInfo{
	//��������б�
	public ArrayList<ShopInfo> searchList;
	//���ڰ󶨿ؼ�����������б�
	public ArrayList<HashMap<String, Object>> searchListBlinding;
	
	SearchInfo(){
		searchList = new ArrayList<ShopInfo>();
		searchListBlinding = new ArrayList<HashMap<String, Object>>();
	}
	
	public void clear(){
		searchList.clear();
		searchListBlinding.clear();
	}
}

/**
 * �͹���Ϣ��
 * @author Jason
 *
 */
class ShopInfo{
	//�͹������Ϣ
	public String shopId;
	public String shopImage;
	public String shopName;
	public String shopAddress;
	public String shopIntroduce;
	
	/** �͹��Ƽ����б� */
	public ArrayList<DishInfo> topList;
	
	/** �͹��Ƽ��˰��б� */
	public ArrayList<HashMap<String, Object>> topListBlinding;
	
	/** ѡ����Ƽ����б� */
	public HashMap<String, Boolean> selectedTopList;
	

	/**
	 * �͹���Ϣ�� ��Ӧ�� ����������
	 * @author Jason
	 *
	 */
	public static final class KEYS{
		public static final String SHOP_ID = "shopId";
		public static final String SHOP_IMAGE = "shopImage";
		public static final String SHOP_NAME = "shopName";
		public static final String SHOP_ADDRESS = "shopAddress";
		public static final String SHOP_INTRODUCE = "shopIntroduce";
		public static final String TOP_LIST = "topList";
		public static final String TOP_LIST_BLINDING = "topListBlinding";
		public static final String SELECTED_TOP_LIST = "selectedTopList";
	}
	
	
	ShopInfo(){
		shopId = null;
		shopImage = null;
		shopName = null;
		shopAddress = null;
		shopIntroduce = null;
		topList = new ArrayList<DishInfo>();
		topListBlinding = new ArrayList<HashMap<String, Object>>();
		selectedTopList = new HashMap<String, Boolean>();
		
	}

	public void clear(){
		shopId = null;
		shopImage = null;
		shopName = null;
		shopAddress = null;
		shopIntroduce = null;
		topList.clear();
		topListBlinding.clear();
		selectedTopList.clear();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String s = shopId + shopImage + shopName + shopAddress + shopIntroduce;
		for(DishInfo item : topList){
			s += item.toString();
		}
		return s;
	}
	
	
}



/**
 * ��Ʒ��Ϣ��
 * @author Jason
 *
 */
class DishInfo{
	//��Ʒ�����Ϣ
	public String dishId;
	public String dishImage;
	public String dishName;
	public String dishTaste;		//��ζ���������
	public String dishFood;			//���ģ����⣬��������ۣ�����
	public String dishCooking;
	public String dishCategory;		//��𣺻�����
	
	/**
	 * ��Ʒ��Ϣ�� ��Ӧ�� ����������
	 * @author Jason
	 *
	 */
	public static final class KEYS{
		public static final String DISH_ID= "dishId";
		public static final String DISH_IMAGE = "dishImage";
		public static final String DISH_NAME = "dishName";
		public static final String DISH_TASTE = "dishTaste";		//��ζ���������
		public static final String DISH_FOOD = "dishFood";		//���ģ����⣬��������ۣ�����
		public static final String DISH_COOKING = "dishCooking";
		public static final String DISH_CATEGORY = "dishCategory";		//��𣺻�����
		public static final String SELECTED = "selected";
	}
	
	DishInfo(){
		dishId =null;
		dishImage = null;
		dishName = null;
		dishTaste = null;
		dishFood = null;
		dishCooking = null;
		dishCategory = null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return dishId + dishImage + dishName + dishTaste + dishFood +
				dishCooking + dishCategory;
		
	}

}



/**
 * ���������Ϣ�ࣨ��¼�ӷ������õ������й���
 * @author Jason
 *
 */
class ConditionInfo{
	/** �������� */
	public HashMap<String, ArrayList<String>> conditions;
	
	public void clear(){
		conditions.clear();
	}
	
	ConditionInfo(){
		conditions = new HashMap<String, ArrayList<String>>();
	}
	
}

/**
 * ��˹�����Ϣ�ࣨ�û�ѡ������й���
 * @author Jason
 *
 */
class RuleInfo{
	//���������Ϣ
	public String shopId;
	public ArrayList<String> staredDishList;
	public ArrayList<String> categoryList;
	public HashMap<String, ArrayList<Boolean>> conditionList;
	
	/**
	 * ��˹�����Ϣ�� ��Ӧ�� ����������
	 * @author Jason
	 *
	 */
	public static final class KEYS{
		public static final String SHOP_ID = "shopId";
		public static final String STARED_DISH_LIST = "staredDishList";
		public static final String CATEGORY_LIST= "categoryList";
		public static final String CONDITION_LIST = "conditionList";
	}
	
	RuleInfo(){
		shopId = null;
		staredDishList = new ArrayList<String>();
		categoryList = new ArrayList<String>();
		conditionList = new HashMap<String, ArrayList<Boolean>>();
	}
	
	public void clear(){
		shopId = null;
		staredDishList.clear();
		categoryList.clear();
		conditionList.clear();
	}
}



/**
 * �˵���Ϣ��
 * @author Jason
 *
 */
class OrderInfo{
	//�˵���Ϣ
	public ConditionInfo ruleInfo;
	
	/** ����Ĳ˵� */
	public HashMap<String, ArrayList<ArrayList<DishInfo>>> dishes;
	
	/** ÿ���������� */
	public HashMap<String, Integer> categoryCount;
	
	/** �˵��� */
	public ArrayList<ArrayList<DishInfo>> dishesBlinding;
	
	OrderInfo(){
		ruleInfo = new ConditionInfo();
		dishes = new HashMap<String, ArrayList<ArrayList<DishInfo>>>();
		categoryCount = new HashMap<String, Integer>();
		dishesBlinding = new ArrayList<ArrayList<DishInfo>>();
	}
	
	public void clear(){
		ruleInfo.clear();
		dishes.clear();
		categoryCount.clear();
		dishesBlinding.clear();
	}
}
