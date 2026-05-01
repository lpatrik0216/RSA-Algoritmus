import java.math.BigInteger;
import java.util.*;

void main() {

    //Generating two random primes for bases. Failsafe.
    BigInteger p = PrimeGenerator.primeGenerator();
    BigInteger q = PrimeGenerator.primeGenerator();
    //Checks if prime, and generates a new one until it is.
    while(!PrimeCheck.isPrime(p,16)) p= PrimeGenerator.primeGenerator();
    while(!PrimeCheck.isPrime(q,16)) q=PrimeGenerator.primeGenerator();

    BigInteger fiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); //Calculating fi(N)

    ArrayList<BigInteger> temp = KeyGenerator.keyGenerator(fiN); //Generates a key-pair (sk, pk) based on the fi(N) calculated previously
    BigInteger sk = temp.get(0);
    BigInteger pk = temp.get(1);


    SecureRandom random = new SecureRandom();
    BigInteger msg = new BigInteger(512, random); //Generates a random, 512 bit long message.

    BigInteger encryptedMessage = Encryption.encrypt(msg,p.multiply(q),pk); //Encrypts the message
    IO.println(encryptedMessage); //Prints the encrypted message
    IO.println(msg.equals(Encryption.decrypt(encryptedMessage,p,q,sk))); //Checks if the message is equal to the decrypted message (Checks if the algorithm is correct)

    IO.println("Signature");
    BigInteger msg2 = new BigInteger(512, random); //Generates a random, 512 bit long message.
    BigInteger signedMessage = Sign.sign(msg2,p.multiply(q),sk); //Signs the message
    IO.println(signedMessage);
    IO.println(msg2.equals(Sign.check(signedMessage,p.multiply(q),pk))); //Cheks whether the algorithm works correctly

}
