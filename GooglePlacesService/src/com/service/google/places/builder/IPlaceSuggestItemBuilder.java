package com.service.google.places.builder;

import java.io.InputStream;

public interface IPlaceSuggestItemBuilder {

	String buildStatus();

	void openStream(InputStream stream)
			throws ResponseBuilderParseException;

	String buildVicinity();

	double buildGeometryLatitude();

	double buildGeometryLongitude();

	String buildIcon();

	String buildName();

	Double buildRating();

	String buildReference();

	String buildType(int typeId);

	int buildTypesCount();

	String buildId();

}