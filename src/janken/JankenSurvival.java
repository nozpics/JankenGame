package janken;

import java.util.Random;

/**
 * 勝ち抜きバトルゲームの動作クラス
 */
public class JankenSurvival {
	public static int winRecord;
	public static String myHand;
	public static String enemyHand;
	public static final String GUU = "グー";
	public static final String CHOKI = "チョキ";
	public static final String PAA = "パー";
	
	/**
	 * 勝ち抜きバトルゲームの動作メソッド
	 */
	public static void jankenSurvival(){
		System.out.println("勝ち抜きバトルを始めます。");
		gameCheck(JankenGroup.gameStart());
		System.out.println("勝ち抜きバトルを終了します。");
		getTitle(winRecord);
		winRecord = 0;
	}
	
	/**
	 * 自分と相手のじゃんけんの手を取得するメソッド。
	 * @param myChoice　自分の手を表す整数。
	 */
	public static void gameCheck(int myChoice) {
		Random random = new Random();
		int enemyChoice;
		myHand = JankenGroup.choice(myChoice);
		enemyChoice = random.nextInt(3)+1;
		enemyHand = JankenGroup.choice(enemyChoice);
		System.out.println("あなたの手：" + myHand + "　相手の手：" + enemyHand);
		survivalBattle(myHand,enemyHand);
	}
	
	/**
	 * じゃんけんの勝敗判定と連勝数を加算するメソッド。
	 * @param me 自分の手
	 * @param e 敵の手
	 */
	public static void survivalBattle(String me,String e) {
		if((me.equals(GUU) && e.equals(CHOKI)) || (me.equals(CHOKI) && e.equals(PAA)) || (me.equals(PAA) && e.equals(GUU))) {
			System.out.println("あなたの勝ちです。");
			winRecord++;
			System.out.println(winRecord + "連勝中！");
			gameCheck(JankenGroup.gameStart());
		}else if((me.equals(GUU) && e.equals(PAA)) || (me.equals(CHOKI) && e.equals(GUU)) || (me.equals(PAA) && e.equals(CHOKI))){
			System.out.println("相手の勝ちです。");
		}else {
			System.out.println("あいこでしょ！");
			gameCheck(JankenGroup.gameStart());
		}
	}
	
	/**
	 * 連勝記録と称号を表示するメソッド。
	 * @param record 連勝数。
	 */
	public static void getTitle(int record) {
		String title;
		if(record >= 10) {
			title = "運だけ神"; 
		}else if(record >= 8) {
			title = "運ゲーの申し子";
		}else if(record >= 5) {
			title = "そろそろ天狗";
		}else if(record >= 3) {
			title = "調子乗り始め";
		}else if(record >= 1) {
			title = "勝てたのはまぐれ？";
		}else {
			title = "負ける天才";
		}
		System.out.println("記録：" + record + "連勝");
		System.out.println("称号：" + title);
	}
}