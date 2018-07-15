import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 18/02/13
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */

public class OSSP {

    int minMakespanT;
    int startT[][];
    int endT[][];
    int machine[][];

    public OSSP(int sol[], int noj, int nom, int jpT[][], int jmS[][]) {
        startT = new int[nom][noj];
        endT = new int[nom][noj];
        machine = new int[nom][noj];

        int maxMakespan = 0;
        int minMakespan = 0;
        int d = noj*nom;

        for(int i=0;i<noj;i++) {
            for(int j=0;j<nom;j++) {
                machine[jmS[i][j]][i] = jpT[i][j];
            }
        }

        for(int i=0;i<noj;i++) {
            for(int j=0;j<nom;j++) {
                maxMakespan += jpT[i][j];
            }
        }

        for(int i=0;i<noj;i++) {
            int ms = 0;
            for(int j=0;j<nom;j++) {
                ms += jpT[i][j];
            }

            minMakespan = Math.max(ms,minMakespan);
        }

        for(int i=0;i<nom;i++) {
            int ms = 0;
            for(int j=0;j<noj;j++) {
                ms += machine[i][j];
            }

            minMakespan = Math.max(ms,minMakespan);
        }

        //minMakespan = 0;

        int maxIter;

        switch (noj) {
            case 3:
                maxIter = 5;
                break;
            case 4:
                maxIter = 25;
                break;
            case 5:
                maxIter = 50;
                break;
            case 6:
                maxIter = 50;
                break;
            case 7:
                maxIter = 50;
                break;
            case 8:
                maxIter = 100;
                break;
            case 9:
                maxIter = 100;
                break;
            default:
                maxIter = 200;
                break;

        }

        int iter = 0;
        boolean stop = false;

        minMakespanT = maxMakespan;

        while (!stop) {

            int cmax = 0;
            boolean solDone[] = new boolean[d];

            for(int i=0;i<d;i++) {
                solDone[i] = false;
            }

            int start[][] = new int[nom][noj];
            int endt[][] = new int[nom][noj];
            int jwo[][] = new int[noj][nom];
            int jom[] = new int[nom];
            int jwp[] = new int[noj];
            int mjn[][] = new int[nom][noj];

            for(int i=0;i<noj;i++) {
                jwp[i] = 0;
                for(int j=0;j<nom;j++) {
                    jwo[i][j] = 0;
                    jom[j] = 0;
                }
            }

            boolean check[][] = new boolean[noj][nom];

            for(int i=0;i<d;i++) {

                int r1 = sol[i]%noj;
                int op_no;

                /*for(int j=0;j<d;j++) {
                    if(r1>j && sol[j]==sol[r1]) {
                        op_no++;
                    }
                }*/

                op_no = r1;

                int job = sol[i]/noj;
                int mac_no = jmS[job][op_no];
                int proc_tim = jpT[job][op_no];
                int op = jwp[job];

                if(jwp[job]==0) {
                    if(jom[mac_no]==0) {
                        start[mac_no][0] = 0;
                        endt[mac_no][0] = proc_tim;
                    }
                    else {
                        start[mac_no][jom[mac_no]] = endt[mac_no][jom[mac_no]-1];
                        endt[mac_no][jom[mac_no]] = start[mac_no][jom[mac_no]] + proc_tim;
                    }
                }
                else {
                    int jo = jwo[job][op-1];
                    int mac = jmS[job][jo];
                    int mj = mjn[mac][job];
                    int jm = jom[mac_no];
                    int jm1 = jom[mac_no]-1;

                    //jm1--;

                    if(jom[mac_no]==0) {
                        start[mac_no][0] = endt[mac][mj];
                        endt[mac_no][0] = start[mac_no][0] + proc_tim;
                    }
                    else {
                        //System.out.println(jm + " " + jm1);
                        start[mac_no][jm] = Math.max(endt[mac_no][jm1], endt[mac][mj]);
                        endt[mac_no][jm] =  start[mac_no][jm] + proc_tim;
                    }
                }

                jwo[job][op] = op_no;
                mjn[mac_no][job] = jom[mac_no];
                jom[mac_no] += 1;
                jwp[job] += 1;
            }

            for(int i=0; i<nom; i++ ) {
                for(int j=0; j<noj; j++ ) {
                    minMakespan = Math.max(endt[i][j], minMakespan);
                    if(iter==0) {
                        startT[i][j] = start[i][j];
                        endT[i][j] = endt[i][j];
                    }
                }

            }

            if(minMakespan<maxMakespan) {
                maxMakespan = minMakespan;

                for (int i=0;i<nom;i++) {
                    for(int j=0;j<noj;j++) {
                        startT[i][j] = start[i][j];
                        endT[i][j] = endt[i][j];
                    }
                }

                minMakespanT = maxMakespan;
            }
            else {
                break;
            }
            iter++;
            if(maxIter==iter) stop = true;
        }

    }
}
