package models;

import java.util.ArrayList;
import java.util.List;

import databases.DBAccess;

public class Admin {

	public static List<Categories> getListOfCategories(){
		ArrayList<Categories> categories=new ArrayList<>();
		
		DBAccess.getQuery().query("select * from produce_category", row->{
			Categories category=new Categories(row.getInt("category_id"),
					row.getString("category_name"));
			categories.add(category);
		});
		
		return categories;
	}
}
