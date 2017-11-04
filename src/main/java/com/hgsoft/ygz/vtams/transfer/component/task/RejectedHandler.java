package com.hgsoft.ygz.vtams.transfer.component.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * taskExecutor的拒绝处理：
 * 当poolSize>maxPoolSize，且queue已满时，如果有新的线程任务出现，就会触发该方法
 *
 * @author 赖少涯
 * @date 2017-10-31
 */
public class RejectedHandler implements RejectedExecutionHandler {

    private static final Logger log = LoggerFactory.getLogger(RejectedHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("Task " + r.toString() + " rejected from " + executor.toString());
    }
}
