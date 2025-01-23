package com.wbs.javamysql_20.aufgabe_2;

import com.wbs.javamysql_20.aufgabe_2.model.Abteilung;
import com.wbs.javamysql_20.aufgabe_2.model.Baustelle;
import com.wbs.util.SqlQueryUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main_aufgabe_2
{
    public static void main(String[] args)
    {
        List<Abteilung> abteilungList = new ArrayList<>();
        List<Baustelle> baustelleList = new ArrayList<>();

        SqlQueryUtil.QueryResult abteilungQueryResult = null;
        SqlQueryUtil.QueryResult baustelleQueryResult = null;

        // Verbindungsaufbau, ResultSets in die Listen speichern, Ressourcen schließen
        try
        {
            // Verbindungsaufbau erstellen und ResultSets in QueryResult Objekte speichern
            abteilungQueryResult = SqlQueryUtil.getQueryResult("hochbau", "select * from abteilung");
            baustelleQueryResult = SqlQueryUtil.getQueryResult("hochbau", "select * from baustelle");

            // Abteilungsliste befüllen / Mapping
            while(abteilungQueryResult.getResultSet().next())
            {
                int abteilungsnummer = abteilungQueryResult.getResultSet().getInt("abteilungsnummer");
                String abteilungsname = abteilungQueryResult.getResultSet().getString("abteilungsname");

                abteilungList.add(new Abteilung(abteilungsnummer, abteilungsname));
            }

            // Baustellenliste befüllen / Mapping
            while(baustelleQueryResult.getResultSet().next())
            {
                String baustellennummer = baustelleQueryResult.getResultSet().getString("baustellennummer");
                String baustellenbezeichnung = baustelleQueryResult.getResultSet().getString("baustellenbezeichnung");

                baustelleList.add(new Baustelle(baustellennummer, baustellenbezeichnung));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            SqlQueryUtil.closeQueryResult(abteilungQueryResult);
            SqlQueryUtil.closeQueryResult(baustelleQueryResult);
        }

        // Ausgabe
        System.out.println();
        System.out.println("Abteilungen:");
        for (Abteilung a: abteilungList)
        {
            System.out.println(a.getAbteilungsnummer() + " -- " + a.getAbteilungsname());
        }

        System.out.println();
        System.out.println("Baustellen:");
        for (Baustelle b: baustelleList)
        {
            System.out.println(b.getBaustellennummer() + " -- " + b.getBaustellenbezeichnung());
        }
    }
}
