package ftp;

import funktionen.GenerateToken;
import geheim.Passwort;
import mysql.games.KartenUpload;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadFile {

    public static void uploadFoto(String id, int gameid){
        FTPClient ftpClient = new FTPClient();

        try {

            ftpClient.connect("45.88.108.231", 21);
            ftpClient.login("u8146-785", Passwort.getDatabasePassword());
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File LocalFile = new File(id + ".png");

            String token = GenerateToken.createToken();

            String remoteFile = "/sensivity.michel929.de/webpanel/assets/images/karten/fertig/" + token + ".png";

            KartenUpload.upload(id, "https://sensivity.michel929.de/webpanel/assets/images/karten/fertig/" + token + ".png", gameid);

            InputStream inputStream = new FileInputStream(LocalFile);

            ftpClient.storeFile(remoteFile, inputStream);
            inputStream.close();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();

        } finally {

            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
