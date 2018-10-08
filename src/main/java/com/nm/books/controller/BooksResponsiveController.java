package com.nm.books.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nm.booktitlerestapi.JsontoJava;
import com.nm.model.InventoryModel;

import org.springframework.ui.ModelMap;

@Controller
public class BooksResponsiveController {

	// new reservation section
	@RequestMapping(value = "booksresponsiveproject", method = RequestMethod.POST)
	public String addReservation(@ModelAttribute("SpringWeb") InventoryModel inventoryModel,

			ModelMap model1) {		

		model1.addAttribute("name", inventoryModel.getName());
		
		String searchName=inventoryModel.getName();

		List<String> inventList = getjsonInventory(searchName);

		model1.addAttribute("EnvList", inventList);

		return "booksresponsive";

	}

	public List getjsonInventory(String term) {

		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
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

				InventoryModel inventoryModel = new InventoryModel();
				String valuesString = (String) iterator.next().get("title").toString();

				// System.out.println("title***" + valuesString);
				if (valuesString.toLowerCase().contains(term.toLowerCase())) {
					inventoryModel.setName(valuesString);

					JSONArray valueslist3 = (JSONArray) iterator.next().get("authors");
					// System.out.println("author***" + valueslist3);
					String _str2= valueslist3.toString();
					inventoryModel.setAuthorDetails(valueslist3.toString());
					
					
					Boolean valueslist4 = (Boolean) iterator.next().get("printdisabled");
					// System.out.println("author***" + valueslist3);
					String printDisabled= valueslist4.toString();
					inventoryModel.setPrintDisabled(printDisabled);
					
					
					/*JSONObject jsonObject2 = (JSONObject) new JSONParser().parse(_str2);
					JsontoJava jsontoJava = new JsontoJava();
					String _javastr= jsontoJava.getJavaString(jsonObject2);
					
					List<String> list = new ArrayList<String>();
					JSONArray array = jsonObject2.getJSONArray("interests");
					for(int i = 0 ; i < array.length() ; i++){
					    System.out.println((array.getJSONObject(i).getString("interestKey"));
					}
				*/
					
					inventoryList.add(inventoryModel);
				}

			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		

		return inventoryList;
	}

}