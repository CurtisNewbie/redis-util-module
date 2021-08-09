package com.curtisnewbie.module.redisutil.spi;

import com.curtisnewbie.module.redisutil.RedisController;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controller of redis using {@link org.redisson.api.RedissonClient}
 *
 * @author yongjie.zhuang
 */
public class RedissonController implements RedisController {

    @Autowired
    private RedissonClient redissonClient;

    public RedissonController() {
    }
}
