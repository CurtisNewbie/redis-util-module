package com.curtisnewbie.module.redisutil.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;

/**
 * Yaml based provider of RedissonClient
 *
 * @author yongj.zhuang
 */
@Configuration
public class YamlBasedRedissonClientProvider {

    private static final Logger log = LoggerFactory.getLogger(YamlBasedRedissonClientProvider.class);
    public static final String REDISSON_YAML_PATH = "redisson-config";

    @Bean
    @ConditionalOnMissingBean
    public RedissonClient redissonClient(Environment env) throws IOException {
        final String key = REDISSON_YAML_PATH;
        log.info("Loading RedissonClient from yaml config file, reading environment property: {}", key);
        final String relPath = env.getRequiredProperty(key);
        return Redisson.create(Config.fromYAML(YamlBasedRedissonClientProvider.class.getClassLoader().getResourceAsStream(relPath)));
    }
}
