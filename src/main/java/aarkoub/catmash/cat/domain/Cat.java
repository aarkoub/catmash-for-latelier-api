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

}
