import java.math.BigInteger;
import java.util.Random;

public class PrimeGenerator {

    BigInteger primeGenerator(){
        BigInteger key = BigInteger.probablePrime(2048,new Random());
        return key;
    }
}
