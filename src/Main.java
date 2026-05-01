import java.math.BigInteger;
import java.util.*;

void main() {
    KeyGenerator kGenerator = new KeyGenerator();
    PrimeGenerator generator = new PrimeGenerator();
    PrimeCheck pCheck = new PrimeCheck();
    Encryption enc = new Encryption();
    //Generating two random primes for bases. Failsafe.
    BigInteger p = generator.primeGenerator();
    BigInteger q = generator.primeGenerator();
    while(!pCheck.isPrime(p,16)) p=generator.primeGenerator();
    while(!pCheck.isPrime(q,16)) q=generator.primeGenerator();

    BigInteger fiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

    ArrayList<BigInteger> temp = kGenerator.keyGenerator(fiN);
    BigInteger sk = temp.get(0);
    BigInteger pk = temp.get(1);


    //FOR TESTING PURPOSES
    SecureRandom random = new SecureRandom();
    BigInteger msg = new BigInteger(512, random);

    BigInteger encryptedMessage = enc.encrypt(msg,p.multiply(q),pk);
    IO.println(encryptedMessage);
    IO.println(msg.equals(enc.decrypt(encryptedMessage,p,q,sk)));

    IO.println("Signature");
    BigInteger msg2 = new BigInteger(512, random);
    Sign sgn = new Sign();
    BigInteger signedMessage = sgn.sign(msg2,p.multiply(q),sk);
    IO.println(signedMessage);
    IO.println(msg2.equals(sgn.check(signedMessage,p.multiply(q),pk)));

}
