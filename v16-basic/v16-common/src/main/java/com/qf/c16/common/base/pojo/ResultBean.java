package com.qf.c16.common.base.pojo;

import java.io.Serializable;

/**统一后端给前端返回的数据类型
 * @authorcom.qf.c16.common.base.pojo
 * @Date2019/8/8
 */
public class ResultBean<T> implements Serializable{
    private String statusCode;
    private T data;


    public ResultBean(String statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public ResultBean() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
