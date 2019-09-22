package com.zkai.hero.service.impl;

import com.zkai.hero.dao.IHeroDao;
import com.zkai.hero.dao.impl.HeroDaoImpl;
import com.zkai.hero.entity.Hero;
import com.zkai.hero.service.IHeroService;

import java.util.List;

public class HeroServiceImpl implements IHeroService<Hero> {

    private IHeroDao<Hero> heroDao=new HeroDaoImpl();

    @Override
    public boolean doAdd(Hero hero) {
        if(!heroDao.isExist(hero.getId())){
            return heroDao.doAdd(hero);
        }else {
            return false;
        }
    }

    @Override
    public boolean doDel(int id) {
        if(heroDao.isExist(id)){
            return heroDao.doDel(id);
        }else {
            return false;
        }
    }

    @Override
    public boolean doUpdate(int id, Hero hero) {
        if(heroDao.isExist(id)){
            return heroDao.doUpdate(id,hero);
        }else {
            return false;
        }
    }

    @Override
    public Hero queryById(int id) {
        return heroDao.queryById(id);
    }

    @Override
    public int queryCount() {
        return heroDao.queryCount();
    }

    @Override
    public List<Hero> queryAll() {
        return heroDao.queryAll();
    }

    @Override
    public List<Hero> queryByPage(int currentPage, int pageSize) {
        return heroDao.queryByPage(currentPage,pageSize);
    }
}
