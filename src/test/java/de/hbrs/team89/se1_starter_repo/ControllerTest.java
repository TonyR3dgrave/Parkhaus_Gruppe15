package de.hbrs.team89.se1_starter_repo;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller;

    @DisplayName("Initialisiert den oben stehenden Controller.")
    @BeforeEach
    void init() {
        controller = new Controller();
    }

    @DisplayName("Testet, ob drei Autos ohne Probleme ins Parkhaus reinfahren können.")
    @Test
    void enter() {
        long now = System.currentTimeMillis() / 1000; // Jetzt in Unix-Timestamp

        // Drei Autos mit den jeweiligen Tickets werden erstellt.
        CarIF car1 = new Car("1", "Male", "SUV", "K GA 2491");
        Ticket ticket1 = new Ticket("1", now);
        CarIF car2 = new Car("2", "Male", "PKW", "K UF 2951");
        Ticket ticket2 = new Ticket("2", now);
        CarIF car3 = new Car("3", "Male", "PKW", "SU G 2452");
        Ticket ticket3 = new Ticket("3", now);

        controller.enter(car1, ticket1);
        controller.enter(car2, ticket2);
        controller.enter(car3, ticket3);

        assertEquals(car1, controller.getParkhauslogik().getCars().get(0));
        assertEquals(car2, controller.getParkhauslogik().getCars().get(1));
        assertEquals(car3, controller.getParkhauslogik().getCars().get(2));

        assertEquals(ticket1, controller.getParkhauslogik().getTickets().get(0));
        assertEquals(ticket2, controller.getParkhauslogik().getTickets().get(1));
        assertEquals(ticket3, controller.getParkhauslogik().getTickets().get(2));
    }

    @DisplayName("Testet, ob drei Autos ohne Probleme das Parkhaus verlassen können, und ob das Model korrekt" +
            "aktualisiert wird. Außerdem wird getestet, wie die leave()-Methode mit ungültigen Argumenten arbeitet.")
    @Test
    void leave() {
        long now = System.currentTimeMillis() / 1000; // Jetzt in Unix-Timestamp

        // Drei Autos mit den jeweiligen Tickets werden erstellt.
        CarIF car1 = new Car("1", "Male", "SUV", "K GA 2491");
        Ticket ticket1 = new Ticket("1", now);
        CarIF car2 = new Car("2", "Male", "PKW", "K UF 2951");
        Ticket ticket2 = new Ticket("2", now);
        CarIF car3 = new Car("3", "Male", "PKW", "SU G 2452");
        Ticket ticket3 = new Ticket("3", now);

        // Drei Autos fahren ein
        controller.enter(car1, ticket1);
        controller.enter(car2, ticket2);
        controller.enter(car3, ticket3);

        // Alle Autos fahren raus
        controller.leave(1);
        controller.leave(2);
        controller.leave(3);

        // Die Car Liste sollte hiermit leer sein
        assertEquals(0, controller.getParkhauslogik().getCars().size());
        // Die Tickets bleiben allerdings gespeichert
        assertEquals(3, controller.getParkhauslogik().getTickets().size());

        // "Ungültige Eingabe"-Test
        controller.leave(1000);
        assertEquals(0, controller.getParkhauslogik().getCars().size());
    }

    @DisplayName("Prüft, ob die Views alle ein Model abboniert haben.")
    @Test
    void viewsSubscribe() {
        Model model = controller.getParkhauslogik();
        List<View> views = controller.getViewList();

        for(View v : views) {
            assertEquals(model, v.model);
        }
    }

    @DisplayName("Prüft, ob die Views korrekt vom Controller initialisiert werden.")
    @Test
    void viewsCorrectlyInitialized() {
        View v1 = controller.getViewList().get(0);
        View v2 = controller.getViewList().get(1);
        View v3 = controller.getViewList().get(2);

        assertNotNull(v1);
        assertNotNull(v2);
        assertNotNull(v3);

        assertTrue(v1 instanceof View1);
        assertTrue(v2 instanceof View2);
        assertTrue(v3 instanceof View3);
    }
}