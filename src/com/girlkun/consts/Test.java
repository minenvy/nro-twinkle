/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girlkun.consts;

import com.girlkun.customdatabase.GirlkunDB;
// import com.girlkun.result.GirlkunResultSet;

/**
 *
 * @Stole By Arriety
 */
public class Test {

    public static void main(String[] args) throws Exception {
        ResultSet rs = GirlkunDB.executeQuery("select * from account where id = 4");
        if (rs.next()) {
            System.out.println(rs.getString("username"));
        }
    }
}
