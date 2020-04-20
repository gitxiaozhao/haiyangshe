package com.rainier.mapper;

import com.rainier.model.EntryType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EntryMapper {
    List getEntryList();

    List<EntryType> getEntryTypeByPid(Integer pid);
}
