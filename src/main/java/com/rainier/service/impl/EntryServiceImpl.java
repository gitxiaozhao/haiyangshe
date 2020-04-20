package com.rainier.service.impl;

import com.rainier.mapper.EntryMapper;
import com.rainier.model.EntryType;
import com.rainier.service.EntryService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EntryServiceImpl implements EntryService{
    @Autowired
    private EntryMapper entryMapper;


    @Override
    public Result getEntryList() {
        List<Map<String,String>> list = entryMapper.getEntryList();

        List<Map> list1 = new ArrayList<>();
        for (Map<String,String> m : list){
            Map map = new HashMap();
            List list2 = new ArrayList();

            String entry_name = m.get("entry_name").toString();
            map.put("entry_name",entry_name);

            for (String key : m.keySet()){
                Map map2 = new HashMap();
                if (key.equals("entry_name") || key.equals("book_name")){
                    continue;
                }
                map2.put("key",key);
                map2.put("value",m.get(key));
                list2.add(map2);
            }
            map.put("information",list2);

            list1.add(map);
        }

        return Result.success(list1);
    }

    @Override
    public Result getEntryType() {
        /*定义最终所有节点的集合*/
        List<EntryType> data = new ArrayList<>();
        /*查询出pid为0的最大级别节点*/
        List<EntryType> list = entryMapper.getEntryTypeByPid(0);
        for (EntryType et : list){
            EntryType entryType = new EntryType();

            entryType.setId(et.getId());
            entryType.setTypeName(et.getTypeName());
            entryType.setPid(et.getPid());
            entryType.setData(EntryData(et.getId()));
            data.add(entryType);

        }
        return Result.success(data);
    }

    public List<EntryType> EntryData(Integer pid){
        /*定义用来存放字节点的集合*/
        List<EntryType> data = new ArrayList<>();
        /*通过传过来的父节点的id查询子节点*/
        List<EntryType> list1 = entryMapper.getEntryTypeByPid(pid);
        /*判断子节点是否存在*/
        if (list1.size() > 0){
            for (EntryType et : list1){
                EntryType entryType = new EntryType();
                entryType.setId(et.getId());
                entryType.setTypeName(et.getTypeName());
                entryType.setPid(et.getPid());
                entryType.setData(EntryData(et.getId()));
                data.add(entryType);

            }
        }
        return data;


    }

}
