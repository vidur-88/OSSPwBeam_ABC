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

public class EXIST_PROBLEM_JSSP extends JFrame {

    public static final int DW = 500;
    public static final int DH = 200;

    private JLabel label;
    private JComboBox combo;
    private static final int DS = 12;

    public EXIST_PROBLEM_JSSP() {
        setTitle("Existing Problem");
        setSize(DW,DH);

        initComponents();

        combo.addItem(" ");
        combo.addItem("abz5");
        combo.addItem("abz6");
        combo.addItem("ft06");
        combo.addItem("ft10");
        combo.addItem("ft20");
        combo.addItem("la01");
        combo.addItem("la02");
        combo.addItem("la03");
        combo.addItem("la04");
        combo.addItem("la05");
        combo.addItem("la06");
        combo.addItem("la07");
        combo.addItem("la08");
        combo.addItem("la09");
        combo.addItem("la10");
        combo.addItem("la12");
        combo.addItem("la13");
        combo.addItem("la14");
        combo.addItem("la15");
        combo.addItem("la16");
        combo.addItem("la17");
        combo.addItem("la18");
        combo.addItem("la19");
        combo.addItem("la20");
        combo.addItem("la21");
        combo.addItem("la22");
        combo.addItem("la23");
        combo.addItem("la24");
        combo.addItem("la25");
        combo.addItem("la26");
        combo.addItem("la27");
        combo.addItem("la28");
        combo.addItem("la29");
        combo.addItem("la30");
        combo.addItem("la31");
        combo.addItem("la32");
        combo.addItem("la33");
        combo.addItem("la34");
        combo.addItem("la35");
        combo.addItem("la36");
        combo.addItem("la37");
        combo.addItem("la38");
        combo.addItem("la39");
        combo.addItem("la40");
        combo.addItem("orb01");
        combo.addItem("orb02");
        combo.addItem("orb03");
        combo.addItem("orb04");
        combo.addItem("orb05");

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                String dataFile = (String)combo.getSelectedItem();
                dataFile += ".txt";
                System.out.println("Vikash " + dataFile);
                int jp[][] = new int[0][];
                int jm[][] = new int[0][];
                int noj=0;
                int nom=0;
                try {
                    BufferedReader input = new BufferedReader(new FileReader(dataFile));
                    //System.out.println("Vikash");

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
                            jp = new int[noj][nom];
                            jm = new int[noj][nom];
                            System.out.println(noj + " " + nom);
                        }
                        else if(k<noj) {
                            String l[] = line.split(" ");
                            int len = l.length;
                            int j=0, jj=0;
                            int fl=0;

                            for (int i=0;i<len;i++) {
                                if(l[i].length()>0 && fl%2==0) {
                                    jm[k][j] = Integer.parseInt(l[i]);
                                    System.out.print(jm[k][j] + " ");
                                    fl++;
                                    j++;
                                }
                                else if(l[i].length()>0) {
                                    jp[k][jj] = Integer.parseInt(l[i]);
                                    System.out.print(jp[k][jj] + " ");
                                    if(nom>9 && noj>9 && jp[k][jj]>20) {
                                        jp[k][jj] -= 6;
                                    }
                                    fl++;
                                    jj++;
                                }
                            }
                            System.out.println();
                            k++;
                        }

                        System.out.println();
                        cn++;
                    }

                } catch (Exception E) {
                    System.err.println("FileInput error");
                }

                new BEAM_ABC_JSSP(noj,nom,jp,jm);
            }
        });

    }

    private void initComponents() {

        label = new JLabel();
        combo = new JComboBox();

        label.setFont(new Font("Tahoma", 0, 14));
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