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
        sampleSections.add(EnumTypeSections.Fruita);
        sampleSections.add(EnumTypeSections.Verdura);

        distributionWithSections = new Distribution(2, 2, sampleOrder, sampleSections, "Greedy");
        distributionWithAlgorithm = new Distribution(3, 3, 10, sampleOrder, "Dijkstra", 100.05);
    }

    @Test
    public void testConstructorWithIdOrderAndAlgorithm() {
        assertEquals(3, distributionWithAlgorithm.getId());
        assertEquals(sampleOrder, distributionWithAlgorithm.getOrder());
        assertNull(distributionWithAlgorithm.getSections());
        assertEquals("Dijkstra", distributionWithAlgorithm.getUsedAlgorithm());
        assertEquals(100.05, distributionWithAlgorithm.getTemps(), 0.01);
        assertEquals(10, distributionWithAlgorithm.getCost(), 0.01);
        assertEquals(3, distributionWithAlgorithm.getSimilarityTableId());
    }

    @Test
    public void testConstructorWithIdOrderSectionsAndAlgorithm() {
        assertEquals(2, distributionWithSections.getId());
        assertEquals(sampleOrder, distributionWithSections.getOrder());
        assertEquals(sampleSections, distributionWithSections.getSections());
        assertEquals("Greedy", distributionWithSections.getUsedAlgorithm());
        assertEquals(2, distributionWithSections.getSimilarityTableId());
    }


    @Test
    public void testChangeOrder() {
        distributionWithSections.changeOrder("Product1", "Product2");
        Vector<String> newOrder = new Vector<>();
        newOrder.add("Product2");
        newOrder.add("Product1");
        assertEquals(newOrder, distributionWithSections.getOrder());
    }

    @Test
    public void testSetSections() {
        distributionWithAlgorithm.setSections(sampleSections);
        assertEquals(sampleSections, distributionWithAlgorithm.getSections());
    }

    @Test
    public void testSetUsedAlgorithm() {
        distributionWithAlgorithm.setUsedAlgorithm("Greedy");
        assertEquals("Greedy", distributionWithAlgorithm.getUsedAlgorithm());
    }

    @Test
    public void testSetCost() {
        distributionWithAlgorithm.setCost(20);
        assertEquals(20, distributionWithAlgorithm.getCost(), 0.01);
    }

}
