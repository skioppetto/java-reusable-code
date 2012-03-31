package com.service.google.places.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scala.actors.threadpool.Arrays;

import com.service.google.places.model.GooAddressItem;
import com.service.google.places.model.PoliticalRelashionship;
import com.service.google.places.model.PoliticalRelashionshipTree;
import com.service.google.places.model.GooPlaceDetail;
import com.service.google.places.repositories.GooAddressItemRepository;
import com.service.google.places.repositories.PlaceDetailRepository;
import com.service.google.places.repositories.PoliticalRelashionshipRepository;

@Component
public class GooPlacesDaoImpl implements IGooPlacesDao {

	@Autowired
	private PlaceDetailRepository placeRepo;
	@Autowired
	private GooAddressItemRepository itemRepo;
	@Autowired
	private PoliticalRelashionshipRepository politicalRepo;

	final static List<?> treeList = Arrays
			.asList(PoliticalRelashionshipTree.tree);

	public void save(GooPlaceDetail detail) {
		List<GooAddressItem> treeItems = new ArrayList<GooAddressItem>();
		for (GooAddressItem gai : detail.getItems())
			if (isTreeElement(gai))
				treeItems.add(gai);
		Collections.sort(treeItems, new Comparator<GooAddressItem>() {
			public int compare(GooAddressItem o1, GooAddressItem o2) {
				return treeList.indexOf(o1.getTreeType())
						- treeList.indexOf(o2.getTreeType());
			}
		});

		for (int i = 0; i < treeItems.size() - 1; i++)
			if (!treeItems.get(i).getTypes().contains(treeItems.get(i + 1))) {
				PoliticalRelashionship rel = itemRepo.save(treeItems.get(i))
						.parentOf(itemRepo.save(treeItems.get(i + 1)),
								treeItems.get(i).getTreeType());
				politicalRepo.save(rel);

			}
		placeRepo.save(detail);
	}

	private boolean isTreeElement(GooAddressItem gai) {
		for (int i = 0; i < PoliticalRelashionshipTree.tree.length; i++)
			if (gai.getTypes().contains(PoliticalRelashionshipTree.tree[i])) {
				gai.setTreeType(PoliticalRelashionshipTree.tree[i]);
				return true;
			}
		return false;
	}

	public GooPlaceDetail getByUid(String uid) {
		return placeRepo.findByPropertyValue("uid", uid);
	}

}
