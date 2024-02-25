package vn.phat.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="test")
public class TestEntity implements EntityAbstract<Long, String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String createdBy;
    private String updatedBy;
    private String deletedBy;

    private Date createdDate;
    private Date updatedDate;
    private Date deletedDate;

    private boolean deletedFlag;

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", deletedBy='" + deletedBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", deletedDate=" + deletedDate +
                ", deletedFlag=" + deletedFlag +
                '}';
    }


    public TestEntity(){
        super();
        this.name = UUID.randomUUID().toString();
    }
}
