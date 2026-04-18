import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class PrimeCheck {

    long getSValue(BigInteger prime){
        long S=0;
        prime=prime.subtract(BigInteger.ONE);
        while(prime.remainder(BigInteger.TWO).equals(BigInteger.ZERO)){
            prime = prime.divide(BigInteger.TWO);
            S+=1;
        }
        return S;
    }

    BigInteger getDValue(BigInteger prime){
        prime=prime.subtract(BigInteger.ONE);
        while(prime.remainder(BigInteger.TWO).equals(BigInteger.ZERO)){
            prime = prime.divide(BigInteger.TWO);
        }
        return prime;
    }

    List<Integer> generateBases(int numberOfRounds){
        Set<Integer> roundBases = new TreeSet<>(); //Generates a SET that will contain the list of Miller-Rabin test. Using SET type to prevent duplications.
        Random random = new Random();
        while(roundBases.size()!=numberOfRounds){  //Generates [numberOfRounds] random number
            int temp = random.nextInt(9999999)+2;
            roundBases.add(temp);
        }
        List<Integer> roundBasesList;
        roundBasesList=roundBases.stream().toList(); //Converts SET to List for easier access
        return roundBasesList;
    }

    boolean isPrime(BigInteger prime, int numberOfRounds){ //Runs a Miller-Rabin test, for the given rounds.
        long S=getSValue(prime);
        BigInteger d = getDValue(prime);
        if (BigInteger.valueOf(numberOfRounds).compareTo(prime.subtract(BigInteger.valueOf(3))) > 0) {
            IO.println("Requested number of rounds exceeds the number of possible bases!");
            return false;
        }
        List<Integer> roundBasesList = generateBases(numberOfRounds);

        //Runs the Miller-Rabin
        for (Integer current : roundBasesList){//current will be the base for the given round
            BigInteger convertedCurrent = BigInteger.valueOf(current);
            boolean isPrime = false;
            for (int i = 0; i < S; i++) { //i will be the number of round within the round
                if(i==0){
                    if(convertedCurrent.modPow(d,prime).equals(BigInteger.ONE) || convertedCurrent.modPow(d,prime).equals(prime.subtract(BigInteger.ONE))) {isPrime=true; break;}
                }
                else{
                    BigInteger exponent = d.multiply(BigInteger.valueOf((long) Math.pow(2,i)));
                    if(convertedCurrent.modPow(exponent,prime).equals(prime.subtract(BigInteger.ONE))) {isPrime=true; break;}
                }
            }
            if(isPrime==false) return false;
        }
        return true;
    }
}
