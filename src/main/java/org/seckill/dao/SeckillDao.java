package org.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	/*
	 * 减库存
	 * 返回类型：更新记录的行数
	 */
	int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime") Date killTime);

	/*
	 * 根据id查询秒杀对象
	 */
	Seckill queryById(long seckillId);

	/*
	 * 根据偏移量查询秒杀商品列表
	 * java没有保存形参记录，形参会变成arg0,arg1,一个参数还好，多个参数要告诉mybatis哪个参数什么位置
	 * @Param("offset")，@Param("offset")：来告诉mybatis具体的参数名字
	 */
	List<Seckill> queryAll(@Param("offet")int offet,@Param("limit") int limit);
	
	void killByProcedure(Map<String,Object>paramMap);

}
