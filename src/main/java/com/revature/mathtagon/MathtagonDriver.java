package com.revature.mathtagon;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SpringBootApplication
public class MathtagonDriver {
    static {
        try (FileInputStream logConfig = new FileInputStream("src/main/resources/log.properties")) {
            LogManager.getLogManager().readConfiguration(logConfig);
            Logger.getLogger("").addHandler(new FileHandler(
                    String.format("logs/mathtagon-%s.log", new Date(new java.util.Date().getTime())),
                    true
            ));
        } catch(IOException fnf) {
            System.err.println("[WARNING]: Could not open log configuration file. Logging to file not configured.\n" +
                    "Trace: "+ ExceptionUtils.getStackTrace(fnf));
        }
    }

    private static final Logger logger = Logger.getLogger(MathtagonDriver.class.getName());

    public static void main(String[] a) {
        logger.info("[INFO] Beginning Mathtagon\n");
        SpringApplication.run(MathtagonDriver.class, a);
    }
}
