package com.service.google.places;

import java.util.List;

public class GooPlaceSuggest {

	private GooResponseStatus status;
	private List<GooPlaceSuggestItem> items;

	public GooResponseStatus getStatus() {
		return status;
	}

	 public List<GooPlaceSuggestItem> getItems() {
		return items;
	}

	 protected  void setStatus(GooResponseStatus status) {
		this.status = status;
	}

	 protected  void setItems(List<GooPlaceSuggestItem> items) {
		this.items = items;
	}

}
