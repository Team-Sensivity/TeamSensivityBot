package funktionen;

import ftp.UploadFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bilder {

    public static void createImage(List<String> image, String id, int gameid) throws IOException {

        List<BufferedImage> bufferedImages = new ArrayList<>();
        int width = 0, weite = 0, max = 0;

        for(int i = 0; i < image.size(); i++) {
            bufferedImages.add(ImageIO.read(new URL(image.get(i))));
        }

        for (int x = 0; x < bufferedImages.size(); x++){
            width = width + bufferedImages.get(x).getWidth();

            if(max < bufferedImages.get(x).getHeight()){
                max = bufferedImages.get(x).getHeight();
            }
        }

    	BufferedImage combinedImage = new BufferedImage(width, max, BufferedImage.TYPE_INT_ARGB);

    	Graphics2D g = combinedImage.createGraphics();

        for (int y = 0; y < bufferedImages.size(); y++) {
            if(y == 0){
                g.drawImage(bufferedImages.get(y), 0,0, null);
                weite = bufferedImages.get(y).getWidth();
            }else{
                weite = weite - 180;
                g.drawImage(bufferedImages.get(y), weite, 0, null);
                int k = y - 1;
                weite = weite + bufferedImages.get(k).getWidth();
            }
        }

        g.dispose();

       ImageIO.write(combinedImage, "PNG", new File(id +  ".png"));

        UploadFile.uploadFoto(id, gameid);

        File last = new File(id + ".png");
        last.delete();
    }

    public static void createAktivImage(List<String> image, String id, int gameid, int aktiv) throws IOException {

        List<BufferedImage> bufferedImages = new ArrayList<>();
        int width = 0, weite = 0, max = 0, hight = 0;

        for(int i = 0; i < image.size(); i++) {
            bufferedImages.add(ImageIO.read(new URL(image.get(i))));
        }

        for (int x = 0; x < bufferedImages.size(); x++){
            width = width + bufferedImages.get(x).getWidth();
            hight = bufferedImages.get(x).getHeight() / 10;

            if(max < bufferedImages.get(x).getHeight()){
                max = bufferedImages.get(x).getHeight();
            }
        }

        BufferedImage combinedImage = new BufferedImage(width, max, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = combinedImage.createGraphics();

        for (int y = 0; y < bufferedImages.size(); y++) {
            if(y == 0 && aktiv == y){
                g.drawImage(bufferedImages.get(y), 0,0, null);
                weite = bufferedImages.get(y).getWidth();
            }else if(y == 0){
                g.drawImage(bufferedImages.get(y), 0,hight, null);
                weite = bufferedImages.get(y).getWidth();
            }else if(aktiv == y) {
                weite = weite - 180;
                g.drawImage(bufferedImages.get(y), weite, 0, null);
                int k = y - 1;
                weite = weite + bufferedImages.get(k).getWidth();
            }else {
                weite = weite - 180;
                g.drawImage(bufferedImages.get(y), weite, hight, null);
                int k = y - 1;
                weite = weite + bufferedImages.get(k).getWidth();
            }
        }

        g.dispose();

        ImageIO.write(combinedImage, "PNG", new File(id +  ".png"));

        UploadFile.uploadFoto(id, gameid);

        File last = new File(id + ".png");
        last.delete();
    }
}
