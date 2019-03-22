package com.sample.mvvmarchitecture.Genres;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

public class Profile {

	@PrimaryKey(autoGenerate = true)
	private int id;

	private Detail detail;
	private String status;

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	@TypeConverter
	public static List<Detail> storedStringToMyObjects(String data) {
		Gson gson = new Gson();
		if (data == null) {
			return Collections.emptyList();
		}
		Type listType = new TypeToken<List<Detail>>() {}.getType();
		return gson.fromJson(data, listType);
	}

	@TypeConverter
	public static String myObjectsToStoredString(List<Detail> myObjects) {
		Gson gson = new Gson();
		return gson.toJson(myObjects);
	}
}
