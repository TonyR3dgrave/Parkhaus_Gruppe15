package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Model parkhauslogik;
    private List<View> viewList;

    public Controller() {
        this.parkhauslogik = new Model();
        this.viewList = new ArrayList<View>() {{
            add(new View1());
            add(new View2());
            add(new View3());
        }};

        viewsSubscribe();
    }

    //region parkhauslogik

    public void enter(CarIF car, Ticket ticket){
        parkhauslogik.enter(car,ticket);
    }

    public void leave(int ticketNr){
        parkhauslogik.leave(ticketNr);
    }
    //endregion

    public void viewsSubscribe(){
        for (View v : viewList) {
            v.subscribe(parkhauslogik);
        }
    }

    //region Getter
    public Model getParkhauslogik() {
        return parkhauslogik;
    }

    public List<View> getViewList() {
        return viewList;
    }
    //endregion
}

