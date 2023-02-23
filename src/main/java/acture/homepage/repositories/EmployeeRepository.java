package acture.homepage.repositories;

import acture.homepage.models.Employee;
import acture.homepage.models.EmployeePFP;
import acture.homepage.models.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JdbcTemplate db;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public EmployeeRepository(JdbcTemplate db){
        this.db=db;
    }
    private Security sec = new Security();

    private Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);

    public boolean saveEmployee(Employee employee, MultipartFile file){
        String sql = "INSERT INTO Employee (email, firstName, lastName, pwd) VALUES(?,?,?,?)";
        String sql2 = "INSERT INTO EmployeePFP (image_data, file_type, eId) VALUES(?,?,?)";
        KeyHolder eId = new GeneratedKeyHolder();
        try{
            db.update(con -> {
                PreparedStatement par = con.prepareStatement(sql, new String[]{"id"});
                par.setString(1,employee.getEmail());
                par.setString(2,employee.getFirstName());
                par.setString(3,employee.getLastName());
                par.setString(4, sec.encryptPassword(employee.getPwd()));
                return par;
            }, eId);
            int employeeID = eId.getKey().intValue();

            byte[] imageData = file.getBytes();
            String fileType = file.getContentType();

            Blob blob = new SerialBlob(imageData);
            db.update(sql2, blob, fileType, employeeID);
            return true;
        }catch(Exception e){
            logger.error("Error in saveEmployee: " + e);
            return false;
        }
    }
    public List<Employee> fetchAllEmployees(){
        String sql = "SELECT id, email, firstName, lastName, hired FROM Employee ORDER BY hired asc";
        try{
            return db.query(sql, new BeanPropertyRowMapper<>(Employee.class));
        }catch(Exception error){
            logger.error("Error in FetchAllMessages: " +error);
            return null;
        }
    }

    public Employee fetchOneEmployee(int id){
        String sql = "SELECT * FROM Employee WHERE id=?";
        try{
            List<Employee> oneEmployee = db.query(sql, new BeanPropertyRowMapper(Employee.class), id);
            return oneEmployee.get(0);
        }catch(Exception error){
            logger.error("Error in fetchOneEmployee: " +error);
            return null;
        }
    }
    public boolean updateInfo(Employee theEmployee, int id){
        String sql= "UPDATE Employee SET email=?, firstName=?, lastName=? WHERE id=?";
        try{
            db.update(sql, theEmployee.getEmail(), theEmployee.getFirstName(), theEmployee.getLastName(), id);
            return true;
        }catch(Exception error){
            logger.error("Error in updateInfo: " +error);
            return false;
        }
    }
    public boolean savePFP(MultipartFile file, int employeeID) throws IOException, SQLException {
        String sql = "INSERT INTO EmployeePFP (image_data, file_type, eId) VALUES(?,?,?)";
        byte[] imageData = file.getBytes();
        String fileType = file.getContentType();

        Blob blob = new SerialBlob(imageData);
        try{
            db.update(sql, blob, fileType, employeeID);
            return true;
        }catch(Exception e){
            logger.error("Error in savePFP: " +e);
            return false;
        }
    }
    public boolean updatePFP(MultipartFile file, int employeeID) throws IOException, SQLException {
        String sql = "UPDATE EmployeePFP SET image_data=?, file_type=? WHERE eId=?";
        try{
            byte[] imageData = file.getBytes();
            String fileType = file.getContentType();

            Blob blob = new SerialBlob(imageData);
            db.update(sql, blob, fileType, employeeID);
            return true;
        }catch(Exception e){
            logger.error("Error in updatePFP: " + e);
            return false;
        }
    }
    public byte[] getPfpByEmployeeId(int employeeID){
        String sql = "Select image_data FROM EmployeePFP WHERE eId=?";
        try{
            byte[] theData = db.queryForObject(sql, byte[].class, employeeID);
            return theData;
        }catch(Exception e){
            logger.error("Couldn't find image data by the given eId: " + e);
            return null;
        }
    }
    public String getPfpFileType(int employeeID){
        String sql = "SELECT file_type FROM EmployeePFP WHERE eId=?";
        try{
            String theData = db.queryForObject(sql, String.class, employeeID);
            return theData;
        }catch (Exception e){
            logger.error("Couldn't get the file_type: " + e);
            return null;
        }
    }
}
