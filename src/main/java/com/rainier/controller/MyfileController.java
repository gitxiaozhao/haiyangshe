package com.rainier.controller;


import com.rainier.service.MyFileService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @描述 上传文件
* @参数注释：
* @创建人  wyz
* @创建时间  2020/3/3
*/
@Controller
@RequestMapping("/file")
public class MyfileController {

    @Value("${imgurl}")
    private String imgurl;

    @Value("${img}")
    private String img;

    @Value("${cbs.imagesPath}")
    private String filePath;

    @Autowired
    private MyFileService myFileService;

    private String  url;


    /**
    * @描述 上传图片
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/3
    */
    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(HttpServletRequest request) {

        MultipartFile file= null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //判断是否为空
        if (isMultipart){
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            file = multipartRequest.getFile("fileName");


            System.out.print("上传文件==="+"\n");
            //判断文件是否为空
            if (file.isEmpty()) {
                return "上传文件不可为空";
            }


            // 获取文件名
            String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");

            fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
            System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");


            //加个时间戳，尽量避免文件名称重复
            String path = img +fileName;
            //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
            //文件绝对路径
            System.out.print("保存文件绝对路径"+path+"\n");

            //创建文件路径
            File dest = new File(path);

            //判断文件是否已经存在
            if (dest.exists()) {
                return "文件已经存在";
            }

            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }

            try {
                //上传文件
                file.transferTo(dest); //保存文件
                System.out.print("保存文件路径"+path+"\n");
                //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
                url=imgurl+fileName;//本地运行项目
                int jieguo= myFileService.insertMyFile(fileName,path,url);
                System.out.print("插入结果"+jieguo+"\n");
                System.out.print("保存的完整url===="+url+"\n");

            } catch (IOException e) {
                return "上传失败";
            }

            return url;
        }else {
            return "文件为空";
        }

    }


}

