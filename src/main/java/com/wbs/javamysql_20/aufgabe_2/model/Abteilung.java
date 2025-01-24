package com.wbs.javamysql_20.aufgabe_2.model;

public class Abteilung
{
    private int abteilungsnummer;
    private String abteilungsname;

    public Abteilung()
    {
    }

    public Abteilung(int abteilungsnummer, String abteilungsname)
    {
        this.abteilungsnummer = abteilungsnummer;
        this.abteilungsname = abteilungsname;
    }

    public int getAbteilungsnummer()
    {
        return abteilungsnummer;
    }

    public String getAbteilungsname()
    {
        return abteilungsname;
    }

    public void setAbteilungsname(String abteilungsname)
    {
        this.abteilungsname = abteilungsname;
    }
}
