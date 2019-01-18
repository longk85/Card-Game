package poker;

public class CardDeck {
	private int cardsNum = 52;
	private String[] suits = {"Heart", "Spade", "Dimond", "Club"};
	private String[] ranks = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
	private int[] cardsID = new int[cardsNum];
	private String[][] cardsName = new String[suits.length][ranks.length];
	
	public CardDeck() {
		for(int i=0; i<cardsNum; i++) {
			cardsID[i] = i;
		}
		for(int i=0; i<suits.length; i++) {
			for(int j=0; j<ranks.length; j++) {
				cardsName[i][j] = suits[i] + " " + ranks[j];
			}
		}
	}
	
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
