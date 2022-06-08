package de.hbrs.team89.se1_starter_repo;

public abstract class View {
    Publisher model;

    public void subscribe(Publisher model){
        this.model = model;
        model.addView(this);
        update();
    }
    public abstract void update();
}
