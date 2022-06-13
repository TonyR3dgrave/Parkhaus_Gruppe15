package de.hbrs.team89.se1_starter_repo;

import java.util.List;

public class View3 extends View{
    int ticketNr = -1;

    /**
     * Zieht sich die Ticket-Liste
     */
    @Override
    public List<?> fetchList() {
        return ((Model) model).getTickets();
    }

    /**
     * Zieht sich den Preis der jeweiligen Ticket-Nr. aus der Ticket-Liste und gibt diese wieder. <br>
     * Falls diese nicht gefunden wurde, ist der Rückgabewert standardmaessig -1.0.
     */
    @Override
    public Number getRelevantData(List<?> list) {
        for (Object o : list) {
            if (((Ticket) o).getNr() == ticketNr) {
                return ((Ticket) o).getPreis();
            }
        }
        return -1.0;
    }

    /**
     * Aktualisiert die View mit der relevanten reduzierten Zahl n
     */
    @Override
    public void updateView(Number n) {
        view = "" + n;
    }

    /**
     * Benutzerschnittstelle fuer diese View:
     * Der Benutzer gibt seine <i>ticketNr</i> ein und erhält dann über update() seinen aktuellen Preis.
     */
    public void setNr(int ticketNr) {
        this.ticketNr = ticketNr;
        update();
    }
}