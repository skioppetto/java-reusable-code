package com.service.google.places.model;

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

	 public void setStatus(GooResponseStatus status) {
		this.status = status;
	}

	 public void setItems(List<GooPlaceSuggestItem> items) {
		this.items = items;
	}

}
