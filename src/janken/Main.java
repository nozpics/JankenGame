package janken;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		System.out.println("じゃんけんゲームの種類を選んでください。");
		System.out.println("1:みんなでバトル　2:勝ち抜きバトル");
		boolean check = true;
		while(check) {
			String inputMode = sc.nextLine();
			try {
				if(inputMode.matches("[0-9]")) {
					int gameMode = Integer.parseInt(inputMode);
					switch(gameMode) {
						case 1 :
							JankenGroup.jankenGroup();
							break;
						case 2 :
							JankenSurvival.jankenSurvival();
							break;
						default :
							System.out.println("半角1,2のどちらかを入力して下さい。");
					}
				}else {
				System.out.println("半角1,2のどちらかを入力して下さい。");
				}
			}catch(NumberFormatException e) {
				System.out.println("半角1,2のどちらかを入力して下さい。");
			}
		}
	}
}
