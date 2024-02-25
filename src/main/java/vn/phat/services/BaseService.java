package vn.phat.services;

import vn.phat.dto.PageWrapper;
import vn.phat.entites.EntityAbstract;

import java.io.Serializable;

public interface BaseService<I extends Serializable, A extends Serializable ,S, E extends EntityAbstract<I, A>> {
    PageWrapper<E> search(S condition, int page, int pageSize);
    E findOne(I id);

    E save(E en);

    void delete(E en) throws Exception;

    void delete(I id) throws Exception;
}
