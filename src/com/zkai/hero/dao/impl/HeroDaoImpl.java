package com.zkai.hero.dao.impl;

import com.zkai.hero.dao.IHeroDao;
import com.zkai.hero.entity.Hero;
import com.zkai.hero.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeroDaoImpl implements IHeroDao<Hero> {

    private ResultSet rs;
    @Override
    public boolean doAdd(Hero hero) {
        String sql="insert into t_hero (id,name,sex,age) values(?,?,?,?)";
        Object[] params={hero.getId(),hero.getName(),hero.getSex(),hero.getAge()};
        return DbUtils.exeUpdate(sql,params);
    }

    @Override
    public boolean doDel(int id) {
        String sql="delete from t_hero where id=?";
        Object[] params={id};
        return DbUtils.exeUpdate(sql,params);
    }

    @Override
    public boolean doUpdate(int id, Hero hero) {
        String sql="update t_hero set name=?,sex=?,age=? where id=?";
        Object[] params={hero.getName(),hero.getSex(),hero.getAge(),id};
        return DbUtils.exeUpdate(sql,params);
    }

    @Override
    public Hero queryById(int id) {
        Hero hero=null;
        String sql="select * from t_hero where id=?";
        Object[] params={id};
        rs=DbUtils.exeQuery(sql,params);
        try {
            if(rs.next()){
               hero=new Hero(rs.getInt(1),
                       rs.getString(2),
                       rs.getString(3),
                       rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(DbUtils.conn,DbUtils.ps,rs);
        }
        return hero;
    }

    @Override
    public boolean isExist(int id) {
        return queryById(id) != null;
    }

    @Override
    public int queryCount() {
        String sql="select count(1) from t_hero";
        return DbUtils.exeCount(sql);
    }

    private List<Hero> query(ResultSet rs){
        List<Hero> heroes=new ArrayList<>();
        try {
            while (rs.next()) {
                Hero hero=new Hero(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                heroes.add(hero);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(DbUtils.conn,DbUtils.ps,rs);
        }
        return heroes;
    }

    @Override
    public List<Hero> queryAll() {
        String sql="select * from t_hero";
        rs=DbUtils.exeQuery(sql,null);
        return query(rs);
    }

    @Override
    public List<Hero> queryByPage(int currentPage, int pageSize) {
        String sql="select * from t_hero limit ?,?";
        Object[] params={(currentPage-1)*pageSize,pageSize};
        rs=DbUtils.exeQuery(sql,params);
        return query(rs);
    }
}
