import java.util.Random;

public class Test {

    public Judge getTrustMatrix(int N){
        return new Judge(N);
    }

    /**
     * create a trust graph
     * @return trust matrix
     */
    public boolean createGraphTest(){
        try{
            Judge j = getTrustMatrix(5);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean negativeGraphTest(){
        try{
            Judge j = getTrustMatrix(5);
            j.addTrust(4,5);
        }catch (Exception e){
            return true;
        }
        return false;
    }

    public boolean findJudgeSmallScale(){
        try{
            Judge j = getTrustMatrix(5);

            j.addTrust(0,1);
            j.addTrust(2,1);
            j.addTrust(3,4);
            j.addTrust(3,1);
            j.addTrust(2,4);
            j.addTrust(4,3);
            j.addTrust(4,1);

            return (j.findJudge(5,j.getTrust()) == 1);

        }catch (Exception e){
            return false;
        }
    }

    public boolean findJudgeLargeScale(){
        try{
            Random rand = new Random();
            int size = rand.nextInt(1000) + 2;
            int Judge = -1;
            int countJudge = 0;
            int[] count = new int[size];
            Judge j = getTrustMatrix(size);
            int entries = rand.nextInt(10000) + 1;
            for(int i = 0; i < entries; i++){
                int n1 = rand.nextInt(size);
                int n2 = rand.nextInt(size);
                if(n1 != n2 && j.getTrust()[n1][n2] != 1){
                    j.addTrust(n1,n2);
                    //count[n1] += 1;
                    count[n2] += 1;
                }
            }
            for(int i = 0; i < size; i++){
                if(count[i] == size -1){
                    Judge = i;
                    countJudge += 1;
                }
            }
            //check if there's only one judge
            if(countJudge == 1){
                return (Judge == j.findJudge(size,j.getTrust()));
            }else if(countJudge!=1){
                return (j.findJudge(size,j.getTrust()) == -1);
            }
            return (Judge == j.findJudge(size,j.getTrust()));
        }catch (Exception e){
            return false;
        }
        //return false;
    }

    public void runTest() {
        int grade = 0;

        if(createGraphTest()){
            grade += 10;
            System.out.println("[+10%] Passed create graph test");
        } else {
            System.out.println("[    ] Failed create graph test");
        }

        if (negativeGraphTest()) {
            grade += 10;
            System.out.println("[+10%] Passed negative graph test");
        } else {
            System.out.println("[    ] Failed negative graph test");
        }

        if(findJudgeSmallScale()){
            grade += 40;
            System.out.println("[+40%] Passed small scale test");
        }else{
            System.out.println("[    ] Failed small scale test");
        }

        if (findJudgeLargeScale()){
            grade += 40;
            System.out.println("[+40%] Passed large scale test");
        }else{
            System.out.println("[    ] Failed large scale test");
        }
        System.out.println("Starting point for this assignment: " + grade + "%");
        System.out.println(grade == 100?"I\'m a F**king genius!!!!" : "I F**king suck!!!!!");
    }


    public static void main(String[] args) {
        Test test = new Test();
        test.runTest();
    }
}
