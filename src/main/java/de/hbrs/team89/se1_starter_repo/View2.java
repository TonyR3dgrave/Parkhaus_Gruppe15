package de.hbrs.team89.se1_starter_repo;

import java.util.List;

public class View2 extends View{

    private final int UNIX_WOCHE = 604800; // Unix-Timestamp von einer Woche

    /**
     * Zieht sich die Ticket-Liste
     */
    @Override
    public List<?> fetchList() {
        return ((Model) model).getTickets();
    }

    /**
     * Reduziert die Liste auf die Summe aller Ticket-Preise, dessen Ausfahrt von vor maximal einer Woche ist.
     */
    @Override
    public Double getRelevantData(List<?> list) {
        long now = System.currentTimeMillis() / 1000;
        double sum = 0;

        for (Object o : list) {
            // Wann hat der Kunde das Parkhaus verlassen?
            if (now - ((Ticket) o).getUnixLeave() < UNIX_WOCHE) {
                sum += ((Ticket) o).getPreis();
            }
        }
        return sum;
    }

    /**
     * Aktualisiert die View mit der relevanten reduzierten Zahl n
     */
    @Override
    public void updateView(Number n) {
        view = "" + n;
    }
}
