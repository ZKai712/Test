package com.zkai.hero.dao;

import java.util.List;

public interface IHeroDao<T> {
    //增
    public boolean doAdd(T hero);
    //删
    public boolean doDel(int id);
    //改
    public boolean doUpdate(int id, T hero);
    //单查
    public T queryById(int id);
    //查询是否存在
    public boolean isExist(int id);
    //查询数据总数
    public  int queryCount();
    //查询全部
    public List<T> queryAll();
    //分页查询//select * from t_hero limit (currentPage-1)*pageSize,pageSize;
    public List<T> queryByPage(int currentPage, int pageSize);
}
