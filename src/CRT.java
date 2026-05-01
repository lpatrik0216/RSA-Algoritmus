import java.math.BigInteger;
import java.util.ArrayList;

public class CRT {
    public static BigInteger chineseRest(BigInteger p, BigInteger q, BigInteger msg, BigInteger exponent) {
        BigInteger M = p.multiply(q);
        BigInteger c1 = QuickPower.quickPow(msg, exponent.mod(p.subtract(BigInteger.ONE)), p);
        BigInteger c2 = QuickPower.quickPow(msg, exponent.mod(q.subtract(BigInteger.ONE)), q);
        ArrayList<BigInteger> result = new ArrayList<>();
        result = EuclidesAlgorithm.euclidesAlgorithm(q, p);
        BigInteger y1 = result.get(1);
        BigInteger y2 = result.get(2);
        BigInteger m1 = c1.multiply(y1).multiply(q);
        BigInteger m2 = c2.multiply(y2).multiply(p);
        BigInteger m = m1.add(m2);
        return m.mod(M);
    }
}
