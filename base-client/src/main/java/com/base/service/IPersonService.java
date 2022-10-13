package com.base.service;

import java.util.List;
import com.base.common.IBaseService;
import com.base.entity.PersonEntity;
import com.base.vo.PersonVo;

/**
 * Person services specification.
 *
 * @author components on 2022/09/08.
 * @version 1.0
 */
public interface IPersonService extends IBaseService<PersonEntity> {

    /**
     * Save Person.
     *
     * @param personVo person object
     */
    void save(PersonVo personVo);

    /**
     * Verify existing person.
     *
     * @param documentNumber document number
     * @return Boolean
     */
    Boolean exist(String documentNumber);

    /**
     * Find persons active list.
     *
     * @return List personVo
     */
    List<PersonVo> list();


}
