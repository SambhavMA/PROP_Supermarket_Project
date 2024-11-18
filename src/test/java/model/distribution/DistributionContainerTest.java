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
        distributionContainer = new DistributionContainer();

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
        assertEquals(0, distributionContainer.getDistributions().size());
        assertEquals(0, distributionContainer.getIdCounter());
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

    @Test
    public void testNewId() {
        assertEquals(1, distributionContainer.newId());
        assertEquals(2, distributionContainer.newId());
    }
}
