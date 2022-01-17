package funktionen;

import java.util.UUID;

public class GenerateToken {

    public static String createToken(){
        return UUID.randomUUID().toString();
    }
}
