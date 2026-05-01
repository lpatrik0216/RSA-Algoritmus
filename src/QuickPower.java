import java.math.BigInteger;
import java.util.ArrayList;

public class QuickPower {
    public BigInteger quickPow(BigInteger n, BigInteger exponent, BigInteger mod){
        ArrayList<Integer> binaryList = new ArrayList<>();
        while(!exponent.equals(BigInteger.ZERO)){
            if(exponent.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) binaryList.add(0);
            else binaryList.add(1);
            exponent=exponent.divide(BigInteger.TWO);
        }
        ArrayList<BigInteger> numbers = new ArrayList<>();
        numbers.add(n);
        for(int i=1;i<binaryList.size();i++){
            numbers.add(numbers.get(i-1).multiply(numbers.get(i-1)).mod(mod));
        }
        BigInteger result=BigInteger.ONE;
        for(int i = 0;i<binaryList.size();i++){
            if(binaryList.get(i)==1) result=result.multiply(numbers.get(i)).mod(mod);
        }
        return result;
    }
}