package com.sample.mvvmarchitecture.Genres;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GenresItem implements Parcelable {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"GenresItem{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}

	public GenresItem(Parcel source) {
		id = source.readInt();
		name = source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
	}

	public static final Creator<GenresItem> CREATOR = new Creator<GenresItem>() {
		@Override
		public GenresItem[] newArray(int size) {
			return new GenresItem[size];
		}

		@Override
		public GenresItem createFromParcel(Parcel source) {
			return new GenresItem(source);
		}
	};
}