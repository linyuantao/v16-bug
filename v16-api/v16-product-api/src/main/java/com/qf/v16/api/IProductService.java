package com.qf.v16.api;

import com.github.pagehelper.PageInfo;
import com.qf.c16.common.base.IBaseService;
import com.qf.v16.api.vo.ProductV0;
import com.qf.v16.entity.TProduct;

import java.util.List;

/**
 * @authorcom.qf.v16.api
 * @Date2019/8/5
 */
public interface IProductService extends IBaseService<TProduct> {
    //需要增加一些非基础增删改查的方法

     public List<TProduct> list();


     //声明分页的方法
     public PageInfo<TProduct> page(Integer pageIndex,Integer pageSzie);

    Long add(ProductV0 v0);
}
