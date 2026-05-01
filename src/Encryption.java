import java.math.BigInteger;

public class Encryption {
    public static BigInteger encrypt(BigInteger msg, BigInteger modulo, BigInteger publicKey){
        return QuickPower.quickPow(msg,publicKey,modulo);
    }


    public static BigInteger decrypt(BigInteger msg, BigInteger p, BigInteger q, BigInteger secretKey){
        return CRT.chineseRest(p,q,msg,secretKey);
    }
}
