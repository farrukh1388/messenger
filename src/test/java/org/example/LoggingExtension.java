package org.example;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class LoggingExtension implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance,
                                        ExtensionContext context) throws Exception {
        Logger logger = LoggerFactory.getLogger(testInstance.getClass());
        testInstance.getClass()
                .getMethod("setLogger", Logger.class)
                .invoke(testInstance, logger);
    }
}
