package in.co.ad.springboot3.features.springboot3features.event;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import in.co.ad.springboot3.features.springboot3features.domain.CustomerDo;
import in.co.ad.springboot3.features.springboot3features.dto.LatestCustomerId;
import lombok.RequiredArgsConstructor;

@RepositoryEventHandler(CustomerDo.class)
@RequiredArgsConstructor
public class CustomerEventHandler {

    private final ApplicationContext applicationContext;

    @HandleAfterCreate
    public void handleAuthorAfterCreate(CustomerDo customerDo) {
        AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;
        registry.removeBeanDefinition("customerIdLatest");

        GenericBeanDefinition myBeanDefinition = new GenericBeanDefinition();
        myBeanDefinition.setBeanClass(LatestCustomerId.class);
        myBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);

        MutablePropertyValues mpv = new MutablePropertyValues();
        mpv.add("id", Integer.valueOf(customerDo.getId()));
        myBeanDefinition.setPropertyValues(mpv);

        registry.registerBeanDefinition("customerIdLatest", myBeanDefinition);
    }

    @HandleAfterSave
    public void handleAuthorAfterSave(CustomerDo customerDo) {
        AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;
        registry.removeBeanDefinition("customerIdLatest");

        GenericBeanDefinition myBeanDefinition = new GenericBeanDefinition();
        myBeanDefinition.setBeanClass(LatestCustomerId.class);
        myBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);

        MutablePropertyValues mpv = new MutablePropertyValues();
        mpv.add("id", Integer.valueOf(customerDo.getId()));
        myBeanDefinition.setPropertyValues(mpv);

        registry.registerBeanDefinition("customerIdLatest", myBeanDefinition);
    }

    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(CustomerDo customerDo) {
        AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;
        registry.removeBeanDefinition("customerIdLatest");

        GenericBeanDefinition myBeanDefinition = new GenericBeanDefinition();
        myBeanDefinition.setBeanClass(LatestCustomerId.class);
        myBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);

        MutablePropertyValues mpv = new MutablePropertyValues();
        mpv.add("id", Integer.valueOf(customerDo.getId()));
        myBeanDefinition.setPropertyValues(mpv);

        registry.registerBeanDefinition("customerIdLatest", myBeanDefinition);
    }
}
