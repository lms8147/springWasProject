package com.mysample.springwas.sample.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.*;

@Service
public class HelloNasServiceImpl implements HelloNasService {

    private static final Logger logger = LoggerFactory.getLogger(HelloNasServiceImpl.class);

    private static final long HELLO_TIME_OUT = 500;

    @Value("#{application['upload.root.path']}")
    private String uploadRootPath;

    @Override
    public boolean hello() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(new NasHelloTask());
        boolean valid = false;
        try {
            logger.info("async file check start");
            valid = future.get(HELLO_TIME_OUT, TimeUnit.MILLISECONDS);
            logger.info("async file check finish");
        } catch (TimeoutException e) {
            logger.info("async file check timeout");
            future.cancel(true);
        }
        executor.shutdownNow();
        return valid;
    }

    private class NasHelloTask implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            File file = new File(uploadRootPath);
            return file.exists();
        }
    }

}
