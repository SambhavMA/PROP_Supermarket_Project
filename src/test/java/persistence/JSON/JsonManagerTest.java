package persistence.JSON;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.exceptions.IncorrectPathException;
import model.exceptions.IOErrorException;
import model.exceptions.FileCanNotReadException;
import model.exceptions.FileCanNotWriteException;

import static org.junit.Assert.*;

public class JsonManagerTest {
    private JsonManager jsonManager;
    private final String testDir = "test_files/";
    private final String validPath = testDir + "valid.json";
    private final String restrictedPath = testDir + "restricted.json";
    private final String validDir = testDir + "validDir/";
    private final String invalidPath = "/:/invalid/test.json";

    @Before
    public void setUp() {
        jsonManager = new JsonManager();

        new File(testDir).mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(validPath))) {
            writer.write("{\"Products\":[{\"name\":\"Poma\",\"type\":\"FRUITA\"}]}");
        } catch (IOException e) {
            throw new FileCanNotWriteException(validPath);
        }

        File restrictedJson = new File(restrictedPath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(restrictedJson))) {
            writer.write("{\"Products\":[]}");
        } catch (IOException e) {
            throw new IOErrorException(restrictedPath, "writing");
        }
        restrictedJson.setWritable(false, false);


        new File(validDir).mkdirs();
    }

    @After
    public void tearDown() {
        deleteDirectory(new File(validDir));
        deleteDirectory(new File(testDir));
    }

    private void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                deleteDirectory(file);
            }
        }
        dir.delete();
    }

    @Test
    public void testImportFromFileValidPath() throws IncorrectPathException, IOErrorException, FileCanNotReadException {
        JsonObject json = jsonManager.importFromFile(validPath);
        assertNotNull(json);
        assertTrue(json.has("Products"));
        assertEquals("Poma", json.getAsJsonArray("Products").get(0).getAsJsonObject().get("name").getAsString());
        assertEquals("FRUITA", json.getAsJsonArray("Products").get(0).getAsJsonObject().get("type").getAsString());
    }

    @Test(expected = IncorrectPathException.class)
    public void testImportFileNonExistentPath() throws IncorrectPathException, IOErrorException, FileCanNotReadException {
        jsonManager.importFromFile(invalidPath);
    }

    @Test
    public void testExportToFileValidPath() throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        JsonObject jsonObject = JsonParser.parseString("{\"Products\":[{\"name\":\"Platan\",\"type\":\"FRUITA\"}]}").getAsJsonObject();

        jsonManager.exportToFile(validPath, jsonObject);

        File exportedFile = new File(validPath);
        assertTrue(exportedFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(exportedFile))) {
            JsonObject exported = JsonParser.parseReader(reader).getAsJsonObject();
            assertNotNull(exported);
            assertTrue(exported.has("Products"));

            JsonObject product = exported.getAsJsonArray("Products").get(0).getAsJsonObject();
            assertEquals("Platan", product.get("name").getAsString());
            assertEquals("FRUITA", product.get("type").getAsString());
        } catch (IOException e) {
            throw new IOErrorException(validPath, "reading");
        }
    }

    @Test
    public void testExportToDirectory() throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        JsonObject jsonObject = JsonParser.parseString("{\"Products\":[{\"name\":\"Platan\",\"type\":\"FRUITA\"}]}").getAsJsonObject();

        jsonManager.exportToFile(validDir, jsonObject);

        File exportedFile = new File(validDir + "output.json");
        assertTrue(exportedFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(exportedFile))) {
            JsonObject exported = JsonParser.parseReader(reader).getAsJsonObject();
            assertNotNull(exported);
            assertTrue(exported.has("Products"));

            JsonObject product = exported.getAsJsonArray("Products").get(0).getAsJsonObject();
            assertEquals("Platan", product.get("name").getAsString());
            assertEquals("FRUITA", product.get("type").getAsString());
        } catch (IOException e) {
            throw new IOErrorException(validDir + "output.json", "reading");
        }
    }

    @Test(expected = IncorrectPathException.class)
    public void testExportFileNonExistentPath() throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        JsonObject jsonObject = new JsonObject();
        jsonManager.exportToFile(invalidPath, jsonObject);
    }

    @Test(expected = FileCanNotWriteException.class)
    public void testExportFileRestrictedPath() throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        JsonObject jsonObject = new JsonObject();
        jsonManager.exportToFile(restrictedPath, jsonObject);
    }
}
