package zuoye;

/*֧�ַ�����������ȣ������Խ�ÿ�δ��������¼���ļ��� */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Question {

	public static String str = "";// ��Ŀ
	public static String strNum;// ���� ������Ŀ
	public static int num = 1;// ÿ�������ĸ��� Ĭ�� 1 �Ӽ��̻�ȡ ��Ŀ�ĸ���
	public static int num_i = 0;// ��Ŀ���������ĸ���
	public static int numberRange = 100;// �������������ȡֵ �Ӽ��� ��ȡ
	public static number sum = new number();// ���

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// �𰸷���Ŀ¼
		String file = "Exercises.txt";
		String ansfile = "Answers.txt";
		String connfileString = null;
		String woronfileString = null;
		int[] a = new int[100000];// ����ȷ���������
		int[] b = new int[100000];// ������������
		int ai = 0;
		int bi = 0;

		System.out.println("---�������ʾ�������� ---");
		System.out.println("Myapp.exe -n ��Ŀ�ĸ���");
		System.out.println("Myapp.exe -r  ��ֵ�ķ�Χ");
		System.out.println("Myapp.exe -r  ��ֵ�ķ�Χ");
		System.out.println("Myapp.exe -e <��¼��ȷ��Ŀ��>.txt  -a <��¼������Ŀ��>.txt");

		Scanner input = new Scanner(System.in);
		strNum = input.next();
		// ��Ŀ�ĸ���
		if (strNum.equals("Myapp.exe")) {
			strNum = input.next();
			if (strNum.equals("-n")) {
				num = input.nextInt();
				System.out.println("��������ݣ�" + num);
			}
		}

		

		strNum = input.next();
		if (strNum.equals("Myapp.exe")) {
			strNum = input.next();
			if (strNum.equals("-r")) {
				numberRange = input.nextInt();
				System.out.println("��������� ��ΧΪ��" + numberRange);
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

		// ��Ŀ
		System.out.println("��ĿΪ:");
		for (int i = 0; i < num;) {

			GetQuestion();// ��Ŀ
			if (sum.toString().indexOf("-") == 0) {
				continue;// ȥ���Ϊ������
			}
			System.out.print(i + 1);
			System.out.println(":" + str + "������� :      ");

			String answer = "";

			answer = input.next();

			System.out.print("��ȷ��:  " + sum.toString() + "    ");
			number re = sum.add(new number(10));
			re = new number(answer);
			if (re.equals(sum)) {
				System.out.println("��ȷ");
				a[ai++] = i + 1;
			} else {
				System.out.println("����");
				b[bi++] = i + 1;
			}
			i++;
			out.println("����������Ŀ" + i + str);
			ansout.println("��" + i + sum.toString());

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
			number w;// �������������
			if (ck != 0)
				w = new number(1 + (int) (Math.random() * numberRange));
			else
				// ��ĸ
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
