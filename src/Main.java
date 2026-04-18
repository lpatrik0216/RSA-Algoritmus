import java.math.BigInteger;

BigInteger primeGenerator(){
    BigInteger key = BigInteger.probablePrime(2048,new Random());
    return key;
}

boolean isPrime(BigInteger prime, int numberOfRounds){ //Runs a Miller-Rabin test, for the given rounds.
    long S=0;
    BigInteger saved = prime;
    prime=prime.subtract(BigInteger.ONE);
    //This loop extracts the S and d values
    while(prime.remainder(BigInteger.TWO).equals(BigInteger.ZERO)){
        prime = prime.divide(BigInteger.TWO);
        S+=1;
    }
    BigInteger d = prime;
    prime=saved;
    Set<Integer> roundBases = new TreeSet<>(); //Generates a SET that will contain the list of Miller-Rabin test
    //Using SET type to prevent duplications.
    Random random = new Random();
    if (BigInteger.valueOf(numberOfRounds).compareTo(prime.subtract(BigInteger.valueOf(3))) > 0) {
        IO.println("Requested number of rounds exceeds the number of possible bases!");
        return false;
    }
    while(roundBases.size()!=numberOfRounds){  //Generates [numberOfRounds] random number
        int temp = random.nextInt(9999999)+2;
        roundBases.add(temp);
    }

    List<Integer> roundBasesList;
    roundBasesList=roundBases.stream().toList(); //Converts SET to List for easier access

    //Runs the Miller-Rabin
    for (Integer current : roundBasesList){//current will be the base for the given round
        BigInteger convertedCurrent = BigInteger.valueOf(current);
        boolean isPrime = false;
        for (int i = 0; i < S; i++) { //i will be the number of round within the round
            if(i==0){
                if(convertedCurrent.modPow(d,prime).equals(BigInteger.ONE) || convertedCurrent.modPow(d,prime).equals(prime.subtract(BigInteger.ONE))) {isPrime=true; break;}
            }
            else{
                BigInteger exponent = d.multiply(BigInteger.valueOf((long) Math.pow(2,i)));
                if(convertedCurrent.modPow(exponent,prime).equals(prime.subtract(BigInteger.ONE))) {isPrime=true; break;}
            }
        }
        if(isPrime==false) return false;
    }
    return true;
}

ArrayList<BigInteger> EuklidesAlgorithm(BigInteger x1, BigInteger x2){
    ArrayList<Integer> k = new ArrayList<>();
    k.add(0); k.add(1);
    ArrayList<BigInteger> r = new ArrayList<>();
    r.add(x1); r.add(x2);
    ArrayList<BigInteger> q = new ArrayList<>();
    q.add(BigInteger.ZERO);
    int i=1;
    ArrayList<BigInteger> x = new ArrayList<>();
    x.add(BigInteger.ONE); x.add(BigInteger.ZERO);
    ArrayList<BigInteger> y = new ArrayList<>();
    y.add(BigInteger.ZERO); y.add(BigInteger.ONE);

    while(!r.get(i).equals(BigInteger.ZERO)){ //gets the GCD of the two input numbers
        r.add(r.get(i-1).remainder(r.get(i)));
        q.add(r.get(i-1).divide(r.get(i)));
        i++;
        k.add(i);
    }
    ArrayList<BigInteger> solution = new ArrayList<>();
    solution.add(r.get(i-1)); //adds the GCD to the returned arraylist, first element of it will be the GCD
    for(i=2; i<k.getLast();i++){
        x.add(x.get(i-1).multiply(q.get(i-1)).add(x.get(i-2)));
        y.add(y.get(i-1).multiply(q.get(i-1)).add(y.get(i-2)));
    }
    solution.add(x.getLast());
    solution.add(y.getLast());
    return solution;  // [GCD, x value, y value]
}

ArrayList<BigInteger> keyGenerator(BigInteger fiN) {
    SecureRandom random = new SecureRandom();
    BigInteger pk;
    do { //generates a public key that fits the restrictions (1 < e < fi(N) )
        pk = new BigInteger(fiN.bitLength(), random);
    } while (pk.compareTo(fiN) >= 0);

    ArrayList<BigInteger> temp = EuklidesAlgorithm(fiN, pk);

    while(!temp.get(0).equals(BigInteger.ONE)){ //Generates a public key that fits the restrictions of PK and fi(N) being relative primes
        do { //generates a public key that fits the restrictions (1 < e < fi(N) )
            pk = new BigInteger(fiN.bitLength(), random);
        } while (pk.compareTo(fiN) >= 0);
        temp = EuklidesAlgorithm(fiN, pk);
    }

    BigInteger sk = temp.get(2);

    ArrayList<BigInteger> keys = new ArrayList<>();
    keys.add(pk); keys.add(sk);
    return keys;
}


void main() {
    //Generating two random primes for bases. Failsafe.
    BigInteger p = primeGenerator();
    BigInteger q = primeGenerator();
    while(!isPrime(p,16)) p=primeGenerator();
    while(!isPrime(q,16)) q=primeGenerator();

    BigInteger fiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

    ArrayList<BigInteger> temp = keyGenerator(fiN);
    BigInteger sk = temp.get(0);
    BigInteger pk = temp.get(1);
    IO.println(sk.multiply(pk).mod(fiN));
}
