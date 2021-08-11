package com.curtisnewbie.module.redisutil.spi;

import com.curtisnewbie.module.redisutil.RedisController;
import com.curtisnewbie.module.redisutil.event.SubListener;
import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
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
        RBucket<T> bucket = redissonClient.getBucket(key);
        T o = bucket.get();
        return o;
    }

    @Override
    public <T> void set(String key, T value) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    @Override
    public Lock getLock(String key) {
        return redissonClient.getLock(key);
    }

    @Override
    public boolean expire(String key, long ttl, TimeUnit unit) {
        RBucket<?> bucket = redissonClient.getBucket(key);
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
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket.trySet(value);
    }

    @Override
    public <T> boolean setIfNotExists(String key, T value, long ttl, TimeUnit unit) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket.trySet(value, ttl, unit);
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

    @Override
    public boolean isLockHeldByCurrentThread(String key) {
        RLock l = redissonClient.getLock(key);
        return l.isHeldByCurrentThread();
    }

    @Override
    public boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit timeUnit) throws InterruptedException {
        RLock l = redissonClient.getLock(key);
        return l.tryLock(waitTime, leaseTime, timeUnit);
    }

    @Override
    public boolean tryLock(String key) throws InterruptedException {
        RLock l = redissonClient.getLock(key);
        return l.tryLock();
    }

    @Override
    public <T> void publish(String channel, T msg) {
        RTopic r = redissonClient.getTopic(channel);
        r.publish(msg);
    }

    @Override
    public <T> void subscribe(String channel, SubListener<T> subListener, Class<T> msgType) {
        RTopic r = redissonClient.getTopic(channel);
        r.addListener(msgType, new MessageListenerAdaptor<>(subListener));
    }

    @Override
    public void unlock(String key) {
        RLock l = redissonClient.getLock(key);
        if (l.isHeldByCurrentThread())
            l.unlock();
    }

    private static class MessageListenerAdaptor<M> implements MessageListener<M> {

        private final SubListener<M> sl;

        private MessageListenerAdaptor(SubListener<M> sl) {
            this.sl = sl;
        }

        @Override
        public void onMessage(CharSequence channel, M msg) {
            sl.onMessage(msg);
        }
    }

}
