package de.hbrs.team89.se1_starter_repo;

public class View1 extends View{

    private final long UNIX_TAG = 86400;
    double view;

    double getView() { return view; }

    /**
     * Berechnet die Tageseinnahmen. (Jetzt vor 24 Stunden)
     */
    @Override
    public void update() {
        view = 0;
        long now = System.currentTimeMillis() / 1000;
        for (Ticket t : ((Model)model).getTickets() ) {
            // Wann hat der Kunde das Parkhaus verlassen?
            if (now - t.getUnixLeave() < UNIX_TAG) {
                view += t.getPreis();
            }
        }
    }
}
