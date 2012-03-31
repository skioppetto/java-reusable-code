package com.service.google.places;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.google.places.builder.PlacesEngineResultDirector;
import com.service.google.places.http.IHttpHandler;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlaceSuggest;
import com.service.google.places.request.GooDetailParameters;
import com.service.google.places.request.GooSuggestParameters;
import com.service.google.places.url.GooOutputType;
import com.service.google.places.url.PlacesEngineUrlBuilder;

@Service
public class GooPlacesEngine implements IGooPlaceEngine {

	@Autowired
	private PlacesEngineUrlBuilder urlBuilder ;
	@Autowired
	private IHttpHandler httpHandler ;
	@Autowired
	private PlacesEngineResultDirector parser ;

	public GooPlaceSuggest suggestPlaces(GooSuggestParameters parameters)
			throws PlacesEngineException {
		urlBuilder.setOutputType(GooOutputType.xml);
		URL url = urlBuilder.buildPlacesSuggestUrl(parameters);
		InputStream stream = httpHandler.getStream(url);
		return parser.parseSuggest(stream);
	}

	public GooPlaceDetail getDetail(GooDetailParameters parameters)
			throws PlacesEngineException {
		urlBuilder.setOutputType(GooOutputType.xml);
		URL url = urlBuilder.buildPlacesDetailUrl(parameters);
		InputStream stream = httpHandler.getStream(url);
		return parser.parseDetailPlace(stream);
	}

	public String suggestPlacesJson(GooSuggestParameters parameters)
			throws PlacesEngineException {
		urlBuilder.setOutputType(GooOutputType.json);
		URL url = urlBuilder.buildPlacesSuggestUrl(parameters);
		try {
			return convertStreamToString(httpHandler.getStream(url));
		} catch (IOException e) {
			throw new PlacesEngineException(e);
		}
	}

	public String getDetailJson(GooDetailParameters parameters)
			throws PlacesEngineException {
		urlBuilder.setOutputType(GooOutputType.json);
		URL url = urlBuilder.buildPlacesDetailUrl(parameters);
		try {
			return convertStreamToString(httpHandler.getStream(url));
		} catch (IOException e) {
			throw new PlacesEngineException(e);
		}
	}

	public String suggestPlacesXml(GooSuggestParameters parameters)
			throws PlacesEngineException {
		urlBuilder.setOutputType(GooOutputType.xml);
		URL url = urlBuilder.buildPlacesSuggestUrl(parameters);
		try {
			return convertStreamToString(httpHandler.getStream(url));
		} catch (IOException e) {
			throw new PlacesEngineException(e);
		}
	}

	public String getDetailXml(GooDetailParameters parameters)
			throws PlacesEngineException {
		urlBuilder.setOutputType(GooOutputType.xml);
		URL url = urlBuilder.buildPlacesDetailUrl(parameters);
		try {
			return convertStreamToString(httpHandler.getStream(url));
		} catch (IOException e) {
			throw new PlacesEngineException(e);
		}
	}

	private String convertStreamToString(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the Reader.read(char[]
		 * buffer) method. We iterate until the Reader return -1 which means
		 * there's no more data to read. We use the StringWriter class to
		 * produce the string.
		 */
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

}
