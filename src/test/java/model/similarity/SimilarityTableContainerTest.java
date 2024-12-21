package model.similarity;

import model.exceptions.SimilarityTableNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SimilarityTableContainerTest {

    private SimilarityTableContainer similarityTableContainer;

    @Mock
    private SimilarityTable mocksimilarityTable;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        similarityTableContainer = SimilarityTableContainer.getInstance();

        when(mocksimilarityTable.getId()).thenReturn(1);
        when(mocksimilarityTable.getFastIndexes()).thenReturn(new HashMap<String, Integer>());
        when(mocksimilarityTable.getRelationMatrix()).thenReturn(new double[0][0]);

        similarityTableContainer.addSimilarityTable(1, mocksimilarityTable);

    }

    @Test
    public void testAddSimilarityTable() {
        SimilarityTable newMockST = mock(SimilarityTable.class);
        when(newMockST.getId()).thenReturn(2);

        similarityTableContainer.addSimilarityTable(2, newMockST);

        try {
            SimilarityTable st = similarityTableContainer.getSimilarityTableById(2);
            assertEquals(newMockST, st);
        } catch (SimilarityTableNotFoundException e) {
            fail("Unexpected SimilarityTableNotFoundException");
        }
    }

    @Test
    public void testModifySimilarityTable() {
        SimilarityTable newMockST = mock(SimilarityTable.class);
        when(newMockST.getId()).thenReturn(2);
        similarityTableContainer.addSimilarityTable(2, newMockST);
        try {
            similarityTableContainer.modifySimilarityTable(2, mocksimilarityTable);
            SimilarityTable st = similarityTableContainer.getSimilarityTableById(2);
            assertEquals(mocksimilarityTable, st);
        } catch (SimilarityTableNotFoundException e) {
            fail("Unexpected SimilarityTableNotFoundException");
        }
    }

    @Test
    public void testModifySimilarityTableWithoutST() {
        try {
            similarityTableContainer.modifySimilarityTable(2, mocksimilarityTable);
            fail("Expected SimilarityTableNotFoundException");
        } catch (SimilarityTableNotFoundException e) {
            assertEquals("The similarity table with id 2 was not found in the system.", e.getMessage());
        }
    }

    @Test
    public void testDeleteSimilarityTableById() {
        similarityTableContainer.addSimilarityTable(2, mocksimilarityTable);
        try {
            similarityTableContainer.deleteSimilarityTableById(2);
            similarityTableContainer.getSimilarityTableById(2);
            fail("Expected SimilarityTableNotFoundException");
        } catch (SimilarityTableNotFoundException e) {
            assertEquals("The similarity table with id 2 was not found in the system.", e.getMessage());
        }
    }

    @Test
    public void testDeleteSimilarityTableByIdWithoutST() {
        try {
            similarityTableContainer.deleteSimilarityTableById(2);
            fail("Expected SimilarityTableNotFoundException");
        } catch (SimilarityTableNotFoundException e) {
            assertEquals("The similarity table with id 2 was not found in the system.", e.getMessage());
        }
    }

}
