package fr.univ_lyon1.info.m1.poneymon_fx.Model;

import fr.univ_lyon1.info.m1.poneymon_fx.App;

public class PoneyImprovedModel extends PoneyModel {
    private boolean usingBoost;
    private boolean alreadyBoost;

    /**
     * Constructeur du Poney amélioré.
     *
     */
    public PoneyImprovedModel() {
        super();
        usingBoost = false;
        alreadyBoost = false;
    }

    /**
     * Double la vitesse du poney (mode NianPoney).
     */
    void boostPoneySpeed() {
        if (!alreadyBoost) {
            speed = speed * 2;
            support.firePropertyChange("superPoneyMode", usingBoost, true);
            this.usingBoost = true;
            this.alreadyBoost = true;
        }
    }

    /**
     * Procédure qui stop le boost d'un poney.
     */
    public void stopBoostPoney() {
        randomSpeed();
        support.firePropertyChange("superPoneyMode", this.usingBoost, false);
        this.usingBoost = false;
    }

    public boolean isUsingBoost() {
        return usingBoost;
    }

    /**
     * Fonction qui établie une stratégie pour un poney donné.
     */
    public void iaFunctionnality() {
        if (progress > 15.0 && progress < 33.3) {
            if (currentRankingPosition == competitors.size()) {
                if (getFarthestPoney().getProgress() - progress > 20.0) {
                    boostPoneySpeed();
                }
            }
        } else if (progress > 33.3 && progress < 66.6) {
            if (currentRankingPosition == 0) {
                if (progress - getNearestPoney().getProgress() > 15.0) {
                    if (speed < 0.3) {
                        boostPoneySpeed();
                    }
                }
            } else if (currentRankingPosition == competitors.size()) {
                if (getFarthestPoney().getProgress() - progress > 20.0) {
                    boostPoneySpeed();
                }
            } else if (currentRankingPosition > 0 && currentRankingPosition < competitors.size()) {
                if (getNearestPoney().getProgress() - progress > 12.0) {
                    if (speed < 0.4) {
                        boostPoneySpeed();
                    }
                }
            }
        } else if (progress > 66.6 && progress < 99.0) {
            if (currentRankingPosition == 0 && progress - getNearestPoney().getProgress() < 20) {
                boostPoneySpeed();
            } else if (currentRankingPosition == competitors.size()) {
                boostPoneySpeed();
            } else if (currentRankingPosition > 0 && currentRankingPosition < competitors.size()) {
                if (speed < 0.5) {
                    boostPoneySpeed();
                }
            }
        }
    }

    @Override
    public void step() {
        super.step();
        if (App.getNbponeys() > 1) {
            iaFunctionnality();
        }
    }
}
