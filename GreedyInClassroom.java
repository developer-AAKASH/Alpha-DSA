import java.util.*;

public class GreedyInClassroom {

    public static void fractionalKnapSack () {
        int val[] = {60, 100, 120};
        int weight[] = {10, 20, 30};
        int w = 50;

        double ratio[][] = new double[val.length][2];
//        0th col -> index, 1st col -> ratio
        for (int i = 0; i < val.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = val[i] / (double)weight[i];
        }

        Arrays.sort(ratio, Comparator.comparingDouble(data -> data[1]));

        int capacity = w, finalValue = 0;
        for (int i = ratio.length - 1; i >= 0; i--) {
            final int index = (int)ratio[i][0];
            if (capacity >= weight[index]) {
//                Include full item
                finalValue += val[index];
                capacity -= weight[index];
            } else {
//                Include fractional item
                finalValue += (ratio[i][1] * capacity);
                capacity = 0;
                break;
            }
        }

        System.out.println("Final valueL: "+ finalValue);
    }
    public static void minimumAbsoluteDifferencePairs () {
        int[] arr1 = {1, 2, 3}; // 4, 1, 8, 7
        int[] arr2 = {2, 1, 3}; // 2, 3, 6, 5

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int minAbsDiff = 0;

        for (int i = 0; i < arr1.length; i++) {
            minAbsDiff += Math.abs(arr1[i] - arr2[i]);
        }

        System.out.println(minAbsDiff);
    }
    public static void maxLengthChainOfPairs () {
        int pairs[][] = new int[5][2];
        pairs[0][0] = 5; pairs[0][1] = 24;
        pairs[1][0] = 39; pairs[1][1] = 60;
        pairs[2][0] = 5; pairs[2][1] = 28;
        pairs[3][0] = 27; pairs[3][1] = 40;
        pairs[4][0] = 50; pairs[4][1] = 90;

        Arrays.sort(pairs, Comparator.comparingDouble(data -> data[1]));

        int maxChainLen = 1;
        int chainEnd = pairs[0][1]; // Last selected pair end // chain end

        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i][0] > chainEnd) {
                maxChainLen++;
                chainEnd = pairs[i][1];
            }
        }

        System.out.println(maxChainLen);

    }
    public static void indianCoins () {
        Integer currency[] = {1, 2, 5, 10, 20, 50, 100, 500, 2000};
        int amount = 590;

        Arrays.sort(currency, Comparator.reverseOrder());

        int countOfCoins = 0;
        ArrayList<Integer> coins = new ArrayList<>();
        for (int i = 0; i < currency.length; i++) {
            if (currency[i] <= amount) {
                while (currency[i] <= amount) {
                    countOfCoins++;
                    coins.add(currency[i]);
                    amount -= currency[i];
                }
            }
        }

        System.out.println(countOfCoins);
        System.out.println(coins); // print array-List
    }
    public static void jobSequencing () {
//      BF --  Find all possible sequences - subset and find the maximum one...

//        Sort jobs based on profit...
        int jobsInfo[][] = {{4, 20}, {1, 10}, {1, 40}, {1, 30}};
        ArrayList<Job> jobs = new ArrayList<>();

        for (int i = 0; i < jobs.size(); i++) {
            jobs.add(new Job(jobsInfo[i][0], jobsInfo[i][1], i));
        }

        Collections.sort(jobs, (obj1, obj2) -> obj2.profit - obj1.profit); // descending
//        Collections.sort(jobs, (obj1, obj2) -> obj1.profit - obj2.profit); // asscending

        ArrayList<Integer> sequences = new ArrayList<>();
        int time = 0;
        for (int i = 0; i < jobs.size(); i++) {
            Job current = jobs.get(i);
            if (current.deadLine > time) {
                sequences.add(current.id);
                time++;
            }
        }

        System.out.println("Maximum Jobs: " + sequences.size());
//        Print all sequences...
    }
    static class Job {
        int deadLine;
        int profit;
        int id;

        Job (final int deadLine, final int profit, final int id) {
            this.deadLine = deadLine;
            this.profit = profit;
            this.id = id;
        }
    }
    public static void chocolaProblem () {
        Integer vCost[] = {2, 1, 3, 1, 4}; // m-1
        Integer hCost[] = {4, 1, 2}; // n-1

        Arrays.sort(hCost, Collections.reverseOrder());
        Arrays.sort(vCost, Collections.reverseOrder());
        int h = 0, v = 0;
        int horiPc = 0, vertiPc = 0;
        int cost = 0;

        while (h < hCost.length && v < vCost.length) {
//            Vertical cose < horizontal cost
            if (vCost[v] <= hCost[h]) {
                // Horixontal Cut
                cost += hCost[h] * vertiPc;
                horiPc++;
                h++;
            } else {
                cost += vCost[v] * horiPc;
                vertiPc++;
                v++;
            }
        }

        while (h < hCost.length) {
            cost += hCost[h] * vertiPc;
            horiPc++;
            h++;
        }

        while (v < vCost.length) {
            cost += vCost[v] * horiPc;
            vertiPc++;
            v++;
        }

        System.out.println("Minimum Cost: "+ cost);
    }
    public static void main(String[] args) {
//        Already sorted based on end of activity...
        int start[] = {1, 3, 0, 5, 8, 5};
        int end[] = {2, 4, 6, 7, 9, 9};

//        Sorting
        int[][] activities = new int[start.length][3];
        for (int i = 0; i < start.length; i++) {
            activities[i][0] = i; // index
            activities[i][1] = start[i];
            activities[i][2] = end[i];
        }

        Arrays.sort(activities, Comparator.comparingDouble(data -> data[2]));

//                ---- Sorted ----
        int maxActivity = 0;
        ArrayList<Integer> answer = new ArrayList<>();

        maxActivity = 1;
        answer.add(0);
        int lastEnd = end[0];
        for (int i = 1; i < end.length; i++) {
            if (start[i] >= lastEnd) {
//                activity select
                maxActivity++;
                answer.add(i);
                lastEnd = end[i];
            }
        }

        System.out.println("Maximum Activities: "+ maxActivity);
        for (int i = 0; i < answer.size(); i++) {
            System.out.print("A" + answer.get(i) + " ");
        }

//        ---- Sorted End ---

//        int maxActivity = 0;
//        ArrayList<Integer> answer = new ArrayList<>();
//
//        maxActivity = 1;
//        answer.add(activities[0][0]);
//        int lastEnd = activities[0][2];
//        for (int i = 1; i < end.length; i++) {
//            if (activities[i][1] >= lastEnd) {
////                activity select
//                maxActivity++;
//                answer.add(activities[i][0]);
//                lastEnd = activities[i][2];
//            }
//        }
//
//        System.out.println("Maximum Activities: "+ maxActivity);
//        for (int i = 0; i < answer.size(); i++) {
//            System.out.print("A" + answer.get(i) + " ");
//        }


    }
}
