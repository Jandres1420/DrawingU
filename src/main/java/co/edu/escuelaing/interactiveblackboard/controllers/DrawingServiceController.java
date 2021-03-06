package co.edu.escuelaing.interactiveblackboard.controllers;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.escuelaing.interactiveblackboard.entities.Board;
import co.edu.escuelaing.interactiveblackboard.entities.DrawingU;
import co.edu.escuelaing.interactiveblackboard.entities.User;
@RestController
public class DrawingServiceController {
    @Resource
    private HttpServletRequest request;
    int posicion = 0;
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
       // System.out.println(request.getSession(true).getId());
    }

    @PostMapping("/index")
    public void getName(@RequestParam(value = "name") String name) {
        System.out.println("name + " + name);
        DrawingU.getInstance().getRooms().getBoard().setUsuario(new User(name));
    }
    
    @PostMapping("/aRooms")
    public void addingUserToRoom(@RequestParam(value = "name") String name,@RequestParam(value = "rooms") String rooms) {
        System.out.println("rooms + " + rooms);
        DrawingU.getInstance().getRooms().settingUser(new User(name), rooms);
    }

    @GetMapping("/getWord")
    public String getWord() {
        sessionManagement();
        String word = DrawingU.getInstance().getRooms().getBoard().getWords().getRandomWord();
        DrawingU.getInstance().getRooms().getPuntaje().setPalabraRandom(word);
        return "{\"getWord\":\""
                + word +"\"}";

    }

    @GetMapping("/numeroPersonasBool")
    public ResponseEntity<?> numeroPersonasBool(@RequestParam(value = "key") String key) {
        return new ResponseEntity<>(DrawingU.getInstance().getRooms().numeroMinimoDePersonas(key), HttpStatus.ACCEPTED);
    }

    @GetMapping("/cambioPintor")
    public ResponseEntity<?> cambioPintor(@RequestParam(value = "pintor") String pintor) {
        return new ResponseEntity<>(DrawingU.getInstance().getRooms().getBoard().changingPintor(pintor), HttpStatus.ACCEPTED);
    }
    @GetMapping("/numeroPersonas")
    public ResponseEntity<?> numeroPersonas(@RequestParam(value = "key") String key) {
        return new ResponseEntity<>(DrawingU.getInstance().getRooms().numeroDePersonas(key), HttpStatus.ACCEPTED);
    }    
    
    @GetMapping("/game")
    public ResponseEntity<?> getStatus(@RequestParam (value = "pintor") String pintor ) {
        sessionManagement();
        posicion = DrawingU.getInstance().getRooms().getBoard().getPositionUser(pintor);
        User user = DrawingU.getInstance().getRooms().getBoard().getUsuarios().get(posicion);
        return new ResponseEntity<>(user.getPintor(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/gettingPoints")
    public ResponseEntity<?> getPoints(@RequestParam (value = "name") String name) {
        return new ResponseEntity<>(DrawingU.getInstance().getRooms().getPuntaje().getPointsByUsername(name), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getRoom")
    public String getRoom(@RequestParam(value = "pintor") String pintor) {
        String hola = "";
        return hola;
    }

    

}