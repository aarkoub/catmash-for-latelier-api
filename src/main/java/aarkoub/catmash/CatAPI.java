package aarkoub.catmash;

import aarkoub.catmash.cat.db.importation.CatDataLoader;
import aarkoub.catmash.cat.domain.Cat;
import aarkoub.catmash.cat.domain.service.ICatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@SpringBootApplication
public class CatAPI {

    @Autowired
    ICatService catService;

    @Autowired
    CatDataLoader catDataLoader;

    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "Hello World!";
    }

    @GetMapping("/cats")
    List<Cat> getAllCats() {
        return catService.getAllCats();
    }

    @RequestMapping("/load")
    void loadData() throws IOException {
        catDataLoader.importCatsFromJSONToDatabase("src/main/resources/db/importation/cats.json");
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CatAPI.class, args);
    }

}
