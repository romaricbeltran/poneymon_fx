package fr.univ_lyon1.info.m1.poneymon_fx.Controller;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.poneymon_fx.App;
import fr.univ_lyon1.info.m1.poneymon_fx.Model.FieldModel;
import fr.univ_lyon1.info.m1.poneymon_fx.View.JfxView;
import fr.univ_lyon1.info.m1.poneymon_fx.View.View;

public class Controller {

    private FieldModel model;
    private List<JfxView> views = new ArrayList<JfxView>();

    /**
     * Charge une vue dans la liste des vues.
     * 
     * @param view Interface graphique.
     */
    public void addView(View view) {
        if (view instanceof JfxView) {
            views.add((JfxView) view);
        }
    }

    /**
     * Charge un modele et initialise les écouteurs pour toutes les vues.
     * 
     * @param m Modèle du terrain.
     */
    public void setModel(FieldModel m) {
        model = m;
        for (JfxView v : views) {
            model.addPropertyChangeListener(v.getField());

            for (int i = 0; i < App.getNbponeys(); i++) {
                model.getPoneyFromIndex(i)
                        .addPropertyChangeListener(v.getField().getPoneyFromIndex(i));
            }
        }
    }

    /**
     * Notification des vues.
     *
     */
    public void notifyViews() {
        for (View v : views) {
            v.update();
        }
    }
}
