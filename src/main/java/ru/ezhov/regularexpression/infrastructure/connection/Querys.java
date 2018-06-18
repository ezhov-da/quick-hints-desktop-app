/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.infrastructure.connection;

/**
 * @author RRNDeonisiusEZH
 */
final class Querys {
    public static final String INSERT = "insert into base(text, description) values(?,?)";
    public static final String DELETE = "delete from base where id = ?";
    public static final String UPDATE = "update base\n" +
            "set idKey = ? , text =?,  description= ?\n" +
            "where id =  ?";
    public static final String SELECT = "select all id, idKey, text, description from base order by idKey desc, text ;";

    public static final String SELECT_SEARCH = "select all id, idKey, text, description from base"
            + " where UPPER(text) like ? or UPPER(description) like ? order by idKey desc, text ;";
    public static final String SELECT_KEY = "select top 1 text  from base where idKey = ? order by idKey desc, text;";
}
