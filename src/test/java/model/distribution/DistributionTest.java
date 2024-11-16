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

        // emptyDistribution = new Distribution(1);
        // distribution = new Distribution(1, sampleOrder);
        distributionWithSections = new Distribution(2, 2, sampleOrder, sampleSections, "Greedy");
        distributionWithAlgorithm = new Distribution(3, 3, 10, sampleOrder, "Dijkstra", 100.05);
    }


//    @Test
//    public void testConstructorWithId() {
//        assertEquals(1, emptyDistribution.getId());
//        assertNull(emptyDistribution.getOrder());
//        assertNull(emptyDistribution.getSections());
//        assertNull(emptyDistribution.getUsedAlgorithm());
//    }
//

//    @Test
//    public void testConstructorWithIdAndOrder() {
//        assertEquals(1, distribution.getId());
//        assertEquals(sampleOrder, distribution.getOrder());
//        assertNull(distribution.getSections());
//        assertNull(distribution.getUsedAlgorithm());
//    }

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
        assertEquals(3, distributionWithAlgorithm.getId());
    }

    @Test
    public void testGetOrder() {
        assertEquals(sampleOrder, distributionWithAlgorithm.getOrder());
    }

    @Test
    public void testGetSections() {
        assertNull(distributionWithAlgorithm.getSections());
        assertEquals(sampleSections, distributionWithSections.getSections());
    }

    @Test
    public void testGetUsedAlgorithm() {
        assertEquals("Dijkstra", distributionWithAlgorithm.getUsedAlgorithm());
    }

    // @Test
    // public void testSetOrder() {
    // Vector<String> newOrder = new Vector<>();
    // newOrder.add("Product3");
    // distribution.setOrder(newOrder);
    // assertEquals(newOrder, distribution.getOrder());
    // }

    @Test
    public void testSetSections() {
        ArrayList<EnumTypeSections> newSections = new ArrayList<>();
        newSections.add(EnumTypeSections.Carn);
        distributionWithSections.setSections(newSections);
        assertEquals(newSections, distributionWithSections.getSections());
    }

    @Test
    public void testSetUsedAlgorithm() {
        distributionWithAlgorithm.setUsedAlgorithm("Prim");
        assertEquals("Prim", distributionWithAlgorithm.getUsedAlgorithm());
    }
}
