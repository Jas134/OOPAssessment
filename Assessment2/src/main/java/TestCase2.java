import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import model.SpeciesDAO;
import model.Species;

class TestCase2 {

	private SpeciesDAO speciesDAO;

    @Before
    public void setUp() {
        speciesDAO = new SpeciesDAO();
    }

    @Test
    public void testUpdateAndDeleteSpecies() throws SQLException {
        // Step 1: Create a new Species
        Species newSpecies = new Species("Update Test", 2, "2023-04-01", "Original Content", false);
        speciesDAO.insertSpecies(newSpecies);

        // Step 2: Read all species and get the ID of the new species
        List<Species> speciesList = speciesDAO.selectAllSpecies();
        int speciesId = -1;
        for (Species species : speciesList) {
            if (species.getTitle().equals("Update Test")) {
                speciesId = species.getSpeciesID();
                break;
            }
        }
        assertNotEquals(-1, speciesId, "The species ID should not be -1");

        // Step 3: Update the species
        Species updatedSpecies = new Species(speciesId, "Update Test", 2, "2023-04-01", "Updated Content", false);
        speciesDAO.updateSpecies(updatedSpecies);

        // Step 4: Read the updated species
        Species fetchedSpecies = speciesDAO.selectSpecies(speciesId);
        assertEquals("The content should be updated", "Updated Content", fetchedSpecies.getContent());

        // Step 5: Delete the species
        speciesDAO.deleteSpecies(speciesId);

        // Step 6: Verify the species is deleted
        List<Species> updatedSpeciesList = speciesDAO.selectAllSpecies();
        boolean speciesDeleted = true;
        for (Species species : updatedSpeciesList) {
            if (species.getSpeciesID() == speciesId) {
                speciesDeleted = false;
                break;
            }
        }

        // Assertion
        assertTrue(speciesDeleted, "Species is deleted");
    }
}
