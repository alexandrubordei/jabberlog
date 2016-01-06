package com.bigstep;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import java.util.logging.*;

import java.io.IOException;
/**
 * Created by alexandrubordei on 17/12/2015.
 */
public class EngTest {
    private static Logger logger = Logger.getLogger(String.valueOf(EngTest.class));

    public static void main(String[] args) throws IOException, XMPPException, SmackException {



        GtalkMessenger messenger = new GtalkMessenger("alexandru.bordei", "plwbibbpqsaollmd");
        //GtalkMessenger messenger = new GtalkMessenger("engchatmonitor", "NiCad54}raga");
        messenger.connect();

        ConversationMonitor monitor = new LoggingConversationMonitor("monitor-output.txt");
        messenger.startMonitor(monitor);

        while(true)
        {

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                messenger.disconnect();
            }

        }

    }
}
