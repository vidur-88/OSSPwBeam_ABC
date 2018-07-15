/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 22/04/13
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Selection extends JFrame {

    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;

    public Selection() {
        initComponents();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                dispose();
                EXIST_PROBLEM_JSSP ep = new EXIST_PROBLEM_JSSP();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int width = (int)screenSize.getWidth();
                int height = (int)screenSize.getHeight();
                int PAD = 100;
                ep.setLocation(width/2-PAD-ep.getWidth()/4,height/2-PAD-ep.getHeight()/2);
                ep.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ep.setVisible(true);
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                dispose();
                EXIST_PROBLEM_OSSP ep = new EXIST_PROBLEM_OSSP();
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

    private void initComponents() {

        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Tahoma", 1, 18));
        jLabel1.setText("Please choose scheduling problem");

        jButton1.setFont(new Font("Tahoma", 0, 14));
        jButton1.setText("JSSP");

        jButton2.setFont(new Font("Tahoma", 0, 14));
        jButton2.setText("OSSP");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(137, 137, 137)
                                                .addComponent(jButton1)
                                                .addGap(94, 94, 94)
                                                .addComponent(jButton2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(73, 73, 73)
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }
}
