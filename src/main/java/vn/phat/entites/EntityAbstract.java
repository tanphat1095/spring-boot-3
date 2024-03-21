package vn.phat.entites;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface EntityAbstract<I extends Serializable, A extends Serializable> {

    I getId();
    void setId(I id);

    void setUpdatedDate(LocalDateTime date);

    void setUpdatedBy(A updatedBy);

    void setCreatedDate(LocalDateTime date);

    void setCreatedBy(A createdBy);

    void setDeletedDate(LocalDateTime date);

    void setDeletedBy(A deletedBy);

    void setDeletedFlag(boolean deletedFlag);

}
