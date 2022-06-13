package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    Model m;
    StateChange s;

    @DisplayName("Initialisiere das Model")
    @BeforeEach
    void setup() {
        m = new Model();
    }

    @DisplayName("Testet das fehlerfreie Einfahren von Autos mithilfe des Command-Patterns")
    @Test
    void enter() {
        long now = System.currentTimeMillis() / 1000; // Jetzt in Unix-Timestamp

        CarIF car1 = new Car("1", "Male", "SUV", "K GA 2491");
        Ticket ticket1 = new Ticket("1", now);
        CarIF car2 = new Car("2", "Male", "PKW", "K UF 2951");
        Ticket ticket2 = new Ticket("2", now);
        CarIF car3 = new Car("3", "Male", "PKW", "SU G 2452");
        Ticket ticket3 = new Ticket("3", now);

        s = new EnterCommand(m);

        assertDoesNotThrow(() -> s.fuehreAus(car1, ticket1));
        assertDoesNotThrow(() -> s.fuehreAus(car2, ticket2));
        assertDoesNotThrow(() -> s.fuehreAus(car3, ticket3));
    }

    @DisplayName("Testet das fehlerfreie Ausfahren von Autos mithilfe des Command-Patterns")
    @Test
    void leave() {
        //region Einfahren
        long now = System.currentTimeMillis() / 1000; // Jetzt in Unix-Timestamp

        CarIF car1 = new Car("1", "Male", "SUV", "K GA 2491");
        Ticket ticket1 = new Ticket("1", now);
        CarIF car2 = new Car("2", "Male", "PKW", "K UF 2951");
        Ticket ticket2 = new Ticket("2", now);
        CarIF car3 = new Car("3", "Male", "PKW", "SU G 2452");
        Ticket ticket3 = new Ticket("3", now);

        s = new EnterCommand(m);

        s.fuehreAus(car1, ticket1);
        s.fuehreAus(car2, ticket2);
        s.fuehreAus(car3, ticket3);
        //endregion

        s = new LeaveCommand(m);

        /*
         * Da vom Model die Methode leave() kein CarIF benötigt wird,
         * übergeben wir dem LeaveCommand nur das Ticket.
         */
        s.fuehreAus(null, ticket1);
        s.fuehreAus(null, ticket2);
        s.fuehreAus(null, ticket3);

        // Die Car Liste sollte hiermit leer sein
        assertEquals(0, m.getCars().size());
        // Die Tickets bleiben allerdings gespeichert
        assertEquals(3, m.getTickets().size());
    }

    @DisplayName("Bei erneutem Einfahren nach einer undo-Operation, sollte der Redo-Strang gecleared werden.")
    @Test
    void newUndoBranch() {
        //region Einfahren
        long now = System.currentTimeMillis() / 1000; // Jetzt in Unix-Timestamp

        CarIF car1 = new Car("1", "Male", "SUV", "K GA 2491");
        Ticket ticket1 = new Ticket("1", now);
        CarIF car2 = new Car("2", "Male", "PKW", "K UF 2951");
        Ticket ticket2 = new Ticket("2", now);
        CarIF car3 = new Car("3", "Male", "PKW", "SU G 2452");
        Ticket ticket3 = new Ticket("3", now);

        s = new EnterCommand(m);

        s.fuehreAus(car1, ticket1);
        s.fuehreAus(car2, ticket2);
        s.fuehreAus(car3, ticket3);
        //endregion

        assertEquals(3, m.getCarUndoStack().size());
        assertEquals(3, m.getTicketsUndoStack().size());

        m.undo();

        assertEquals(2, m.getCarUndoStack().size());
        assertEquals(2, m.getTicketsUndoStack().size());

        assertEquals(1, m.getCarRedoStack().size());
        assertEquals(1, m.getTicketsRedoStack().size());

        s.fuehreAus(car3, ticket3);
        assertEquals(0, m.getCarRedoStack().size());
        assertEquals(0, m.getTicketsRedoStack().size());
    }

    @DisplayName("Testet das fehlerfreie Undo vor und nach dem Einfahren.")
    @Test
    void undo() {
        assertDoesNotThrow(() -> m.undo());

        long now = System.currentTimeMillis() / 1000; // Jetzt in Unix-Timestamp

        CarIF car1 = new Car("1", "Male", "SUV", "K GA 2491");
        Ticket ticket1 = new Ticket("1", now);

        s = new EnterCommand(m);
        s.fuehreAus(car1, ticket1);

        assertEquals(1, m.getCars().size());
        assertEquals(1, m.getTickets().size());

        m.undo();

        assertTrue(m.getCars().isEmpty());
        assertTrue(m.getTickets().isEmpty());

        assertDoesNotThrow(() -> m.undo());
    }

    @DisplayName("Testet das fehlerfreie Redo nach beliebig vielen Undo-Operationen.")
    @Test
    void redo() {
        assertDoesNotThrow(() -> m.redo());

        long now = System.currentTimeMillis() / 1000; // Jetzt in Unix-Timestamp

        CarIF car1 = new Car("1", "Male", "SUV", "K GA 2491");
        Ticket ticket1 = new Ticket("1", now);
        CarIF car2 = new Car("2", "Male", "SUV", "K GA 2491");
        Ticket ticket2 = new Ticket("2", now);

        s = new EnterCommand(m);

        s.fuehreAus(car1, ticket1);
        s.fuehreAus(car2, ticket2);

        m.undo();
        m.undo();

        m.redo();

        assertEquals(1, m.getCars().size());
        assertEquals(1, m.getTickets().size());

        assertDoesNotThrow(() -> m.redo());
    }
}