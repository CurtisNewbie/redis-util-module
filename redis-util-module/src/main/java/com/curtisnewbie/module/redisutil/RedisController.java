package com.curtisnewbie.module.redisutil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Controller of Redis
 *
 * @author yongjie.zhuang
 */
public interface RedisController {

    /**
     * Get value by key
     *
     * @param key key
     * @return value
     */
    <T> T get(String key);

    /**
     * Set value by key
     *
     * @param key   key
     * @param value value
     */
    <T> void set(String key, T value);

    /**
     * Get a lock backed by a key in redis
     *
     * @param key key
     * @return lock
     */
    Lock getLock(String key);

    /**
     * Set expiry time for a key
     *
     * @param key  key
     * @param ttl  time-to-live
     * @param unit time unit
     * @return whether the expiry time is set for the key
     */
    boolean expire(String key, long ttl, TimeUnit unit);

    /**
     * Set the value of the key, as well as the expiry time
     *
     * @param key   key
     * @param value value
     * @param ttl   time-to-live
     * @param unit  time unit
     * @return
     */
    <T> boolean expire(String key, T value, long ttl, TimeUnit unit);

    /**
     * Set value for a key if not exists
     *
     * @param key   key
     * @param value value
     * @return whether the key is set
     */
    <T> boolean setIfNotExists(String key, T value);

    /**
     * Increase the key's value
     *
     * @param key key
     * @param amt amount
     * @return the value after increase
     */
    long increaseBy(String key, int amt);

    /**
     * Increase the key's value
     *
     * @param key          key
     * @param defaultValue default value to use if not exists
     * @param amt          amount
     * @return the value after increase
     */
    long increaseBy(String key, int defaultValue, int amt);

    /**
     * Increment the key's value
     *
     * @param key key
     * @return the value after incrementation
     */
    long increment(String key);

    /**
     * Increment the key's value
     *
     * @param key          key
     * @param defaultValue default value to use if not exists
     * @return the value after incrementation
     */
    long increment(String key, int defaultValue);

    /**
     * Check if the key exists
     *
     * @param key key
     */
    boolean exists(String key);

    /**
     * Delete key
     *
     * @param key key
     */
    boolean delete(String key);
}
