package janken;
import java.util.Random;
import java.util.Scanner;

/**
 * みんなでバトルゲームの動作クラス。
 */
public class JankenGroup {
	public static int enemyCount;
	public static int totalRounds;
	public static int totalWins;
	public static String[] enemyHands;
	
	public static String myHand;
	public static String enemiesHand;
	
	public static final String GUU = "グー";
	public static final String CHOKI = "チョキ";
	public static final String PAA = "パー";
	public static final String ENEMYERROR = "2以上の半角数字を入力して下さい。";
	public static final String ROUNDSERROR = "1以上の半角数字を入力して下さい。";
	public static final String HANDSERROR = "半角1,2,3のどれかを入力して下さい。";
	
	/**
	 * みんなでバトルゲームの動作メソッド。
	 */
	public static void jankenGroup() {
		System.out.println("じゃんけんを始めます。");
		enemyCount = getMemberCount();
		totalRounds = getGameCount();
		for(int i=1; i<totalRounds+1;i++) {
			System.out.println(i + "回戦");
			gameCheck(gameStart());
		}
		System.out.println("勝ち数：" + totalWins);
		System.out.println("じゃんけんを終了します。");
	}
	
	/**
	 * 対戦人数を返すメソッド
	 * @return 対戦人数
	 * @throws NumberFormatException
	 * 文字が入力された場合
	 */
	public static int getMemberCount() {
		Scanner sc= new Scanner(System.in);
		boolean check = true;
		int count = 0;
		System.out.println("対戦人数を入力して下さい。");
		
		while(check) {
			String inputMember = sc.nextLine();
			try{
				if(inputMember.matches("\\d+")) {
					count = Integer.parseInt(inputMember);
					if(count<2) {
						System.out.println(ENEMYERROR);	
					}else {
					check = false;
					count -= 1; //自分を減らして、相手の人数を返す。
					}
				}else {
					System.out.println(ENEMYERROR);
				}
			}catch(NumberFormatException e){
				System.out.println(ENEMYERROR);
			}
		}
		return count;
	}
	
	/**
	 * ゲーム回数を決めて返すメソッド
	 * @return ゲーム回数
	 * @throws NumberFormatException
	 * 文字が入力された場合
	 */
	public static int getGameCount() {
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		int count = 0;
		System.out.println("対戦回数を入力して下さい。");
		while(check) {
			String inputCount = sc.nextLine();
			try {
				if(inputCount.matches("\\d+")) {
					count = Integer.parseInt(inputCount);
					if(count<1) {
						System.out.println(ROUNDSERROR);
					}else {
						check = false;
					}
				}else {
					System.out.println(ROUNDSERROR);
				}
			}catch(NumberFormatException e) {
				System.out.println(ROUNDSERROR);
			}
		}
		return count;
	}
	
	/**
	 * じゃんけんを始め、自分の手を決めるまでのメソッド。
	 * @return 入力された自分のじゃんけんの手を表す数字。
	 * @throws NumberFormatException
	 * 文字が入力された場合
	 */
	public static int gameStart() {
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		int choice = 0;
		System.out.println("あなたの出す手を選択して下さい。");
		while(check) {
			System.out.println("1:"+ GUU + "、2:" + CHOKI + "、3:" + PAA);
			String input = sc.nextLine();
			try {
				if(!input.matches("[1-3]")) {
					System.out.println(HANDSERROR);
				} else {
					choice = Integer.parseInt(input); 
				check = false;
				}
			}catch(NumberFormatException e) {
				System.out.println(HANDSERROR);
			}
		}
		return choice;
	}
	
	/**
	 * ゲームの勝敗を決めるメソッド。
	 * @param myChoice　自分のじゃんけんの手。
	 */
	public static void gameCheck(int myChoice) {
		myHand = choice(myChoice);
		enemiesHand = enemyCheck(); 
		System.out.println("あなたの手：" + myHand + "　相手の手：" + enemiesHand);
		battle(myHand,enemyHands);
	}
	
	/**
	 * 相手の手をランダムで人数分作成するメソッド。
	 * @return 相手の人数分の作成した手。
	 */
	public static String enemyCheck() {
		Random random = new Random();
		String enemy;
		StringBuilder enemiesHand = new StringBuilder();
		
		//相手の人数分の配列を作成
		int[] enemyChoice = new int[enemyCount];
		enemyHands = new String[enemyCount];
		
		for(int i=0;i<enemyCount;i++) {
			enemyChoice[i] = random.nextInt(3)+1;//相手の手をランダムに取得
			enemyHands[i] = choice(enemyChoice[i]);//取得した手をフィールド配列に代入。
			enemiesHand.append(enemyHands[i] + " ");
		}
		enemy = enemiesHand.toString();
		return enemy;
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
	 * あいこの場合、再帰的に呼び出して再度じゃんけんを行う。
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
			totalWins++;
		}else if(loseCount > 0 && winCount == 0) {
			System.out.println("相手の勝ちです。");
		}else {
			System.out.println("あいこでしょ！！");
			gameCheck(gameStart());
		}
	}
}
