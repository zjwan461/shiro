package com.itsu.app.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * Created by Su Ben on 2019/6/17
 */
public class EncPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        String encPwd = props.getProperty("jdbc.password");
        String pwd = EncUtil.decrypt(encPwd, EncUtil.KEY);
        props.setProperty("jdbc.password", pwd);
        super.processProperties(beanFactoryToProcess, props);
    }
}
