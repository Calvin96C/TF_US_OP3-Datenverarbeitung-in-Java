package com.wbs;

import com.wbs.javamysql_20.aufgabe_2.model.Abteilung;
import com.wbs.javamysql_20.aufgabe_2.model.Baustelle;
import com.wbs.util.DBConnectionUtil;
import com.wbs.util.SqlQueryUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args)
    {
        List<Abteilung> abteilungList = new ArrayList<>(SqlQueryUtil.resultSetToList("hochbau", Abteilung.class));
        List<Baustelle> baustelleList = new ArrayList<>(SqlQueryUtil.resultSetToList("hochbau", Baustelle.class));
    }
}