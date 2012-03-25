package com.service.google.places.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.test.ImpermanentGraphDatabase;

import scala.actors.threadpool.Arrays;

import com.service.google.places.model.GooPlacesType;

public class GooPlaceRelationsTest {

	private ImpermanentGraphDatabase graphDb;
	private GooPlaceRelations relations = GooPlaceRelations.getInstace();

	@Before
	public void prepareTestDatabase() {
		graphDb = new ImpermanentGraphDatabase();
	}

	@After
	public void destroyTestDatabase() {
		graphDb.shutdown();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void buildRelations() {
		graphDb.beginTx();
		GooPlaceNode placeN = new GooPlaceNode(graphDb.createNode());
		GooAddressItemNode n1 = new GooAddressItemNode(graphDb.createNode());
		GooAddressItemNode n2 = new GooAddressItemNode(graphDb.createNode());
		GooAddressItemNode n3 = new GooAddressItemNode(graphDb.createNode());
		GooAddressItemNode n4 = new GooAddressItemNode(graphDb.createNode());
		GooAddressItemNode n5 = new GooAddressItemNode(graphDb.createNode());
		/*
		 * GooPlacesType.sublocality_level_5, GooPlacesType.sublocality_level_4,
		 * GooPlacesType.sublocality_level_3, GooPlacesType.sublocality_level_2,
		 * GooPlacesType.sublocality_level_1, GooPlacesType.sublocality,
		 * GooPlacesType.locality, GooPlacesType.administrative_area_level_3,
		 * GooPlacesType.administrative_area_level_2,
		 * GooPlacesType.administrative_area_level_1, GooPlacesType.country
		 */
		n3.setShort("locality");
		n3.setTypes(Arrays.asList(new GooPlacesType[] { GooPlacesType.locality,
				GooPlacesType.political }));
		n5.setShort("admin1");
		n5.setTypes(Arrays.asList(new GooPlacesType[] {
				GooPlacesType.administrative_area_level_1,
				GooPlacesType.political }));
		n4.setShort("admin2");
		n4.setTypes(Arrays.asList(new GooPlacesType[] {
				GooPlacesType.administrative_area_level_2,
				GooPlacesType.political }));
		n1.setShort("sub1");
		n1.setTypes(Arrays.asList(new GooPlacesType[] {
				GooPlacesType.sublocality_level_1, GooPlacesType.political }));
		n2.setShort("sublocality");
		n2.setTypes(Arrays.asList(new GooPlacesType[] {
				GooPlacesType.sublocality, GooPlacesType.political }));

		List<GooAddressItemNode> items = Arrays
				.asList(new GooAddressItemNode[] { n1, n2, n3, n4, n5 });
		List<Node> itemNodes = Arrays.asList(new Node[] {
				n1.getUnderlyingNode(), n2.getUnderlyingNode(),
				n3.getUnderlyingNode(), n4.getUnderlyingNode(),
				n5.getUnderlyingNode() });
		relations.buildRelations(placeN, items);
		// test place_is_located place 1->n addressItem
		Node placeL = graphDb.getNodeById(placeN.getUnderlyingNode().getId());
		Assert.assertNotNull(placeL);
		Iterable<Relationship> rels = placeL.getRelationships(
				GooPlaceRelations.AddressItemRelations.PLACE_IS_LOCATED,
				Direction.OUTGOING);
		int count = 0;
		// test place node contains all address items
		List<Node> endNodes = new ArrayList<Node>();
		for (Relationship relationship : rels) {
			count++;
			Node item = relationship.getEndNode();
			endNodes.add(item);
			Assert.assertTrue(itemNodes.contains(item));
		}
		Assert.assertEquals(5, count);
		// test all item nodes are contained in place relations end nodes
		for (Node n : itemNodes)
			Assert.assertTrue(endNodes.contains(n));

		// test location_contains relations between items
		Node n1L = graphDb.getNodeById(n1.getUnderlyingNode().getId());
		Node n2L = graphDb.getNodeById(n2.getUnderlyingNode().getId());
		Node n3L = graphDb.getNodeById(n3.getUnderlyingNode().getId());
		Node n4L = graphDb.getNodeById(n4.getUnderlyingNode().getId());
		Node n5L = graphDb.getNodeById(n5.getUnderlyingNode().getId());
		testItemContainRelation(n1L, n2L);
		testItemContainRelation(n2L, n3L);
		testItemContainRelation(n3L, n4L);
		testItemContainRelation(n4L, n5L);
	}

	private void testItemContainRelation(Node n1, Node n2) {
		Iterable<Relationship> reln1 = n1.getRelationships(
				GooPlaceRelations.AddressItemRelations.LOCATION_CONTAINS,
				Direction.OUTGOING);
		int count = 0;
		for (Relationship relationship : reln1) {
			count++;
			Assert.assertEquals(n2, relationship.getEndNode());
		}
		Assert.assertEquals(1, count);
	}
}
