package poker;

import org.junit.Assert;
import org.junit.Test;

public class VideoPokerTest {
	
	VideoPoker vp = new VideoPoker();
	int[] handCards = {1, 3, 2, 4, 0};
	int[] suitList = {0, 0, 1, 2, 3};
	int[] rankList = {1, 2, 3, 4, 5};

	@Test
	public void testCheckRank() {
		Assert.assertEquals("", vp.checkRank(handCards));
	}

	@Test
	public void testCheckFlush() {
		Assert.assertEquals(false, vp.checkFlush(suitList));
	}

	@Test
	public void testCheckStraight() {
		Assert.assertEquals(true, vp.checkStraight(rankList));
	}

	@Test
	public void testCheckSameCards() {
		Assert.assertEquals("nothing", vp.checkSameCards(rankList));
	}

	@Test
	public void testCheckHighPair() {
		Assert.assertEquals(true, vp.checkHighPair(rankList));
	}

}
