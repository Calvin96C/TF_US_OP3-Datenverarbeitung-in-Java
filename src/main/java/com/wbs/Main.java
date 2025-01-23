package com.wbs;

import com.wbs.javamysql_20.aufgabe_2.model.Abteilung;
import com.wbs.util.DBConnectionUtil;
import com.wbs.util.SqlQueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        SqlQueryUtil.printClassProperties(Abteilung.class);
        List<Abteilung> abteilungList = new ArrayList<>(SqlQueryUtil.resultSetToList("hochbau", Abteilung.class));
        //SqlQueryUtil.resultSetToList("hochbau", Abteilung.class);
    }
}