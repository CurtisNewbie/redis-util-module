package com.curtisnewbie.module.redisutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Populate SPI loaded bean as Spring managed bean
 *
 * @author yongjie.zhuang
 */
@Configuration
public class RedisControllerBeanLoader {

    private static final Logger log = LoggerFactory.getLogger(RedisControllerBeanLoader.class);
    private final ServiceLoader<RedisController> serviceLoader = ServiceLoader.load(RedisController.class);

    @Bean
    public RedisController loadRedisController() {
        // always use the first instance loaded
        Iterator<RedisController> ite = serviceLoader.iterator();
        if (ite.hasNext()) {
            RedisController rc = ite.next();
            log.info("Loaded {} implementation using SPI - {}", RedisController.class.getSimpleName(),
                    rc.getClass().getName());
            return rc;

        } else {
            throw new IllegalStateException("No " + RedisController.class.getName()
                    + " implementation found, SPI not properly configured");
        }
    }
}
