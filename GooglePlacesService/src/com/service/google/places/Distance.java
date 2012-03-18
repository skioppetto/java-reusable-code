package com.service.google.places;

public class Distance {
	public enum Unit {
		Meters, Kilometers
	}

	private int value;
	private Unit unit;

	public Distance(int value, Unit unit) {
		super();
		this.value = value;
		this.unit = unit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	int getMeters() {
		return (this.unit.equals(Unit.Meters) ? value : value * 1000);
	}

}
