package com.qf.v16.api.vo;

import com.qf.v16.entity.TProduct;

import java.io.Serializable;

/**
 * @authorcom.qf.v16.api.vo
 * @Date2019/8/7
 */
public class ProductV0 implements Serializable {
    private TProduct product;
    private String productDesc;


    public ProductV0(TProduct product, String productDesc) {
        this.product = product;
        this.productDesc = productDesc;
    }

    public ProductV0() {
    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
