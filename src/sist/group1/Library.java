package sist.group1;

import java.util.Scanner;

public class Library {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		LibraryService service = new LibraryService();
		try {
<<<<<<< HEAD
			while(true) {
			System.out.println("<ìŒìš© ë„ì„œê´€>");
			System.out.println("ë¡œê·¸ì¸ì´ í•„ìš”í•©ë‹ˆë‹¤. íšŒì›ê°€ì… ë˜ëŠ” ë¡œê·¸ì¸ì„ ì§„í–‰ í•´ì£¼ì„¸ìš”.");
			System.out.println("1.íšŒì›ê°€ì… 2.ë¡œê·¸ì¸ 0.ì¢…ë£Œ");
			System.out.print("ì„ íƒ>");
			
			int input = sc.nextInt();
			sc.nextLine();
			
			if(input == 0) break;
				switch(input) {
				case 1: service.register(sc);break;
				case 2: service.login(sc);break;
				default : System.out.println("ì•Œ ìˆ˜ ì—…ëŠ” ì…ë ¥ì…ë‹ˆë‹¤. ë‹¤ì‹œ ì‹œë„í•˜ì„¸ìš”.");break;
=======
			while (true) {
				System.out.println();
				System.out.println("<<<½Ö¿ë µµ¼­°ü>>>>");
				System.out.println("·Î±×ÀÎÀÌ ÇÊ¿äÇÕ´Ï´Ù. È¸¿ø°¡ÀÔ ¶Ç´Â ·Î±×ÀÎÀ» ÁøÇà ÇØÁÖ¼¼¿ä.\n");
				System.out.println("1.È¸¿ø°¡ÀÔ   2.·Î±×ÀÎ   0.Á¾·á");
				System.out.print("¼±ÅÃ> ");
				int input = sc.nextInt();
				sc.nextLine();
				if (input == 0)
					break;
				switch (input) {
				case 1:
					service.register(sc);
					break;
				case 2:
					service.login(sc);
					break;
				default:
					System.out.println("¾Ë ¼ö ¾ø´Â ÀÔ·ÂÀÔ´Ï´Ù. ´Ù½Ã ½ÃµµÇÏ¼¼¿ä.");
					break;
>>>>>>> upstream/master
				}
			}
			sc.close();
			service.fileSave();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
	
}
=======

}
>>>>>>> upstream/master
