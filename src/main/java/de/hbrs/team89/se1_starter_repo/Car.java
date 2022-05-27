package de.hbrs.team89.se1_starter_repo;

import java.util.Arrays;

/**
 * params:
 * [0] enter / leave
 * [1] nr
 * [2] Einfahrts-Zeit in sekunden nach 1970 (Unix Timestamp)
 * [3] Dauer in Sekunden (impl. Ausfahrts-Zeit)
 * [4] Preis
 * [5] Ticket hash-Wert
 * [6] Farbe des Autos
 * [7] Parkplatz-Nr.
 * [8] Geschlecht
 * [9] Auto-Typ (PKW, SUV, ...)
 * [10] Kennzeichen
 * [11] = [2] ???
 * [12] Parkhaus (Etage) (=NAME)
 */

public class Car implements CarIF {
    String[] params;
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
        return Integer.parseInt(params[4]);
    }

    public int space() {
        return Integer.parseInt(params[7]);
    }

    public String gender() {
        return params[8];
    }

    public String type() {
        return params[9];
    }

    public String license() {
        return params[10];
    }

    @Override
    public String toString(){
        return Arrays.toString( params );
    }
}
