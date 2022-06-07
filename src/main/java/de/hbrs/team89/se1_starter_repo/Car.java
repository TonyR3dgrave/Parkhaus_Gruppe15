package de.hbrs.team89.se1_starter_repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * params:
 * [0] nr
 * [1] Einfahrts-Zeit in sekunden nach 1970 (Unix Timestamp)
 * [2] Dauer in Sekunden (impl. Ausfahrts-Zeit)
 * [3] Preis
 * [4] Ticket hash-Wert
 * [5] Farbe des Autos
 * [6] Parkplatz-Nr.
 * [7] Geschlecht
 * [8] Auto-Typ (PKW, SUV, ...)
 * [9] Kennzeichen
 * [10] = [2] ???
 * [11] Parkhaus (Etage) (=NAME)
 */

public class Car implements CarIF {
    public static final List<String> TYPES = new ArrayList<>() {{
        add("PKW");
        add("SUV");
    }};
    public static final List<String> GENDERS = new ArrayList<>() {{
        add("Frau");
        add("Mann");
    }};
    private final String gender, type, license;
    private final int nr;

    public Car(String nr, String gender, String type, String license) {
        this.nr = Integer.parseInt(nr);
        this.gender = gender;
        this.type = type;
        this.license = license;
    }

    public String gender() {
        return gender;
    }

    public String type() {
        return type;
    }

    public String license() {
        return license;
    }

    public int nr() { return nr; }

    @Override
    public String toString(){
        return Arrays.toString( new String[] {gender, type, license} );
    }
}
