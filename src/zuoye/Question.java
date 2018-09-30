package zuoye;

/*支持分数和随机长度，并可以将每次答题情况记录到文件里 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Question {

	public static String str = "";// 题目
	public static String strNum;// 键盘 接收题目
	public static int num = 1;// 每题中数的个数 默认 1 从键盘获取 题目的个数
	public static int num_i = 0;// 题目中已有数的个数
	public static int numberRange = 100;// 运算中数的最大取值 从键盘 获取
	public static number sum = new number();// 结果

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// 答案放在目录
		String file = "Exercises.txt";
		String ansfile = "Answers.txt";
		String connfileString = null;
		String woronfileString = null;
		int[] a = new int[100000];// 放正确结果的数组
		int[] b = new int[100000];// 错误结果的数组
		int ai = 0;
		int bi = 0;

		System.out.println("---请根据提示输入数据 ---");
		System.out.println("Myapp.exe -n 题目的个数");
		System.out.println("Myapp.exe -r  数值的范围");
		System.out.println("Myapp.exe -r  数值的范围");
		System.out.println("Myapp.exe -e <记录正确题目的>.txt  -a <记录错误题目的>.txt");

		Scanner input = new Scanner(System.in);
		strNum = input.next();
		// 题目的个数
		if (strNum.equals("Myapp.exe")) {
			strNum = input.next();
			if (strNum.equals("-n")) {
				num = input.nextInt();
				System.out.println("输入的数据：" + num);
			}
		}

		

		strNum = input.next();
		if (strNum.equals("Myapp.exe")) {
			strNum = input.next();
			if (strNum.equals("-r")) {
				numberRange = input.nextInt();
				System.out.println("输入的数据 范围为：" + numberRange);
			}
		}

		strNum = input.next();
		if (strNum.equals("Myapp.exe")) {
			strNum = input.next();
			if (strNum.equals("-e")) {
				connfileString = input.next();
				connfileString = connfileString.replaceAll("<", "");
				connfileString = connfileString.replaceAll(">", "");
			}

			strNum = input.next();
			if (strNum.equals("-a")) {
				woronfileString = input.next();
				woronfileString = woronfileString.replaceAll("<", "");
				woronfileString = woronfileString.replaceAll(">", "");
			}
		}

		PrintStream out = null;
		PrintStream ansout = null;
		PrintStream eout = null;
		PrintStream aout = null;

		if (args.length >= 1)
			file = args[0];
		try {

			out = new PrintStream(new FileOutputStream(file));
			ansout = new PrintStream(new FileOutputStream(ansfile));
			eout = new PrintStream(new FileOutputStream(connfileString));
			aout = new PrintStream(new FileOutputStream(woronfileString));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			input.close();
			return;
		}

		// 题目
		System.out.println("题目为:");
		for (int i = 0; i < num;) {

			GetQuestion();// 题目
			if (sum.toString().indexOf("-") == 0) {
				continue;// 去结果为负数的
			}
			System.out.print(i + 1);
			System.out.println(":" + str + "请输入答案 :      ");

			String answer = "";

			answer = input.next();

			System.out.print("正确答案:  " + sum.toString() + "    ");
			number re = sum.add(new number(10));
			re = new number(answer);
			if (re.equals(sum)) {
				System.out.println("正确");
				a[ai++] = i + 1;
			} else {
				System.out.println("错误");
				b[bi++] = i + 1;
			}
			i++;
			out.println("四则运算题目" + i + str);
			ansout.println("答案" + i + sum.toString());

		}
		out.println();
		input.close();
		out.close();
		System.out.println();
		int sum = ai;
		eout.print("Correct:" + sum + "(");
		for (int i = 0; i < ai; i++) {
			if (i == ai - 1) {
				eout.print(a[i]);
			} else {
				eout.print(a[i]+",");
			}
			
		}
		eout.print(")");

		sum = bi;
		aout.print("Wrong:" + sum + "(");
		for (int i = 0; i < bi; i++) {
			if (i == bi - 1) {
				aout.print(b[i]);
			} else {
				aout.print(b[i]+",");
			}
			
		}
		aout.print(")");
	}

	private static void GetQuestion() {

		str = "";
		sum.set(0);
		;
		num_i = (int) (Math.random() * 1) + 2;

		quesGrow();

	}

	private static void quesGrow() {
		if (num_i > 1) {
			int j = num_i;
			num_i--;
			quesGrow();

			int ck = (int) (Math.random() * 4);
			number w;// 随机产生的数字
			if (ck != 0)
				w = new number(1 + (int) (Math.random() * numberRange));
			else
				// 分母
				w = new number(1 + (int) (Math.random() * numberRange),
						1 + (int) (Math.random() * numberRange));
			int t = (int) (Math.random() * 2);
			int f = (int) (Math.random() * 4);

			if (t == 0) {
				if (f == 0) {
					sum = sum.add(w);
					str = str + "+" + w.toString();
				}
				if (f == 1) {
					sum = sum.sub(w);
					str = str + "-" + w.toString();
				}
				if (f == 2) {
					if (j < 3) {
						sum = sum.mul(w);
						str = str + "*" + w.toString();
					} else {
						sum = sum.mul(w);
						str = "(" + str + ")" + "*" + w.toString();
					}
				}
				if (f == 3) {

					if (j < 3) {
						sum = sum.div(w);
						str = str + " / " + w.toString();
					} else {
						sum = sum.div(w);
						str = "(" + str + ")" + " / " + w.toString();
					}
				}
			} else {
				if (f == 0) {
					sum = sum.add(w);
					str = w.toString() + "+" + str;
				}
				if (f == 1) {
					if (j < 3) {
						sum = w.sub(sum);
						str = w.toString() + "-" + str;
					} else {
						sum = w.sub(sum);
						str = w.toString() + "-" + "(" + str + ")";
					}
				}
				if (f == 2) {
					if (j < 3) {
						sum = sum.mul(w);
						str = w.toString() + "*" + str;
					} else {
						sum = sum.mul(w);
						str = w.toString() + "*" + "(" + str + ")";
					}
				}
				if (f == 3) {
					if (j < 3) {
						sum = w.div(sum);
						str = w.toString() + " / " + str;
					} else {
						sum = w.div(sum);
						str = w.toString() + " / " + "(" + str + ")";
					}
				}
			}
		} else if (num_i == 1) {
			int ck = (int) (Math.random() * 4);
			number w;
			if (ck != 0)
				w = new number(1 + (int) (Math.random() * numberRange));
			else
				w = new number(1 + (int) (Math.random() * numberRange),
						1 + (int) (Math.random() * numberRange));
			sum = sum.add(w);
			str = str + w.toString();
		}
	}
}
