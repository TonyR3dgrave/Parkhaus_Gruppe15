package de.hbrs.team89.se1_starter_repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {

    Controller controller;

    @BeforeEach
    void init() {
        controller = new Controller();

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

        controller.leave(1);
        controller.leave(2);
        controller.leave(3);
    }

    @Test
    void fetchList() {
        View v = controller.getViewList().get(0); //View1

        List<?> list = v.fetchList();

        // Sind auch wirklich drei Autos reingefahren?
        assertEquals(3, list.size());

        // ist es auch wirklich die Ticket Liste?
        assertTrue(list.get(0) instanceof Ticket);
        assertTrue(list.get(1) instanceof Ticket);
        assertTrue(list.get(2) instanceof Ticket);

        // --------------- VIEW 2 ------------------
        v = controller.getViewList().get(1); //View1

        list = v.fetchList();

        // Sind auch wirklich drei Autos reingefahren?
        assertEquals(3, list.size());

        // ist es auch wirklich die Ticket Liste?
        assertTrue(list.get(0) instanceof Ticket);
        assertTrue(list.get(1) instanceof Ticket);
        assertTrue(list.get(2) instanceof Ticket);

        // --------------- VIEW 3 ------------------
        v = controller.getViewList().get(2); //View1

        list = v.fetchList();

        // Sind auch wirklich drei Autos reingefahren?
        assertEquals(3, list.size());

        // ist es auch wirklich die Ticket Liste?
        assertTrue(list.get(0) instanceof Ticket);
        assertTrue(list.get(1) instanceof Ticket);
        assertTrue(list.get(2) instanceof Ticket);
    }

    @Test
    void getRelevantData() {
        fail("Nicht testbar, da man dafür die Simulation einige Zeit laufen lassen müsste");
    }

    @Test
    void updateView() {
        fail("Nicht testbar, da man dafür die Simulation einige Zeit laufen lassen müsste");
    }
}