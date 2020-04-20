package com.rainier.service.impl;

import com.rainier.mapper.BannerMapper;
import com.rainier.mapper.MyFileMapper;
import com.rainier.mapper.ProblemMapper;
import com.rainier.mapper.UserMapper;
import com.rainier.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class MyFileServiceImpl implements MyFileService {
    @Autowired
    private MyFileMapper myFileMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BannerMapper bannerMapper;

    @Value("${img}")
    private String img;

    @Override
    public int insertMyFile(String name, String bendiUrl, String fuwuqiUrl) {
        int jieguo=myFileMapper.insertMyFile(name,bendiUrl,fuwuqiUrl);
        return jieguo;
    }

    @Override
    public void quartzDeleteMyFile() {

        /*存放所有url的集合*/
        List<String> urlList = new ArrayList();

        /*查询出问题表所有图片的url*/
        List<Map> problemImg = problemMapper.getProblemImg();
        /*查询出评论表所有图片的url*/
        List<Map> replyImg = problemMapper.getReplyImg();
        /*查询出用户表所有的头像url*/
        List<Map> portrait = userMapper.getUserPortrait();
        /*查询所有广告表的url*/
        List<Map> advertisementImg = bannerMapper.getAdvertisementImg();

        List<List<Map>> list = new ArrayList<>();
        list.add(problemImg);
        list.add(replyImg);
        list.add(portrait);
        list.add(advertisementImg);
        for (List<Map> a : list){
            for (Map m : a){
                if (m.get("url")!=null){
                    urlList.add(m.get("url").toString());
                }

            }
        }
        myFileMapper.quartzDeleteMyFile(urlList);

    }

    @Override
    public void deleteFile() {
        //查询所有绝对路径
        List<Map> urls = myFileMapper.getMyFileLujing();
        Scanner sc = new Scanner(System.in);
        String pan = img;
        File file = new File(pan);
        File[] files = file.listFiles();
        lists(file,urls);
    }
    public static void lists(File file,List<Map> urls) {
        if (file.isDirectory()) { // 判断是否为文件夹
            File[] list = file.listFiles(); // 使用数组接收带有完整路径的文件夹
            if (list != null) {

                // 循环遍历文件
                for (int i = 0; i < list.length; i++) {
                    Integer ta = 0;


                    //遍历路径
                    for (int j = 0; j < urls.size(); j++) {
                        String bendiUrl = urls.get(j).get("bendiUrl").toString().replaceAll("/","\\\\");
                        if (list[i].toString().contains(bendiUrl)){
                            ta = 1;
                        }
                    }
                    //不等于1证明没有匹配到
                    if (ta != 1){
                        File file1 = new File(list[i].toString());
                        file1.delete();
                    }
                }
            }
        }
    }


}
