package com.qf.c16.common.base;

/**
 * @authorcom.qf.c16.common.base
 * @Date2019/8/5
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

   //未能确定的实现方式采用抽象方法声明(因为不知道你传进来的是什么类型的参数)
  public abstract IBaseDao<T> getBaseDao();


    //提供一些公共的基础实现

    public int deleteByPrimaryKey(Long id) {
        return getBaseDao().deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return getBaseDao().insert(record);
    }

    public int insertSelective(T record) {
        return getBaseDao().insertSelective(record);
    }

    public T selectByPrimaryKey(Long id) {
        return getBaseDao().selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T record) {
        return getBaseDao().updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(T record) {
        return getBaseDao().updateByPrimaryKeyWithBLOBs(record);
    }

    public int updateByPrimaryKey(T record) {
        return getBaseDao().updateByPrimaryKey(record);
    }
}
