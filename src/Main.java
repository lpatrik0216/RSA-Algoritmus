import java.math.BigInteger;
import java.util.*;

void main() {
    KeyGenerator kGenerator = new KeyGenerator();
    PrimeGenerator generator = new PrimeGenerator();
    PrimeCheck pCheck = new PrimeCheck();
    //Generating two random primes for bases. Failsafe.
    BigInteger p = generator.primeGenerator();
    BigInteger q = generator.primeGenerator();
    while(!pCheck.isPrime(p,16)) p=generator.primeGenerator();
    while(!pCheck.isPrime(q,16)) q=generator.primeGenerator();

    BigInteger fiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

    ArrayList<BigInteger> temp = kGenerator.keyGenerator(fiN);
    BigInteger sk = temp.get(0);
    BigInteger pk = temp.get(1);
    IO.println(sk.multiply(pk).mod(fiN));
}
