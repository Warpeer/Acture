package acture.homepage.models;

import org.mindrot.jbcrypt.BCrypt;

public class Security {
    public String encryptPassword(String pwd){
        return BCrypt.hashpw(pwd, BCrypt.gensalt(6));
    }

    public boolean checkPassord(String pwd, String hashPwd){
        return BCrypt.checkpw(pwd,hashPwd);
    }
}
