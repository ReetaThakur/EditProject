package com.example.inshorts_app.Response;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("date")
	private String date;

	@SerializedName("readMoreUrl")
	private String readMoreUrl;

	@SerializedName("author")
	private String author;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("time")
	private String time;

	@SerializedName("title")
	private String title;

	@SerializedName("content")
	private String content;

	@SerializedName("url")
	private String url;

	public String getDate(){
		return date;
	}

	public String getReadMoreUrl(){
		return readMoreUrl;
	}

	public String getAuthor(){
		return author;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public String getTime(){
		return time;
	}

	public String getTitle(){
		return title;
	}

	public String getContent(){
		return content;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"date = '" + date + '\'' + 
			",readMoreUrl = '" + readMoreUrl + '\'' + 
			",author = '" + author + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",time = '" + time + '\'' + 
			",title = '" + title + '\'' + 
			",content = '" + content + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}