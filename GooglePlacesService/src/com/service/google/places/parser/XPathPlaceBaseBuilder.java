package com.service.google.places.parser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.service.google.places.IPlaceSuggestItemBuilder;
import com.service.google.places.ResponseBuilderParseException;

 abstract class XPathPlaceBaseBuilder implements IPlaceSuggestItemBuilder{

	 XPath xpath;
	 Document document;
	
	
	public void openStream(InputStream stream) throws ResponseBuilderParseException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			document = builder.parse(stream);
			xpath = XPathFactory.newInstance().newXPath();
		} catch (ParserConfigurationException e) {
			throw new ResponseBuilderParseException(e);
		} catch (SAXException e1) {
			throw new ResponseBuilderParseException(e1);
		} catch (IOException e2) {
			throw new ResponseBuilderParseException(e2);
		}

	}

	public abstract String buildStatus();

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
