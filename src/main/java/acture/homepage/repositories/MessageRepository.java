package acture.homepage.repositories;
import acture.homepage.models.ContactMessage;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepository {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JdbcTemplate db;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public MessageRepository(JdbcTemplate db){
        this.db=db;
    }

    private Logger logger = LoggerFactory.getLogger(MessageRepository.class);

    public boolean saveMessage(ContactMessage message){
        String sql = "INSERT INTO ContactMessage (fullName, email, message) VALUES(?,?,?)";
        try{
            db.update(sql, message.getFullName(), message.getEmail(), message.getMessage());
            return true;
        }catch(Exception error){
            logger.error("Error in saveMessage: " + error);
        }
        return false;
    }

    public List<ContactMessage> fetchAllUnhandledMessages(){
        String sql = "SELECT id, fullName, email, message, received, messageStatus FROM ContactMessage WHERE messageStatus='unhandled' ORDER BY received ASC";
        try{
            return db.query(sql, new BeanPropertyRowMapper<>(ContactMessage.class));
        }catch(Exception error){
            logger.error("Error in FetchAllUnhandledMessages: " +error);
            return null;
        }
    }
    public boolean updateMessageStatus(String status, int eID, int id){
        String sql = "UPDATE ContactMessage SET messageStatus=?, messenger=? WHERE id=?";
        try{
            db.update(sql, status, eID, id);
            return true;
        }catch(Exception e){
            logger.error("Error in updateMessageStatus: " + e);
            return false;
        }
    }
    public List<ContactMessage> fetchAllActiveMessages(int employeeID){
        String sql = "SELECT id, fullName, email, message, received, messageStatus FROM ContactMessage WHERE messageStatus='active' AND messenger=? ORDER BY received ASC";
        try{
            return db.query(sql, new BeanPropertyRowMapper<>(ContactMessage.class), employeeID);
        }catch(Exception e){
            logger.error("Error in fetchAllActiveMessages: " + e);
            return null;
        }
    }
    public List<ContactMessage> fetchAllProcessedMessages(){
        String sql = "SELECT id, fullName, email, message, received, messageStatus FROM ContactMessage WHERE messageStatus='processed' ORDER BY received ASC";
        try{
            return db.query(sql, new BeanPropertyRowMapper<>(ContactMessage.class));
        }catch(Exception e){
            logger.error("Error in fetchAllProcessedMessages: " + e);
            return null;
        }
    }
}
