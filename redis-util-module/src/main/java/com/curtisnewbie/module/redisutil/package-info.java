/**
 * <h1>
 * Redis-Util-Module
 * </h1>
 * <p>
 * Simple module that uses Redis to provide functionalities like caching or distributed locking. It internally wraps
 * frameworks like Redisson. It's an internal module that can only work for very limited scenarios, it's used only to make
 * things a bit easier and consistent.
 * </p>
 * <h2>
 * To use this module:
 * </h2>
 * <p>
 * 1.Configure redis-server connection by setting properties or yaml files whichever you like.
 * <p>
 * For example:
 * <pre>
 *    {@code
 *      spring.redis.database=0
 *      spring.redis.host=localhost
 *      spring.redis.port=6379
 *      spring.redis.password=youPw
 *      spring.redis.timeout=60000
 *    }
 * </pre>
 * <p>
 * 2. Inject {@link com.curtisnewbie.module.redisutil.RedisController} and use it
 *
 * @author yongjie.zhuang
 */
package com.curtisnewbie.module.redisutil;