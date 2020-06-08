package com.rainier.mapper;

import com.rainier.model.Problem;
import com.rainier.model.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProblemMapper {
    int addProblem(@Param("problem") Problem problem);

    int addReply(@Param("reply") Reply reply);

    List<Map> getProblemImg();

    List<Map> getReplyImg();

    List<Map> getReplyByProblemId(@Param("problemId") String problemId,@Param("ascOrDesc") String ascOrDesc,@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);

    Integer getReplyByProblemIdCount(@Param("problemId") String problemId);

    List getReplyByAdopt(@Param("reply") Reply reply);

    int updateReplyAdopt(@Param("reply") Reply reply);

    Map getProblemById(@Param("id")String id);

    void addClick(@Param("id")String id);

    void updateProblem(@Param("problem") Problem problem);

    void deleteProblemByIds(@Param("ids")List ids);

    void deleteReplyByProblemIds(@Param("ids")List ids);

    List getProblemHot();

    Integer getProblemListCount( @Param("key") String key,@Param("yearType") String yearType);

    List getProblemList(@Param("key") String key,@Param("yearType") String yearType, @Param("pageStart") Integer pageStart, @Param("pageEnd") Integer pageSize);


}
