package com.service.google.places.parser;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import com.service.google.places.builder.IPlaceSuggestBuilder;

public class XPathPlaceSuggestBuilder extends XPathPlaceBaseBuilder implements
		IPlaceSuggestBuilder {

	private int resultId;

	
	public void setItemId(int resultId) {
		this.resultId = resultId;
	}

	
	public String buildStatus() {
		try {
			return (String) xpath.compile("/PlaceSearchResponse/status/text()")
					.evaluate(document, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return null;
		}
	}

	
	public int buildTypesCount() {

		try {
			return ((Number) xpath.compile(
					"count(/PlaceSearchResponse/result[" + (resultId + 1)
							+ "]/type)").evaluate(document,
					XPathConstants.NUMBER)).intValue();
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return 0;
		}
	}

	public int buildResultsCount() {
		try {
			return ((Number) xpath
					.compile("count(/PlaceSearchResponse/result)").evaluate(
							document, XPathConstants.NUMBER)).intValue();
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return 0;
		}
	}

	protected Double getResultItemDouble(String key) {
		try {
			Number val = ((Number) xpath.compile(
					"/PlaceSearchResponse/result[" + (resultId + 1) + "]/"
							+ key + "/text()").evaluate(document,
					XPathConstants.NUMBER));
			if (val.equals(Double.NaN))
				return null;
			return val.doubleValue();
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return null;
		}
	}

	protected String getResultItemString(String key) {
		try {
			return (String) xpath.compile(
					"/PlaceSearchResponse/result[" + (resultId + 1) + "]/"
							+ key + "/text()").evaluate(document,
					XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return null;
		}
	}
}
