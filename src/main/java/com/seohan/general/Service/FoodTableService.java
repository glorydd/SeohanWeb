package com.seohan.general.Service;

import java.util.List;

import com.seohan.general.Domain.FoodTable;

public interface FoodTableService { 
	public List<FoodTable> foodTableList(FoodTable foodTable) throws Exception; 
}
