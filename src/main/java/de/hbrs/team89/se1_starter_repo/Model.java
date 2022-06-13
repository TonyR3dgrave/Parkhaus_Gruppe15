package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Model extends Publisher{
    private List<Parkplatz> parkplaetze;
    private List<Ticket> tickets;
    private Stack<List<Ticket>> undo_tickets;
    private Stack<List<Ticket>> redo_tickets;

    private List<CarIF> cars;
    private Stack<List<CarIF>> undo_cars;
    private Stack<List<CarIF>> redo_cars;


    public Model() {
        parkplaetze = new ArrayList<>();
        tickets = new ArrayList<>();
        cars = new ArrayList<>();

        undo_cars = new Stack<>();
        redo_cars = new Stack<>();
        undo_tickets = new Stack<>();
        redo_tickets = new Stack<>();
    }

    /**
        Fügt ein Auto der Liste der parkenden Autos hinzu (ENTER).
     */
    public void enter(CarIF car, Ticket ticket) {
        newUndoBranch();

        cars.add(car);
        tickets.add(ticket);
        update();
    }

    /**
        Entfernt ein Auto aus der Liste der parkenden Autos (LEAVE).
     */
    public void leave(int ticketNr) {
        newUndoBranch();

        for (CarIF car : cars) {
            if (car.nr() == ticketNr) {
                cars.remove(car);
                break;
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

    public void newUndoBranch() {
        List<CarIF> currentState_cars = new ArrayList<>(cars);
        List<Ticket> currentState_tickets = new ArrayList<>(tickets);

        undo_cars.push(currentState_cars);
        undo_tickets.push(currentState_tickets);

        redo_cars = new Stack<>();
        redo_tickets = new Stack<>();
    }

    public void undo() {
        if (undo_cars.isEmpty() || undo_tickets.isEmpty()) {
            cars = new ArrayList<>();
            tickets = new ArrayList<>();
            return;
        }

        redo_cars.push(cars);
        cars = undo_cars.pop();
        redo_tickets.push(tickets);
        tickets = undo_tickets.pop();
    }
    public void redo() {
        if (redo_cars.isEmpty() || redo_tickets.isEmpty()) return;

        undo_cars.push(cars = redo_cars.pop());
        undo_tickets.push(tickets = redo_tickets.pop());
    }

    public Stack<List<CarIF>> getCarUndoStack() {
        return undo_cars;
    }
    public Stack<List<Ticket>> getTicketsUndoStack() {
        return undo_tickets;
    }
    public Stack<List<CarIF>> getCarRedoStack() {
        return redo_cars;
    }
    public Stack<List<Ticket>> getTicketsRedoStack() {
        return redo_tickets;
    }
}
