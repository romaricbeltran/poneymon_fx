package fr.univ_lyon1.info.m1.poneymon_fx.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Class de gestion d'un poney.
 *
 */
public class PoneyModel {
    protected PropertyChangeSupport support;

    protected int nbLapDone;
    protected double speed;
    protected double progress;
    protected int currentRankingPosition;
    protected ArrayList<PoneyModel> competitors;

    /**
     * Constructeur du Poney.
     *
     */
    public PoneyModel() {
        // Initialisation des attributs membres.
        support = new PropertyChangeSupport(this);
        randomSpeed();
        progress = 0;
        nbLapDone = 0;
        competitors = new ArrayList<PoneyModel>();
        currentRankingPosition = 0;
    }

    /**
     * Avancement d'un poney et envoi de sa progression à la vue.
     * 
     */
    public void step() {
        if (progress < 111.0) {
            // On divise speed par 50 pour ralentir la progression.
            double newProgress = progress + (speed / 5.0);
            // On notifie poneyView si progress est différent de newProgress.
            support.firePropertyChange("poneyProgress", progress, newProgress);
            progress = newProgress;
        }
    }

    /**
     * Génération d'une vitesse aléatoire.
     * 
     */
    public void randomSpeed() {
        Random randomGenerator = new Random();
        speed = randomGenerator.nextFloat();
    }

    /**
     * Récupération du poney concurrent le plus proche.
     * 
     * @return Poney le plus proche.
     */
    public PoneyModel getNearestPoney() {
        PoneyModel nearestPoney = competitors.stream()
                .min(Comparator.comparing(item -> this.progress - item.getProgress())).get();
        return nearestPoney;
    }

    /**
     * Récupération du poney concurrent le plus lointain.
     * 
     * @return Poney le plus lointain.
     */
    public PoneyModel getFarthestPoney() {
        PoneyModel farthestPoney = competitors.stream()
                .max(Comparator.comparing(item -> this.progress - item.getProgress())).get();
        return farthestPoney;
    }

    /**
     * Notification de la progression complète du vainqueur à la vue.
     * 
     */
    public void theWinner() {
        support.firePropertyChange("poneyWinner", progress, progress = 50.0);
    }

    /**
     * Accesseurs et Mutateurs.
     * 
     */

    public double getPoneySpeed() {
        return speed;
    }

    public void setPoneySpeed(double spd) {
        speed = spd;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double prg) {
        progress = prg;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public int getNbLapDone() {
        return nbLapDone;
    }

    public void setNbLapDone(int nbLap) {
        this.nbLapDone = nbLap;
    }

    /**
     * Setter pour ajouter un poney dans la liste des poneys voisins.
     */
    void addCompetitor(PoneyModel newPoney) {
        competitors.add(newPoney);
    }

    /**
     * Setter pour supprimer un poney dans la liste des poneys voisins.
     */
    void removeCompetitor(PoneyModel poneyToDelete) {
        competitors.remove(poneyToDelete);
    }

    public int getCurrentRankingPosition() {
        return currentRankingPosition;
    }

    public void setCurrentRankingPosition(int currentRankingPosition) {
        this.currentRankingPosition = currentRankingPosition;
    }
}
