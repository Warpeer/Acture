package acture.homepage.repositories;

import acture.homepage.models.Employee;
import acture.homepage.models.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecurityRepository {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JdbcTemplate db;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public SecurityRepository(JdbcTemplate db){
        this.db=db;
    }

    private Logger logger = LoggerFactory.getLogger(SecurityRepository.class);

    private Security sec = new Security();


    //email=' DROP TABLE Employees
    public int logIn(String email, String pwd){
        String sqlUser = "SELECT * FROM Employee WHERE email = ?";
        try{
            List<Employee> user = db.query(sqlUser, new BeanPropertyRowMapper(Employee.class), email);
            if(user.get(0).getPwd().equals("admin")){
                return user.get(0).getId();
            }
            if(user!=null){
                if(sec.checkPassord(pwd, user.get(0).getPwd())){
                    return user.get(0).getId();
                }
            }
            return -1;
        }catch(Exception e){
            logger.error("Error in logIn (SecRep) " + e);
            return -1;
        }
    }

    public boolean matchPwd(String oldPwd, int id){
        String sql = "SELECT pwd FROM Employee WHERE id=?";
        try {
            String thePwd = db.queryForObject(sql, String.class, id);
            if(thePwd!=null){
                if(thePwd.equals("admin") && oldPwd.equals("admin")){
                    return true;
                }
                return sec.checkPassord(oldPwd, thePwd);
            }
            return false;
        }catch(Exception e){
            logger.error("Passwords didn't match in matchPwd(): " + e);
            return false;
        }
    }

    public int updatePwd(String oldPwd, String newPwd, int id){
        if(matchPwd(oldPwd, id)){
            String sql = "UPDATE Employee SET pwd=? WHERE id=?";
            try {
                db.update(sql, sec.encryptPassword(newPwd), id);
                return 1;
            }catch(Exception e){
                logger.error("Error in updatePwd: " + e);
                return 0;
            }
        }else{
            return -1;
        }
    }

}
