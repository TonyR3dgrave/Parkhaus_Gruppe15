package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.List;

public class Model {
    //ToDo: Überlegen, wie man das ganze abstrahiert.
    private List<Parkplatz> parkplaetze;
    private List<Ticket> tickets;
    private List<CarIF> cars;

    public Model() {
        parkplaetze = new ArrayList<>();
        tickets = new ArrayList<>();
        cars = new ArrayList<>();
    }

    /**
        Fuegt ein Auto der Liste der parkenden Autos hinzu (ENTER).
     */
    public void enter(CarIF car, Ticket ticketIF) {
        cars.add(car);
    }

    /**
        Entfernt ein Auto aus der Liste der parkenden Autos (LEAVE)
        und das Ticket aus der Liste der Tickets.
     */
    public void leave(int ticketNr) {
        for (Ticket ticket : tickets) {
            if (ticket.getNr() == ticketNr) {
                tickets.remove(ticket);
            }
        }
        for (CarIF car : cars) {
            if (car.nr() == ticketNr) {
                cars.remove(car);
            }
        }
    }

    /**
        Sucht den groessten Preis eines Tickets und gibt diesen zurueck.
     */
    public double getMax() {
        double max = Double.MIN_VALUE;
        for (Ticket ticket : tickets) {
            double preis = ticket.getPreis();
            if (preis > max) {
                max = preis;
            }
        }
        return max;
    }

    /**
        Sucht den kleinsten Preis eines Tickets und gibt diesen zurueck.
     */
    public double getMin() {
        double min = Double.MAX_VALUE;
        for (Ticket ticket : tickets) {
            double preis = ticket.getPreis();
            if (preis < min) {
                min = preis;
            }
        }
        return min;
    }

    /**
     * Gibt die Anzahl der aktuell parkenden Autos wieder
     */
    public int getTotalCars() {
        return cars.size();
    }
}
