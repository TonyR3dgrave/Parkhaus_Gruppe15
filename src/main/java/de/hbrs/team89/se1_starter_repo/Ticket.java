package de.hbrs.team89.se1_starter_repo;

public class Ticket {
    private final int nr;
    private double preis;
    private long unixEnter, unixLeave;

    public Ticket(String nr, long unixEnter) {
        this.nr = Integer.parseInt(nr);
        this.unixEnter = unixEnter;
    }

    /**
     * Setzt das unixLeave Timestamp.
     */
    public void setLeaveTimestamp(long unixLeave) {
        this.unixLeave = unixLeave;
    }

    /**
        Gibt die Ticketnr. zurueck
     */
    public int getNr() {
        return nr;
    }

    /**
     *  Gibt die Dauer zurueck, die das Auto geparkt hat.
     *  Falls das Auto noch im Parkhaus steht, wird die aktuelle Parkzeit wiedergegeben.
     */
    public long getDauer() {
        return (unixLeave != 0L) ? (unixLeave - unixEnter) : ( (System.currentTimeMillis() / 1000L) - unixEnter );
    }

    //region Getter
    /**
     * Gibt den berechneten Preis fuer die [aktuelle] Parkdauer an.
     */
    public double getPreis() {
        return preis;
    }

    public long getUnixEnter() {
        return unixEnter;
    }

    public long getUnixLeave() {
        return unixLeave;
    }
    //endregion
}
