package pers.zb.wechat.rpc.api.wxinf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    
    private final static String configFile = Constants.CONFIG_FILE;
    
    private static ConfigManager theInstance = null;
    
    private Properties theProperties = null;
    
    private ConfigManager() {
        super();
        this.load();
    }
    
    public static synchronized ConfigManager getInstance() {
        if(theInstance == null) {
            theInstance = new ConfigManager();
        }
        return theInstance;
    }
    
    private void load() {
        InputStream is = ConfigManager.class.getResourceAsStream(configFile);
        if (is == null) {
            return;
        }
        theProperties = new Properties();
        try {
            theProperties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Properties getConfig() {
        return theProperties;
    }
    
    /**
     * 根据Key返回表或配置文件中对应的配置项的值
     * @param key -- 表或配置文件中配置项的key值
     * @return String -- 配置项的值
     */
    public String getConfigValue(String key) {
        if (theProperties == null) return null;
        return theProperties.getProperty(key);
    }
    
    /**
     * 根据Key返回表或配置文件中对应的配置项的值,如果表或配置文件中没有指定的项,则返回defaultValue
     * @param key -- 配置文件中配置项的key值
     * @param defaultValue -- 配置项的缺省值
     * @return String -- 配置项的值
     */
    public String getConfigValue(String key, String defaultValue) {
        if (theProperties == null) {
            return defaultValue;
        }
        return theProperties.getProperty(key, defaultValue);
    }
}
