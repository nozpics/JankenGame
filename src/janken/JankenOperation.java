package janken;
import java.util.Random;
import java.util.Scanner;

/**
 * じゃんけんゲームの動作クラス。
 */
public class JankenOperation {
	public static int member;
	public static int myChoice;
	public static int[] enemyChoice;
	public static String myHand;
	public static String[] enemyHand;
	public static final String GUU = "グー";
	public static final String CHOKI = "チョキ";
	public static final String PAA = "パー";
	
	
	public static void jankenOperation() {
		
		System.out.println("じゃんけんを始めます。");
		member = battleMember();
		myChoice = gameStart();
		gameCheck(myChoice);
		System.out.println("じゃんけんを終了します。");
	}
	
	/**
	 * 対戦人数を返すメソッド
	 * @return 対戦人数
	 * @throws NumberFormatException
	 * 文字が入力された場合
	 */
	public static int battleMember() {
		Scanner sc= new Scanner(System.in);
		boolean check = true;
		System.out.println("対戦人数を入力して下さい。");
		while(check) {
			String inputMember = sc.nextLine();
			try{
				if(inputMember.matches("\\d+")) {
					member = Integer.parseInt(inputMember);
					if(member<2) {
						System.out.println("２以上の半角数字を入力して下さい。");	
					}else {
					check = false;
					}
				}else {
					System.out.println("２以上の半角数字を入力して下さい。");
				}
			}catch(NumberFormatException e){
				System.out.println("２以上の半角数字を入力して下さい。");
			}
		}
		return member;
	}
	
	/**
	 * じゃんけんを始め、自分の手を決めるまでのメソッド。
	 * @return 自分のじゃんけんの手を表す数字。
	 * @throws NumberFormatException
	 * 文字が入力された場合
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
		StringBuilder enemiesHand = new StringBuilder();
		enemyChoice = new int[member];
		enemyHand = new String[member];
		Random random = new Random();
		
		myHand = choice(myChoice);
		for(int i=0;i<member;i++) {
			enemyChoice[i] = random.nextInt(3)+1;//敵のじゃんけんの種類をランダムに取得
			enemyHand[i] = choice(enemyChoice[i]);
			enemiesHand.append(enemyHand[i] + " ");
		}
		System.out.println("あなたの手：" + myHand + "　相手の手：" + enemiesHand);
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
	 * @param enemy 敵の手
	 */
	public static void battle(String me,String[] enemy) {
		int winCount = 0;
		int loseCount = 0;
		for(String e : enemy) {
			if((me.equals(GUU) && e.equals(CHOKI)) || (me.equals(CHOKI) && e.equals(PAA)) || (me.equals(PAA) && e.equals(GUU))) {
				winCount++;
			}else if((me.equals(GUU) && e.equals(PAA)) || (me.equals(CHOKI) && e.equals(GUU)) || (me.equals(PAA) && e.equals(CHOKI))){
				loseCount++;
			}else {
				continue;
			}
		}
		//勝ち数と負け数で勝敗を判定
		if(winCount > 0 && loseCount == 0) {
			System.out.println("あなたの勝ちです。");
		}else if(loseCount > 0 && winCount == 0) {
			System.out.println("あなたの負けです。");
		}else {
			System.out.println("あいこでしょ！！");
			gameStart();
			gameCheck(myChoice);
		}
	}
}
