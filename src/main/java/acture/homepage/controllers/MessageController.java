package acture.homepage.controllers;


import acture.homepage.models.ContactMessage;
import acture.homepage.repositories.MessageRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



import java.io.IOException;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private HttpSession session;

    @PostMapping("/saveMessage")
    public void saveMessage(ContactMessage message, HttpServletResponse response) throws IOException {
        if(!messageRepo.saveMessage(message)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i database");
        }
    }
    @GetMapping("/fetchAllUnhandledMessages")
    public List<ContactMessage> fetchAllUnhandledMessages(HttpServletResponse response) throws IOException {
        List<ContactMessage> allMessages = messageRepo.fetchAllUnhandledMessages();
        if(allMessages==null){
            response.sendError(HttpStatus.NOT_FOUND.value(), "Fant ingen meldinger");
        }
        return allMessages;
    }
    @GetMapping("/fetchAllActiveMessages")
    public List<ContactMessage> fetchAllActiveMessages(HttpServletResponse response) throws IOException{
        List<ContactMessage> allMessages = messageRepo.fetchAllActiveMessages((int)session.getAttribute("id"));
        if(allMessages==null){
            response.sendError(HttpStatus.NOT_FOUND.value(), "Ingen aktive dialoger.");
        }
        return allMessages;
    }
    @PostMapping("/updateMessageStatus")
    public boolean updateMessageStatus(String status, int id, HttpServletResponse response) throws IOException{
        if(!messageRepo.updateMessageStatus(status, (int)session.getAttribute("id"), id)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kunne ikke oppdatere meldingsstatus.");
            return false;
        }
        return true;
    }
    @GetMapping("/fetchProcessedMessages")
    public List<ContactMessage> fetchAllProcessedMessages(HttpServletResponse response) throws IOException{
        List<ContactMessage> allMessages = messageRepo.fetchAllProcessedMessages();
        if(allMessages==null){
            response.sendError(HttpStatus.NOT_FOUND.value(), "Ingen arkiverte meldinger");
        }
        return allMessages;
    }
}
