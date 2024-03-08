package vn.phat.services;

import vn.phat.dto.BaseCondition;
import vn.phat.dto.PageWrapper;
import vn.phat.entites.EntityAbstract;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author phatle
 * @since 09/03/2024
 */
public interface BaseService<U extends Enum<U> ,I extends Serializable, A extends Serializable ,C extends BaseCondition<U>, E extends EntityAbstract<I, A>> {
    PageWrapper<E> search(C condition, int page, int pageSize);
    E findOne(I id);

    E save(E en);

    List<E> saveAll(Iterable<E> ens);

    void delete(E en) throws Exception;

    void delete(I id) throws Exception;
}
