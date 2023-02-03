package junit;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIShelterHomepageController;
import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.exception.NoPetsFoundException;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.ShelterPetsList;
import com.ispwproject.adoptme.model.ShelterModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestShowPets {
    /**
     * FRANCESCA PAVONE SALAFIA
     * Il seguente test va a testare che nell'interfaccia grafica della homepage di uno Shelter
     * venga mostrato il corretto numero di animali adottabili da esso registrati
     */
    @Test
    public void testShowPets(){
        ShelterModel shelterModel = new ShelterModel(1);
        ShelterPetsList shelterPetsList;
        int petNum = -1;

        try {
            shelterPetsList = PetDAO.retrievePetByShelterId(shelterModel, null);
            petNum = shelterPetsList.getPetList().size();
        } catch (NoPetsFoundException e) {
            petNum = 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("com/ispwproject/adoptme/ShelterHomepage.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);

        GUIShelterHomepageController guiShelterHomepageController = fxmlLoader.getController();
        guiShelterHomepageController.setCurrentPage(null);
        int shownPetNum = guiShelterHomepageController.getGrid().getChildren().size();

        assertEquals(petNum, shownPetNum, 0);
    }
}
