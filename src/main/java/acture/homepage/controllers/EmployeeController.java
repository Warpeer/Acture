package acture.homepage.controllers;

import acture.homepage.models.Employee;
import acture.homepage.models.FileHandler;
import acture.homepage.repositories.EmployeeRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private HttpSession session;
    private final FileHandler fileHandler = new FileHandler();
    @Transactional
    @PostMapping("/saveEmployee")
    public void saveEmployee(Employee employee, HttpServletResponse response) throws IOException {
        MultipartFile file = fileHandler.readLocalFile("src/main/resources/static/images/profile_pictures/defaultpfp.jpg");
        if(!employeeRepo.saveEmployee(employee, file)){
            response.sendError(HttpStatus.FORBIDDEN.value(), "Inputfeil");
        }
    }
    @GetMapping("/fetchAllEmployees")
    public List<Employee> fetchEmployees(HttpServletResponse response) throws IOException {
        List<Employee> allEmployees = employeeRepo.fetchAllEmployees();
        if(allEmployees==null){
            response.sendError(HttpStatus.NOT_FOUND.value(), "Fant ingen ansatte.");
        }
        return allEmployees;
    }
    @GetMapping("/fetchOneEmployee")
    public Employee fetchOneEmployee(HttpServletResponse response) throws IOException{
        Employee theEmployee = employeeRepo.fetchOneEmployee((int)session.getAttribute("id"));
        if(theEmployee==null){
            response.sendError(HttpStatus.NOT_FOUND.value(), "Fant ikke den ansatte.");
        }
        return theEmployee;
    }
    @PostMapping("/updateInfo")
    public boolean updateInfo(Employee employee, HttpServletResponse response) throws IOException{
        if(!employeeRepo.updateInfo(employee, (int) session.getAttribute("id"))){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kunne ikke oppdatere info");
            return false;
        }
        return true;
    }
    @GetMapping("/getPfp")
    public ResponseEntity<byte[]> getPfp(HttpServletResponse response) throws IOException{
        byte[] imageData = employeeRepo.getPfpByEmployeeId((int)session.getAttribute("id"));
        if(imageData==null){
            response.sendError(HttpStatus.NOT_FOUND.value(), "Kunne ikke finne bildet.");
            return null;
        }else{
            String fileType = employeeRepo.getPfpFileType((int)session.getAttribute("id"));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(fileType));
            headers.setContentLength(imageData.length);
            headers.setContentDisposition(ContentDisposition.builder("inline").build());
            ResponseEntity<byte[]> imageResponse = new ResponseEntity<>(imageData, headers, HttpStatus.OK);
            return imageResponse;
        }
    }
    @PostMapping("/savePfp")
    public boolean uploadPfp(MultipartFile file, HttpServletResponse response) throws IOException{
        try{
            return employeeRepo.savePFP(file, (int)session.getAttribute("id"));
        }catch(Exception e){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kunne ikke lagre bildet.");
            return false;
        }
    }
    @PostMapping("/updatePFP")
    public boolean updatePfp(MultipartFile file, HttpServletResponse response) throws IOException{
        try{
            return employeeRepo.updatePFP(file, (int) session.getAttribute("id"));
        }catch(Exception e){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kunne ikke oppdatere profilbildet.");
            return false;
        }
    }
}
