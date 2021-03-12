package ru.sergeantalexander.paste.logger;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Component
public class LoggerFactory {

    public Logger getLogger() throws IOException {
        FileInputStream ins = new FileInputStream("C:\\Sultanov\\Development\\log.config");
        LogManager.getLogManager().readConfiguration(ins);

        return Logger.getLogger(LoggerFactory.class.getName());
    }
}
