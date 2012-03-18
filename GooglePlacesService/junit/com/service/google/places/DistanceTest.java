package com.service.google.places;

import junit.framework.Assert;

import org.junit.Test;

import com.service.google.places.Distance.Unit;

public class DistanceTest {

	@Test
	public void testMeters() {
		Distance d = new Distance(10, Unit.Kilometers);
		Assert.assertEquals(10000, d.getMeters());

		d = new Distance(10, Unit.Meters);
		Assert.assertEquals(10, d.getMeters());

	}

}
