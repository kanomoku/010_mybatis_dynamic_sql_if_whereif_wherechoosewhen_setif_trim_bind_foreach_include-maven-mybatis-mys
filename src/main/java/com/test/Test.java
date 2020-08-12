package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mapper.LogMapper;
import com.pojo.LogInfo;

public class Test {
	public static void main(String[] args) throws IOException {

		// 构建者设计模式
		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = factory.openSession();
		LogMapper logMapper = session.getMapper(LogMapper.class);
		
//		1. 根据不同的条件需要执行不同的SQL 命令.称为动态SQL
//		2. MyBatis 中动态SQL 在mapper.xml 中添加逻辑判断等.
//		3. If 使用
		List<LogInfo> selByAccOutAccIn1 = logMapper.selByAccOutAccIn1("1", "");
		for (LogInfo logInfo : selByAccOutAccIn1) {
//			System.out.println(logInfo);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		4. <where>
//			4.1 当编写where 标签时,如果内容中第一个是and 去掉第一个and
//			4.2 如果<where>中有内容会生成where 关键字,如果没有内容不生成where 关键
//			4.3 使用示例
//				4.3.1 比直接使用<if>少写where 1=1
		List<LogInfo> selByAccOutAccIn2 = logMapper.selByAccOutAccIn2("1", "");
		for (LogInfo logInfo : selByAccOutAccIn2) {
//			System.out.println(logInfo);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		5. <choose> <when> <otherwise>
//			5.1 只有有一个成立,其他都不执行.
//			5.2 代码示例
//				5.2.1 如果accin 和accout 都不是null 或不是””生成的sql 中只有where accin=?
		List<LogInfo> selByAccOutAccIn3 = logMapper.selByAccOutAccIn3("1", "");
		for (LogInfo logInfo : selByAccOutAccIn3) {
//			System.out.println(logInfo);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		6. <set>用在修改SQL 中set 从句
//			6.1 作用:去掉最后一个逗号
//			6.2 作用:如果<set>里面有内容生成set 关键字,没有就不生成
//			6.3 示例
//				6.3.1 id=#{id} 目的防止<set>中没有内容,mybatis 不生成set 关键字,如果修改中没有set 从句SQL 语法错误.
		LogInfo LogInfo = new LogInfo();
		LogInfo.setId(3);
		LogInfo.setAccIn("8");
		LogInfo.setAccOut("9");
		LogInfo.setMoney(522);
//		logMapper.updLogInfo(LogInfo);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		7. Trim
//			7.1 prefix 在前面添加内容
//			7.2 prefixOverrides 去掉前面内容
//			7.3 suffix 在后面添加内容
//			7.4 suffixOverrieds 去掉后面内容
//			7.5 执行顺序去掉内容后添加内容
//			7.6 代码示例
		List<LogInfo> selByAccOutAccIn4 = logMapper.selByAccOutAccIn4("1", "");
		for (LogInfo logInfo : selByAccOutAccIn4) {
//			System.out.println(logInfo);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		8. <bind>
//			8.1 作用:给参数重新赋值
//			8.2 场景:
//				8.2.1 模糊查询
//				8.2.2 在原内容前或后添加内容
//			8.3 示例
		List<LogInfo> selByAccOutAccIn5 = logMapper.selByAccOutAccIn5("1", "");
		for (LogInfo logInfo : selByAccOutAccIn5) {
//			System.out.println(logInfo);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			9. <foreach>标签
//				9.1 循环参数内容,还具备在内容的前后添加内容,还具备添加分隔符功能.
//				9.2 适用场景:in 查询中.批量新增中(mybatis 中foreach 效率比较低)
//					9.2.1 如果希望批量新增,SQL 命令
//					9.2.2 openSession()必须指定
//						9.2.2.1 底层JDBC 的PreparedStatement.addBatch();
//				9.3 示例
//					9.3.1 collectino="" 要遍历的集合
//					9.3.2 item 迭代变量, #{迭代变量名}获取内容
//					9.3.3 open 循环后左侧添加的内容
//					9.3.4 close 循环后右侧添加的内容
//					9.3.5 separator 每次循环时,元素之间的分隔符
		List<Integer> intList = new ArrayList();
		intList.add(1);
		intList.add(2);
		intList.add(3);
		List<LogInfo> selByAccOutAccIn6 = logMapper.selIn(intList);
		for (LogInfo logInfo : selByAccOutAccIn6) {
//			System.out.println(logInfo);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

//			10. <sql> 和<include>
//			10.1 某些SQL 片段如果希望复用,可以使用<sql>定义这个片段
//			10.2 在<select>或<delete>或<update>或<insert>中使用<include>
//			引用
		List<LogInfo> selByAccOutAccIn7 = logMapper.selByAccOutAccIn6("1", "");
		for (LogInfo logInfo : selByAccOutAccIn7) {
			System.out.println(logInfo);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		session.commit();
		session.close();
	}

}
