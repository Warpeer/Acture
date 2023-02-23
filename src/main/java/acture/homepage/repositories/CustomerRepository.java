package acture.homepage.repositories;

import acture.homepage.models.ContactMessage;
import acture.homepage.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JdbcTemplate db;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public CustomerRepository(JdbcTemplate db){
        this.db=db;
    }

    private Logger logger = LoggerFactory.getLogger(MessageRepository.class);

    public boolean saveCustomer(int messageID){
        String sql = "SELECT fullName, email, received, messenger FROM ContactMessage WHERE id=?";
        String sql2 = "INSERT INTO Customer (fullName, email, messenger) VALUES(?,?,?)";
        try{
            ContactMessage messageInfo = db.queryForObject(sql, new BeanPropertyRowMapper<>(ContactMessage.class), messageID);
            if(messageInfo!=null){
                db.update(sql2, messageInfo.getFullName(), messageInfo.getEmail(), messageInfo.getMessenger());
                return true;
            }
            return false;
        }catch(Exception e){
            logger.error("Error in saveCustomer: " + e);
            return false;
        }
    }
    public List<Customer> fetchAllCustomers(){
        String sql = "SELECT * FROM Customer";
        try{
            return db.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        }catch(Exception e){
            logger.error("Error in fetchAllCustomers: " +e);
            return null;
        }
    }
}
