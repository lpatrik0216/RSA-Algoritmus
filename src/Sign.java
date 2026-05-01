import java.math.BigInteger;

public class Sign {
    public static BigInteger sign(BigInteger msg, BigInteger modulo, BigInteger secretKey){
        return QuickPower.quickPow(msg,secretKey,modulo);
    }


    public static BigInteger check(BigInteger msg, BigInteger mod, BigInteger publicKey){
        return QuickPower.quickPow(msg,publicKey,mod);
    }
}
