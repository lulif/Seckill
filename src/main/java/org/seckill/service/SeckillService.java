package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
/*
 * ҵ��ӿڣ�վ��"ʹ����"�Ƕ���ƽӿ�
 * �������棺������������(ҵ������Щ����)������(��ֱ��,��ҪMap���ֵ�)���������ͣ�return ����/�쳣��
 */

public interface SeckillService {
	/*
	 * ��ѯ������ɱ��¼
	 */
	List<Seckill> getSeckillList();

	/*
	 * ����id��ѯ��ɱ��Ʒ
	 */
	Seckill getById(long SeckillId);

	/*
	 * ��ɱ����ʱ�����ɱ�ӿڵ�ַ, �������ϵͳʱ�����ɱʱ��
	 */
	Exposer exportSeckillUrl(long seckillId);

	/*
	 * ִ����ɱ����
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;
	
	SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;
}
