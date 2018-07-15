/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 18/04/13
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public class BEAM_ABC_JSSP {

    static ABC_JSSP abc = new ABC_JSSP();
    int nom;
    int noj;

    public BEAM_ABC_JSSP(int job, int mac, int pt[][], int ms[][]) {

        nom = mac;
        noj = job;
        abc.jmS = new int[noj][nom];
        abc.jpT = new int[noj][nom];

        abc.noj = noj;
        abc.nom = nom;
        abc.D = noj*nom;
        abc.Foods = new int[abc.SN][abc.D];
        abc.Food = new int[abc.SN][abc.D];
        abc.solution = new int[abc.D];
        abc.sol1 = new int[abc.D];
        abc.GlobalParams = new int[abc.D];

        switch (nom) {
            case 1: abc.maxCycle = 100; break;
            case 2: abc.maxCycle = 500; break;
            case 3: abc.maxCycle = 1000; break;
            case 4: abc.maxCycle = 1500; break;
            case 5: abc.maxCycle = 2500; break;
            case 6: abc.maxCycle = 2500; break;
            case 7: abc.maxCycle = 2500; break;
            case 10: abc.maxCycle = 2500; break;
            default: abc.maxCycle = 2500; break;
        }

        //System.out.println(abc.noj + " " + abc.nom + " " + abc.D);

        for(int i=0;i<noj;i++) {
            for(int j=0;j<nom;j++) {
                abc.jpT[i][j] = pt[i][j];
                abc.jmS[i][j] = ms[i][j];
            }
        }

        int iter = 0;
        int run = 0;
        int j = 0;
        double mean = 0;
        int bestSeq[][] = new int[abc.runtime][abc.D];

        for( run=0; run<abc.runtime; run++ ) {
            abc.initial();
            abc.MemorizeBestSource();

            for( iter=0; iter<abc.maxCycle; iter++ ) {
                abc.SendEmployedBees();                 //calling employee bees
                abc.CalculateProbabilities();           //calculating probability of each food source which found by employee bee
                abc.SendOnlookerBees();                 //calling onlooker bees to select best food source
                abc.MemorizeBestSource();               //memorizing best food source
                abc.SendScoutBees();                    //calling scout bees
                abc.BeamSearch();
            }

            for( j=0; j<abc.D; j++ ) {
                bestSeq[run][j] = abc.GlobalParams[j];
                System.out.print(abc.GlobalParams[j] + " ");
            }

            System.out.println();
            System.out.println("minMakespan = " + abc.GlobalMin);

            abc.GlobalMins[run] = abc.GlobalMin;
            mean = mean + abc.GlobalMin;

        }

        int best = abc.GlobalMin;
        int bt = run-1;

        for( run=0; run<abc.runtime; run++ ) {
            if( best>abc.GlobalMins[run] ) {
                best = abc.GlobalMins[run];
                bt = run;
            }
        }

        System.out.println();
        System.out.println("Best operation scheduling list : ");

        for( j=0; j<abc.D; j++ ) {
            System.out.print(bestSeq[bt][j] + " ");
        }

        System.out.println();
        //System.out.println(abc.CalculateFunction(bestSeq[bt]));
        System.out.println(abc.GlobalMins[bt]);

        System.out.println();
        System.out.println();
        System.out.println("MakeSpanTime in Best case = " + best);

        mean /= abc.runtime;
        System.out.println();
        System.out.println("Means of " + abc.runtime + "runs : " + mean);


        JSSP jssp = new JSSP(bestSeq[bt],abc.noj,abc.nom, abc.jpT, abc.jmS);

        GANTT_CHART_JSSP gc = new GANTT_CHART_JSSP();
        gc.drawGraph(abc.jmS, bestSeq[bt], jssp.startT, jssp.endT, abc.noj, abc.nom);

    }
}
