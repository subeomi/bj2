package org.lsb.bj2.mappers;

import org.apache.ibatis.annotations.Mapper;


public interface TimeMapper {
    
    //@Select("select now()")
    String getTime();
}
