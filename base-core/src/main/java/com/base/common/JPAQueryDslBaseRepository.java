package com.base.common;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Repository base with support querydsl.
 *
 * @param <T> Entity
 * @author components on 2022/09/26
 * @version 1.0
 */
public abstract class JPAQueryDslBaseRepository<T> extends QuerydslRepositorySupport
    implements IQueryDslBaseRepository<T> {

    /**
     * Entity class
     */
    private transient final Class<T> domainClass;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     * @author components on 2022/09/26
     */
    public JPAQueryDslBaseRepository(Class<T> domainClass) {
        super(domainClass);
        this.domainClass = domainClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(T obj) {
        getEntityManager().persist(obj);
    }

    /**
     * Find page by query.
     *
     * @param query JPQLQuery
     * @param pageable Pageable
     * @param count boolean
     * @param <Q> entity
     * @return Page
     * @author components on 2022/09/26
     */
    protected <Q> Page<Q> findPagedData(JPQLQuery<Q> query, Pageable pageable, boolean count) {
        long totalSupplier = count ? query.fetchCount() : 0L;
        return new PageImpl<>(getQuerydsl().applyPagination(pageable, query).fetch(), pageable,
            totalSupplier);
    }

}
