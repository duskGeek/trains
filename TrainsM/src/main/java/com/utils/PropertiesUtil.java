package com.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties _prop = new Properties();

    /**
     * ��ȡ�����ļ�
     * @param fileName
     * @throws IOException 
     */
    public static void readProperties(String fileName) throws Exception{
        try {
            InputStream in = PropertiesUtil.class.getResourceAsStream("/"+fileName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            _prop.load(bf);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    /**
     * ����key��ȡ��Ӧ��value
     * @param key
     * @return
     */
    public static String getProperty(String key){
        return _prop.getProperty(key);
    }
    
    public static List<String> getProps() {
    	List<String> valueList=new ArrayList<String>();
    	
    	Enumeration e=_prop.propertyNames();
    	while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			valueList.add(_prop.getProperty(key));
		}
    	return valueList;
    }
}