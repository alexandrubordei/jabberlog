package com.bigstep;

import java.util.Date;

/**
 * Created by alexandrubordei on 17/12/2015.
 */
public interface ConversationMonitor {
     void handleMessage(String message,String from, String to,Date date);
}
