package fr.univ_lyon1.info.m1.poneymon_fx.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import fr.univ_lyon1.info.m1.poneymon_fx.App;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Gestion de l'affichage du terrain.
 *
 */
public class FieldView extends Canvas implements View, PropertyChangeListener {

    private static GraphicsContext GC;
    private PoneyView[] tabPoneys;
    private Image fieldImage;

    /**
     * Canvas dans lequel on va dessiner le jeu.
     *
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas.
     */
    public FieldView(Scene scene) {
        // Initialisation du canvas.
        super(App.getWidth(), App.getHeight());
        fieldImage = new Image("assets/grass-texture.png", App.getWidth(), App.getHeight(), false,
                false);

        // Initialisation du contexte graphique.
        GC = this.getGraphicsContext2D();

        // Initialisation des poneys et de leurs positions.
        tabPoneys = new PoneyView[App.getNbponeys()];
        for (int i = 0; i < App.getNbponeys(); i++) {
            this.tabPoneys[i] = new PoneyView(i * PoneyView.getY_Init());
        }

        // Affichage des poneys
        for (int i = 0; i < App.getNbponeys(); i++) {
            tabPoneys[i].display();
        }
    }

    /**
     * Retourne un poneyView de tabPoneys en fonction de l'indice passé en
     * paramètre.
     * 
     * @param index index voulu par l'utilisateur.
     * @return référence sur PoneyView.
     */
    public PoneyView getPoneyFromIndex(int index) {
        int i = 0;
        for (; i < App.getNbponeys(); i++) {
            if (index == i) {
                break;
            }
        }
        return tabPoneys[i];
    }

    /**
     * Affichage du terrain et découpage en tours.
     * 
     */
    @Override
    public void display() {
        double position;
        GC.fillRect(0, 0, App.getWidth(), App.getHeight());
        GC.setStroke(Color.PINK);
        GC.setLineWidth(2);
        GC.drawImage(fieldImage, 0, 0);

        for (int i = 1; i < App.getNblap(); ++i) {
            position = i / (double) App.getNblap() * (App.getWidth());
            GC.strokeLine(position, 0, position, App.getHeight());
        }

    }

    /**
     * Se lance à chaque notification du modèle.
     * 
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        display();
    }

    /**
     * Inutile car on force le raffraichissement constant vers la méthode
     * propertyChange via FieldModel.
     * 
     */
    @Override
    public void update() {

    }

    /**
     * Accesseurs et Mutateurs.
     * 
     */
    public static GraphicsContext getGc() {
        return GC;
    }
}
