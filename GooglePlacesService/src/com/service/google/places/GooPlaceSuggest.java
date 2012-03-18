package com.service.google.places;

import java.util.List;

public class GooPlaceSuggest implements IGooStatus {

	private RequestStatus status;
	private List<GooPlaceSuggestItem> items;

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public List<GooPlaceSuggestItem> getItems() {
		return items;
	}

	public void setItems(List<GooPlaceSuggestItem> items) {
		this.items = items;
	}

}
