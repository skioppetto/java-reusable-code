package com.service.google.places.builder;


public interface IPlaceDetailBuilder extends IPlaceSuggestItemBuilder{

	String buildFormattedPhoneNumber();

	String buildInternationalPhoneNumber();

	String buildUrlGoolge();

	String buildUrlPlace();

	int buildAddressComponentsCount();

	String buildAddressComponentShortValue(int addressId);

	String buildAddressComponentLongValue(int addressId);

	String buildAddressComponentType(int addressId, int typeId);
	
	int buildAddressComponentTypesCount(int addressId);

	String buildFormattedAddress();

	
}
