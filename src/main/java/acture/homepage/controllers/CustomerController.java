package acture.homepage.controllers;

import acture.homepage.models.Customer;
import acture.homepage.repositories.CustomerRepository;
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
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepo;

    @PostMapping("/saveCustomer")
    public boolean saveCustomer(int messageID, HttpServletResponse response) throws IOException {
        if(!customerRepo.saveCustomer(messageID)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kunne ikke lagre kunde.");
            return false;
        }
        return true;
    }
    @GetMapping("fetchAllCustomers")
    public List<Customer> fetchAllCustomers(HttpServletResponse response) throws IOException {
        List<Customer> theCustomers = customerRepo.fetchAllCustomers();
        if(theCustomers==null){
            response.sendError(HttpStatus.NOT_FOUND.value(), "Fant ingen aktive kunder");
            return null;
        }
        return theCustomers;
    }
}
