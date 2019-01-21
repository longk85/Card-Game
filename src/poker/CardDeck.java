package poker;

/**
 * @author Moebear
 * 这个类用来表示一副扑克牌。
 */
public class CardDeck {
	private int cardsNum = 52; //一副牌的牌数
	private String[] suits = {"Heart", "Spade", "Dimond", "Club"}; //四种花色的名字
	private String[] ranks = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"}; //十三个牌值
	private int[] cardsID = new int[cardsNum]; //每张牌的ID序列
	private String[][] cardsName = new String[suits.length][ranks.length]; //每张牌的名字，包括花色和牌值
	
	/**
	 * The constructing method of class CardDeck.
	 */
	public CardDeck() {
		for(int i=0; i<cardsNum; i++) {
			cardsID[i] = i;
		}
	}
	
	/**
	 * 通过牌的ID来获取牌的名字
	 * @param cardID 牌的ID
	 * @return 牌的名字
	 */
	public String getCardName(int cardID) {
		int suitsID;
		int ranksID;
		String cardName;
		
		suitsID = cardID / 13;
		ranksID = cardID % 13;
		cardName = this.cardsName[suitsID][ranksID];
		
		return cardName;
	}

	public int getCardsID(int index) {
		return cardsID[index];
	}

	public void setCardsID(int index, int cardID) {
		this.cardsID[index] = cardID;
	}

	public int getCardsNum() {
		return cardsNum;
	}

	public void setCardsNum(int cardsNum) {
		this.cardsNum = cardsNum;
	}

}
