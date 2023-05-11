package com.hayelny.core.diagnosis;

import com.hayelny.core.patient.Gender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.EmbeddableInstantiator;
import org.hibernate.metamodel.spi.ValueAccess;

public class JudgementInstantiator implements EmbeddableInstantiator {
    Logger log = LogManager.getLogger(this.getClass()
            .getName());

    @Override
    public Object instantiate(ValueAccess valueAccess, SessionFactoryImplementor sessionFactory) {
        return Judgement.valueOf(valueAccess.getValues()[0].toString());
    }

    @Override
    public boolean isInstance(Object object, SessionFactoryImplementor sessionFactory) {
        return object instanceof Gender;
    }

    @Override
    public boolean isSameClass(Object object, SessionFactoryImplementor sessionFactory) {
        return object.getClass()
                .equals(Gender.class);
    }
}
