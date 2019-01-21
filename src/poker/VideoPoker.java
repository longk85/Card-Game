package poker;

import java.util.*;

/**
 * @author moebear
 * Jacks or Better 的基本规则和游戏逻辑
 */
public class VideoPoker {
	private int handCardsNum = 5; //手牌数量
	private CardDeck newDeck = new CardDeck(); //每一局游戏创建一副新牌组
	private int[] handCards = new int[handCardsNum]; //手牌的ID序列
	private String[] rankList = {"Royal flush", "Straight flush", "4 of a kind", "Full house", "Flush", "Straight", "3 of a kind", "Two pair", "Jacks or better"}; //中奖牌型的名字序列
	private int[] reward = {4000, 250, 125, 45, 30, 20, 15, 10, 5}; //对应的中奖牌型的奖励回报
	
	public VideoPoker() {
		
	}
	
	/**
	 * 随机生成五张手牌
	 */
	public void generateHandCards() {
		Random random = new Random();
		int end = newDeck.getCardsNum() - 1;
		int num;
		for(int i=0; i<this.handCards.length; i++) {
			num = random.nextInt(end+1);
			this.handCards[i] = this.newDeck.getCardsID(num);
			this.newDeck.setCardsID(num, newDeck.getCardsID(end));
			end--;
		}	
	}
	
	/**
	 * 在玩家决定舍弃的牌后随机生成新牌
	 * @param changeFlag 用来表示舍弃哪些牌：true 舍弃； false 保留
	 */
	public void changeHandCards(boolean[] changeFlag) {
		Random random = new Random();
		int end = newDeck.getCardsNum() - 6;
		int num;
		for(int i=0; i<this.handCards.length; i++) {
			if(changeFlag[i] == true) {
				num = random.nextInt(end+1);
				handCards[i] = newDeck.getCardsID(num);
				this.newDeck.setCardsID(num, newDeck.getCardsID(end));
			}
			end--;
		}
	}
	
	/**
	 * 中奖牌型检查
	 * @param handCards 待检查的手牌ID序列
	 * @return 中奖牌型的名字
	 */
	public String checkRank(int[] handCards) {
		String rank = "";
		int[] suitList = new int[handCards.length]; //花色ID序列
		int[] rankList = new int[handCards.length]; //牌值ID序列
		boolean flush;
		boolean straight;
		int pairs;
		int trikind;
		int forkind;
		
		/* 获取手牌的花色ID序列 */
		for(int i=0; i<handCards.length; i++) {
			int num;
			num = handCards[i] / 13;
			suitList[i] = num;
		}
		
		/* 获取手牌的牌值ID序列 */
		for(int i=0; i<handCards.length; i++) {
			int num;
			num = handCards[i] % 13;
			rankList[i] = num;
		}
		
		return rank;
	}
	
	/**
	 * 检查牌型是否是同花
	 * @param suitList 手牌花色ID序列
	 * @return 是或否
	 */
	public boolean checkFlush(int[] suitList) {
		HashSet<Integer> suitCheck = new HashSet<Integer>();
		boolean result = false;
		
		for(int i=0; i<suitList.length; i++) {
			if(!suitCheck.contains(suitList[i])) {
				suitCheck.add(suitList[i]);
			}
		}
		if(suitCheck.size() == 1) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 检查是否顺子
	 * @param rankList 手牌牌值ID序列
	 * @return 是或否
	 */
	public boolean checkStraight(int[] rankList) {
		List<Integer> straightCheck = new ArrayList<Integer>();
		boolean result = false;
		
		for(int i=0; i<rankList.length; i++) {
			straightCheck.add(rankList[i]);
		}
		
		/* 将牌值序列按递增顺序重排 */
		Collections.sort(straightCheck);
		
		/* 检查首尾牌值差值，如果不等于4且不等于12则不是顺子（等于12是A、2、3、4、5的情况） */
		int len = straightCheck.size();
		int temp = Math.abs(straightCheck.get(0) - straightCheck.get(len - 1));
		if(temp != 4 && temp != 12) {
			return result;
		}
		
		/* 检查是否有相同的牌 */
		for(int i=0; i<len-1; i++) {
			temp = straightCheck.get(i+1) - straightCheck.get(i);
			if(temp == 0) {
				return result;
			}
		}
		
		result = true;
		
		return result;
	}
	
	public String checkSameCards(int[] rankList) {
		int pairs;
		int trikind;
		int forkind;
	}

}
