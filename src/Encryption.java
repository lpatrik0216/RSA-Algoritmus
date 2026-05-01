import java.math.BigInteger;

public class Encryption {
    public BigInteger encrypt(BigInteger msg, BigInteger modulo, BigInteger publicKey){
        QuickPower qp = new QuickPower();
        return qp.quickPow(msg,publicKey,modulo);
    }


    public BigInteger decrypt(BigInteger msg, BigInteger p, BigInteger q, BigInteger secretKey){
        CRT crt = new CRT();
        return crt.chineseRest(p,q,msg,secretKey);
    }
}
