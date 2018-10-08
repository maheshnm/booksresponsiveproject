package com.nm.booktitlerestapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.nm.model.InventoryModel;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

public class Restjsonresponsebooks {
	
	public List getjsonInventory(String term) {
		
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
		List<String> inventList = new ArrayList<String>();
		
		InventoryModel inventoryModel = new InventoryModel();
		List<InventoryModel> inventoryList = new ArrayList<InventoryModel>();

		try {

			HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
			GetMethod loginLink = new GetMethod("https://openlibrary.org/subjects/textbooks.json");
			client.executeMethod(loginLink);
			String _str = loginLink.getResponseBodyAsString();

			JSONObject jsonObject = (JSONObject) new JSONParser().parse(_str);
			JSONArray valueslist = (JSONArray) jsonObject.get("works");

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = valueslist.iterator();

			while (iterator.hasNext()) {
				String valuesString = (String) iterator.next().get("title").toString();
				System.out.println(valuesString);
				if (valuesString.toLowerCase().contains(term.toLowerCase())) {
					inventList.add(valuesString);
					inventoryModel.setName(valuesString);
					inventoryList.add(inventoryModel);
				}

				JSONArray valueslist3 = (JSONArray) iterator.next().get("authors");
				System.out.println(valueslist3);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return inventList;
	}

}
