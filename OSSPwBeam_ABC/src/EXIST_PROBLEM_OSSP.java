/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 18/04/13
 * Time: 20:54
 * To change this template use File | Settings | File Templates.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class EXIST_PROBLEM_OSSP extends JFrame {

    public static final int DW = 500;
    public static final int DH = 200;

    private JLabel label;
    private JComboBox combo;
    private static final int DS = 12;

    public EXIST_PROBLEM_OSSP() {
        setTitle("Existing Problem");
        setSize(DW,DH);

        initComponents();

        combo.addItem(" ");
        combo.addItem("tai4x4_1");
        combo.addItem("tai4x4_2");
        combo.addItem("tai4x4_3");
        combo.addItem("tai4x4_4");
        combo.addItem("tai4x4_5");
        combo.addItem("tai4x4_6");
        combo.addItem("tai4x4_7");
        combo.addItem("tai4x4_8");
        combo.addItem("tai4x4_9");
        combo.addItem("tai4x4_10");
        combo.addItem("tai5x5_1");
        combo.addItem("tai5x5_2");
        combo.addItem("tai5x5_3");
        combo.addItem("tai5x5_4");
        combo.addItem("tai5x5_5");
        combo.addItem("tai5x5_6");
        combo.addItem("tai5x5_7");
        combo.addItem("tai5x5_8");
        combo.addItem("tai5x5_9");
        combo.addItem("tai5x5_10");
        combo.addItem("tai7x7_1");
        combo.addItem("tai7x7_2");
        combo.addItem("tai7x7_3");
        combo.addItem("tai7x7_4");
        combo.addItem("tai7x7_5");
        combo.addItem("tai7x7_6");
        combo.addItem("tai7x7_7");
        combo.addItem("tai7x7_8");
        combo.addItem("tai7x7_9");
        combo.addItem("tai7x7_10");
        combo.addItem("tai10x10_1");
        combo.addItem("tai10x10_2");
        combo.addItem("tai10x10_3");
        combo.addItem("tai10x10_4");
        combo.addItem("tai10x10_5");
        combo.addItem("tai10x10_6");
        combo.addItem("tai10x10_7");
        combo.addItem("tai10x10_8");
        combo.addItem("tai10x10_9");
        combo.addItem("tai10x10_10");
        combo.addItem("tai15x15_1");
        combo.addItem("tai15x15_2");
        combo.addItem("tai15x15_3");
        combo.addItem("tai15x15_4");
        combo.addItem("tai15x15_5");
        combo.addItem("tai15x15_6");
        combo.addItem("tai15x15_7");
        combo.addItem("tai15x15_8");
        combo.addItem("tai15x15_9");
        combo.addItem("tai15x15_10");

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                String dataFile = (String)combo.getSelectedItem();
                dataFile += ".txt";
                int jpT[][] = new int[0][];
                int jmS[][] = new int[0][];
                int noj=0;
                int nom=0;
                try {
                    BufferedReader input = new BufferedReader(new FileReader(dataFile));
                    String line;
                    int cn = 0;
                    int k = 0;
                    int flag = 0;

                    while ((line=input.readLine())!= null) {
                        if (cn==1) {
                            String l[] = line.split(" ");
                            int len = l.length;
                            int fl = 0;

                            for(int i=0;i<len;i++) {
                                if(l[i].length()>0 && fl==0) {
                                    noj = Integer.parseInt(l[i]);
                                    fl = 1;
                                }
                                else if(l[i].length()>0 && fl == 1) {
                                    nom = Integer.parseInt(l[i]);
                                    fl = 2;
                                }
                                if(fl==2) break;
                            }
                            jpT = new int[noj][nom];
                            jmS = new int[noj][nom];
                            System.out.println(noj + " " + nom);
                        }
                        else if (cn>=3 && cn<noj+3) {
                            String l[] = line.split(" ",0);
                            int len = l.length;
                            int j = 0;

                            for (int i=0;i<len;i++) {
                                if(l[i].length()>0) {
                                    jpT[k][j] = Integer.parseInt(l[i]);
                                    System.out.print(jpT[k][j] + " ");
                                    if(nom>9 && jpT[k][j]>3) jpT[k][j]-=1;
                                    j++;
                                }
                            }
                            k++;
                        }
                        else if(cn==noj+3) {
                            k=0;
                        }
                        else if(cn>=noj+4) {
                            String l[] = line.split(" ");
                            int len = l.length;
                            int j = 0;

                            for (int i=0;i<len;i++) {
                                //System.out.print(l[i].length() + " " + k);
                                if(l[i].length()>0) {
                                    jmS[k][j] = Integer.parseInt(l[i])-1;
                                    System.out.print(jmS[k][j] + " ");
                                    j++;
                                }
                            }
                            k++;
                        }

                        System.out.println();
                        cn++;

                    }

                } catch (Exception E) {
                    System.err.println("FileInput error");
                }

                new BEAM_ABC_OSSP(noj,nom,jpT,jmS);
            }
        });

    }

    private void initComponents() {

        label = new JLabel();
        combo = new JComboBox();

        label.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        label.setText("Existing Problem");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(label, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)
                                .addComponent(combo,GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(192, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }
}

