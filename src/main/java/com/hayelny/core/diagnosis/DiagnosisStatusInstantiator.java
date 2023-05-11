package com.hayelny.core.diagnosis;

import com.hayelny.core.patient.Gender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.EmbeddableInstantiator;
import org.hibernate.metamodel.spi.ValueAccess;

public class DiagnosisStatusInstantiator implements EmbeddableInstantiator {
    Logger log = LogManager.getLogger(this.getClass()
            .getName());

    public boolean isInstance(Object object, SessionFactoryImplementor sessionFactory) {
        return object instanceof Gender;
    }

    public boolean isSameClass(Object object, SessionFactoryImplementor sessionFactory) {
        return object.getClass()
                .equals(Gender.class);
    }

    @Override
    public Object instantiate(ValueAccess valueAccess, SessionFactoryImplementor sessionFactory) {
        return DiagnosisStatus.valueOf(valueAccess.getValues()[0].toString());
    }
}
