/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 18/04/13
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class JSSP {

    int minMakespanT;        //Minimum makespan time
    int startT[][];          //start time of each job on each machine
    int endT[][];

    /*int jpT[][] = { {3,6,1,7,6,3},
        {10,8,5,4,10,10},
        {0,1,5,4,7,8},
        {5,5,5,3,8,9},
        {3,3,9,4,5,4},
        {10,3,1,3,3,9}
    };

    int jmS[][] = { {2,0,1,3,5,4},
        {1,2,4,5,0,3},
        {2,3,5,0,1,4},
        {1,0,2,3,4,5},
        {2,1,4,5,0,3},
        {1,3,5,0,4,2}
    };
    */
    /*tai_1*/
    /*int jpT[][] = { {54, 34, 61,  2},
            {9, 15, 89, 70},
            {38, 19, 28, 87},
            {95, 34,  7, 29} };

    int jmS[][] = { {2,  0,  3,  1},
        {3,  0,  1,  2},
        {0,  1,  2,  3},
        {0,  2,  1,  3}};
    */
    /*tai_2*/
    /*int jpT[][] = { {5, 70, 45,  83},
            {24, 80, 58, 45},
            {29, 56, 29, 61},
            {43, 64, 45, 74} };

    int jmS[][] = { {3,  2,  0,  1},
        {3,  0,  2,  1},
        {0,  1,  2,  3},
        {3,  2,  1,  0}};
    */
    /*tai_3*/
    /*int jpT[][] = { {2, 38, 5, 66},
            {68, 14, 95, 85},
            {89, 54, 77, 38},
            {59, 51, 63, 72} };

    int jmS[][] = { {0,  2,  1,  3},
        {1,  2,  3,  0},
        {2,  0,  1,  3},
        {0,  3,  1,  2}};
     */

    /*tai_4*/
    /*int jpT[][] = { {90, 50, 64, 65},
            {47, 92, 73, 26},
            {27, 94, 48, 93},
            {43, 76, 87, 65} };

    int jmS[][] = { {0,  2,  1,  3},
            {3,  2,  0,  1},
            {3,  1,  0,  2},
            {2,  0,  1,  3}};
    */

    int jpT[][];
    int jmS[][];

    JSSP(int sol[],int noj,int nom,int jpt[][], int jms[][]) {
        int d = nom*noj;
        int i,j;
        //System.out.println(d);

        jpT = new int[noj][nom];
        jmS = new int[noj][nom];

        for(i=0;i<noj;i++) {
            for(j=0;j<nom;j++) {
                jpT[i][j] = jpt[i][j];
                jmS[i][j] = jms[i][j];
                //System.out.print(jpT[i][j] + " ");
            }
            //System.out.println();
        }

        //System.out.println(noj + " " + nom + " " + d);

        startT = new int[nom][noj];
        endT = new int[nom][noj];

        int jwp[] = new int[noj];       //job working procedure
        int jom[] = new int[nom];       // job on machine
        int mjn[][] = new int[nom][noj];   //Job running on machine

        //calculatin start time and end time of each job on each machine
        for( i=0; i<d; i++ ) {
            int job = sol[i];
            int op_no = jwp[job];
            int mac_no = jmS[job][op_no];

            if( op_no==0 ) {
                //System.out.println(jmS[sol[i]][0]);
                if( jom[ mac_no ]==0 ) {
                    startT[mac_no][0] = 0;
                    endT[mac_no][0] = jpT[job][0];
                }
                else {
                    startT[mac_no][jom[mac_no]] = endT[mac_no][jom[mac_no]-1];
                    endT[mac_no][jom[mac_no]] = startT[mac_no][jom[mac_no]] + jpT[job][0];
                }
            }
            else {
                if( jom[mac_no]==0 ) {
                    startT[mac_no][0] = endT[jmS[job][op_no-1]][mjn[jmS[job][op_no-1]][job]];
                    endT[mac_no][0] = startT[mac_no][0] + jpT[job][op_no];
                }
                else {
                    startT[mac_no][jom[mac_no]] = Math.max( endT[mac_no][jom[mac_no]-1], endT[jmS[job][op_no-1]][mjn[jmS[job][op_no-1]][job]]);
                    endT[mac_no][jom[mac_no]] = startT[mac_no][jom[mac_no]] + jpT[job][op_no];
                }

            }

            mjn[mac_no][job] = jom[mac_no];
            jom[mac_no] += 1;
            jwp[job] += 1;


        }



        //Calculating minimum makespan time
        for(i=0; i<nom; i++ )
            for(j=0; j<noj; j++ )
                minMakespanT = Math.max(endT[i][j], minMakespanT);

    }
}