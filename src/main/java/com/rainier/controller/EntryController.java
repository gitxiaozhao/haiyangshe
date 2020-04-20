package com.rainier.controller;

import com.rainier.model.Entry;
import com.rainier.service.EntryService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @描述 词条
* @参数注释：
* @创建人  wyz
* @创建时间  2020/3/20
*/
@Controller
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Value("${solrUrl}")
    private String solrUrl;



    /**
    * @描述 查询词条列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/20
    */
    @RequestMapping("getEntryList")
    @ResponseBody
    public Result getEntryList(){
        return entryService.getEntryList();
    }

    /**
    * @描述
    * @参数注释： 获取词条分类
    * @创建人  wyz
    * @创建时间  2020/3/24
    */
    @RequestMapping("getEntryType")
    @ResponseBody
    public Result getEntryType(){
        return entryService.getEntryType();
    }

    /**
    * @描述 solr检索词条（全站搜索）
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/30
    */
    @RequestMapping("/queryEntry")
    @ResponseBody
    public Result queryEntry(@RequestBody Map<String,Object> map) throws IOException, SolrServerException {

        //如果没有值则按照默认查询起始位置和条数
        Integer pageIndex = 1;
        Integer pageSize = 1000;

        //设置指定core
        HttpSolrClient solrClient = new HttpSolrClient(solrUrl + "entry_core");
        SolrQuery solrQuery = new SolrQuery();

        /*查询关键字*/
        String key = map.get("key").toString();

        //设置关键字查询条件
        StringBuilder params = new StringBuilder();
        params.append("entry_name:*"+key+"* ");
        if (!map.get("entry_type").equals("")){
            params.append(" AND entry_type:*"+map.get("entry_type").toString()+"* ");
        }
        //设置查询条件
        System.out.println(params.toString());
        solrQuery.setQuery(params.toString());
        solrQuery.setStart((pageIndex-1)*pageSize);
        solrQuery.setRows(pageSize);

        //依据条件查询并返回数据
        QueryResponse response=solrClient.query(solrQuery);
        SolrDocumentList results = response.getResults();
        List<Map<String,Object>> list=new ArrayList<>();
        list.addAll(results);

        /*如果集合长度为0，此词条不存在*/
        if (list.size() == 0){
            return Result.error("您搜索的词条不存在！");
        }
        Map<Object, Map<Object, List<Map<String, Object>>>> glist = list.stream().collect(Collectors.groupingBy(e -> e.get("entry_name").toString(),Collectors.groupingBy(e -> e.get("entry_type").toString())));

        List dataList = new ArrayList();
        for (Object obj : glist.keySet()){


            for (Object obj1 : glist.get(obj).keySet()){
                Map map1 = new HashMap();
                List list1 = new ArrayList();

                for (Map<String, Object> map2 : glist.get(obj).get(obj1)){

                    Map map3 = new HashMap();
                    map3.put("key",map2.get("entry_key").toString());
                    map3.put("value",map2.get("entry_value").toString());
                    list1.add(map3);
                }
                map1.put("entry_name",obj);
                map1.put("entry_type",obj1);
                map1.put("information",list1);
                dataList.add(map1);
            }

        }

        return Result.success(dataList);
    }



}
