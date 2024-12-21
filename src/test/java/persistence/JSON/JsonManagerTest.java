package persistence.JSON;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.exceptions.IncorrectPathException;
import model.exceptions.IOErrorException;
import model.exceptions.FileCanNotReadException;
import model.exceptions.FileCanNotWriteException;

import java.io.*;

import static org.junit.Assert.*;

public class JsonManagerTest {
    private JsonManager jsonManager;
    private final String testDir = "test_files/";
    private final String validPath = testDir + "valid.json";
    private final String restrictedPath = testDir + "restricted.json";
    private final String nonExistentPath = testDir + "non_existent.json";

    @Before
    public void setUp() throws IOException {
        jsonManager = new JsonManager();
        new File(testDir).mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(validPath))) {
            writer.write("{\"Products\":[{\"name\":\"Poma\",\"type\":\"FRUITA\"}]}");
        }

        File restrictedFile = new File(restrictedPath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(restrictedFile))) {
            writer.write("{\"Products\":[]}");
        }
        restrictedFile.setWritable(false, false); // Make it read-only
    }

    @After
    public void tearDown() {
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
        File validFile = new File(validPath);
        JsonObject json = jsonManager.importFromFile(validFile);

        assertNotNull(json);
        assertTrue(json.has("Products"));
        JsonObject product = json.getAsJsonArray("Products").get(0).getAsJsonObject();
        assertEquals("Poma", product.get("name").getAsString());
        assertEquals("FRUITA", product.get("type").getAsString());
    }

    @Test(expected = IncorrectPathException.class)
    public void testImportFromFileNonExistentPath() throws IncorrectPathException, IOErrorException, FileCanNotReadException {
        File nonExistentFile = new File(nonExistentPath);
        jsonManager.importFromFile(nonExistentFile);
    }


    @Test
    public void testExportToFileValidPath() throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        JsonObject jsonObject = JsonParser.parseString("{\"Products\":[{\"name\":\"Platan\",\"type\":\"FRUITA\"}]}").getAsJsonObject();
        File outputFile = new File(testDir + "output.json");

        jsonManager.exportToFile(jsonObject, outputFile);

        assertTrue(outputFile.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            JsonObject exported = JsonParser.parseReader(reader).getAsJsonObject();
            assertNotNull(exported);
            assertTrue(exported.has("Products"));
            JsonObject product = exported.getAsJsonArray("Products").get(0).getAsJsonObject();
            assertEquals("Platan", product.get("name").getAsString());
            assertEquals("FRUITA", product.get("type").getAsString());
        } catch (IOException e) {
            fail("Error reading exported file");
        }
    }

    @Test(expected = FileCanNotWriteException.class)
    public void testExportToFileRestrictedPath() throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        JsonObject jsonObject = new JsonObject();
        File restrictedFile = new File(restrictedPath);

        jsonManager.exportToFile(jsonObject, restrictedFile);
    }

    @Test
    public void testExportToFileCreatesFile() throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        JsonObject jsonObject = JsonParser.parseString("{\"Products\":[{\"name\":\"Mandarina\",\"type\":\"FRUITA\"}]}").getAsJsonObject();
        File newFile = new File(testDir + "new_file.json");

        jsonManager.exportToFile(jsonObject, newFile);

        assertTrue(newFile.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(newFile))) {
            JsonObject exported = JsonParser.parseReader(reader).getAsJsonObject();
            assertNotNull(exported);
            assertTrue(exported.has("Products"));
            JsonObject product = exported.getAsJsonArray("Products").get(0).getAsJsonObject();
            assertEquals("Mandarina", product.get("name").getAsString());
            assertEquals("FRUITA", product.get("type").getAsString());
        } catch (IOException e) {
            fail("Error reading exported file");
        }
    }

    @Test(expected = IOErrorException.class)
    public void testExportToFileIOException() throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        JsonObject jsonObject = JsonParser.parseString("{\"Products\":[{\"name\":\"Mandarina\",\"type\":\"FRUITA\"}]}").getAsJsonObject();
        File invalidFile = new File("/invalid_path/output.json");

        jsonManager.exportToFile(jsonObject, invalidFile);
    }

    @Test(expected = IOErrorException.class)
    public void testExportToFileCannotCreateFile() throws IncorrectPathException, IOErrorException, FileCanNotWriteException {
        File invalidFile = new File("/invalid_path/output.json");

        JsonObject jsonObject = JsonParser.parseString("{\"Products\":[]}").getAsJsonObject();
        jsonManager.exportToFile(jsonObject, invalidFile);
    }

}
