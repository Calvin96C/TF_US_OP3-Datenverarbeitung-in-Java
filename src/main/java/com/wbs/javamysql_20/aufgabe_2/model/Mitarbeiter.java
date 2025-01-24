package com.wbs.javamysql_20.aufgabe_2.model;

public class Mitarbeiter
{
    private String mitarbeiternummer;
    private boolean maschinenberechtigung;
    private String mitarbeitername;
    private String mitarbeiterPLZ;
    private int abteilungsnummer;

    public Mitarbeiter()
    {
    }

    public Mitarbeiter(String mitarbeiternummer, boolean maschinenberechtigung, String mitarbeitername, String mitarbeiterPLZ, int abteilungsnummer)
    {
        this.mitarbeiternummer = mitarbeiternummer;
        this.maschinenberechtigung = maschinenberechtigung;
        this.mitarbeitername = mitarbeitername;
        this.mitarbeiterPLZ = mitarbeiterPLZ;
        this.abteilungsnummer = abteilungsnummer;
    }

    public String getMitarbeiternummer()
    {
        return mitarbeiternummer;
    }

    public void setMitarbeiternummer(String mitarbeiternummer)
    {
        this.mitarbeiternummer = mitarbeiternummer;
    }

    public boolean isMaschinenberechtigung()
    {
        return maschinenberechtigung;
    }

    public void setMaschinenberechtigung(boolean maschinenberechtigung)
    {
        this.maschinenberechtigung = maschinenberechtigung;
    }

    public String getMitarbeitername()
    {
        return mitarbeitername;
    }

    public void setMitarbeitername(String mitarbeitername)
    {
        this.mitarbeitername = mitarbeitername;
    }

    public String getMitarbeiterPLZ()
    {
        return mitarbeiterPLZ;
    }

    public void setMitarbeiterPLZ(String mitarbeiterPLZ)
    {
        this.mitarbeiterPLZ = mitarbeiterPLZ;
    }

    public int getAbteilungsnummer()
    {
        return abteilungsnummer;
    }

    public void setAbteilungsnummer(int abteilungsnummer)
    {
        this.abteilungsnummer = abteilungsnummer;
    }
}
