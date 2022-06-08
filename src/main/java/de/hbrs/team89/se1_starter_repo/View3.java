package de.hbrs.team89.se1_starter_repo;

public class View3 extends View{
    double view = 0;
    int ticketNr = -1;

    double getView(){ return view; }

    /**
        Sucht das Ticket mit der Nummer <i>ticketNr</i> und setzt als view dessen Preis.
        Falls kein Ticket mit dieser Nummer gefunden wurde, bleibt der berechnete Preis <i>view</i> auf 0,00€.
     */
    @Override
    public void update() {
        for (Ticket t : ((Model)model).getTickets() ) {
            if (t.getNr() == ticketNr) {
                view = t.getPreis();
                return;
            }
        }
        view = 0;
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