package zuoye;


// 分子 分子的
public class number {
	private int numerator,denominator;
	
	public number() {
		// TODO Auto-generated constructor stub
		numerator=0;
		denominator=1;
	}
	
	public number (String str) {
		int pos=str.indexOf('/');
		if(pos==-1)
		{
				numerator=Integer.parseInt(str);
				denominator=1;
		}
		else {
				numerator=Integer.parseInt(str.substring(0, pos).trim());
				if(pos!=str.length()-1)
				denominator=Integer.parseInt(str.substring(pos+1).trim());
				else denominator=1;
		}
	}
	
	
	public number(int a) {
		// TODO Auto-generated constructor stub
		numerator = a;
        denominator = 1;
	}
	
	public number(int a, int b){
        numerator = a;
        denominator = b;
    }
	
	public void set(int a) {
		numerator = a;
        denominator = 1;
	}
	
	public void set(int a,int b) {
		numerator = a;
        denominator = b;
	}

	public String toString() {
		check();
		if (numerator == 0)
        {
           return "0";
        }
        else{
            if (denominator == 1){
                return numerator+"";
            }
            else{
                return "("+numerator+"/"+denominator+")";
            }
        }
	}
	
	public void check()
	{
		if(numerator<0&&denominator<0||numerator>0&&denominator<0)
		{
			numerator=-1*numerator;
			denominator=-1*denominator;
		}
	}
	
	
	public boolean equals(number n2) {
		check();
		n2.check();
		number x1=this.yuefen();
		number x2=n2.yuefen();
		if(x1.numerator==x2.numerator&&x1.denominator==x2.denominator)
			return true;
		else return false;
	}
	
	public number yuefen() {
		check();
		number ans=new number(0);
		if(numerator==0||denominator==0)  return ans;
		int divisor;//最大公约数
		divisor = gcd(denominator, numerator);
        ans.denominator = denominator / divisor;
        ans.numerator = numerator / divisor;
        return ans;
	}
	
	
	public number add(number n2){
        number re=new number();
        int divisor;//最大公约数

        re.denominator = denominator * n2.denominator;
        re.numerator = numerator * n2.denominator + n2.numerator * denominator;
        divisor = gcd(re.denominator, re.numerator);
        re.denominator = re.denominator / divisor;
        re.numerator = re.numerator / divisor;
        re.check();
        return re;
    }
	
	private int gcd(int n1,int n2)
	{
		return n2==0?n1:gcd(n2, n1%n2);
	}
	
	public double ParseDouble(){
		return numerator*1.0/denominator;
	}
	
	public number sub(number n2){
		number re=new number();
        int divisor;//最大公约数
        
        re.denominator = denominator * n2.denominator;
        re.numerator = numerator * n2.denominator - n2.numerator * denominator;
        if (re.numerator != 0){
            divisor = gcd(re.denominator, re.numerator);
            re.denominator = re.denominator / divisor;
            re.numerator = re.numerator / divisor;
        }
        re.check();
        return re;
        
	}
	
	public number mul(number n2){
		number re=new number();
        int divisor;//最大公约数
        
        re.denominator = denominator * n2.denominator;
        re.numerator = numerator * n2.numerator;
        if (re.numerator != 0){
            divisor = gcd(re.denominator, re.numerator);
            re.denominator = re.denominator / divisor;
            re.numerator = re.numerator / divisor;
        }
        re.check();
        return re;
        
	}
	
	public number div(number n2) {
		number re=new number();
        int divisor;//最大公约数
        
        if(numerator==0)  return new number(0);
        re.numerator=numerator*n2.denominator;
        re.denominator=denominator*n2.numerator;
        if (re.numerator != 0){
            divisor = gcd(re.denominator, re.numerator);
            re.denominator = re.denominator / divisor;
            re.numerator = re.numerator / divisor;
        }
        re.check();
        return re;
	}
	
	
}
