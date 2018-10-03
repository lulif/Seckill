package org.seckill.dao.cache;

import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
	private final JedisPool jedisPool;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

	public RedisDao(String ip, int port) {
		jedisPool = new JedisPool(ip, port);
	}

	/*
	 * redis���õ��ֽ����飬ͨ��protostuffת��Ϊ��ɱ����
	 */
	public Seckill getSeckill(long seckillId) {
		// redis�õ�Seckill����ȥ����db
		Jedis jedis = jedisPool.getResource();
		try {
			String key = "seckill:" + seckillId;
			// redis��û��ʵ���ڲ������л�����
			// get��byte[]�������л���Onject(Seckill)
			// �����Զ������л�
			// ��һ��Clas���Class��Ҫ��protostuff��pojo
			byte[] bytes = jedis.get(key.getBytes());
			// �����ػ�ȡ��
			if (bytes != null) {
				// �ն���
				Seckill seckill = schema.newMessage();
				ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
				// seckill�������л�
				return seckill;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return null;
	}

	public String putSeckill(Seckill seckill) {
		// ����û�е�ʱ��putһ��Seckill
		// set Object(Seckill)�����л���byte[]
		Jedis jedis = jedisPool.getResource();
		try {
			String key = "seckill:" + seckill.getSeckillId();
			byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
					LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			int timeout = 60 * 60;
			//���r����
			String result = jedis.setex(key.getBytes(), timeout, bytes);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return null;
	}

}
