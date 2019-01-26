package com.programcreek.helloworld.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class PrototypeScopedBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		 for (String beanName : beanFactory.getBeanDefinitionNames()) {
			 
	            BeanDefinition beanDef = beanFactory.getBeanDefinition(beanName);
	 
	            String explicitScope = beanDef.getScope();
	 
	            if ("".equals(explicitScope)) {
	 
	                beanDef.setScope("prototype");
	            }
	        }
		
	}
	 

	
}