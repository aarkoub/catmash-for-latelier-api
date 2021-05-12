package aarkoub.catmash;

import aarkoub.catmash.db.cat.importation.CatDataLoader;
import aarkoub.catmash.domain.cat.Cat;
import aarkoub.catmash.domain.cat.service.ICatService;
import aarkoub.catmash.domain.catmatch.CatMatch;
import aarkoub.catmash.domain.catmatch.service.ICatMatchService;
import aarkoub.catmash.domain.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@SpringBootApplication
public class API {

    @Autowired
    ICatService catService;

    @Autowired
    IUserService userService;

    @Autowired
    ICatMatchService catMatchService;

    @Autowired
    CatDataLoader catDataLoader;


    public @Nullable UUID getUserId(HttpServletRequest request){
        UUID userId = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("userId")) {
                    userId = UUID.fromString(cookies[i].getValue());
                    break;
                }
            }
        }
        return userId;
    }

    @RequestMapping("/")
    @ResponseBody
    Cookie[] index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return cookies;
    }

    @GetMapping("/cats")
    @ResponseBody
    List<Cat> getAllCats() {
        return catService.getAll();
    }

    @GetMapping("/cats/match")
    @ResponseBody
    CatMatch generateCatMatch(HttpServletRequest request){
        return catMatchService.generateMatch(getUserId(request));
    }

    @PostMapping(path="/cats/match/vote", consumes = "application/json;charset=UTF-8")
    CatMatch voteForCat( HttpServletRequest request, @RequestBody Map<String, Long> catIds){
        try {
           return catMatchService.changeVote(getUserId(request), catIds.get("catId1"), catIds.get("catId2"),
                   catIds.get("catIdVoted"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/user")
    @ResponseBody
    void retrieveUser(HttpServletRequest request, HttpServletResponse response){
        response.addCookie(new Cookie("userId", userService.retrieveUser(getUserId(request)).getId().toString()));
    }

    @RequestMapping("/load")
    void loadData() throws IOException {
        catDataLoader.importCatsFromJSONToDatabase("src/main/resources/db/importation/cats.json");
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(API.class, args);
    }

}
