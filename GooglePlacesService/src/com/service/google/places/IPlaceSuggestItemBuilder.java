package com.service.google.places;

import java.io.InputStream;

public interface IPlaceSuggestItemBuilder {

	public abstract String buildStatus();

	public abstract void openStream(InputStream stream)
			throws GooBuilderParseException;

	public abstract String buildVicinity();

	public abstract double buildGeometryLatitude();

	public abstract double buildGeometryLongitude();

	public abstract String buildIcon();

	public abstract String buildName();

	public abstract Double buildRating();

	public abstract String buildReference();

	public abstract String buildType(int typeId);

	public abstract int buildTypesCount();

	public abstract String buildId();

}