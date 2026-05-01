import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class KeyGenerator {

    public static ArrayList<BigInteger> keyGenerator(BigInteger fiN) {
        SecureRandom random = new SecureRandom();
        BigInteger pk;
        do { //generates a public key that fits the restrictions (1 < e < fi(N) )
            pk = new BigInteger(fiN.bitLength(), random);
        } while (pk.compareTo(fiN) >= 0);

        ArrayList<BigInteger> temp = EuclidesAlgorithm.euclidesAlgorithm(fiN, pk);

        while(!temp.get(0).equals(BigInteger.ONE)){ //Generates a public key that fits the restrictions of PK and fi(N) being relative primes
            do { //generates a public key that fits the restrictions (1 < e < fi(N) )
                pk = new BigInteger(fiN.bitLength(), random);
            } while (pk.compareTo(fiN) >= 0);
            temp = EuclidesAlgorithm.euclidesAlgorithm(fiN, pk); // euclides returns: [GCD, x_value, y_value]
        }

        BigInteger sk = temp.get(2); //get the secret key from the euclides algorithm

        ArrayList<BigInteger> keys = new ArrayList<>();
        keys.add(pk); keys.add(sk);
        return keys; //returns [pk,sk]
    }
}
