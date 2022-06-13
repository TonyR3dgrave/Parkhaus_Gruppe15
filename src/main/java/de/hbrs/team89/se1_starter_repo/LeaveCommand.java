package de.hbrs.team89.se1_starter_repo;

public class LeaveCommand implements StateChange {
    private Model m;

    public LeaveCommand(Model m) {
        this.m = m;
    }

    @Override
    public void fuehreAus(CarIF car, Ticket ticket) {
        m.leave(ticket.getNr());
    }
}
