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

class NEW_PROBLEM extends JFrame {

    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JMenuBar jMenuBar1;
    private JTextField jTextField1;
    private JTextField jTextField2;

    public final static int DW = 200;
    public final static int DH = 300;

    private int noj;
    private int nom;

    String job;
    String mach;

    public NEW_PROBLEM() {
        initComponents();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                int ver = verify();
                if(ver==1) {
                    dispose();
                    NEW_MATRIX nm = new NEW_MATRIX(noj,nom);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = (int)screenSize.getWidth();
                    int height = (int)screenSize.getHeight();
                    int PAD = 100;

                    nm.setLocation(width/2-PAD-nm.getWidth()/4,height/2-PAD-nm.getHeight()/2);
                    nm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    nm.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Some fields are blank","Message",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    int verify() {
        try {
            job = jTextField1.getText();
            mach = jTextField2.getText();
            noj = Integer.parseInt(job);
            nom = Integer.parseInt(mach);
            if(noj>0 && nom>0) return 1;
            else return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jButton1 = new JButton();
        jTextField1 = new JTextField(null);
        jTextField2 = new JTextField(null);
        jMenuBar1 = new JMenuBar();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Number of Jobs :");

        jLabel2.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Number of Machines :");

        jButton1.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("next");
        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                                .addGap(50, 50, 50)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                                        .addComponent(jTextField2)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(134, 134, 134)
                                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(36, 36, 36)
                                                .addComponent(jLabel2))
                                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(55, 55, 55))
        );

        pack();
    }


    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NEW_PROBLEM().setVisible(true);
            }
        });
    }*/

}
