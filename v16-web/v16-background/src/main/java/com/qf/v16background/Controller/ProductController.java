package com.qf.v16background.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.qf.c16.common.base.pojo.ResultBean;
import com.qf.v16.api.IProductService;
import com.qf.v16.api.vo.ProductV0;
import com.qf.v16.entity.TProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @authorcom.qf.v16background
 * @Date2019/8/6
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductService productService;

    @RequestMapping("getById/{id}")
    @ResponseBody
    public TProduct getById(@PathVariable ("id") Long id){

        return productService.selectByPrimaryKey(id);
    }

    //页面展示接口
    @RequestMapping("list")
    public String list(Model model){
        //1.获取商品信息(用集合存值)
        List<TProduct>list = productService.list();

        model.addAttribute("list",list);

        //2.然后再跳转页面进行展示
        return "product/list";
    }

    //分页接口
    @RequestMapping("page/{pageIndex}/{pageSize}")
    public String page(Model model,@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize){

        //1.获取分页的信息
        PageInfo<TProduct> pageInfo = productService.page(pageIndex, pageSize);
        //2.跳转页面进行展示
        model.addAttribute("pageInfo",pageInfo);
        return "product/list";

    }

    //数据添加的方法
    @RequestMapping("add")
    public String add(ProductV0 v0){
        System.out.println(v0);
        //新建添加方法
        Long newId= productService.add(v0);
        //后续作为通知其他系统做相关操作的标志
        return "redirect:/product/page/1/1";

    }
    //删除单个
    @RequestMapping("delById/{id}")
    @ResponseBody
    public ResultBean delById(@PathVariable("id") Long id){
        System.out.println(id);
      int count = productService.deleteByPrimaryKey(id);
      if(count>0){
          return new ResultBean("200","删除成功！");
      }
        return new ResultBean("404","删除失败！你的操作有误！");
    }



}
