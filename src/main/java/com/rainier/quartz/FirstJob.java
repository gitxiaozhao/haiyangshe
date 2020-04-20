package com.rainier.quartz;


import com.rainier.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@EnableScheduling
@Component
public class FirstJob {

    @Autowired
    private MyFileService myFileService;

    /**
    * @描述 删除文件表里多余的数据
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/4
    */
    public void task() {
        System.out.println("任务1执行....");
        myFileService.quartzDeleteMyFile();
    }

    /**
    * @描述 删除多余的文件
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/4
    */
    public void task2(){
        System.out.println("任务2执行....");
        myFileService.deleteFile();
    }

}
