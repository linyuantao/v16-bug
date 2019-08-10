package com.qf.v16background.Controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.c16.common.base.pojo.ResultBean;
import com.qf.c16.common.base.pojo.WangEditorResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 处理文件的服务接口
 * @authorcom.qf.v16background.Controller
 * @Date2019/8/9
 */
@RestController
@RequestMapping("file")
public class FileController {


    //注入fastfds文件上传对象
    @Autowired
    private FastFileStorageClient client;

    @Value("${image.server}")
    private String imageServer;


    @RequestMapping("upload")
    public ResultBean upload(MultipartFile file){
        //将文件上传到fastDFS上

        //1.获取后缀名
        String originalfilename = file.getOriginalFilename();
        String extname = originalfilename.substring(originalfilename.lastIndexOf(".")+1);
        //2.上传文件
        try {
            StorePath storePath = client.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extname, null);

            //获取上传后的的保存路径
            String Path = storePath.getFullPath();
            //传递回给客户端进行展示
            return new ResultBean("200",Path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResultBean("500","文件上传失败");

    }


    //富文本框多个文件上传
    @RequestMapping("multiUpload")
    public WangEditorResultBean multiUpload(MultipartFile[] files){


        //创建一个数组,来保存上传的图片路径
        String[] data =new String[files.length];

        for(int i =0; i<files.length;i++){
            //将文件上传到fastDFS上
            //1.获取后缀名
            MultipartFile file = files[i];
            String originalfilename = file.getOriginalFilename();
            String extname = originalfilename.substring(originalfilename.lastIndexOf(".")+1);
            //2.上传文件
            try {
                StorePath storePath = client.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extname, null);
                //获取上传后的的保存路径
                String Path = storePath.getFullPath();
                //保存到数组中stringbuilder
                  data[i] =imageServer+Path;
                  System.out.println(data[i]);
            } catch (IOException e) {
                e.printStackTrace();
                return new WangEditorResultBean("-1",null);
            }

        }
                //传递回给客户端进行展示
                return new WangEditorResultBean("0",data);

    }


}
