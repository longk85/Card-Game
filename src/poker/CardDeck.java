package poker;

/**
 * @author Moebear
 * �����������ʾһ���˿��ơ�
 */
public class CardDeck {
	private int cardsNum = 52; //һ���Ƶ�����
	private String[] suits = {"Heart", "Spade", "Dimond", "Club"}; //���ֻ�ɫ������
	private String[] ranks = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"}; //ʮ������ֵ
	private int[] cardsID = new int[cardsNum]; //ÿ���Ƶ�ID����
	private String[][] cardsName = new String[suits.length][ranks.length]; //ÿ���Ƶ����֣�������ɫ����ֵ
	
	/**
	 * The constructing method of class CardDeck.
	 */
	public CardDeck() {
		for(int i=0; i<cardsNum; i++) {
			cardsID[i] = i;
		}
	}
	
	/**
	 * ͨ���Ƶ�ID����ȡ�Ƶ�����
	 * @param cardID �Ƶ�ID
	 * @return �Ƶ�����
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
