import java.math.BigInteger;
import java.util.Random;

public class PrimeGenerator {

    public static BigInteger primeGenerator(){
        return BigInteger.probablePrime(2048,new Random());
    }
}
