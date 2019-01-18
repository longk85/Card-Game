package poker;

import java.util.Random;

public class VideoPoker {
	static private int handCardsNum = 5;
	private CardDeck newDeck = new CardDeck();
	private int[] handCards = new int[handCardsNum];
	private String[] rankList = {"Royal flush", "Straight flush", "4 of a kind", "Full house", "Flush", "Straight", "3 of a kind", "Two pair", "Jacks or better"};
	private int[] reward = {4000, 250, 125, 45, 30, 20, 15, 10, 5};
	
	public VideoPoker() {
		
	}
	
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
	
	public String checkRank(int[] handCards) {
		String rank = "";
		int[] suitList = new int[handCards.length];
		int[] rankList = new int[handCards.length];
		
		return rank;
	}

}
