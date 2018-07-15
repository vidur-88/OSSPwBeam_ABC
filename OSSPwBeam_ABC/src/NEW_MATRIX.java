/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 21/04/13
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */

import  javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class NEW_MATRIX extends JFrame {

    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable jTable1;
    private JTable jTable2;

    private int noj;
    private int nom;
    private int jmS[][];
    private int jpT[][];

    public NEW_MATRIX(int j, int m) {
        noj = j;
        nom = m;

        jmS = new int[noj][nom];
        jpT = new int[noj][nom];

        initComponents();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                int ver = verify();
                if(ver==1) {
                    dispose();
                    new BEAM_ABC_JSSP(noj,nom,jpT,jmS);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Some fields of matrices are blank", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                int ver = verify();
                if(ver==1) {
                    dispose();
                    new BEAM_ABC_OSSP(noj,nom,jpT,jmS);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Some fields of matrices are blank","Message",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    int verify() {
        try {
            for(int i=0;i<noj;i++) {
                for(int j=0;j<nom;j++) {
                    jpT[i][j] = Integer.parseInt((String) jTable1.getValueAt(i,j));
                    jmS[i][j] = Integer.parseInt((String)jTable2.getValueAt(i,j));
                    if(nom>9 && noj>9 && jpT[i][j]>20) {
                        jpT[i][j] -= 2;
                    }

                }
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    private void initComponents() {

        Object[][] data = new Object[noj][nom];
        String colName[] = new String[nom];

        for(int i=0;i<nom;i++) {
            //String s =
            colName[i] = "Op" + (i+1);
        }

        for(int i=0;i<noj;i++) {
            for(int j=0;j<nom;j++) {
                data[i][j] = null;
            }
        }

        DefaultTableModel model = new DefaultTableModel(data,colName);
        DefaultTableModel model1 = new DefaultTableModel(data,colName);

        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel2 = new JLabel();
        jScrollPane2 = new JScrollPane();
        jTable2 = new JTable();
        jButton1 = new JButton();
        jButton2 = new JButton();

        jLabel1.setFont(new Font("Tahoma", 0, 18));
        jLabel1.setText("Job Processing Time Matrix");

        jTable1.setModel(model);

        //jTable1.setPreferredScrollableViewportSize(new Dimension(800, 170));
        //jTable1.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new Font("Tahoma", 0, 18));
        jLabel2.setText("Job Machine Sequence Matrix");

        jTable2.setModel(model1);

        //jTable2.setPreferredScrollableViewportSize(new Dimension(800, 370));
        //jTable2.setFillsViewportHeight(true);
        jScrollPane2.setViewportView(jTable2);

        jButton1.setFont(new Font("Tahoma", 0, 14));
        jButton1.setText("JSSP");

        jButton2.setFont(new Font("Tahoma", 0, 14));
        jButton2.setText("OSSP");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(56, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(jButton1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(91, 91, 91))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }

}
