package com.zkai.hero.utils;

import java.sql.*;

public class DbUtils {
    private static final String URL="jdbc:mysql://localhost:3306/tt?serverTimezone=UTC";
    private static final String USER="root";
    private static final String PASSWORD="root";
    public static Connection conn=null;
    public static PreparedStatement ps=null;
    public static ResultSet rs=null;

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    public static void close(Connection conn,Statement stam,ResultSet rs){
        if(conn!=null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(stam!=null) try {
            stam.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(rs!=null) try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //TODO:封装preparestatement
    private static PreparedStatement getPs(String sql,Object[] params) throws SQLException {
        ps = conn.prepareStatement(sql);
        if(params!=null){
            for(int i=0;i<params.length;i++){
                ps.setObject(i+1,params[i]);
            }
        }
        return ps;
    }
    //TODO:增删改
    public static boolean exeUpdate(String sql,Object[] params){
        try {
            conn = getConn();
            ps=getPs(sql,params);
            return ps.executeUpdate()>0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            close(conn,ps,null);
        }
        return false;
    }
    //TODO:查询
    public static ResultSet exeQuery(String sql,Object[] params){
        try {
            conn=getConn();
            ps=getPs(sql,params);
            rs = ps.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    //TODO:查询总数
    public static int exeCount(String sql){
        int count=-1;
        try {
            conn=getConn();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                count=rs.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn,ps,rs);
        }
        return count;
    }
}
