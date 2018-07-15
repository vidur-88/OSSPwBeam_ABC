/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 20/02/13
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */
import java.awt.*;
import javax.swing.*;


public class OSSPwBEAMABC {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                HOME home = new HOME();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int)screenSize.getWidth();
                int height = (int)screenSize.getHeight();
                int PAD = 100;
                home.setLocation(width/2-PAD-home.DW/4,height/2-PAD-home.DH/2);
                home.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                home.setVisible(true);
            }
        });
    }
}
