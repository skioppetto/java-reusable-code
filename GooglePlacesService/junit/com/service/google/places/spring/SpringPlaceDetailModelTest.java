package com.service.google.places.spring;

import java.util.HashSet;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import scala.actors.threadpool.Arrays;

import com.service.google.places.dao.GooPlaceSpringFactory;
import com.service.google.places.model.GooAddress;
import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.model.GooPlacesType;
import com.service.google.places.repositories.GooAddressItemRepository;
import com.service.google.places.repositories.PlaceDetailRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:META-INF/GooglePlaceService-context.xml" })
public class SpringPlaceDetailModelTest {

	@Autowired
	private PlaceDetailRepository detailRepo;
	@Autowired
	private GooAddressItemRepository addressRepo;
	@Autowired
	private GooPlaceSpringFactory factory;
	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	public void buildAndFindGooPlaceDetailTest() {

		GooPlaceDetail det = new GooPlaceDetail();
		det.setName("myName");
		det.setUid("myUid");
		GooAddress addr = new GooAddress();
		addr.setFormattedAddress("formatted");
		GooAddressItem item1 = new GooAddressItem();
		item1.setShort("shortItem1");
		item1.setValue("longValue");
		item1.setTypes(new HashSet(Arrays.asList(new Object[] {
				GooPlacesType.administrative_area_level_2,
				GooPlacesType.amusement_park })));
		addr.setAddressItems(new HashSet(Arrays
				.asList(new GooAddressItem[] { item1 })));
		det.setAddress(addr);
		GooPlaceDetail saved = detailRepo.save(det);
		Assert.assertTrue(saved.getNodeId() > 0);
		GooPlaceDetail retrivied = detailRepo.findByPropertyValue("uid",
				saved.getUid());
		Assert.assertNotNull(retrivied);
		Assert.assertTrue(retrivied.getAddress().getAddressItems().size() == 1);
		Assert.assertEquals(det.getUid(), retrivied.getUid());
	}

	@Test
	public void buildAndFindGooAddressItemTest() {
		GooAddressItem item = new GooAddressItem();
		item.setShort("shortVal");
		item.setValue("value");

		GooAddressItem saved = addressRepo.save(item);
		Assert.assertTrue(saved.getNodeId() > 0);
		Assert.assertEquals(item.getShortValue(), saved.getShortValue());

	}
	
	@Test
	@Transactional
	public void createUsingFactory(){
		GooPlaceDetail detail = factory.createGooPlaceDetail("val");
		Assert.assertNull(detail.getNodeId() );
		GooPlaceDetail saved = detailRepo.save(detail);
		Assert.assertTrue(saved.getNodeId() > 0);
		GooPlaceDetail secondRetrieve = factory.createGooPlaceDetail("val");
		Assert.assertTrue(secondRetrieve.getNodeId() > 0);
		
		GooAddressItem item = factory.createGooAddressItem("item");
		Assert.assertNull(item.getNodeId() );
		GooAddressItem savedItem = addressRepo.save(item);
		Assert.assertTrue(savedItem.getNodeId() > 0);
		GooAddressItem secondRetrieveItem = factory.createGooAddressItem("item");
		Assert.assertTrue(secondRetrieveItem.getNodeId() > 0);
		
	}
}
