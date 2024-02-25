package vn.phat.entites;

import java.io.Serializable;
import java.util.Date;

public interface EntityAbstract<I extends Serializable, A extends Serializable> {

    I getId();
    void setId(I id);

    void setUpdatedDate(Date date);

    void setUpdatedBy(A updatedBy);

    void setCreatedDate(Date date);

    void setCreatedBy(A createdBy);

    void setDeletedDate(Date date);

    void setDeletedBy(A deletedBy);

    void setDeletedFlag(boolean deletedFlag);
}
