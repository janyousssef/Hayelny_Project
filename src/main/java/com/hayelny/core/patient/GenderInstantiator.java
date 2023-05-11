package com.hayelny.core.patient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.EmbeddableInstantiator;
import org.hibernate.metamodel.spi.ValueAccess;

public class GenderInstantiator implements EmbeddableInstantiator {

    public boolean isInstance(Object object, SessionFactoryImplementor sessionFactory) {
        return object instanceof Gender;
    }

    public boolean isSameClass(Object object, SessionFactoryImplementor sessionFactory) {
        return object.getClass()
                .equals(Gender.class);
    }

    @Override
    public Object instantiate(ValueAccess valueAccess, SessionFactoryImplementor sessionFactory) {
        for (Object value : valueAccess.getValues()) {
            System.out.println("value = " + value);
        }
        return Gender.MALE;
    }
}
