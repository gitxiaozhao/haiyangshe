package com.rainier.controller;

import com.rainier.model.Problem;
import com.rainier.model.Reply;
import com.rainier.service.ProblemService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @描述 问答
* @参数注释：
* @创建人  wyz
* @创建时间  2020/3/3
*/
@Controller
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Value("${solrUrl}")
    private String solrUrl;

    /**
    * @描述 提问问题
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/3
    */
    @ResponseBody
    @RequestMapping("addProblem")
    public Result addProblem(@RequestBody Problem problem, HttpServletRequest request){
        return problemService.addProblem(problem,request);
    }

    /**
    * @描述 评论问题
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/4
    */
    @ResponseBody
    @RequestMapping("addReply")
    public Result addReply(@RequestBody Reply reply, HttpServletRequest request){
        return problemService.addReply(reply,request);
    }

    /**
    * @描述 查看某条问题的答案
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/5
    */
    @ResponseBody
    @RequestMapping("getReplyByProblemId")
    public Result getReplyByProblemId(@RequestBody Map map) throws ParseException {
        return problemService.getReplyByProblemId(map);
    }

    /**
    * @描述 采纳答案
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/5
    */
    @ResponseBody
    @RequestMapping("updateReplyAdopt")
    public Result updateReplyAdopt(@RequestBody Reply reply) {
        return problemService.updateReplyAdopt(reply);
    }

    /**
     * @描述 查看某条问题详情
     * @参数注释：
     * @创建人  wyz
     * @创建时间  2020/3/5
     */
    @ResponseBody
    @RequestMapping("getProblemById")
    public Result getProblemById(@RequestBody Map map){
        return problemService.getProblemById(map);
    }

    /**
     * @描述 编辑问题
     * @参数注释：
     * @创建人  wyz
     * @创建时间  2020/3/13
     */
    @RequestMapping("updateProblem")
    @ResponseBody
    public Result updateProblem(@RequestBody Problem problem, HttpServletRequest request){
        return problemService.updateProblem(problem,request);
    }

    /**
    * @描述 删除指定问题
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/16
    */
    @RequestMapping("deleteProblemByIds")
    @ResponseBody
    public Result deleteProblemByIds(@RequestBody Map map){
        return problemService.deleteProblemByIds(map);
    }

    /**
    * @描述 热门问答列表（访问量前十）
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/17
    */
    @RequestMapping("getProblemHot")
    @ResponseBody
    public Result getProblemHot(){
        return problemService.getProblemHot();
    }

    /**
    * @描述 问答检索
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/18
    */
    @RequestMapping("/queryProblem")
    @ResponseBody
    public Result queryProblem(@RequestBody Map<String,Object> map) throws IOException, SolrServerException {

        //如果没有值则按照默认查询起始位置和条数
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
        if (map.get("pageIndex") == null && map.get("pageSize") == null){
            pageIndex = 1;
            pageSize = 10;
        }

        //设置指定core
        HttpSolrClient solrClient = new HttpSolrClient(solrUrl + "problem_core");
        SolrQuery solrQuery = new SolrQuery();

        /*查询关键字*/
        String key = map.get("key").toString();

        //设置关键字查询条件
        StringBuilder params = new StringBuilder();
        params.append("problem:*"+key+"* ");

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

        //统一返回格式
        Page<Map<String,Object>> page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords((int) results.getNumFound());
        page.setList(list);
        return Result.success(page);

    }

}
