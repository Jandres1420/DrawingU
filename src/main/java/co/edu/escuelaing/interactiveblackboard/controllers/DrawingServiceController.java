package co.edu.escuelaing.interactiveblackboard.controllers;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import co.edu.escuelaing.interactiveblackboard.configurator.entities.Board;
import co.edu.escuelaing.interactiveblackboard.configurator.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DrawingServiceController {
    @Resource
    private HttpServletRequest request;

    @GetMapping("/status")
    public String status() {
        sessionManagement();
        String name = (String) request.getSession().getAttribute("name");
        return "{\"status\":\"Greetings from Spring Boot "
                + name + ", "
                + java.time.LocalDate.now() + ", "
                + java.time.LocalTime.now()
                + ". " + "The server is Runnig!\"}";
    }

    public void sessionManagement() {
        System.out.println(request.getSession(true).getId());
    }

    @GetMapping("/index")
    public void getName(@RequestParam(value = "name", defaultValue = "Anónimo") String name) {
        System.out.println("name + " + name);
        Board.getInstance().setUsuario(new User(name));
        System.out.println("ENTRE ENTRE ENTRE");
        System.out.println("ENTRE ENTRE ENTRE");
        System.out.println("ENTRE ENTRE ENTRE");
    }

    @GetMapping("/getWord")
    public String getWord() {
        sessionManagement();
        String name = (String) request.getSession().getAttribute("name");
        return "{\"getWord\":\"Greetings from Spring Boot "
                + Board.getInstance().getWords().getRandomWord() + ", "
                + java.time.LocalDate.now() + ", "
                + java.time.LocalTime.now()
                + ". " + "The server is Runnig!\"}";

    }
}