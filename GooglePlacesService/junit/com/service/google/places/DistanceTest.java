package com.service.google.places;

import junit.framework.Assert;

import org.junit.Test;

import com.service.google.places.GooDistance.Unit;

public class DistanceTest {

	@Test
	public void testMeters() {
		GooDistance d = new GooDistance(10, Unit.Kilometers);
		Assert.assertEquals(10000, d.getMeters());

		d = new GooDistance(10, Unit.Meters);
		Assert.assertEquals(10, d.getMeters());

	}

}
