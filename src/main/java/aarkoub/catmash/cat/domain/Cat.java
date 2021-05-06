package aarkoub.catmash.cat.domain;

import javax.persistence.*;

@Entity
@Table(name="CAT")
public class Cat {

    @Id
    @GeneratedValue
    public long id;
    private String pictureUrl;
    private int nbVotes;

    public Cat(long id, String pictureUrl, int nbVotes){
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.nbVotes = nbVotes;
    }

    public Cat(String pictureUrl, int nbVotes){
        this.pictureUrl = pictureUrl;
        this.nbVotes = nbVotes;
    }

    public Cat(){}

    public void setId(long id){
        this.id = id;
    }

    public void setPictureUrl(String pictureUrl){
        this.pictureUrl = pictureUrl;
    }

    public void setNbVotes(int nbVotes){
        this.nbVotes = nbVotes;
    }

    public long getId(){
        return id;
    }

    public String getPictureUrl(){
        return pictureUrl;
    }

    public int getNbVotes(){
        return nbVotes;
    }

    @Override
    public boolean equals(Object o){
        if(o==null) return false;
        if(o==this) return true;
        if(o instanceof Cat){
            Cat c = (Cat) o;
            if(c.id == this.id && c.pictureUrl == this.pictureUrl && c.nbVotes == this.nbVotes)
                return true;
        }
        return false;
    }

}
