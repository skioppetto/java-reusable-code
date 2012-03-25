package com.service.google.places.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.service.google.places.model.GooPlacesType;
import com.service.google.places.request.GooBaseParameters;
import com.service.google.places.request.GooDetailParameters;
import com.service.google.places.request.GooSuggestParameters;

public class PlacesEngineUrlBuilder {

	private static String suggestBasePath = "https://maps.googleapis.com/maps/api/place/search/";
	private static String detailBasePath = "https://maps.googleapis.com/maps/api/place/details/";

	private GooOutputType outputType;

	public PlacesEngineUrlBuilder(GooOutputType type) {
		this.setOutputType(type);
	}

	public URL buildPlacesSuggestUrl(GooSuggestParameters parameters)
			throws PlacesEngineUrlBuilderException {

		if (parameters.getCoordinates() == null
				|| parameters.getRadius() == null)
			throw new PlacesEngineUrlBuilderException();

		/* mandatory parameters */
		StringBuilder sb = new StringBuilder(suggestBasePath);
		sb.append(getOutputType().toString());
		sb.append(commonMandatoryParameters(parameters));
		sb.append("&radius=").append(parameters.getRadius().getMeters());
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
			throw new PlacesEngineUrlBuilderException(e);
		}
	}

	public URL buildPlacesDetailUrl(GooDetailParameters parameters)
			throws PlacesEngineUrlBuilderException {
		StringBuilder sb = new StringBuilder(detailBasePath);
		sb.append(getOutputType().toString());
		sb.append(commonMandatoryParameters(parameters));
		if (parameters.getReference() == null)
			throw new PlacesEngineUrlBuilderException();
		sb.append("&reference=").append(parameters.getReference());

		/* optional parameters */
		if (parameters.getLanguage() != null)
			sb.append("&language=").append(parameters.getLanguage().getCode());

		try {
			return new URL(sb.toString());
		} catch (MalformedURLException e) {
			throw new PlacesEngineUrlBuilderException(e);
		}
	}

	private static String commonMandatoryParameters(GooBaseParameters parameters)
			throws PlacesEngineUrlBuilderException {
		StringBuilder mandatory = new StringBuilder();
		if (parameters.getKey() == null
				|| parameters.getFromDeviceUsingSensor() == null)
			throw new PlacesEngineUrlBuilderException();
		mandatory.append("?key=").append(parameters.getKey())
				.append("&sensor=")
				.append(parameters.getFromDeviceUsingSensor());
		return mandatory.toString();
	}

	private static String buildTypesChain(List<GooPlacesType> types) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < types.size(); i++) {
			sb.append(types.get(i).toString());
			if (i < types.size() - 1)
				sb.append("|");
		}
		return sb.toString();
	}

	public GooOutputType getOutputType() {
		return outputType;
	}

	public void setOutputType(GooOutputType outputType) {
		this.outputType = outputType;
	}

}
