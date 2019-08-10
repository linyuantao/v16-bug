package com.qf.c16.common.base.pojo;

import java.io.Serializable;

/**
 * 根据我们引入的富文本集合文本插件，要求返回的数据格式的设计的结果对象
 * @authorcom.qf.c16.common.base.pojo
 * @Date2019/8/10
 */
public class WangEditorResultBean implements Serializable {
    private String erron;
    private String[] data;


    public WangEditorResultBean(String erron, String[] data) {
        this.erron = erron;
        this.data = data;
    }


    public WangEditorResultBean() {
    }

    public String getErron() {
        return erron;
    }

    public void setErron(String erron) {
        this.erron = erron;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
