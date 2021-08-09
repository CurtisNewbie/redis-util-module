package com.curtisnewbie.module.redisutil.spi;

import com.curtisnewbie.module.redisutil.RedisController;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Controller of redis using {@link org.redisson.api.RedissonClient}
 *
 * @author yongjie.zhuang
 */
public class RedissonRedisController implements RedisController {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public <T> T get(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return (T) bucket.get();
    }

    @Override
    public <T> void set(String key, T value) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    @Override
    public Lock getLock(String key) {
        return redissonClient.getLock(key);
    }

    @Override
    public boolean expire(String key, long ttl, TimeUnit unit) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.expire(ttl, unit);
    }

    @Override
    public <T> boolean expire(String key, T value, long ttl, TimeUnit unit) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value);
        return bucket.expire(ttl, unit);
    }

    @Override
    public <T> boolean setIfNotExists(String key, T value) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.trySet(value);
    }

    @Override
    public long increaseBy(String key, int amt) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        return atomicLong.addAndGet(amt);
    }

    @Override
    public long increaseBy(String key, int defaultValue, int amt) {
        setIfNotExists(key, defaultValue);
        return increaseBy(key, amt);
    }

    @Override
    public long increment(String key) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        return atomicLong.incrementAndGet();
    }

    @Override
    public long increment(String key, int defaultValue) {
        setIfNotExists(key, defaultValue);
        return increment(key);
    }

    @Override
    public boolean exists(String key) {
        RBucket<?> b = redissonClient.getBucket(key);
        return b.isExists();
    }

    @Override
    public boolean delete(String key) {
        RBucket<?> b = redissonClient.getBucket(key);
        return b.delete();
    }

}
