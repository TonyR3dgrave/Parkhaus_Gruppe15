package de.hbrs.team89.se1_starter_repo;

public class EnterCommand implements StateChange {
    private Model m;

    public EnterCommand(Model m) {
        this.m = m;
    }

    @Override
    public void fuehreAus(CarIF car, Ticket ticket) {
        m.enter(car, ticket);
    }
}
