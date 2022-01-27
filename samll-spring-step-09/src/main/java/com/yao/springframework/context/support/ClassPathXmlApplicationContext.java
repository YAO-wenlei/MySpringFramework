package com.yao.springframework.context.support;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月22日 11:55 上午
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    String[] configLocations;

    public ClassPathXmlApplicationContext() {

    }

    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
