package cn.sina.api.commons.util;

import org.apache.log4j.Logger;


/**
 * api common logger  
 * 
 * @author yangwm Mar 22, 2013 12:15:01 AM
 */
public class ApiLogger {
	
    /**
     * Logger for this class
     */
    private static final Logger log = Logger.getLogger("com.weibo.api.commons.memcache");
    
    public static boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }
    
	public static void info(Object msg) {
	    log.info(msg);
	}

	public static void warn(Object msg) {
	    log.warn(msg);
	}

	public static void warn(Object msg, Throwable e) {
	    log.warn(msg, e);
	}

	public static void error(Object msg) {
	    log.error(msg);
	}

	public static void error(Object msg, Throwable e) {
	    log.error(msg, e);
	}

}
