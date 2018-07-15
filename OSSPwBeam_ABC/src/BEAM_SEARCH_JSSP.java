import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 18/04/13
 * Time: 20:54
 * To change this template use File | Settings | File Templates.
 */
public class BEAM_SEARCH_JSSP {

    int seq[];
    int noj;
    int nom;
    int D;
    int jpT[][];
    int jmS[][];
    int bestSeq[];
    int startT[][];
    int endT[][];
    int jmSeq[][];
    int jN[][];
    int jP[];
    int jom[];
    int seqJ[];
    int macJP[][];
    int eachJob[];
    int eachMac[];

    public BEAM_SEARCH_JSSP(int gp[], int n, int m, int d, int jp[][], int jm[][], int st[][], int end[][]) {
        noj = n;
        nom = m;
        D = d;

        seq = new int[D];
        bestSeq = new int[D];
        jpT = new int[noj][nom];
        jmS = new int[noj][nom];
        startT = new int[nom][noj];
        endT = new int[nom][noj];
        jmSeq = new int[nom][noj];
        jN = new int[nom][noj];
        jP = new int[noj];
        jom = new int[noj];
        seqJ = new int[D];
        macJP = new int[nom][noj];
        eachJob = new int[noj];
        eachMac = new int[nom];

        for(int i=0;i<noj;i++) {
            eachJob[i] = 0;
            for(int j=0;j<nom;j++) {
                jpT[i][j] = jp[i][j];
                eachJob[i] += jp[i][j];
                jmS[i][j] = jm[i][j];
                macJP[jm[i][j]][i] = jp[i][j];
            }
        }

        for(int i=0;i<nom;i++) {
            eachMac[i] = 0;
            for(int j=0;j<noj;j++) {
                startT[i][j] = st[i][j];
                endT[i][j] = end[i][j];
                eachMac[i] += macJP[i][j];
            }
        }

        for(int i=0;i<D;i++) {
            seq[i] = gp[i];
        }

        int level = 0;
        int sigmaStar = 0;
        int beamW = 2;
        int mac[] = new int[beamW];
        int beamSeq[][] = new int[beamW][D];
        boolean check[][] = new boolean[beamW][D];

        mac[0] = 0;
        mac[1] = nom;

        while(level<D) {
            int wid = 0;

            if(level==0) {
                sigmaStar = 0;

                int k = 0;
                boolean jjob[][] = new boolean[noj][nom];
                int selJ[] = new int[D];

                for(int i=0;i<D;i++) {
                    int job = seq[i];
                    int op_no = jP[seq[i]];
                    int ma = jmS[job][op_no];

                    if(ma == mac[0]) {
                        if(!jjob[seq[i]][op_no]) {
                            jjob[seq[i]][op_no] = true;
                            selJ[k] = seq[i];
                            jP[seq[i]]++;
                            k++;
                        }
                    }
                }

                int globalV[] = new int[D];

                for(int i=0;i<D;i++) globalV[i] = 0;

                for(int i=0;i<k;i++) {
                    int job = selJ[i];
                    globalV[selJ[i]] = Math.max(eachJob[job], eachMac[mac[0]]);
                    //System.out.println("vik " + globalV[selJ[i]]);
                }

                int min1=0;
                int min2=0;
                int global[] = new int[D];

                for(int i=0;i<D;i++) {
                    global[seq[i]] = globalV[seq[i]];
                }

                Arrays.sort(global);
                int fl = 0;

                for(int i=0;i<D;i++) {
                    if(global[seq[i]]!=0 && fl==0) {
                        min1 = global[seq[i]];
                        fl++;
                    }
                    else if(global[i]!=0 && fl==1) {
                        min2 = global[seq[i]];
                        fl++;
                    }
                    else if(fl==2) break;
                }

                for(int i=0;i<D;i++) {
                    //System.out.println("vik1 " + globalV[i]);
                    if(globalV[seq[i]]==min1 || globalV[seq[i]]==min2) {
                        beamSeq[wid][level] = seq[i];
                        check[wid][i] = true;
                        wid++;
                    }
                    if(wid==beamW) break;
                }

            }
            else {
                for(int j=0;j<beamW;j++) {
                    sigmaStar = 0;

                    int k = 0;
                    boolean jjob[][] = new boolean[noj][nom];
                    int selJ[] = new int[noj];

                    int globalV[] = new int[D];

                    for(int i=0;i<D;i++) globalV[i] = 0;

                    for(int i=0;i<k;i++) {
                        int job = selJ[i];
                        globalV[selJ[i]] = Math.max(eachJob[job], eachMac[mac[j]]);
                        //System.out.println("vik " + selJ[i]);
                    }

                    int min1=0;
                    int global[] = new int[D];

                    for(int i=0;i<D;i++) {
                        global[seq[i]] = globalV[seq[i]];
                    }

                    Arrays.sort(global);
                    int fl = 0;

                    for(int i=0;i<D;i++) {
                        if(global[i]!=0 && fl==0) {
                            min1 = global[seq[i]];
                            fl++;
                        }
                        else if(fl==1) break;
                    }

                    boolean flag = false;

                    for(int i=0;i<D;i++) {
                        //System.out.println("vik " + globalV[i]);
                        if(!check[j][i] && globalV[seq[i]]==min1) {
                            //System.out.println("vik " + i);
                            beamSeq[j][level] = seq[i];
                            check[j][i] = true;
                            flag = true;
                        }
                        if(flag) break;
                    }
                }
            }

            mac[0]++;
            mac[1]--;
            level++;
        }

        int ck[][] = new int[beamW][noj];
        boolean ck1[][] = new boolean[beamW][noj];
        int pos=0;

        /*for(int i=0;i<beamW;i++) {
            for(int j=0;j<noj;j++) {
                ck[i][beamSeq[i][j]]++;
                //System.out.println("Vik" + ck[i][beamSeq[i][j]]);
            }

            for(int j=0;j<noj;j++) {
                if(ck[i][j]==0) {
                    //System.out.println("Vik");
                    pos = j;
                }
            }

            for(int j=0;j<D;j++) {
                if(ck1[i][beamSeq[i][j]]) beamSeq[i][j] = pos;
                else ck1[i][beamSeq[i][j]] = true;
            }
        }
        */

        int seq1[][] = new int[beamW][D];

        for(int j=0;j<beamW;j++) {
            for(int k=0;k<D;k++) {
                seq1[j][k] = beamSeq[j][k];
            }
        }


        int best = 0;
        int ind = 0;

        /*for (int i=0;i<beamW;i++) {
            for (int j=0;j<D;j++) {
                System.out.print(seq1[i][j] + " ");
            }
            System.out.println();
        }
        */

        for (int j = 0;j<beamW; j++) {
            JSSP ossp = new JSSP(seq1[j], noj, nom, jpT, jmS);
            if(j==0) {
                best = ossp.minMakespanT;
                ind = j;

            }
            else if(best>ossp.minMakespanT) {
                best = ossp.minMakespanT;
                ind = j;
            }
        }

        for(int i=0;i<D;i++) {
            bestSeq[i] = seq1[ind][i];
        }

    }
}