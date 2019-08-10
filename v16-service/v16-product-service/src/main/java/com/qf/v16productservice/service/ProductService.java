package com.qf.v16productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.c16.common.base.BaseServiceImpl;
import com.qf.c16.common.base.IBaseDao;
import com.qf.v16.api.IProductService;
import com.qf.v16.api.vo.ProductV0;
import com.qf.v16.entity.TProduct;
import com.qf.v16.entity.TProductDesc;
import com.qf.v16.mapper.TProductDescMapper;
import com.qf.v16.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @authorcom.qf.v16productservice.service
 * @Date2019/8/5
 * 发布服务(需要引入dubbo相关的配置和依赖)
 *
 */
@Service
public class ProductService extends BaseServiceImpl<TProduct> implements IProductService {

    //引入具体要的mapper
    @Autowired
    private TProductMapper productMapper;

    //另一张表的信息添加（商品描述）
    @Autowired
    private TProductDescMapper productDescMapper;


    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }

    @Override
    public List<TProduct> list() {
        return productMapper.list();
    }

    @Override
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSzie) {
        //1.设置分页参数
        PageHelper.startPage(pageIndex,pageSzie);
        //2.获取分页数据
        List<TProduct> list =list();
        PageInfo<TProduct> pageInfo =new PageInfo<TProduct>(list,3);
        return pageInfo;
    }

    @Override
    @Transactional
    public Long add(ProductV0 v0) {
        //1.添加商品信息进数据库(主键回填)
        TProduct Product = v0.getProduct();
        productMapper.insertSelective(Product);

        //2.添加商品的描述信息
        TProductDesc desc = new TProductDesc();

        //将产品主键位回填的ID值给productDesc中的ProductId赋值
        desc.setProductId(Product.getId());
        //vo中带的描述信息直接赋值给描述表信息
        desc.setProductDesc(v0.getProductDesc());
        productDescMapper.insertSelective(desc);
        return Product.getId();
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        //重写删除方法把真删除改为假删除内部其实为更新
        TProduct product = new TProduct();
        //给商品类赋值 ID flag 和修改时间
        product.setId(id);
        product.setFlag(false);
        product.setUpdateTime(new Date());

        return productMapper.updateByPrimaryKeySelective(product);

    }
}
