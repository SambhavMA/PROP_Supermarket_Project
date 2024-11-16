package model.distribution;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import static org.junit.Assert.*;

public class DistributionContainerTest {

    private DistributionContainer distributionContainer;
    private Distribution distribution;

    @Before
    public void setUp() {
        distributionContainer = new DistributionContainer();

        ArrayList<EnumTypeSections>  sampleSections = new ArrayList<>();
        sampleSections.add(EnumTypeSections.Fruita);
        sampleSections.add(EnumTypeSections.Verdura);

        Vector<String> sampleOrder = new Vector<>();
        sampleOrder.add("Product1");
        sampleOrder.add("Product2");

        distribution = new Distribution(1, sampleOrder, sampleSections, "Greedy");
    }

    @Test
    public void testConstructor() {
        assertNotNull(distributionContainer.getDistributions());
        assertEquals(0, distributionContainer.getDistributions().size());
        assertEquals(0, distributionContainer.getIdCounter());
    }

    /*
        Alomejor no añadir esta porque ya se comprueba en testAddDistribution

    @Test
    public void testGetDistributionById() {
        Distribution distribution = new Distribution(1, sampleOrder, sampleSections, "Greedy");
        distributionContainer.addDistribution(distribution);
        assertEquals(distribution, distributionContainer.getDistributionById(1));
    }

     */

    @Test
    public void testAddDistribution() {
        distributionContainer.addDistribution(distribution);
        assertEquals(distribution, distributionContainer.getDistributions().get(1));
    }

    @Test
    public void testDeleteDistributionById() {
        distributionContainer.addDistribution(distribution);
        distributionContainer.deleteDistributionById(1);
        assertNull(distributionContainer.getDistributions().get(1));
    }

    @Test
    public void testNewId() {
        assertEquals(1, distributionContainer.newId());
        assertEquals(2, distributionContainer.newId());
    }

    @Test
    public void testGetIdCounter() {
        assertEquals(0, distributionContainer.getIdCounter());
    }
}
