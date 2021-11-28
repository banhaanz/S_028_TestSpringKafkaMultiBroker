package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.config;

import com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.serializer.JsonRedisSerializer;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisMerchantProfileConfiguration {

	@Value("${redis.profile.host}")
	private String host;
	@Value("${redis.profile.port}")
	private int port;
	@Value("${redis.profile.lettuce.pool.max-active}")
	private int maxActive;
	@Value("${redis.profile.lettuce.pool.max-idle}")
	private int maxIdle;
	@Value("${redis.profile.lettuce.pool.min-idle}")
	private int minIdle;
	@Value("${redis.profile.lettuce.pool.max-wait}")
	private int maxWait;
	@Value("${redis.profile.database.index}")
	private int databaseIndex;

	private StringRedisSerializer stringRedisSerializer;
	private JsonRedisSerializer jsonRedisSerializer;

	public RedisMerchantProfileConfiguration() {
		stringRedisSerializer = new StringRedisSerializer();
		jsonRedisSerializer = new JsonRedisSerializer();
	}

	@Bean("profileRedisTemplate")
	RedisTemplate<String, Object> redisTemplate(@Qualifier("profileRedisConnectionFactory") RedisConnectionFactory rcf) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(rcf);
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(stringRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(jsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@SuppressWarnings("rawtypes")
	@Bean("profileRedisConnectionFactory")
	public RedisConnectionFactory connectionFactory(
			@Qualifier("profileRedisStandaloneConfiguration") RedisStandaloneConfiguration redisStandaloneConfiguration,
			@Qualifier("profileRedisGenericPool") GenericObjectPoolConfig pool) {
		LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder().poolConfig(pool).build();
		LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration,clientConfig);
		factory.setShareNativeConnection(false);
		factory.setDatabase(databaseIndex);
		return factory;
	}

	@SuppressWarnings("rawtypes")
	@Bean("profileRedisGenericPool")
	GenericObjectPoolConfig createGenericPool() {
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
		genericObjectPoolConfig.setMaxIdle(maxIdle);
		genericObjectPoolConfig.setMinIdle(minIdle);
		genericObjectPoolConfig.setMaxTotal(maxActive);
		genericObjectPoolConfig.setMaxWaitMillis(maxWait);
		return genericObjectPoolConfig;
	}


	@Bean("profileRedisStandaloneConfiguration")
	RedisStandaloneConfiguration redisStandaloneConfiguration() {
		return new RedisStandaloneConfiguration(host, port);
	}
}
