package model.distribution;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.exceptions.DistributionNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DistributionContainerTest {

    private DistributionContainer distributionContainer;

    @Mock
    private Distribution mockdistribution;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        distributionContainer = distributionContainer.getInstance();

        ArrayList<EnumTypeSections>  sampleSections = new ArrayList<>();
        sampleSections.add(EnumTypeSections.Fruita);
        sampleSections.add(EnumTypeSections.Verdura);

        Vector<String> sampleOrder = new Vector<>();
        sampleOrder.add("Product1");
        sampleOrder.add("Product2");

        when(mockdistribution.getSections()).thenReturn(sampleSections);
        when(mockdistribution.getOrder()).thenReturn(sampleOrder);
        when(mockdistribution.getUsedAlgorithm()).thenReturn("Greedy");
        when(mockdistribution.getId()).thenReturn(1);
    }

    @Test
    public void testConstructor() {
        assertNotNull(distributionContainer.getDistributions());
        assertEquals(1, distributionContainer.getDistributions().size());
        assertEquals(0, distributionContainer.getIdCounter());
        assertEquals(1, distributionContainer.nextId());
        assertEquals(1, distributionContainer.newId());
        try {
            assertEquals(1, distributionContainer.getDistributionById(1).getId());
        } catch (DistributionNotFoundException e) {
            fail("DistributionNotFoundException thrown");
        }
    }

    @Test
    public void testAddDistribution() {
        distributionContainer.addDistribution(1, mockdistribution);
        assertEquals(mockdistribution, distributionContainer.getDistributions().get(1));
    }

    @Test
    public void testDeleteDistributionById() {
        distributionContainer.addDistribution(1, mockdistribution);
        distributionContainer.deleteDistributionById(1);
        assertNull(distributionContainer.getDistributions().get(1));
    }

}
