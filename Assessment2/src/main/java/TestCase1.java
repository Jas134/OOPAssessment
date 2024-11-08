import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import model.SpeciesDAO;
import model.Species;

class TestCase1 {

	private SpeciesDAO speciesDAO;

    @Before
    public void setUp() {
        speciesDAO = new SpeciesDAO();
    }

    @Test
    public void testCreateAndReadSpecies() throws SQLException {
        // Step 1: Create a new Species
        Species newSpecies = new Species("Test Species", 2, "2023-04-01", "Test Content", false);
        speciesDAO.insertSpecies(newSpecies);

        // Step 2: Read all species and check if the new species was added
        List<Species> speciesList = speciesDAO.selectAllSpecies();
        boolean speciesExists = false;
        for (Species species : speciesList) {
            if (species.getTitle().equals("Test Species") && species.getContent().equals("Test Content")) {
                speciesExists = true;
                break;
            }
        }

        // Assertion
        assertTrue(speciesExists, "New species in list");
    }
}
