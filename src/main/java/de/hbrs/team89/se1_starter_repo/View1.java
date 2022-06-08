package de.hbrs.team89.se1_starter_repo;

public class View1 extends View{
    double view;

    double getView(){return view;}

    @Override
    public void update() {
        for (Ticket t : ((Model)model).getTickets() ) {
            view += t.getPreis();
        }
    }
}
