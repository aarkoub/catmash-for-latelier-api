package aarkoub.catmash.db.cat.importation;

import aarkoub.catmash.db.cat.ICatRepository;
import aarkoub.catmash.domain.cat.Cat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class CatDataLoader {

    @Autowired
    ICatRepository repository;

    public void importCatsFromJSONToDatabase(String filePath) throws IOException {
        System.out.println("starting loading data from json");
        Path p = Paths.get(filePath);
        String jsonString = Files.readString(p.toAbsolutePath());
        JSONObject jo = new JSONObject(jsonString);
        JSONArray ja = jo.getJSONArray("images");
        for(int i=0; i<ja.length(); i++){
            JSONObject jCat = (JSONObject) ja.get(i);
            String url = (String) jCat.get("url");
            repository.addCat(new Cat(url));
        }
        System.out.println("loading data process has finished");
    }

}
