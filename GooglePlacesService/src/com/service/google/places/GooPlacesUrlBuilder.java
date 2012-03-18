package com.service.google.places;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GooPlacesUrlBuilder {

	private static String suggestBasePath = "https://maps.googleapis.com/maps/api/place/search/";
	private OutputType outputType;

	public GooPlacesUrlBuilder(OutputType type){this.outputType = type;}
	
	public URL buildPlacesSuggestUrl(SuggestParameters parameters)
			throws GooUrlBuilderException {

		if (parameters.getCoordinates() == null || parameters.getKey() == null
				|| parameters.getRadius() == null
				|| parameters.getFromDeviceUsingSensor() == null)
			throw new GooUrlBuilderException();
		
		/* mandatory parameters */
		StringBuilder sb = new StringBuilder(suggestBasePath);
		sb.append(outputType.toString());
		sb.append("?key=").append(parameters.getKey());
		sb.append("&radius=").append(parameters.getRadius().getMeters());
		sb.append("&sensor=").append(parameters.getFromDeviceUsingSensor());
		sb.append("&location=")
				.append(parameters.getCoordinates().getLatitude()).append(",")
				.append(parameters.getCoordinates().getLongitude());
		
		/* optional parameters */
		if (parameters.getAllFieldsSearchTerm() != null
				&& !parameters.getAllFieldsSearchTerm().isEmpty())
			sb.append("&keyword=").append(parameters.getAllFieldsSearchTerm());
		if (parameters.getPlaceNameSearchTerm() != null
				&& !parameters.getPlaceNameSearchTerm().isEmpty())
			sb.append("&name=").append(parameters.getPlaceNameSearchTerm());
		if (parameters.getLanguage() != null)
			sb.append("&language=").append(parameters.getLanguage().getCode());
		if (parameters.getTypes() != null && !parameters.getTypes().isEmpty())
			sb.append("&types=").append(buildTypesChain(parameters.getTypes()));
		try {
			return new URL(sb.toString());
		} catch (MalformedURLException e) {
			throw new GooUrlBuilderException(e);
		}
	}

	private String buildTypesChain(List<GooPlacesType> types) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < types.size(); i++) {
			sb.append(types.get(i).toString());
			if (i < types.size() - 1)
				sb.append("|");
		}
		return sb.toString();

	}

	public URL buildPlacesDetailUrl(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

}
