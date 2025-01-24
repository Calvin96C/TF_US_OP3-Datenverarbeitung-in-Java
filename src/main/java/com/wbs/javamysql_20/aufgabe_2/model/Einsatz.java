package com.wbs.javamysql_20.aufgabe_2.model;

public class Einsatz
{
    private String mitarbeiternummer;
    private String baustellennummer;
    private int stunden;

    public Einsatz()
    {
    }

    public Einsatz(String mitarbeiternummer, String baustellennummer, int stunden)
    {
        this.mitarbeiternummer = mitarbeiternummer;
        this.baustellennummer = baustellennummer;
        this.stunden = stunden;
    }

    public String getMitarbeiternummer()
    {
        return mitarbeiternummer;
    }

    public String getBaustellennummer()
    {
        return baustellennummer;
    }

    public int getStunden()
    {
        return stunden;
    }

    public void setStunden(int stunden)
    {
        this.stunden = stunden;
    }
}
