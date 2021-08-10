package com.curtisnewbie.module.redisutil.event;

import java.util.EventListener;

/**
 * Listener for subscription to a single topic
 *
 * @author yongjie.zhuang
 */
public interface SubListener<T> extends EventListener {

    /**
     * On Message
     *
     * @param msg message
     */
    void onMessage(T msg);

}
