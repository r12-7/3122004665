package Bean;

public class Fraction {
    private int zi;
    private int mu;

    public Fraction(int zi, int mu) {
        this.zi = zi;
        this.mu = mu;
    }

    public double toDouble(){
        return zi*1.0/mu;
    }

    public Fraction plus(Fraction r){
        Fraction m=new Fraction(0,1);
        m.mu=r.mu*mu;
        m.zi=r.zi*mu+r.mu*zi;
        return m;
    }

    public Fraction multiply(Fraction r){
        Fraction m=new Fraction(0,1);
        m.mu=r.mu*mu;
        m.zi=zi*r.zi;
        return m;
    }

    //传入的是被减数
    public Fraction sub(Fraction r){
        Fraction m=new Fraction(0,1);
        m.mu=r.mu*mu;
        m.zi=r.zi*mu-r.mu*zi;
        return m;
    }

    //传入的是除数
    public Fraction division(Fraction r){
        Fraction m=new Fraction(0,1);
        m.mu=mu*r.zi;
        m.zi=zi*r.mu;
        return m;
    }

    //将分数转化为字符串形式
    public String turnToString(){
        if(mu!=1){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(zi);
            stringBuilder.append("/");
            stringBuilder.append(mu);
            return stringBuilder.toString();
        }else {
            return String.valueOf(zi);
        }

    }

    //将字符串转化为Fraction形式
    public static Fraction turnToFraction(String str){
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        int count=0;
        if(str.contains("/")){//检测到其中有/，是一个分数，将其拆分为两个字符串
            for (char c : str.toCharArray()) {
                if(c!='/'){
                    str1.append(c);
                    count++;
                }else break;
            }
            for(count+=1;count<str.length();count++){
                char c = str.charAt(count);
                str2.append(c);
            }
            int zi = Integer.parseInt(str1.toString());
            int mu = Integer.parseInt(str2.toString());
            Fraction fraction = new Fraction(zi, mu);
            return fraction;
        }else {
            Fraction fraction = new Fraction(Integer.parseInt(str), 1);
            return fraction;
        }

    }

    //将分数化简,并返回简化后分数的Fraction格式
    public Fraction simplify(){
        int i,max=1;
        for (i=1;i<mu;i++){//找出最大公约数
            if(mu%i==0&&zi%i==0){
                max=i;
            }
        }
        zi=zi/max;
        mu=mu/max;
        Fraction fraction = new Fraction(zi, mu);
        return fraction;
    }

    //将带分数转化为假分数形式
    public static Fraction getResult(String str){
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        int count=0;
        if(str.contains("'")){
            for (char c : str.toCharArray()) {
                if(c!='\''){
                    str1.append(c);
                    count++;
                }else break;
            }
            for(count+=1;count<str.length();count++){
                char c = str.charAt(count);
                str2.append(c);
            }
        }
        Fraction f1 = new Fraction(Integer.parseInt(str1.toString()), 1);
        Fraction f2 = Fraction.turnToFraction(str2.toString());
        return f1.plus(f2);

    }
    @Override
    public String toString() {
        return "Fraction{" +
                "zi=" + zi +
                ", mu=" + mu +
                '}';
    }

    public int getZi() {
        return zi;
    }

    public void setZi(int zi) {
        this.zi = zi;
    }

    public int getMu() {
        return mu;
    }

    public void setMu(int mu) {
        this.mu = mu;
    }
}
