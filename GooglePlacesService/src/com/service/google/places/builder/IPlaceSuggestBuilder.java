package com.service.google.places.builder;



public interface IPlaceSuggestBuilder extends IPlaceSuggestItemBuilder {

	public abstract int buildResultsCount();
	
	void setItemId (int resultId);


}
