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
	 * redis中拿到字节数组，通过protostuff转换为秒杀对象
	 */
	public Seckill getSeckill(long seckillId) {
		// redis拿到Seckill而不去访问db
		Jedis jedis = jedisPool.getResource();
		try {
			String key = "seckill:" + seckillId;
			// redis并没有实现内部的序列化操作
			// get→byte[]→反序列化→Onject(Seckill)
			// 采用自定义序列化
			// 给一个Clas这个Class的要求：protostuff：pojo
			byte[] bytes = jedis.get(key.getBytes());
			// 缓存重获取到
			if (bytes != null) {
				// 空对象
				Seckill seckill = schema.newMessage();
				ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
				// seckill被反序列化
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
		// 缓存没有的时候put一个Seckill
		// set Object(Seckill)→序列化→byte[]
		Jedis jedis = jedisPool.getResource();
		try {
			String key = "seckill:" + seckill.getSeckillId();
			byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
					LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			int timeout = 60 * 60;
			//超時緩存
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
