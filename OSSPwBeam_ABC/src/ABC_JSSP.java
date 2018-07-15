/**
 * Created with IntelliJ IDEA.
 * User: vikash
 * Date: 13/02/13
 * Time: 20:52
 * To change this template use File | Settings | File Templates.
 */

public class ABC_JSSP {

    int NP = 50;                 //Size of colony
    int SN = NP/2;               // No. of food source
    int limit = 100;             // A food source which could not be improved through "limit" trials(that food source) is abandoned by its employed bee
    int maxCycle;         //No. of cycle for foraging

    int noj;                // No. of job
    int nom;                // No. of machine
    int D;            // No. of parameter in sequence

    int runtime = 30;

    int Foods[][];// = new int[SN][D];
    int Food[][];// = new int[SN][D];
    int f[] = new int[SN];              //hold makespantime for each food source
    double fitness[] = new double[SN];  //hold fitness value of each food source
    int trial[] = new int[SN];          //for improvement of food source
    double prob[] = new double[SN];     //probability of each food source
    int solution[];// = new int[D];        //operation scheduling list
    int sol1[];// = new int[D];

    int ObjValSol;
    double FitnessSol;
    int neighbour;

    // Parameter for memorizing the best source
    int GlobalMin;
    int GlobalParams[];// = new int[D];
    int GlobalMins[] = new int[runtime];

    int r1;
    int r2;
    double pr;

    int jmS[][];
    int jpT[][];

    // Fitness funtion
    double CalculateFitness(int fun) {
        double result;

        result = 1.0/fun;

        return result;
    }


    // Best food source is memorized
    void MemorizeBestSource() {
        int i,j;

        for( i=0; i<SN; i++ ) {
            if( f[i]<GlobalMin ) {
                GlobalMin = f[i];
                //System.out.println("memorize" + " " + i + " " + GlobalMin);

                for( j=0; j<D; j++ ) {
                    GlobalParams[j] = Foods[i][j];
                    // System.out.print(Foods[i][j] + " ");
                }

                //System.out.println();
                //System.out.println(CalculateFunction(Foods[i]));
            }
        }
    }

    void init(int index) {
        int i,j;
        int flag[] = new int[noj];

        for( j=0; j<D; j++) {
            pr = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );   //Generate random in [0,1]
            r1 = (int)(pr*noj);

            while( flag[r1]>=nom ) {
                pr = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
                r1 = (int)(pr*noj);
            }

            flag[r1]++;
            Foods[index][j] = r1;
        }

        for( i=0; i<D; i++ ) {
            solution[i] = Foods[index][i];
            //System.out.print(solution[i] + " ");
        }

        //System.out.println();

