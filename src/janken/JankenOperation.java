package janken;
import java.util.Random;
import java.util.Scanner;

/**
 * じゃんけんゲームの動作クラス。
 */
public class JankenOperation {
	public static int myChoice;
	public static int enemyChoice;
	public static String myHand;
	public static String enemyHand;
	public static final String GUU = "グー";
	public static final String CHOKI = "チョキ";
	public static final String PAA = "パー";
	
	
	public static void jankenOperation() {
		System.out.println("じゃんけんを始めます。");
		myChoice = gameStart();
		gameCheck(myChoice);
		System.out.println("じゃんけんを終了します。");
	}
	
	
	/**
	 * じゃんけんを始め、自分の手を決めるまでのメソッド。
	 * @return 自分のじゃんけんの手を表す数字。
	 */
	public static int gameStart() {
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		System.out.println("あなたの出す手を選択して下さい。");
		while(check) {
		System.out.println("1:"+ GUU + "、2:" + CHOKI + "、3:" + PAA);
		String input = sc.nextLine();
		try {
		if(!input.matches("[1-3]")) {
			System.out.println("半角1,2,3のどれかを入力して下さい。");
		} else {
		myChoice = Integer.parseInt(input); 
		check = false;
		}
		}catch(NumberFormatException e) {
			System.out.println("半角1,2,3のどれかを入力して下さい。");
		}
		}
		return myChoice;
	}
	
	/**
	 * ゲームの勝敗を決めるメソッド。
	 * @param yourChoice　自分のじゃんけんの手。
	 */
	public static void gameCheck(int myChoice) {
		Random random = new Random();
		enemyChoice = random.nextInt(3)+1;//敵のじゃんけんの種類をランダムに取得
		
		myHand = choice(myChoice);
		enemyHand = choice(enemyChoice);
		
		System.out.println("あなたの手：" + myHand + "　相手の手：" + enemyHand);
		battle(myHand,enemyHand);
	}
	
	/**
	 * 引数からじゃんけんの種類を返すメソッド
	 * @param hand じゃんけんの種類を表す整数
	 * @return じゃんけんの種類
	 */
	public static String choice(int hand) {
		String choiceHand="";
		switch(hand) {
			case 1:
				choiceHand = GUU;
				break;
			case 2:
				choiceHand = CHOKI;
				break;
			case 3:
				choiceHand = PAA;
				break;
			}
		return choiceHand;
	 }
	/**
	 * じゃんけんの種類による勝ち負けの判定メソッド。
	 * あいこの場合はもう一度。
	 * @param me 自分の手
	 * @param enemy　敵の手
	 */
	public static void battle(String me,String enemy) {
		if((me.equals(GUU) && enemy.equals(CHOKI)) || (me.equals(CHOKI) && enemy.equals(PAA)) || (me.equals(PAA) && enemy.equals(GUU))) {
			System.out.println("あなたの勝ちです。");
		}else if((me.equals(GUU) && enemy.equals(PAA)) || (me.equals(CHOKI) && enemy.equals(GUU)) || (me.equals(PAA) && enemy.equals(CHOKI))) {
			System.out.println("あなたの負けです。");
		}else {
			System.out.println("あいこでしょ！！");
			gameStart();
			gameCheck(myChoice);
		}
	}
}
