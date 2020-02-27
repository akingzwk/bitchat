package io.bitchat.server.session;

import io.bitchat.server.channel.ChannelType;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.util.List;

/**
 * @author houyi
 */
public class SessionHelper {

    private static final AttributeKey<String> SESSION_ID = AttributeKey.newInstance("sessionId");

    private static SessionManager sessionManager = DefaultSessionManager.getInstance();

    private SessionHelper() {

    }

    /**
     * mark this channel with attribute sessionId
     *
     * @param channel the channel
     */
    public static void markOnline(Channel channel, String sessionId) {
        channel.attr(SESSION_ID).set(sessionId);
    }


    /**
     * mark this channel with attribute sessionId
     *
     * @param channel the channel
     */
    public static void markOffline(Channel channel) {
        channel.attr(SESSION_ID).set(null);
    }

    /**
     * check whether the channel is login
     *
     * @param channel the channel
     * @return true if logged in otherwise false
     */
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(SESSION_ID) && channel.attr(SESSION_ID).get() != null;
    }

    public static String getSessionId(Channel channel) {
        return channel.hasAttr(SESSION_ID) ? channel.attr(SESSION_ID).get() : null;
    }

    public static Session getSession(Channel channel) {
        return sessionManager.getSession(getSessionId(channel));
    }

    public static List<Session> getSessionsByUserId(long userId) {
        return sessionManager.getSessionsByUserId(userId);
    }

    public static List<Session> getSessionsByUserIdAndChannelType(long userId, ChannelType channelType) {
        return sessionManager.getSessionsByUserIdAndChannelType(userId, channelType);
    }

    public static List<Session> getAllSessions() {
        return sessionManager.getAllSessions();
    }
}
