package com.tziogas.articledownloader.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Wrapper methods and utilities for log4j loggers
 *
 */
public class LogWrapper {
	
	private Logger realLogger;
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));

	}

	private LogWrapper(Logger logger) {
		realLogger = logger;
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("log4j.properties"));
			PropertyConfigurator.configure(props);
		} catch (IOException e) {
			logger.error("Could not initialize logger properties.", e);
		}
	}

	/**
	 * Return a LogWrapper containing the logger for class clazz
	 * <p>
	 * example: final static LogWrapper logger =
	 * LogWrapper.getLogger(MethodHandles.lookup().lookupClass());
	 * 
	 * @param clazz
	 * @return
	 */
	public static LogWrapper getLogger(Class<?> clazz) {
		return LogWrapper.getLogger(clazz.getName());
	}

	/**
	 * Return a LogWrapper containing a logger with name loggerName
	 * 
	 * @param loggerName
	 * @return
	 */
	public static LogWrapper getLogger(String loggerName) {
		return new LogWrapper(Logger.getLogger(loggerName));
	}

	/**
	 * Wrapper methods
	 */
	public void error(String message, Exception e) {
		realLogger.error(message, e);
	}

	public void error(String message) {
		realLogger.error(message);
	}

	public void warning(String message, Exception e) {
		realLogger.warn(message, e);
	}

	public void warning(String message) {
		warning(message, null);
	}

	public void info(String message) {
		realLogger.info(message);
	}

	public void debug(String message) {
		realLogger.debug(message);
	}
	
}