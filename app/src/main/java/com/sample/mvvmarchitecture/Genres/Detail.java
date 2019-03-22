package com.sample.mvvmarchitecture.Genres;

import java.util.List;

public class Detail{
	private List<ListItem> list;

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	@Override
 	public String toString(){
		return 
			"Detail{" + 
			"list = '" + list + '\'' + 
			"}";
		}
}