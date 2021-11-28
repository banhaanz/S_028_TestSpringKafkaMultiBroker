package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class ListenerLogger {
    public final static String SERVICE_NAME = "service";
    public final static String TOPIC_NAME = "topicName";
    public final static String CONSUMER_GROUP = "consumerGroup";
    private final static Marker MARKER_JSON = MarkerManager.getMarker("ELK_JSON");
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger;

    public ListenerLogger(Class callerClass) {
        logger = LogManager.getLogger(callerClass);
    }

    public static String getHostIp() {
        String ipAddr = getHostAddress();
        String[] addr = Objects.isNull(ipAddr) ? null : ipAddr.split(":");
        if (Objects.nonNull(addr) && addr.length > 1) {
            return addr[0];
        } else {
            return null;
        }
    }

    public static String getPort() {
        String ipAddr = getHostAddress();
        String[] addr = Objects.isNull(ipAddr) ? null : ipAddr.split(":");
        if (Objects.nonNull(addr) && addr.length > 1) {
            return addr[1];
        } else {
            return null;
        }
    }

    public static String getHostAddress() {
        String ipAddr = "";

        try {
            ipAddr = InetAddress.getLocalHost().getHostAddress();
            return ipAddr;
        } catch (UnknownHostException var3) {
            return null;
        }
    }

    public static String getHostName() {
        String hostName = "";

        try {
            hostName = InetAddress.getLocalHost().getHostName();
            return hostName;
        } catch (UnknownHostException var3) {
            return null;
        }
    }

    public void error(String message, Object... objs) {
        logger.log(Level.ERROR, message, objs);
    }

    public void error(Object obj) {
        if (CharSequence.class.isAssignableFrom(obj.getClass())) {
            info((String) obj);
        } else {
            updateThreadContextMap();
            try {
                logger.log(Level.ERROR, objectMapper.writeValueAsString(obj));
            } catch (JsonProcessingException e) {
                logger.log(Level.ERROR, "Error occurred while logging.", e);
            }
        }
    }

    public void info(String message, Object... objs) {
        logger.log(Level.INFO, message, objs);
    }

    public void info(String message, Object obj) {
        try {
            logger.log(Level.INFO, message.concat(objectMapper.writeValueAsString(obj)));
        } catch (JsonProcessingException e) {
            logger.log(Level.ERROR, "Error occurred while logging.", e);
        }
    }

    public void debug(String message, Object... objs) {
        logger.log(Level.DEBUG, message, objs);
    }

    public void debug(String message, Object obj) {
        try {
            logger.log(Level.DEBUG, message.concat(objectMapper.writeValueAsString(obj)));
        } catch (JsonProcessingException e) {
            logger.log(Level.ERROR, "Error occurred while logging.", e);
        }
    }

    public void warn(String message, Object... objs) {
        logger.log(Level.WARN, message, objs);
    }

    public void warn(String message, Object obj) {
        try {
            logger.log(Level.WARN, message.concat(objectMapper.writeValueAsString(obj)));
        } catch (JsonProcessingException e) {
            logger.log(Level.ERROR, "Error occurred while logging.", e);
        }
    }

    private void updateThreadContextMap() {
        ThreadContext.put("serverIp", getHostIp());
        ThreadContext.put("port", getPort());
    }

    public void updateContextMap(String key, String val) {
        ThreadContext.put(key, val);
    }

}

