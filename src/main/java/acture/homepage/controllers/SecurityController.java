package acture.homepage.controllers;

import acture.homepage.repositories.SecurityRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SecurityController {
    @Autowired
    private HttpSession session;
    @Autowired
    private SecurityRepository securityRepo;

    @GetMapping("/authenticate")
    public boolean autheticate(){
        if(session.getAttribute("loggedIn")==null){
            session.setAttribute("loggedIn", false);
        }
        if(session.getAttribute("id")==null){
            session.setAttribute("id", -1);
        }
        return (boolean) session.getAttribute("loggedIn") && !(session.getAttribute("id").equals(-1));
    }
    @GetMapping("/getID")
    public int getId(){
        if((boolean) session.getAttribute("loggedIn")){
            return (int) session.getAttribute("id");
        }
        return -1;
    }

    @GetMapping("/logIn")
    public boolean logIn(String email, String pwd){
        int id = securityRepo.logIn(email, pwd);
        if(id!=-1){
            session.setAttribute("loggedIn", true);
            session.setAttribute("id", id);
            return true;
        }
        return false;
    }
    @GetMapping("/logOut")
    public void logOut(){
        session.setAttribute("loggedIn", false);
        session.setAttribute("id", -1);
    }

    //1=ok, 0=server error, -1=gammelt passord feil
    @GetMapping("/updatePwd")
    public int updatePwd(String oldPwd, String newPwd, HttpServletResponse response) throws IOException {
        switch (securityRepo.updatePwd(oldPwd, newPwd, (int) session.getAttribute("id"))){
            case 1:
                return 1;
            case 0:
                response.sendError(HttpStatus.FORBIDDEN.value(), "Databasefeil, prøv igjen senere.");
                return 0;
            case -1:
                response.sendError(HttpStatus.FORBIDDEN.value(), "Gammelt passord stemmer ikke!");
                return -1;
        }
        return 0;
    }
}
