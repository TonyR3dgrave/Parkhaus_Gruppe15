package de.hbrs.team89.se1_starter_repo;

import java.util.List;

public abstract class View {

    public Publisher model; // Das Model, woher die Daten kommen
    public String view; // Anzeige

    /**
     * Abboniere das Model.
     */
    public void subscribe(Publisher model){
        this.model = model;
        model.addView(this);
        update();
    }

    /**
     * Template Method vom Aktualisieren der View<br>
     * fetchList() zieht sich die relevante Liste vom Model <br>
     * getRelevantData() reduziert die Liste auf einen für die Liste relevanten Wert <br>
     * updateView() aktualisiert die Ansicht
     */
    public void update() {
        List<?> updatedList = fetchList();
        Number reducedData = getRelevantData(updatedList);
        updateView(reducedData);
    }

    public String getView() { return view; }

    /**
        Siehe void update()
     */
    public abstract List<?> fetchList();

    /**
     Siehe void update()
     */
    public abstract Number getRelevantData(List<?> list);

    /**
     * Siehe void update()
     */
    public abstract void updateView(Number n);
}
