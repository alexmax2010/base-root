package com.base.repository;

import static com.base.entity.QPersonEntity.personEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.List;
import com.base.common.JPAQueryDslBaseRepository;
import com.base.entity.PersonEntity;
import com.base.vo.PersonVo;
import com.querydsl.jpa.JPQLQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * PersonRepository.
 *
 * @author components on 8/9/2022.
 * @version 1.0
 */
@Lazy
@Repository
public class PersonRepository extends JPAQueryDslBaseRepository<PersonEntity> implements
    IPersonRepository {

    /**
     * Constructor.
     */
    public PersonRepository() {
        super(PersonEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean exist(String documentNumber) {
        JPQLQuery<String> query = from(personEntity)
            .select(personEntity.personId)
            .where(personEntity.documentNumber.eq(documentNumber));
        String response = query.fetchFirst();
        return StringUtils.isNotBlank(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonVo> list() {
        return from(personEntity)
            .select(bean(PersonVo.class,
                personEntity.personId,
                personEntity.firstName,
                personEntity.lastName,
                personEntity.documentNumber,
                personEntity.email,
                personEntity.createDate,
                personEntity.status)).fetch();
    }
}
