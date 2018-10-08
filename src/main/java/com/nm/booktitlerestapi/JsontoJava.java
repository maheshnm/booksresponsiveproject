package com.nm.booktitlerestapi;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;




public class JsontoJava {
	
	public String getJavaString(JSONObject jsonObject2) {
		// TODO Auto-generated method stub
		String str= null;
		List<String> list = new ArrayList<String>();
		JSONArray array = jsonObject2.getJSONArray("authors");
		for(int i = 0 ; i < array.length() ; i++){
		    System.out.println("new name"+array.getJSONObject(i).getString("name"));
		    
		     str=array.getJSONObject(i).getString("name");
		}
		
		return str;
	

	}

}
