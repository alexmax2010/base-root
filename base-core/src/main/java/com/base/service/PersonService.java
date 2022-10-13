package com.base.service;

import java.util.List;
import com.base.common.BaseService;
import com.base.entity.PersonEntity;
import com.base.repository.IPersonRepository;
import com.base.util.ProjectUtil;
import com.base.vo.PersonVo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PersonService.
 *
 * @author components on 8/9/2022.
 * @version 1.0
 */
@Transactional(readOnly = true)
@Lazy
@Service
public class PersonService extends BaseService<PersonEntity, IPersonRepository> implements
    IPersonService {

    /**
     * Constructor.
     *
     * @param repository IPersonRepository
     */
    public PersonService(IPersonRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(PersonVo personVo) {
        this.repository.updateValues(personVo);


        PersonEntity personEntity = ProjectUtil.convert(personVo, PersonEntity.class);
        personEntity.setCompanyCode(1);
        this.repository.save(personEntity);
        personVo.setPersonId(personEntity.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean exist(String documentNumber) {
        return this.repository.exist(documentNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonVo> list() {
        return this.repository.list();
    }
}