        f[index] = CalculateFunction(solution);
        //System.out.println(f[index]);
        fitness[index] = CalculateFitness(f[index]);
        trial[index] = 0;

    }

    //Initialization of food source
    void initial() {
        int i,j;

        for( i=0; i<SN; i++ ) {
            init(i);
            /*
             * for( int j=0;j<D;j++)
                System.out.print(Foods[i][j] + " ");
            System.out.println();
             *
             */
            for( j=0; j<D; j++ )
                Food[i][j] = Foods[i][j];
        }

        GlobalMin = f[0];

        for( i=0; i<D; i++ )
            GlobalParams[i] = Foods[0][i];
    }

    // Employed Bees Phase
    void SendEmployedBees() {
        int i,j;

        for( i=0; i<SN; i++ ) {

            int randSelecJ[] = new int[noj];
            boolean randSelecD[] = new boolean[D];
            int nj[] = new int[noj];

            //Arrays.fill(randSelecJ, 0);

            for( j=0; j<noj; j++)
                nj[j] = nom;

            // Exchanging information with a neighbouring food source based on PBX Method
            for( j=0; j<D; j++ ) {
                pr = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
                if( pr>=0.5 ) {
                    randSelecJ[Food[i][j]]++;
                    randSelecD[j] = true;
                }

            }

            pr = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
            r1 = (int)(pr*SN);

            while( r1==i ) {
                pr = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
                r1 = (int)(pr*SN);
            }

            neighbour = r1;

            int k = 0;

            for( j=0; j<D; j++ ) {
                if( randSelecD[j] ) {
                    while( randSelecJ[Food[neighbour][k]]==0 )
                        k++;
                    randSelecJ[Food[neighbour][k]]--;
                    Food[i][j] = Food[neighbour][k];
                    k++;
                }
            }

            for( j=0; j<D; j++ ) {
                solution[j] = Food[i][j];
            }

            ObjValSol = CalculateFunction(solution);
            FitnessSol = CalculateFitness(ObjValSol);

            //System.out.println(ObjValSol + " " + FitnessSol + " " + fitness[i]);

            if( FitnessSol>fitness[i] ) {
                trial[i] = 0;
                //System.out.println("Employe");
                for( j=0; j<D; j++ ) {
                    Foods[i][j] = solution[j];
                    //System.out.print(Foods[i][j] + " ");
                }

                //System.out.println("\n" + i + " " + CalculateFunction(Foods[i]));

                f[i] = ObjValSol;
                fitness[i] = FitnessSol;
                //System.out.println(f[i]);

            }
            else {
                trial[i] += 1;
                for( j=0; j<D; j++ )
                    Food[i][j] = Foods[i][j];

            }
        }

    }

    //Probability of all food sources based on their fitness value
    void CalculateProbabilities() {
        int i;
        double totfit = fitness[0];

        for( i=1; i<SN; i++ )
            totfit += fitness[i];

        for( i=0; i<SN; i++ )
            prob[i] = fitness[i]/totfit;
    }

    // Onlooker bee phase
    void SendOnlookerBees() {
        int i,j,t;
        i = t = 0;

        while( t<SN ) {
            pr = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
            if( pr<prob[i] ) {
                t++;
                int randSelecJ[] = new int[noj];
                boolean randSelecD[] = new boolean[D];
                int nj[] = new int[noj];

                for( j=0; j<noj; j++)
                    nj[j] = nom;

                // Exchanging information with a neighbouring food source based on PBX Method
                for( j=0; j<D; j++ ) {
                    pr = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
                    if( pr>=0.5 ) {
                        randSelecJ[Food[i][j]]++;
                        randSelecD[j] = true;
                    }

                }

                pr = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
                r1 = (int)(pr*SN);

                while( r1==i ) {
                    pr = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
                    r1 = (int)(pr*SN);
                }

                neighbour = r1;

                int k = 0;

                for( j=0; j<D; j++ ) {
                    if( randSelecD[j] ) {
                        while( randSelecJ[Food[neighbour][k]] == 0 )
                            k++;
                        randSelecJ[Food[neighbour][k]]--;
                        Food[i][j] = Food[neighbour][k];
                        k++;
                    }
                }

                for( j=0; j<D; j++ ) {
                    solution[j] = Food[i][j];
                }

                ObjValSol = CalculateFunction(solution);
                FitnessSol = CalculateFitness(ObjValSol);

                //System.out.println();
                //System.out.println(ObjValSol + " " + FitnessSol + " " + fitness[i]);

                if( FitnessSol>fitness[i] ) {
                    trial[i] = 0;
                    //System.out.println("Onlooker");

                    for( j=0; j<D; j++ ) {
                        Foods[i][j] = solution[j];
                        //System.out.print(Foods[i][j] + " ");
                    }

                    //System.out.println("\n" + i +" " +CalculateFunction(Foods[i]));

                    f[i] = ObjValSol;
                    fitness[i] = FitnessSol;
                    //System.out.println(f[i]);

                }
                else {
                    trial[i] += 1;

                    for( j=0; j<D; j++ )
                        Food[i][j] = Foods[i][j];

                }
            }
            i++;
            if( i==SN ) i = 0;
        }

    }

    // Scout bee phase
    void SendScoutBees() {
        int i,maxtrialindex = 0;

        for( i=1; i<SN; i++ ) {
            if( trial[i]>trial[maxtrialindex] )
                maxtrialindex = i;
            if( trial[maxtrialindex]>=limit )           // Abandon food source F[maxtrialindex]
                init(maxtrialindex);
        }
    }

    //Apply beam search on best solution
    void BeamSearch() {
        JSSP ossp = new JSSP(GlobalParams, noj,  nom, jpT, jmS);
        BEAM_SEARCH_JSSP bossp = new BEAM_SEARCH_JSSP(GlobalParams, noj, nom, D, jpT, jmS, ossp.startT, ossp.endT);

        int beamSol = CalculateFunction(bossp.bestSeq);
        double beamFit = CalculateFitness(beamSol);
        double global = CalculateFitness(GlobalMin);

        int sol[] = new int[D];

        for(int i=0;i<D;i++) {
            sol[i] = GlobalParams[i];
        }

        if(beamFit>global) {
            for(int i=0;i<D;i++) {
                GlobalParams[i] = bossp.bestSeq[i];
            }
            GlobalMin = beamSol;

            int minI = 0;
            int minV = f[0];

            for(int i=1;i<SN;i++) {
                if(f[i]>minV) {
                    minI = i;
                    minV = f[i];
                }
            }

            for(int i=0;i<D;i++) {
                Food[minI][i] = Foods[minI][i] = bossp.bestSeq[i];
                f[minI] = minV;
            }

        }
        else {
            int randSelecJ[] = new int[D];
            boolean randSelecD[] = new boolean[D];
            int nj[] = new int[noj];
            double pr;
            //Arrays.fill(randSelecJ, 0);

            for(int j=0; j<noj; j++)
                nj[j] = nom;

            // Exchanging information with a neighbouring food source based on PBX Method
            for(int j=0; j<D; j++ ) {
                pr = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
                if( pr>=0.55 ) {
                    randSelecJ[sol[j]]++;
                    randSelecD[j] = true;
                }
            }

            int k = 0;

            for(int j=0; j<D; j++ ) {
                if( randSelecD[j] ) {
                    while( randSelecJ[bossp.bestSeq[k]]==0 )
                        k++;
                    randSelecJ[bossp.bestSeq[k]]--;
                    sol[j] = bossp.bestSeq[k];
                    k++;
                }
            }

            ObjValSol = CalculateFunction(sol);
            FitnessSol = CalculateFitness(ObjValSol);

            if( FitnessSol>global) {
                for(int j=0; j<D; j++ ) {
                    GlobalParams[j] = sol[j];
                }

                GlobalMin = ObjValSol;

                int minI = 0;
                int minV = f[0];

                for(int i=1;i<SN;i++) {
                    if(f[i]>minV) {
                        minI = i;
                        minV = f[i];
                    }
                }

                for(int i=0;i<D;i++) {
                    Food[minI][i] = Foods[minI][i] = bossp.bestSeq[i];
                    f[minI] = minV;
                }

            }
        }


    }

    //Calculate makespan time of operation sequence list
    int CalculateFunction(int sol[]) {
        JSSP jssp = new JSSP(sol,noj,nom,jpT,jmS);
        return jssp.minMakespanT;
    }
}