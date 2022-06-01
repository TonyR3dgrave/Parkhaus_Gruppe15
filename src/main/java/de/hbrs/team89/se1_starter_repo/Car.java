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
    String[] params;
    public static final List<String> Genders = new ArrayList<>(){{
        add("Female");
        add("Male");
    }};

    public Car( String[] params ){
        this.params = params;
    }

    @Override
    public int nr() {
        return Integer.parseInt(params[0]);
    }

    @Override
    public long begin() {
        return Long.parseLong(params[1]);
    }

    @Override
    public long end() {
        // End = Begin (Unix Timestamp) + Duration (Seconds)
        return Long.parseLong(params[1]) + Long.parseLong(params[2]);
    }

    @Override
    public int duration() {
        return Integer.parseInt(params[2]);
    }

    @Override
    public int price() {
        return Integer.parseInt(params[3]);
    }

    public int space() {
        return Integer.parseInt(params[6]);
    }

    public String gender() {
        return params[7];
    }

    public String type() {
        return params[8];
    }

    public String license() {
        return params[9];
    }

    @Override
    public String toString(){
        return Arrays.toString( params );
    }
}
