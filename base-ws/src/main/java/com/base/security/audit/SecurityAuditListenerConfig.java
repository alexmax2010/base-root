package com.base.security.audit;

import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import com.base.common.AbstractBaseAuditable;
import org.hibernate.HibernateException;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * SecurityAuditListenerConfig.
 *
 * @author components on 13/10/2022.
 * @version 1.0
 */

@Component
@Configuration
public class SecurityAuditListenerConfig implements PreInsertEventListener,
    PersistEventListener {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private IKeycloakUserInfo keycloakUserInfo;

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = this.entityManagerFactory.unwrap(
            SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry()
            .getService(EventListenerRegistry.class);
        registry.prependListeners(EventType.PERSIST, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPersist(PersistEvent persistEvent) throws HibernateException {
        if (persistEvent.getObject() instanceof AbstractBaseAuditable) {
            this.loadInsertAuditFields((AbstractBaseAuditable) persistEvent.getObject());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onPreInsert(PreInsertEvent preInsertEvent) {
        if (preInsertEvent.getEntity() instanceof AbstractBaseAuditable) {
            this.loadInsertAuditFields((AbstractBaseAuditable) preInsertEvent.getEntity());
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPersist(PersistEvent event, Map createdAlready) throws HibernateException {
    }

    /**
     * Load insert audit fields.
     *
     * @param audit AbstractBaseAuditable
     */
    private void loadInsertAuditFields(AbstractBaseAuditable audit) {
        audit.setCreatedByUser(this.keycloakUserInfo.getUserId());
        audit.setCreatedFromIp(this.keycloakUserInfo.getIp());
        audit.setCreateDate(new Date());
    }

    /**
     * Load update audit fields.
     *
     * @param audit AbstractBaseAuditable
     */
    private void loadUpdateAuditFields(AbstractBaseAuditable audit) {
        audit.setLastModifiedByUser(this.keycloakUserInfo.getUserId());
        audit.setLastModifiedDate(new Date());
    }
}
