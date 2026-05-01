import java.math.BigInteger;
import java.util.ArrayList;

public class EuclidesAlgorithm {
    public static ArrayList<BigInteger> euclidesAlgorithm(BigInteger x1, BigInteger x2){
        //Creates the table for the Euclidean Algorithm
        ArrayList<Integer> k = new ArrayList<>();
        k.add(0); k.add(1);
        ArrayList<BigInteger> r = new ArrayList<>();
        r.add(x1); r.add(x2);
        ArrayList<BigInteger> q = new ArrayList<>();
        q.add(BigInteger.ZERO);
        int i=1;
        ArrayList<BigInteger> x = new ArrayList<>();
        x.add(BigInteger.ONE); x.add(BigInteger.ZERO);
        ArrayList<BigInteger> y = new ArrayList<>();
        y.add(BigInteger.ZERO); y.add(BigInteger.ONE);

        //Euclidean Algorithm
        while(!r.get(i).equals(BigInteger.ZERO)){ //gets the GCD of the two input numbers
            r.add(r.get(i-1).remainder(r.get(i)));
            q.add(r.get(i-1).divide(r.get(i)));
            i++;
            k.add(i);
        }
        ArrayList<BigInteger> solution = new ArrayList<>();
        solution.add(r.get(i-1)); //adds the GCD to the returned arraylist, first element of it will be the GCD
        //Expansion for the Euclidean algorithm (gets the x and y values)
        for(i=2; i<k.getLast();i++){
            x.add(x.get(i-2).subtract(x.get(i-1).multiply(q.get(i-1))));
            y.add(y.get(i-2).subtract(y.get(i-1).multiply(q.get(i-1))));
        }

        BigInteger finalX = x.getLast();
        BigInteger finalY = y.getLast();
        if (finalY.compareTo(BigInteger.ZERO) < 0) { //if y is negative, turns it into positive
            finalY = finalY.add(x1);
        }
        solution.add(finalX);
        solution.add(finalY);
        return solution;  // [GCD, x value, y value]
    }
}
