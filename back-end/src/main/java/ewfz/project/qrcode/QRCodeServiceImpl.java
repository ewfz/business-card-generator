package ewfz.project.qrcode;


import ewfz.project.entities.User;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class QRCodeServiceImpl {

    public void generate(User user) {

        int width = 250;
        int height = 250;
        String data =  user.toString();


        try (ByteArrayOutputStream bos = QRCode.from(data).withSize(width, height).stream(); ) {


            ByteArrayInputStream inStreambj = new ByteArrayInputStream(bos.toByteArray());
            BufferedImage newImage = ImageIO.read(inStreambj);

            ImageIO.write(newImage, "jpg", new File("/home/business-card-generator/back-end/src/main/resources/qrcodeimage"));


        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}