package com.base.repository;

import java.util.List;
import com.base.common.IQueryDslBaseRepository;
import com.base.entity.PersonEntity;
import com.base.vo.PersonVo;

/**
 * Person repository specification.
 *
 * @author components on 2022/09/08.
 * @version 1.0
 */
public interface IPersonRepository extends IQueryDslBaseRepository<PersonEntity> {


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
     * @return An array of persons
     */
    List<PersonVo> list();

}
