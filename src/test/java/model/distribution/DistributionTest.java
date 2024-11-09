package model.distribution;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Vector;

import static org.junit.Assert.*;

public class DistributionTest {

    private Distribution emptyDistribution;
    private Distribution distribution;
    private Distribution distributionWithSections;
    private Distribution distributionWithAlgorithm;
    private Vector<String> sampleOrder;
    private ArrayList<EnumTypeSections> sampleSections;

    @Before
    public void setUp() {
        sampleOrder = new Vector<>();
        sampleOrder.add("Product1");
        sampleOrder.add("Product2");

        sampleSections = new ArrayList<>();
        sampleSections.add(EnumTypeSections.FOOD);
        sampleSections.add(EnumTypeSections.CLOTHING);

        emptyDistribution = new Distribution(1);
        distribution = new Distribution(1, sampleOrder);
        distributionWithSections = new Distribution(2, sampleOrder, sampleSections, "Greedy");
        distributionWithAlgorithm = new Distribution(3, sampleOrder, "Dijkstra");
    }

    @Test
    public void testConstructorWithId() {
        assertEquals(1, emptyDistribution.getId());
        assertNull(emptyDistribution.getOrder());
        assertNull(emptyDistribution.getSections());
        assertNull(emptyDistribution.getUsedAlgorithm());
    }

    @Test
    public void testConstructorWithIdAndOrder() {
        assertEquals(1, distribution.getId());
        assertEquals(sampleOrder, distribution.getOrder());
        assertNull(distribution.getSections());
        assertNull(distribution.getUsedAlgorithm());
    }

    @Test
    public void testConstructorWithIdOrderAndAlgorithm() {
        assertEquals(3, distributionWithAlgorithm.getId());
        assertEquals(sampleOrder, distributionWithAlgorithm.getOrder());
        assertNull(distributionWithAlgorithm.getSections());
        assertEquals("Dijkstra", distributionWithAlgorithm.getUsedAlgorithm());
    }

    @Test
    public void testConstructorWithIdOrderSectionsAndAlgorithm() {
        assertEquals(2, distributionWithSections.getId());
        assertEquals(sampleOrder, distributionWithSections.getOrder());
        assertEquals(sampleSections, distributionWithSections.getSections());
        assertEquals("Greedy", distributionWithSections.getUsedAlgorithm());
    }

    @Test
    public void testGetId() {
        assertEquals(1, distribution.getId());
    }

    @Test
    public void testGetOrder() {
        assertEquals(sampleOrder, distribution.getOrder());
    }

    @Test
    public void testGetSections() {
        assertNull(distribution.getSections());
        assertEquals(sampleSections, distributionWithSections.getSections());
    }

    @Test
    public void testGetUsedAlgorithm() {
        assertNull(distribution.getUsedAlgorithm());
        assertEquals("Greedy", distributionWithSections.getUsedAlgorithm());
    }

//    @Test
//    public void testSetOrder() {
//        Vector<String> newOrder = new Vector<>();
//        newOrder.add("Product3");
//        distribution.setOrder(newOrder);
//        assertEquals(newOrder, distribution.getOrder());
//    }

    @Test
    public void testSetSections() {
        ArrayList<EnumTypeSections> newSections = new ArrayList<>();
        newSections.add(EnumTypeSections.ELECTRONICS);
        distribution.setSections(newSections);
        assertEquals(newSections, distribution.getSections());
    }

    @Test
    public void testSetUsedAlgorithm() {
        distribution.setUsedAlgorithm("Prim");
        assertEquals("Prim", distribution.getUsedAlgorithm());
    }
}
