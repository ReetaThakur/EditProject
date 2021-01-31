package com.example.inshorts_app.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseMenu{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("category")
	private String category;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"ResponseMenu{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}