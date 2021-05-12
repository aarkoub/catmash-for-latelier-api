package aarkoub.catmash.domain.user;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="\"user\"")
public class User implements Persistable<UUID> {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    public User(){}

    public User(UUID id){
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id==null;
    }

    @Override
    public boolean equals(Object o ){
        if(o==null)
            return false;
        if(o==this)
            return true;
        if(o instanceof User){
            User user = (User) o;
            if(user.getId() == id)
                return true;
            return false;
        }
        return  false;
    }

}
