package com.qf.c16.common.base;

/**
 * @authorcom.qf.c16.common.base
 * @Date2019/8/5
 */
public interface IBaseDao<T>{
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKey(T record);

}
