import java.math.BigInteger;

public class Sign {
    public BigInteger sign(BigInteger msg, BigInteger modulo, BigInteger secretKey){
        QuickPower qp = new QuickPower();
        return qp.quickPow(msg,secretKey,modulo);
    }


    public BigInteger check(BigInteger msg, BigInteger mod, BigInteger publicKey){
        QuickPower qp = new QuickPower();
        return qp.quickPow(msg,publicKey,mod);
    }
}
