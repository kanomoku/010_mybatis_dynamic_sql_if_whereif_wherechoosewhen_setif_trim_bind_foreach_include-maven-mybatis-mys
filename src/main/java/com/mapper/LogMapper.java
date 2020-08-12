package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.LogInfo;

public interface LogMapper {
	List<LogInfo> selAll();

	List<LogInfo> selByAccOutAccIn1(@Param("accOut") String accOut, @Param("accIn") String accIn);

	List<LogInfo> selByAccOutAccIn2(@Param("accOut") String accOut, @Param("accIn") String accIn);

	List<LogInfo> selByAccOutAccIn3(@Param("accOut") String accOut, @Param("accIn") String accIn);

	List<LogInfo> selByAccOutAccIn4(@Param("accOut") String accOut, @Param("accIn") String accIn);

	List<LogInfo> selByAccOutAccIn5(@Param("accOut") String accOut, @Param("accIn") String accIn);

	List<LogInfo> selByAccOutAccIn6(@Param("accOut") String accOut, @Param("accIn") String accIn);

	int updLogInfo(LogInfo logInfo);

	int updLogInfo2(LogInfo logInfo);

	List<LogInfo> selIn(List<Integer> list);

	int ins(List<Integer> list);

}
