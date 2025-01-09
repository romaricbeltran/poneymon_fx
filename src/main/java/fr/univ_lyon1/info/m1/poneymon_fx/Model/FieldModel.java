package fr.univ_lyon1.info.m1.poneymon_fx.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.poneymon_fx.App;
import javafx.animation.AnimationTimer;

/**
 * Gestion des traitements du terrain de jeu et du classement.
 *
 */
public class FieldModel {

    private PropertyChangeSupport support;
    private boolean pause = false;

    private PoneyModel[] tabPoneys;
    private List<Integer> ranking = new ArrayList<Integer>();

    /**
     * Constructeur du terrain qui charge les poneys.
     *
     */
    public FieldModel() {
        support = new PropertyChangeSupport(this);

        // Initialisation des poneys sur le terrain et dans le classement.
        tabPoneys = new PoneyModel[App.getNbponeys()];
        for (int i = 0; i < App.getNbponeys(); i++) {
            tabPoneys[i] = new PoneyImprovedModel();
            ranking.add(i);
        }

        // Initialisation des références vers les autres poneys.
        for (int i = 0; i < tabPoneys.length; i++) {
            for (int j = tabPoneys.length - 1; j >= 0; j--) {
                if (i != j) {
                    tabPoneys[i].addCompetitor(tabPoneys[j]);
                }
            }
        }

        // Boucle de notifications des traitements à la vue.
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                support.firePropertyChange("field", true, false);
                if (pause) {
                    try {
                        Thread.sleep(1000);
                        System.err.println("Pause");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
                if (step() == -1) {
                    tabPoneys[ranking.get(0)].theWinner();
                    System.err.println("Partie terminée");
                    this.stop();
                }
            }
        }.start();
    }

    /**
     * Traitement incrémental appellé pour chaque frame.
     * 
     * @return -1 si la course est terminée 0 sinon.
     */
    public int step() {

        // Avancement des poneys et réinitialisation de la vitesse à chaque tour.
        for (int i = 0; i < App.getNbponeys(); i++) {
            tabPoneys[i].step();
            if (checkLapDone(tabPoneys[i])) {
                tabPoneys[i].randomSpeed();
            }
        }

        /*
         * Lambda-Expression qui trie le classement en comparant récursivement les
         * progressions des poneys.
         */
        ranking.sort((
                o1, o2
        ) -> (int) tabPoneys[o2].getProgress() - (int) tabPoneys[o1].getProgress());

        /*
         * Affichage en temps réel du classement dans la console
         */
        System.out.println("----------------------------\n");
        for (int i = 0; i < App.getNbponeys(); i++) {
            tabPoneys[i].setCurrentRankingPosition(ranking.indexOf(i));
        }
        for (Integer item : ranking) {
            System.out.println(item);
        }
        System.out.println("----------------------------\n");

        // Vérification de fin de course.
        if (checkRaceFinished() == true) {
            return -1;
        }
        return 0;
    }

    /**
     * Récupération d'un poney de tabPoney en fonction de l'indice passé en
     * paramètre.
     * 
     * @param index index voulu par l'utilisateur.
     * @return référence sur PoneyModel
     */
    public PoneyModel getPoneyFromIndex(int index) {
        int i = 0;
        for (; i < App.getNbponeys(); i++) {
            if (index == i) {
                break;
            }
        }
        return tabPoneys[i];
    }

    /**
     * Incrémentation du nombre de tour.
     * 
     * @param poney poneyModel analysé.
     * @return true si nombre de tour augmenté false sinon.
     */
    public boolean checkLapDone(PoneyModel poney) {
        if ((int) poney.getProgress() != 0
                && (int) poney.getProgress() % (100 / App.getNblap()) == 0) {
            /*
             * Puisque l'on a des valeurs de double avec virgules ce bloc va être appelé
             * plusieurs fois pour éviter ca on check la progression avec le nb de tours si
             * il est égal au calcul alors on l'a déja incrémenté =>
             * (progression_courante/100.0 * nbLapDone).
             */
            if (poney.getNbLapDone() < ((int) poney.getProgress() / 100.0 * App.getNblap())) {
                poney.setNbLapDone(poney.getNbLapDone() + 1);
                if (poney instanceof PoneyImprovedModel) {
                    ((PoneyImprovedModel) poney).stopBoostPoney();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Vérification de l'état fini ou non de la course.
     * 
     * @return true si tous les poneys ont une progression de 100% false sinon.
     */
    public boolean checkRaceFinished() {
        for (int i = 0; i < App.getNbponeys(); i++) {
            if ((int) tabPoneys[i].getProgress() < 111) {
                return false;
            }
        }
        return true;
    }

    /**
     * Boost du poney spécifié par l'index en paramètre.
     * 
     * @param index index dans tabPoneys
     */
    public void boostSpecificPoney(int index) {
        if (tabPoneys[index] instanceof PoneyImprovedModel) {
            ((PoneyImprovedModel) tabPoneys[index]).boostPoneySpeed();
        }
    }

    /**
     * Accesseurs et Mutateurs.
     * 
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}