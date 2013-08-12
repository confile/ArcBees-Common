package com.arcbees.guicyresteasy;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.plugins.guice.GuiceResourceFactory;
import org.jboss.resteasy.plugins.server.servlet.FilterDispatcher;
import org.jboss.resteasy.spi.Registry;
import org.jboss.resteasy.spi.ResourceFactory;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.util.GetRestful;

import com.google.inject.Binding;
import com.google.inject.Injector;

@Singleton
public class GuiceRestEasyFilterDispatcher extends FilterDispatcher {
    @Inject
    Injector injector;

    @Override
    public void init(FilterConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        Registry registry = getDispatcher().getRegistry();
        ResteasyProviderFactory providerFactory = getDispatcher().getProviderFactory();

        for (final Binding<?> binding : injector.getBindings().values()) {
            Type type = binding.getKey().getTypeLiteral().getType();
            if (type instanceof Class) {
                Class<?> beanClass = (Class) type;
                if (GetRestful.isRootResource(beanClass)) {
                    ResourceFactory resourceFactory = new GuiceResourceFactory(binding.getProvider(), beanClass);
                    registry.addResourceFactory(resourceFactory);
                }

                if (beanClass.isAnnotationPresent(Provider.class)) {
                    providerFactory.registerProviderInstance(binding.getProvider().get());
                }
            }
        }
    }
}
