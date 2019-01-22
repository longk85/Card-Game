package poker;

import java.util.*;

/**
 * @author moebear
 * Jacks or Better 的游戏逻辑
 */
public class VideoPoker {
	private int handCardsNum = 5; //手牌数量
	private CardDeck newDeck = new CardDeck(); //每局游戏生成一副新牌
	private int[] handCards = new int[handCardsNum]; //手牌ID序列
	private String[] rankList = {"Royal flush", "Straight flush", "4 of a kind", "Full house", "Flush", "Straight", "3 of a kind", "Two pair", "Jacks or better"}; //中奖牌型名称
	private int[] reward = {4000, 250, 125, 45, 30, 20, 15, 10, 5}; //中奖牌型奖励
	
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
	 * 更换不要的手牌
	 * @param changeFlag 
	 */
	public void changeHandCards(boolean[] changeFlag) {
		Random random = new Random();
		int end = newDeck.getCardsNum() - 6; //从剩下47张牌中选择
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
	 * 确定中奖牌型
	 * @param handCards 手牌ID序列
	 * @return 中奖类型
	 */
	public String checkRank(int[] handCards) {
		String rank = "";
		int[] suitList = new int[handCards.length]; //花色序列
		int[] rankList = new int[handCards.length]; //牌值序列
		boolean royal = false;
		boolean flush = false;
		boolean straight = false;
		String sameCard = "nothing";
		
		/* 确定花色序列 */
		for(int i=0; i<handCards.length; i++) {
			int num;
			num = handCards[i] / 13;
			suitList[i] = num;
		}
		
		/* 确定牌值序列 */
		for(int i=0; i<handCards.length; i++) {
			int num;
			num = handCards[i] % 13;
			rankList[i] = num;
		}
		
		flush = this.checkFlush(suitList);
		straight = this.checkStraight(rankList);
		sameCard = this.checkSameCards(rankList);
		royal = this.checkRoyal(rankList);
		
		if(royal == true && flush == true && straight == true) {
			rank = this.rankList[0];
		} else if(royal == false && flush == true && straight == true) {
			rank = this.rankList[1];
		} else if(straight == false && flush == true) {
			rank = this.rankList[4];
		} else if(straight == true && flush == false) {
			rank = this.rankList[5];
		} else if(sameCard != "" && sameCard != "nothing" && sameCard != "pair") {
			rank = sameCard;
		} else if(sameCard == "pair") {
			if(this.checkHighPair(rankList) == true) {
				rank = this.rankList[8];
			}else{
				rank = "";
			}
		} else {
			rank = "";
		}
		
		return rank;
	}
	
	/**
	 * 确定奖励点数
	 * @param rank 奖励等级
	 * @return 奖励点数
	 */
	public int getRewardPoints(String rank) {
		int rewardPoints = 0;
		
		for(int i=0; i<this.rankList.length; i++) {
			if(rank == rankList[i]) {
				rewardPoints = this.reward[i];
			}
		}
		
		return rewardPoints;
	}
	
	/**
	 * 判断是否同花
	 * @param suitList 手牌花色序列
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
	 * 判断是否顺子
	 * @param rankList 手牌牌值序列
	 * @return 是或否
	 */
	public boolean checkStraight(int[] rankList) {
		List<Integer> straightCheck = new ArrayList<Integer>();
		boolean result = false;
		
		for(int i=0; i<rankList.length; i++) {
			straightCheck.add(rankList[i]);
		}
		
		/* 按大小顺序重新排列手牌 */
		Collections.sort(straightCheck);
		
		/* 计算最大最小牌差值是否超过4 */
		int len = straightCheck.size();
		int temp = Math.abs(straightCheck.get(0) - straightCheck.get(len - 1));
		if(temp != 4 && temp != 12) {
			return result;
		}
		
		/* 检查是否有相同牌值 */
		for(int i=0; i<len-1; i++) {
			temp = straightCheck.get(i+1) - straightCheck.get(i);
			if(temp == 0) {
				return result;
			}
		}
		
		result = true;
		
		return result;
	}
	
	/**
	 * 检查相同的牌的情况
	 * @param rankList 手牌牌值序列
	 * @return 相同牌的情况
	 */
	public String checkSameCards(int[] rankList) {
		int pairs = 0; //对子
		int trikind = 0; //三张一样
		int forkind = 0; //四张一样
		int temp = 0;
		HashSet<Integer> sameCard = new HashSet<Integer>();
		String reward = "nothing";
		
		for(int i=0; i<rankList.length - 1; i++) {
			temp = 0;
			if(!sameCard.contains(rankList[i])) {
				sameCard.add(rankList[i]);
				for(int j=i+1; j<rankList.length; j++) {
					if(rankList[i] == rankList[j]) {
						temp++;
					}
				}
				switch(temp) {
				case 1: pairs++; break;
				case 2: trikind++; break;
				case 3: forkind++; break;
				default: break;
				}
			}
		}
		
		if(forkind == 1) {
			reward = this.rankList[2];
		}
		
		if(trikind == 1 && pairs == 1) {
			reward = this.rankList[3];
		}
		
		if(trikind == 1 && pairs != 1) {
			reward = this.rankList[6];
		}
		
		if(pairs == 2) {
			reward = this.rankList[7];
		}
		
		if(pairs == 1 && trikind != 1) {
			reward = "pair";
		}
		
		return reward;
	}
	
	/**
	 * 检查是否高对
	 * @param rankList 手牌牌值序列
	 * @return 是或否
	 */
	public boolean checkHighPair(int[] rankList) {
		int pairRank = 0;
		boolean result = false;
		
		for(int i=0; i<rankList.length-1; i++) {
			for(int j=i+1; j<rankList.length; j++) {
				if(rankList[i] == rankList[j]) {
					pairRank = rankList[i];
				}
			}
		}
		
		if(pairRank < 4) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 检查是否皇家同花顺
	 * @param rankList 手牌牌值序列
	 * @return 是或否
	 */
	public boolean checkRoyal(int[] rankList) {
		boolean A = false;
		boolean K = false;
		boolean royal = false;
		
		for(int i=0; i<rankList.length; i++) {
			if(rankList[i] == 0) {
				A = true;
			}
			if(rankList[i] == 1) {
				K = true;
			}
		}
		
		if(A == true && K == true) {
			royal = true;
		}
		
		return royal;
	}

}
