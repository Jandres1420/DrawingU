package co.edu.escuelaing.interactiveblackboard.controllers;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.escuelaing.interactiveblackboard.entities.Board;
import co.edu.escuelaing.interactiveblackboard.entities.User;
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

    @PostMapping("/index")
    public void getName(@RequestParam(value = "name") String name) {
        System.out.println("name + " + name);
        Board.getInstance().setUsuario(new User(name));
    }

    @GetMapping("/getWord")
    public String getWord() {
        sessionManagement();
        String name = (String) request.getSession().getAttribute("name");
        return "{\"getWord\":\""
                + Board.getInstance().getWords().getRandomWord() +"\"}";

    }

    @GetMapping("/game")
    public String getStatus(@RequestParam (value = "pintor") String pintor ) {
        sessionManagement();
        int posicion = Board.getInstance().getPositionUser(pintor);
        User user = Board.getInstance().getUsuarios().get(posicion);
        return ("El usuario " + user.getName() + " es un " +  user.getPintor());
    }
}