package aarkoub.catmash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class AppAPI {

	@RequestMapping("/")
	@ResponseBody
   	String index() {
      		return "Hello World!";
    	}

	public static void main(String[] args) {

		SpringApplication.run(AppAPI.class, args);
	}

}
