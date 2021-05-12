package aarkoub.catmash.domain.catmatch;

import aarkoub.catmash.domain.cat.Cat;
import aarkoub.catmash.domain.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "CAT_MATCH")
public class CatMatch {

    @EmbeddedId
    private CatMatchPK primaryKey;

    @MapsId("userId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("catId1")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_id_1")
    private Cat cat1;

    @MapsId("catId2")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_id_2")
    private Cat cat2;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_id_voted")
    private Cat catVoted;

    public CatMatch() {
    }

    public CatMatch(User user, Cat cat1, Cat cat2) {
        this(user, cat1, cat2, null);
    }

    public CatMatch(User user, Cat cat1, Cat cat2, Cat catVoted) {
        this.user = user;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.catVoted = catVoted;
        this.primaryKey = new CatMatchPK();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCat1(Cat cat1) {
        this.cat1 = cat1;
    }

    public void setCat2(Cat cat2) {
        this.cat2 = cat2;
    }

    public void setCatVoted(Cat catVoted) {
        this.catVoted = catVoted;
    }

    public User getUser() {
        return user;
    }

    public Cat getCat1() {
        return cat1;
    }

    public Cat getCat2() {
        return cat2;
    }

    public Cat getCatVoted() {
        return catVoted;
    }

    @Embeddable
    public static class CatMatchPK implements Serializable {

        private UUID userId;
        private long catId1;
        private long catId2;

        public CatMatchPK() {
        }

        public CatMatchPK(UUID userId, long catId1, long catId2) {
            this.userId = userId;
            this.catId1 = catId1;
            this.catId2 = catId2;
        }


        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public void setCatId1(long catId1) {
            this.catId1 = catId1;
        }

        public void setCatId2(long catId2) {
            this.catId2 = catId2;
        }

        public UUID getUserId() {
            return userId;
        }

        public long getCatId1() {
            return catId1;
        }

        public long getCatId2() {
            return catId2;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o instanceof CatMatch) {
            CatMatch cm = (CatMatch) o;
            if (cm.getUser() == user &&
                    cm.getCat1() == cat1 &&
                    cm.getCat2() == cat2) {
                if( (cm.getCatVoted()==null && catVoted==null) ||
                        (cm.getCatVoted().getId() == catVoted.getId()))
                    return true;
            }
        }
        return false;
    }

}
