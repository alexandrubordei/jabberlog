package com.bigstep;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by alexandrubordei on 17/12/2015.
 */
public class LoggingConversationMonitor implements ConversationMonitor {
    private static Logger logger = Logger.getLogger(String.valueOf(LoggingConversationMonitor.class));

    private String fileName;
    public LoggingConversationMonitor(String csvfile) throws IOException {


        fileName=csvfile;

    }

    @Override
    public void handleMessage(String message, String from, String to,Date date) {

        try {
            File file =new File(fileName);

            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(fileName, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(date.getTime() / 1000+","+from+","+to+"\n");
            bufferWritter.close();

        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }
}
