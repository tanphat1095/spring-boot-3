package vn.phat.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.phat.dto.PageWrapper;
import vn.phat.entites.EntityAbstract;
import vn.phat.services.BaseService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class BaseServiceImpl<I extends Serializable, A extends Serializable, C, E extends EntityAbstract<I, A>, R extends JpaRepository<E, I>>
        implements BaseService<I, A, C, E> {

    abstract R getRepository();

    @Override
    public PageWrapper<E> search(C condition, int page, int pageSize) {
        Page<E> pageData = getPage(condition, page, pageSize);
        return new PageWrapper<>(pageData.getContent(), (int)pageData.getTotalElements());
    }

    abstract Page<E> getPage(C condition, int page, int pageSize);

    @Override
    public E findOne(I id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public E save(E en) {
        updateModifiedInfo(en);
        return getRepository().save(en);
    }

    @Override
    public List<E> saveAll(Iterable<E> ens) {
        ens.forEach(this::updateModifiedInfo);
        return getRepository().saveAll(ens);
    }

    void updateModifiedInfo(E en){
        I id = en.getId();
        Date now = new Date();
        A author = getAuthor();
        if(id == null){
            en.setCreatedDate(now);
            en.setCreatedBy(author);
        }
        else{
            en.setUpdatedBy(author);
            en.setUpdatedDate(now);
        }

    }

    @Override
    public void delete(E en) throws Exception {
        I id = en.getId();
        if(id == null) throw new NullPointerException("Id not null");
        delete(id);
    }

    @Override
    public void delete(I id) throws Exception {
        E en = getRepository().findById(id).orElse(null);
        if(en == null) throw new NullPointerException("Cannot find entity");
        en.setDeletedBy(getAuthor());
        en.setDeletedFlag(true);
        en.setDeletedDate(new Date());
        getRepository().save(en);
    }

    abstract A getAuthor();
}
