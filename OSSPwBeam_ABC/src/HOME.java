/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 18/04/13
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HOME extends JFrame {

    public static final int DW = 300;
    public static final int DH = 200;

    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;

    private static final int DS = 12;

    public HOME() {
        setTitle("Home");
        setSize(DW,DH);

        initComponents();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.
                        dispose();
                        Selection ep = new Selection();
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        int width = (int)screenSize.getWidth();
                        int height = (int)screenSize.getHeight();
                        int PAD = 100;
                        ep.setLocation(width/2-PAD-ep.getWidth()/4,height/2-PAD-ep.getHeight()/2);
                        ep.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        ep.setVisible(true);
                    }
                });
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //To change body of implemented methods use File | Settings | File Templates.
                        dispose();
                        /*NEW_PROBLEM np = new NEW_PROBLEM();
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        int width = (int)screenSize.getWidth();
                        int height = (int)screenSize.getHeight();
                        int PAD = 100;
                        np.setLocation(width/2-PAD-np.DW/4,height/2-PAD-np.DH/2);
                        np.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        np.setVisible(true);
                        */
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                //To change body of implemented methods use File | Settings | File Templates.
                                NEW_PROBLEM np = new NEW_PROBLEM();
                                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                int width = (int)screenSize.getWidth();
                                int height = (int)screenSize.getHeight();
                                int PAD = 100;
                                np.setLocation(width/2-PAD-np.DW/4,height/2-PAD-np.DH/2);
                                np.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                np.setVisible(true);
                            }
                        });
                    }
                });
            }
        });
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();

        jLabel1.setFont(new Font("Tahoma", 1, 18));
        jLabel1.setText("    Beam-ABC for solving OSSP");

        jLabel2.setFont(new Font("Tahoma", 0, 14));
        jLabel2.setText("Do you want to use existing problem?");

        jButton1.setText("Yes");

        jButton2.setText("No");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(82, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(35, 35, 35))
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel2,GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(74, 74, 74)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(66, 66, 66)
                                                .addComponent(jButton1)
                                                .addGap(58, 58, 58)
                                                .addComponent(jButton2)
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }
}
