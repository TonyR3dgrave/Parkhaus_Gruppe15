package de.hbrs.team89.se1_starter_repo;

public class View2 extends View{

    private final int UNIX_WOCHE = 604800;
    double view;

    double getView(){return view;}

    /**
     * Berechnet die Wocheneinnahmen. (Jetzt vor 7 Tagen)
     */
    @Override
    public void update() {
        view = 0;
        long now = System.currentTimeMillis() / 1000;
        for (Ticket t : ((Model)model).getTickets() ) {
            // Wann hat der Kunde das Parkhaus verlassen?
            if (now - t.getUnixLeave() < UNIX_WOCHE) {
                view += t.getPreis();
            }
        }
    }
}
