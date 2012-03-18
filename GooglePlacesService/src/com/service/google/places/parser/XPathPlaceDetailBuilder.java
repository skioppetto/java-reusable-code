package com.service.google.places.parser;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import com.service.google.places.IPlaceDetailBuilder;

public class XPathPlaceDetailBuilder extends XPathPlaceBaseBuilder implements
		IPlaceDetailBuilder {

	@Override
	public String buildFormattedPhoneNumber() {
		return getResultItemString("formatted_phone_number");
	}

	@Override
	public String buildInternationalPhoneNumber() {
		return getResultItemString("international_phone_number");
	}

	@Override
	public String buildUrlGoolge() {
		return getResultItemString("url");
	}

	@Override
	public String buildUrlPlace() {
		return getResultItemString("website");
	}

	@Override
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

	@Override
	public String buildAddressComponentShortValue(int i) {
		return getResultItemString("address_component[" + (i + 1)
				+ "]/short_name");
	}

	@Override
	public String buildAddressComponentLongValue(int i) {
		return getResultItemString("address_component[" + (i + 1)
				+ "]/long_name");
	}

	@Override
	public String buildAddressComponentType(int i, int j) {
		return getResultItemString("address_component[" + (i + 1) + "]/type["
				+ (j + 1) + "]");
	}

	@Override
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

	@Override
	public String buildFormattedAddress() {
		return getResultItemString("formatted_address");
	}

	@Override
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
