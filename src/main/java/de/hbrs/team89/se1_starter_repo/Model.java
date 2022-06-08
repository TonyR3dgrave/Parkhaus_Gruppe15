package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.List;

public class Model extends Publisher{
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
        Fügt ein Auto der Liste der parkenden Autos hinzu (ENTER).
     */
    public void enter(CarIF car, Ticket ticket) {
        cars.add(car);
        tickets.add(ticket);
        update();
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
        update();
    }

    public List<Parkplatz> getParkplaetze() {
        return parkplaetze;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<CarIF> getCars() {
        return cars;
    }
}
