package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
/*
 * 业务接口：站在"使用者"角度设计接口
 * 三个方面：方法定义粒度(业务有哪些操作)，参数(简单直接,不要Map这种的)，返回类型（return 类型/异常）
 */

public interface SeckillService {
	/*
	 * 查询所有秒杀记录
	 */
	List<Seckill> getSeckillList();

	/*
	 * 根据id查询秒杀商品
	 */
	Seckill getById(long SeckillId);

	/*
	 * 秒杀开启时输出秒杀接口地址, 否则输出系统时间和秒杀时间
	 */
	Exposer exportSeckillUrl(long seckillId);

	/*
	 * 执行秒杀操作
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;
	
	SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;
}
