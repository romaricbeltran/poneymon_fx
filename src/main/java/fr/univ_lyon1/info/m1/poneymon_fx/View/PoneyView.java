package fr.univ_lyon1.info.m1.poneymon_fx.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import fr.univ_lyon1.info.m1.poneymon_fx.App;
import javafx.scene.image.Image;

public class PoneyView implements View, PropertyChangeListener {

    // Ligne de départ.
    private static int Y_INIT = 110;
    private static int CURRENT_NB_PONEYS = 0;

    // Position courante.
    private double currentX;
    private double currentY;


    private static String[] colorMap = new String[] { "blue", "green", "orange", "purple",
        "yellow" };
    private String poneyColor;

    Image currentPoney;
    Image poneyImage;

    /**
     * Constructeur de poneyView.
     *
     * @param nbSign ContextGraphic dans lequel on va afficher le poney.
     * @param yField Position par rapport au nombre de poneys déjà sur le terrain.
     */
    PoneyView(int yField) {

        // Initialisation de la piste du poney.
        currentY = yField;
        
        // On récupère le nombre de poneys courant pour changer de couleurs.
        poneyColor = colorMap[CURRENT_NB_PONEYS];
        CURRENT_NB_PONEYS++;

        // Chargement de l'image associée au poney
        poneyImage = new Image("assets/pony-" + poneyColor + "-running.gif");
        currentPoney = poneyImage;

    }

    /**
     * Affichage du poney.
     * 
     */
    @Override
    public void display() {
        FieldView.getGc().drawImage(currentPoney, currentX, currentY);
    }

    /**
     * Mise à jour du poney.
     * 
     */
    @Override
    public void update() {
        display();
    }

    /**
     * Se lance à chaque notification de PoneyModel.
     * 
     * @param evt Evènement récupéré par le propertyChangeListener.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Déplacement de l'image du poney.
        if (evt.getPropertyName() == "poneyProgress") {
            currentX = ((((Double) evt.getNewValue()) / 100.0) * (App.getWidth() - 105.0)) - 105.0;
        }
        // Transformation en superPoney.
        if (evt.getPropertyName() == "superPoneyMode") {
            if ((Boolean) evt.getNewValue()) {
                poneyImage = new Image("assets/pony-" + poneyColor + "-rainbow.gif");
                currentPoney = poneyImage;
            } else {
                poneyImage = new Image("assets/pony-" + poneyColor + "-running.gif");
                currentPoney = poneyImage;
            }
        }
        // Affichage du poney gagnant.
        if (evt.getPropertyName() == "poneyWinner") {
            currentX = ((((Double) evt.getNewValue()) / 100.0) * (App.getWidth() - 105.0)) - 105.0;
            currentY = App.getHeight() / 2 - 100;
        }
        update();
    }

    /**
     * Accesseurs et Mutateurs.
     * 
     */
    public static int getY_Init() {
        return Y_INIT;
    }

    public static int getCurrentNbPoneys() {
        return CURRENT_NB_PONEYS;
    }
}
