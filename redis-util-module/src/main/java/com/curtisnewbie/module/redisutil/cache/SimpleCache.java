package com.curtisnewbie.module.redisutil.cache;

import com.curtisnewbie.module.redisutil.RedisController;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Simple Cache based on redis
 *
 * @author yongj.zhuang
 */
public class SimpleCache<T> {

    private final RedisController redisController;
    private final Supplier<T> valueSupplier;
    private final String lockKey;
    private final TimeUnit timeUnit;
    private final long ttl;

    public SimpleCache(RedisController redisController, Supplier<T> valueSupplier, String lockKey, TimeUnit timeUnit, long ttl) {
        Assert.notNull(lockKey, "lockKey == null");
        Assert.notNull(timeUnit, "timeUnit == null");
        Assert.notNull(redisController, "redisController == null");
        Assert.notNull(valueSupplier, "valueSupplier == null");

        this.redisController = redisController;
        this.valueSupplier = valueSupplier;
        this.lockKey = lockKey;
        this.timeUnit = timeUnit;
        this.ttl = ttl;
    }


    /**
     * Load from cache
     *
     * @param key key
     */
    public T loadFromCache(String key) {
        return redisController.loadFromCache(key, valueSupplier, lockKey, timeUnit, ttl);
    }

}
