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

        similarityTableContainer.addSimilarityTable(1, mocksimilarityTable);

    }

    @Test
    public void testConstructor() {
        HashMap<Integer, SimilarityTable> similarityTables = similarityTableContainer.getSimilarityTables();
        assertEquals(1, similarityTables.size());
        assertTrue(similarityTables.containsKey(1));
        assertEquals(mocksimilarityTable, similarityTables.get(1));
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
            assertEquals("La tabla de similitud con id: 2 no se ha encontrado en el sistema.", e.getMessage());
        }
    }

    @Test
    public void testModifySimilarityTableWithST() {
        similarityTableContainer.addSimilarityTable(2, mocksimilarityTable);
        try {
            similarityTableContainer.modifySimilarityTable(2, mocksimilarityTable);
            SimilarityTable st = similarityTableContainer.getSimilarityTableById(2);
            assertEquals(mocksimilarityTable, st);
        } catch (SimilarityTableNotFoundException e) {
            fail("Unexpected SimilarityTableNotFoundException");
        }
    }

    @Test
    public void testDeleteSimilarityTableByIdWithoutST() {
        try {
            similarityTableContainer.deleteSimilarityTableById(3);
            fail("Expected SimilarityTableNotFoundException");
        } catch (SimilarityTableNotFoundException e) {
            assertEquals("La tabla de similitud con id: 3 no se ha encontrado en el sistema.", e.getMessage());
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
            assertEquals("La tabla de similitud con id: 2 no se ha encontrado en el sistema.", e.getMessage());
        }
    }

    @Test
    public void testNewId() {
        assertEquals(1, similarityTableContainer.newId());
    }

    @Test
    public void testNextId() {
        assertEquals(2, similarityTableContainer.nextId());
    }

}
