package com.skynet.redis.entity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author Skynet
 * @date 2017年05月05日 14:20
 */
@Component
public class Receiver {

    private static final Logger LOGGER = LogManager.getLogger(Receiver.class);

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        LOGGER.info("Received < {} >",message);
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch(){
        return this.countDownLatch;
    }

}
