public class Judge {
    private int[][] trust;

    public Judge(int N){
        this.trust = new int[N][N];
    }

    /**
     * getter for the matrix
     * @return trust matrix
     */
    public int[][] getTrust(){
        return trust;
    }

    /**
     * union n1 to n2
     * @param n1 person 1
     * @param n2 person 2
     * @throws Exception
     */
    public void addTrust(int n1, int n2) throws Exception{
        if(n1 < 0 || n2 < 0 || n1 >= trust[0].length || n2 >= trust[0].length){
            throw new Exception();
        }
        trust[n1][n2] = 1;
    }

    /**
     *
     * @param N
     * @param trust
     * @return judge or -1 if there is no judge
     * @throws Exception
     */
    public int findJudge(int N, int[][] trust) throws Exception{
        if(N < 0 || N > trust[0].length) {
            throw new Exception();
        }
        int[] possible = new int[N];
        for(int i = 0; i < N; i++){
            int trustN = 0; // people who N trusts
            int trustBy = 0; // all the people who trust N
            for(int j = 0; j < N; j++){
                trustN += trust[i][j];
                trustBy += trust[j][i];
            }
            if(trustN == 0 && trustBy == N-1 ){
                possible[i] = 1;
            }
        }
        int sum = 0;
        for(int i = 0; i < N; i++){
            sum += possible[i];
        }
        if(sum == 1){
            for(int i = 0; i < N; i++){
                if(possible[i] == 1){
                    return i;
                }
            }
        }
        return -1;
    }
}
