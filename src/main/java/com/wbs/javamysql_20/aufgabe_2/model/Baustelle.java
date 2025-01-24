package com.wbs.javamysql_20.aufgabe_2.model;

public class Baustelle
{
    private String baustellennummer;
    private String baustellenbezeichnung;

    public Baustelle()
    {
    }

    public Baustelle(String baustellennummer, String baustellenbezeichnung)
    {
        this.baustellennummer = baustellennummer;
        this.baustellenbezeichnung = baustellenbezeichnung;
    }

    public String getBaustellennummer()
    {
        return baustellennummer;
    }

    public void setBaustellennummer(String baustellennummer)
    {
        this.baustellennummer = baustellennummer;
    }

    public String getBaustellenbezeichnung()
    {
        return baustellenbezeichnung;
    }

    public void setBaustellenbezeichnung(String baustellenbezeichnung)
    {
        this.baustellenbezeichnung = baustellenbezeichnung;
    }
}
