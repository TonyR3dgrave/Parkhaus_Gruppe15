package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    Model parkhauslogik;
    List<View> viewList;

    public Controller() {
        this.parkhauslogik = new Model();
        this.viewList = new ArrayList<View>(){{

        }};
    }

    //region parkhauslogik

    public void enter(CarIF car, Ticket ticket){
        parkhauslogik.enter(car,ticket);
    }

    public void leave(int ticketNr){
        parkhauslogik.leave(ticketNr);
    }
    //endregion

    public void subscribe(){
        for (View v : viewList) {
            v.subscribe(parkhauslogik);
        }
    }

}
