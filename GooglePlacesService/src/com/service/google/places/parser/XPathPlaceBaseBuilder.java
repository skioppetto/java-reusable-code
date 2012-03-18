package com.service.google.places.parser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.service.google.places.GooBuilderParseException;
import com.service.google.places.IPlaceSuggestItemBuilder;

public abstract class XPathPlaceBaseBuilder implements IPlaceSuggestItemBuilder{

	protected XPath xpath;
	protected Document document;
	
	
	public void openStream(InputStream stream) throws GooBuilderParseException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			document = builder.parse(stream);
			xpath = XPathFactory.newInstance().newXPath();
		} catch (ParserConfigurationException e) {
			throw new GooBuilderParseException(e);
		} catch (SAXException e1) {
			throw new GooBuilderParseException(e1);
		} catch (IOException e2) {
			throw new GooBuilderParseException(e2);
		}

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

	public String buildVicinity() {
		return getResultItemString("vicinity");
	}

	public double buildGeometryLatitude() {
		return getResultItemDouble("geometry/location/lat");
	}

	public double buildGeometryLongitude() {
		return getResultItemDouble("geometry/location/lng");

	}

	public String buildIcon() {
		return getResultItemString("icon");
	}

	public String buildName() {
		return getResultItemString("name");
	}

	public Double buildRating() {
		return getResultItemDouble("rating");
	}

	public String buildReference() {
		return getResultItemString("reference");
	}
	
	public String buildId() {
		return getResultItemString("id");
	}


	public String buildType(int typeId) {
		return getResultItemString("type[" + (typeId + 1) + "]");
	}

	abstract String getResultItemString(String string);

	abstract Double getResultItemDouble(String string);
}
