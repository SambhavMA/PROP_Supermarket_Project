package persistence.JSON;

import com.google.gson.JsonArray;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JsonManagerTest {
    private JsonManager jsonManager;
    private final String testDir = "test_files/";
    private final String exportPath = testDir + "exported_products.json";

    @Before
    public void setUp() throws IOException {
        jsonManager = new JsonManager();
        new File(testDir).mkdirs();
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
    public void testExportAndImportProducts() throws IncorrectPathException, IOErrorException, FileCanNotWriteException, FileCanNotReadException {
        // Crear lista de productos para exportar
        List<JsonObject> productsToExport = new ArrayList<>();
        JsonObject product1 = new JsonObject();
        product1.addProperty("name", "Poma");
        product1.addProperty("type", "FRUITA");
        productsToExport.add(product1);

        JsonObject product2 = new JsonObject();
        product2.addProperty("name", "Platan");
        product2.addProperty("type", "FRUITA");
        productsToExport.add(product2);

        // Crear objeto JSON para exportar
        JsonObject exportObject = new JsonObject();
        JsonArray productsArray = new JsonArray();
        productsToExport.forEach(productsArray::add);
        exportObject.add("Products", productsArray);

        // Exportar productos a un archivo
        File exportFile = new File(testDir + "exported_products.json");
        jsonManager.exportToFile(exportObject, exportFile);

        // Verificar que el archivo se ha creado correctamente
        assertTrue(exportFile.exists());

        // Importar el archivo generado
        JsonObject importedData = jsonManager.importFromFile(exportFile);

        // Verificar que los productos importados coincidan con los exportados
        assertTrue(importedData.has("Products"));
        JsonArray importedProducts = importedData.getAsJsonArray("Products");
        assertEquals(productsToExport.size(), importedProducts.size());

        for (int i = 0; i < productsToExport.size(); i++) {
            JsonObject original = productsToExport.get(i);
            JsonObject imported = importedProducts.get(i).getAsJsonObject();

            assertEquals(original.get("name").getAsString(), imported.get("name").getAsString());
            assertEquals(original.get("type").getAsString(), imported.get("type").getAsString());
        }
    }

}
