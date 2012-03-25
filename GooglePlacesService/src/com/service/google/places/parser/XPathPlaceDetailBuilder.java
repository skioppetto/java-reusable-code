package com.service.google.places.parser;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import com.service.google.places.builder.IPlaceDetailBuilder;

public class XPathPlaceDetailBuilder extends XPathPlaceBaseBuilder implements
		IPlaceDetailBuilder {

	
	public String buildFormattedPhoneNumber() {
		return getResultItemString("formatted_phone_number");
	}

	
	public String buildInternationalPhoneNumber() {
		return getResultItemString("international_phone_number");
	}

	
	public String buildUrlGoolge() {
		return getResultItemString("url");
	}

	
	public String buildUrlPlace() {
		return getResultItemString("website");
	}

	
	public int buildAddressComponentsCount() {
		try {
			return ((Number) xpath.compile(
					"count(/PlaceDetailsResponse/result/address_component)")
					.evaluate(document, XPathConstants.NUMBER)).intValue();
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return 0;
		}
	}

	
	public String buildAddressComponentShortValue(int i) {
		return getResultItemString("address_component[" + (i + 1)
				+ "]/short_name");
	}

	
	public String buildAddressComponentLongValue(int i) {
		return getResultItemString("address_component[" + (i + 1)
				+ "]/long_name");
	}

	
	public String buildAddressComponentType(int i, int j) {
		return getResultItemString("address_component[" + (i + 1) + "]/type["
				+ (j + 1) + "]");
	}

	
	public int buildAddressComponentTypesCount(int addressId) {
		try {
			return ((Number) xpath.compile(
					"count(/PlaceDetailsResponse/result/address_component["
							+ (addressId + 1) + "]/type)").evaluate(document,
					XPathConstants.NUMBER)).intValue();
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return 0;
		}
	}

	
	public String buildFormattedAddress() {
		return getResultItemString("formatted_address");
	}

	
	public String buildStatus() {
		try {
			return (String) xpath
					.compile("/PlaceDetailsResponse/status/text()").evaluate(
							document, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return null;
		}
	}

	public int buildTypesCount() {

		try {
			return ((Number) xpath.compile(
					"count(/PlaceDetailsResponse/result/type)").evaluate(
					document, XPathConstants.NUMBER)).intValue();
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return 0;
		}
	}

	protected Double getResultItemDouble(String key) {
		try {
			Number val = ((Number) xpath.compile(
					"/PlaceDetailsResponse/result/" + key + "/text()")
					.evaluate(document, XPathConstants.NUMBER));
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
					"/PlaceDetailsResponse/result/" + key + "/text()")
					.evaluate(document, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO:manage logging
			return null;
		}
	}
}
