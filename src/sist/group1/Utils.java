package sist.group1;

public class Utils {
	
	/*
	 @Param
<<<<<<< HEAD
	 ê´€ë¦¬ìž ID,PW -> admin123
	 Singleton -> ì¸ìŠ¤í„´ìŠ¤ë¡œ Utils utils = Utils.getInstance(); í˜•ì‹ìœ¼ë¡œ ê°€ì ¸ì˜¤ë©´ ë©ë‹ˆë‹¤.
	 í´ëž˜ìŠ¤ ë‚´ì˜ ì•„ë¬´ ê³³ì—ì„œë‚˜ utils.ë©”ì†Œë“œëª…(); ìœ¼ë¡œ ê°’ í•¸ë“¤ë§ ê°€ëŠ¥
	 */
	private static final String ADMIN ="admin123";
	private static Utils utils = null;
	private String uid;
=======
	 °ü¸®ÀÚ ID,PW -> admin123
	Singleton -> ÀÎ½ºÅÏ½º·Î Utils utils = Utils.getInstance(); Çü½ÄÀ¸·Î °¡Á®¿À¸é µË´Ï´Ù.
	 Å¬·¡½º ³»ÀÇ ¾Æ¹« °÷¿¡¼­³ª utils.¸Þ¼Òµå¸í(); À¸·Î °ª ÇÚµé¸µ °¡´É
	 */
	//°ü¸®ÀÚ ¾ÆÀÌµð, ºñ¹Ð¹øÈ£
	private final String ADMIN ="admin123";
	//Utils static °´Ã¼ »ý¼º
	private static Utils utils = null;
	//ÇöÀç »ç¿ëÀÚ Á¤º¸ ÀúÀå¿ë º¯¼ö ¼±¾ð
	private User user;
>>>>>>> upstream/master
	
	private Utils() {
		
	}
	
	public static Utils getInstance() {
		if(utils == null) {
			utils = new Utils();
		}
		return Utils.utils;
	}
	
	public static String getAdmin() {
		return Utils.ADMIN;
	}
	
	public void setCurrentUser(String uid) {
		this.uid = uid;
	}
	
	public String getCurrentUser() {
		return uid;
	}

}