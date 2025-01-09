package fr.univ_lyon1.info.m1.poneymon_fx;

import java.util.Scanner;

import fr.univ_lyon1.info.m1.poneymon_fx.Controller.Controller;
import fr.univ_lyon1.info.m1.poneymon_fx.Model.FieldModel;
import fr.univ_lyon1.info.m1.poneymon_fx.View.JfxView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Classe principale de l'application avec interface graphique JavaFx.
 *
 */
public class App extends Application {

    // Paramètres de Gameplay
    private static int NB_PONEYS;
    private static final int NB_LAPS = 5;

    // Paramètres graphiques
    static final int WINDOW_WIDTH = 1200;
    static final int WINDOW_HEIGHT = 600;

    /**
     * Lancement de l'application.
     *
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Affichage du menu
        menu();

        // Construction MVC
        final FieldModel m = new FieldModel();
        final JfxView v = new JfxView(stage);
        Controller c = new Controller();
        buttonEvent(v, m);
        c.addView(v);
        c.setModel(m);
    }

    /**
     * Gestion des évènements souris.
     * 
     * @param v Vue JavaFX
     * @param m FieldModel
     */
    public void buttonEvent(JfxView v, FieldModel m) {
        // Event Booster
        v.getButtonBoost().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                m.boostSpecificPoney(v.getPoniesList().getSelectionModel().getSelectedIndex());
            }
        });

        // Event Pause
        v.getButtonPause().setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                if (!(m.isPause())) {
                    m.setPause(true);
                    Timeline timeline = new Timeline(
                        new KeyFrame(Duration.millis(5000), e -> m.setPause(false)),
                        new KeyFrame(Duration.millis(1000),
                            e -> v.getButtonPause().setText("3 sec")),
                        new KeyFrame(Duration.millis(2000),
                            e -> v.getButtonPause().setText("2 sec")),
                        new KeyFrame(Duration.millis(3000),
                            e -> v.getButtonPause().setText("1 sec")),
                        new KeyFrame(Duration.millis(4000),
                            e -> v.getButtonPause().setText("Pause")));
                    timeline.play();
                }
            }
        });

        // Event Quit
        v.getButtonQuit().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                try {
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println("Impossible de quitter maintenant !");
                }
            }
        });
    }

    /**
     * Affichage du menu avec choix du nombre de poneys.
     * 
     */
    public void menu() {

        System.out.println("Bienvenue dans PoneyRunRun !");

        Scanner sc = new Scanner(System.in);
        int reponse = -1;

        while (!(0 < reponse && reponse <= 5)) {
            try {
                System.out.println(
                        "-------------------------"
                        + "Choisir le nombre de poneys :--------------------------");
                System.out.println("       1.Un                                  ");
                System.out.println("       2.Deux                               ");
                System.out.println("       3.Trois                            ");
                System.out.println("       4.Quatre                           ");
                System.out.println("       5.Cinq                             ");
                System.out.println("");
                System.out.println("Taper votre choix ou 0 pour quitter:");
                reponse = Integer.parseInt(sc.nextLine());
                switch (reponse) {
                    case 1:
                        setNbPoneys(1);
                        System.out.println("C'est parti !");
                        sc.close();
                        break;
                    case 2:
                        setNbPoneys(2);
                        System.out.println("C'est parti !");
                        sc.close();
                        break;
                    case 3:
                        setNbPoneys(3);
                        System.out.println("C'est parti !");
                        sc.close();
                        break;
                    case 4:
                        setNbPoneys(4);
                        System.out.println("C'est parti !");
                        sc.close();
                        break;
                    case 5:
                        setNbPoneys(5);
                        System.out.println("C'est parti !");
                        sc.close();
                        break;
                    case 0:
                        System.out.println("Au revoir !");
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Veuillez respecter le menu.");
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /*
     * Accesseurs et Mutateurs.
     * 
     */
    public static int getNbponeys() {
        return NB_PONEYS;
    }

    public static void setNbPoneys(int nbPoneys) {
        App.NB_PONEYS = nbPoneys;
    }

    public static int getNblap() {
        return NB_LAPS;
    }

    public static int getWidth() {
        return WINDOW_WIDTH;
    }

    public static int getHeight() {
        return WINDOW_HEIGHT;
    }
}
