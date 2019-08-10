package com.qf.springbootfastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootFastdfsApplicationTests {

	//注入fastfds文件上传对象
	@Autowired
	private FastFileStorageClient client;


	//测试图片是否能上传到存储服务的路径
	@Test
	public void uploadTest() throws FileNotFoundException {
		File file =new File("E:\\IdeaWork\\v16\\v16-temp\\springboot-fastdfs\\1.jpg");

		FileInputStream fileInputStream =new FileInputStream(file);

		StorePath storePath = client.uploadImageAndCrtThumbImage(fileInputStream, file.length(), "jpg", null);
		System.out.println(storePath);
		System.out.println(storePath.getPath());
		System.out.println(storePath.getGroup());
		System.out.println(storePath.getFullPath());

	}


	//测试图片删除
	@Test
	public void delTest(){
		client.deleteFile("group1/M00/00/00/wKhAgF1NqBGAN416AABBpSDkGu0140.jpg");
		System.out.println("OK");



	}

}
