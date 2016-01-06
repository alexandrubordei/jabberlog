package com.bigstep;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.util.Date;
import java.util.logging.*;
import java.io.IOException;

/**
 * Created by alexandrubordei on 17/12/2015.
 */
public class GtalkMessenger {

    private static Logger logger = Logger.getLogger(String.valueOf(GtalkMessenger.class));

    private XMPPTCPConnectionConfiguration config;
    private AbstractXMPPConnection conn;

    public  GtalkMessenger(String username, String password)
    {
       config = XMPPTCPConnectionConfiguration.builder()

               .setUsernameAndPassword(username, password)
                .setServiceName("gmail.com")
                .setHost("talk.google.com")
                .setPort(5222)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.required)
                .setHostnameVerifier((s, ssl) -> s.equals("gmail.com"))
                .build();

        conn = new XMPPTCPConnection(config);
    }


    public void connect() throws IOException, XMPPException, SmackException {

        logger.fine("Connecting...");
        conn.connect();
        logger.finest("Connected!");
        conn.login();
        logger.finest("Logged in");


    }

    public void disconnect()
    {
        logger.fine("Disconnecting...");
        conn.disconnect();
        logger.fine("Disconnected!");
    }

    public void startMonitor(ConversationMonitor monitor) throws IOException, XMPPException, SmackException {


        ChatManager chatmanager = ChatManager.getInstanceFor(conn);

        chatmanager.addChatListener( (chat, createdLocally) -> {

                chat.addMessageListener((c, message) -> {
                    //      if (message.getBody() != null && !message.getBody().equals("")) {

                    String from = message.getFrom();
                    if (message.getExtension("record", "http://jabber.org/protocol/archive") != null) {
                        if ((message.getBody() == null))
                            from = message.getTo();


                        monitor.handleMessage(
                                message.getBody(),
                                from,
                                message.getTo(),
                                new Date());
                        logger.info(chat.toString());
                        logger.info(message.toString());
                    }
                    //    }

                });

        });


    }


}
