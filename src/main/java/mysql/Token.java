package mysql;

import java.util.UUID;

public class Token {
    public String createToken(){
        String token = String.valueOf(UUID.randomUUID());
        return token;
    }

    public static String uploadToken(){
        String link = "https://sensivity.team/dashboard/login.php?token=";



        return link;
    }
}
