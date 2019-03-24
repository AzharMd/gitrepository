/*Cake Delivery
Number of Cakes
A cake factory makes various different varieties of cakes. It is the largest factory in the state and takes orders from different places to prepare and deliver cakes. 
The cake factory has a very large number of employees but not all employees come for work everyday. 
Due to this, some days the cake factory can make a large delivery while on other days, they may be able to deliver only a handful of cakes.

The factory produces M number of cakes each day and all cakes weigh differently. 
Every morning, the manager gets an order to deliver N kgs of cake. The factory can either completely deliver the order or not deliver the order at all. 
Help the manager of the cake factory to determine whether can be delivered for the day or not.

Note: The cakes produced by the factory have to be delivered as a whole; they cannot be cut into pieces.

Constraints

1 <= T <= 400

1 <= M <= 1000

1 <= weight of each cake <= 1000

1 <= N <= 10000

Sample Input Format

First line contains T which indicates the number of days for which the delivery is to be made.

Second line contains M + 1 space separated integers in which the first integer denotes the number of cakes to be made in one day 
and the following M integers denote the weight of each cake.

Third line contains N which indicates the weight of cake in kgs which needs to delivered.

Sample Output Format

Output YES if the delivery for that day can be made, otherwise NO for each test case.
Sample Input

3
4 12 2 15 9
11
7 20 13 5 8 2 11 3
23
3 7 4 1
6
Sample Output

YES
YES
NO
Explanation

In the first case, the cake factory produces 4 cakes in a day.The weight of each cake is 12 kg, 2 kg, 15 kg, 9 kg respectively. 
The order received for cake delivery is 11 kg. If the cake factory delivers 2 kg and 9 kg cake, then the total order would be equal 
to 11 kg. Therefore, the delivery for this day can be made successfully.*/

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.*;
import java.security.*;
import java.text.*;
import java.math.*;

class DeliverCakes {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int numOfCakes = 0;
        
        int numOfDays = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        
        for(int i = 0; i < numOfDays; i++) {
            String[] numNWeights = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            
            if(numNWeights != null && numNWeights.length > 0 && !numNWeights[0].equals(" ")) {
                try {
                    numOfCakes = Integer.parseInt(numNWeights[0]);
                }catch(NumberFormatException e) {
                    System.out.println("Exception azhar");
                }
            }
            
            int[] weightOfCakes = new int[numOfCakes];
            for(int j = 1, k = 0; j < numNWeights.length; j++, k++) {
                weightOfCakes[k] = Integer.parseInt(numNWeights[j]);
            }
        
            int weightToBeDelivered = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            
            boolean result = isDeliveryPossible(numOfCakes, weightOfCakes, weightToBeDelivered);
            System.out.println(result == true ? "YES" : "NO");
        }

        scanner.close();
    }
  
    private static boolean isDeliveryPossible(int numOfCakes, int[] weightOfCakes, int weightToBeDelivered) {
       if(weightToBeDelivered == 0) {
           return true;
       }
       
       if(numOfCakes == 0 && weightToBeDelivered != 0) {
           return false;
       }
       
       if(weightOfCakes[numOfCakes - 1] > weightToBeDelivered) {
           return isDeliveryPossible(numOfCakes - 1, weightOfCakes, weightToBeDelivered);
       }
       
       return isDeliveryPossible(numOfCakes - 1, weightOfCakes, weightToBeDelivered) || 
                isDeliveryPossible(numOfCakes - 1, weightOfCakes, weightToBeDelivered - weightOfCakes[numOfCakes - 1]);
    }
}
