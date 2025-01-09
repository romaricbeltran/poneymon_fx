package fr.univ_lyon1.info.m1.poneymon_fx.View;

import fr.univ_lyon1.info.m1.poneymon_fx.App;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Gestion de la fenêtre JavaFx.
 *
 */
public class JfxView implements View {

    Stage stage;
    private FieldView field;
    private Button buttonBoost;
    private Button buttonPause;
    private Button buttonQuit;
    private ComboBox<String> poniesList;

    /**
     * Constructeur de JfxView qui initialise la fenêtre avec une scene.
     * 
     * @param stage  top-container de JavaFx
     */
    public JfxView(Stage stage) {

        // Construction de la scène.
        stage.setTitle("PoneyRunRun");
        Group root = new Group();
        Scene scene = new Scene(root);
        field = new FieldView(scene);

        root.getChildren().add(field);
        root.getChildren().add(getHbox());

        // Mise en place et affichage de la scène.
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Construction du menu déroulant et des boutons.
     * 
     * @return Objet HBox contenant les éléments à charger.
     */
    public HBox getHbox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(2, 20, 0, 12));
        hbox.setSpacing(10);
        hbox.setLayoutX((App.getWidth() / 2.0) - (1.0 / 5.0 * App.getWidth()));
        hbox.setLayoutY(App.getHeight() - 50);

        poniesList = new ComboBox<String>();

        for (int i = 1; i <= App.getNbponeys(); i++) {
            poniesList.getItems().add("Poney " + i);
        }

        poniesList.setStyle("-fx-font: 22px \"Serif\";");
        poniesList.getSelectionModel().selectFirst();
        poniesList.setPrefSize(1.0 / 6.0 * App.getWidth(), 1.0 / 12.5 * App.getHeight());

        buttonBoost = new Button("Booster");
        buttonBoost.setPrefSize(1.0 / 11.0 * App.getWidth(), 1.0 / 12.5 * App.getHeight());
        buttonBoost.setStyle("-fx-font: 20px \"Serif\";");

        buttonPause = new Button("pause");
        buttonPause.setPrefSize(1.0 / 11.0 * App.getWidth(), 1.0 / 12.5 * App.getHeight());
        buttonPause.setStyle("-fx-font: 20px \"Serif\";");

        buttonQuit = new Button("quitter");
        buttonQuit.setPrefSize(1.0 / 11.0 * App.getWidth(), 1.0 / 12.5 * App.getHeight());
        buttonQuit.setStyle("-fx-font: 20px \"Serif\";");

        hbox.getChildren().addAll(poniesList, buttonBoost, buttonPause, buttonQuit);

        return hbox;
    }

    /**
     * Affichage générique.
     * 
     */
    @Override
    public void display() {
        // TODO Auto-generated method stub
    }

    /**
     * Mise à jour de la vue.
     * 
     */
    @Override
    public void update() {

    }

    /**
     * Accesseurs et Mutateurs.
     * 
     */
    public Stage getStage() {
        return stage;
    }

    public FieldView getField() {
        return field;
    }

    public Button getButtonBoost() {
        return buttonBoost;
    }

    public Button getButtonPause() {
        return buttonPause;
    }

    public Button getButtonQuit() {
        return buttonQuit;
    }

    public ComboBox<String> getPoniesList() {
        return poniesList;
    }

}
