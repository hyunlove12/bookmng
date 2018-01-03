package sist.group1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class LibraryDAO {

	/*
	
	this.users.add(new User("","","","","")); 전체 사용자 정보 저장용 리스트에 넣는 방법
	this.books.add(new Book("","","","")); 전체 책 정보 저장용 리스트에 넣는 방법
	this.checkOuts.add(new CheckOut("B001","U001","2017-12-27")); 대출 전체 정보 리스트에 넣는 방법
	for(CheckOut c : checkOuts) { 리스트에서 특정 매개변수 값에 맞게 객체 정보 꺼내오는 방법
		if(c.getcBookNo().equals("B001")) {
		
		}
	 Set<String>key = TestClass.testBook.keySet();
	 Iterator it = key.iterator();
	 while(it.hasNext()){ 맵에서 특정 매개변수 값에 맞게 객체 정보 꺼내오는 방법
	 	String key = (String)it.next();
	 	Book b = TestClass.testBook.get(key);
	 }
		
	}*/
	
	/*
	 @Param
	 사용자 정보(현재 접속한 사용자 아이디, 책 정보), 사용자 전체 정보, 책 전체 정보, 대출 전체 정보 
	 */
	
	private Utils utils = Utils.getInstance();
	private Map<String, User> users = new HashMap<String, User>();
	private Map<String, Book> books = new HashMap<String, Book>();
	private List<CheckOut>checkOuts = new ArrayList<CheckOut>();
	
	
	
	private static final String USER_FILE = "users.data";
	private static final String BOOK_FILE = "books.data";
	private static final String CHECKOUT_FILE = "checkOuts.data";

	public LibraryDAO() {
		File file = new File(USER_FILE);
		File file1 = new File(BOOK_FILE);
		File file2 = new File(CHECKOUT_FILE);
		Object obj = null;
		
		if (file.exists()) {
			obj = deSerialization(USER_FILE);
			this.users = (Map<String, User>)obj;
			obj = null;
		}
		if(file1.exists()) {
			obj = deSerialization(BOOK_FILE);
			this.books = (Map<String, Book>)obj;
			obj = null;
		}
		if(file2.exists()) {
			obj = deSerialization(CHECKOUT_FILE);
			this.checkOuts = (List<CheckOut>)obj;
		}
	}
	
	
	/*
	-회원가입이 완료 되었습니다.
	-이미 존재하는 id 입니다. 다시 입력해주세요.
	-잘못된 비밀번호 형식입니다. 다시 입력해주세요.
	-잘못된 전화번호 형식입니다. 다시 입력해주세요.*/
	
	public void register(String userId, String password, String name, String phoneNumber) {
		
		User user = null;
		String userNo = "U001";
		
		Set<String>key = this.users.keySet();
		List<String>temp = new ArrayList<String>(key);
				
		if(this.users.size()>0){
			user = this.users.get("");
			userNo = String.format("U%03d", Integer.parseInt(user.getUserNo().substring(1)) + 1); 
		}
		
		User u = new User(userNo, userId, password, name, phoneNumber);
		this.users.put(userNo, u);
	}
	
	/*
	 @param
	 아이디, 비밀번호 입력 값
	 사용자 로그인용 메소드, 현재 사용자의 고유 번호 저장
	 */
	public void login(String userId, String password) {
		
	}
	
	/*
	 사용자 아이디 이미 존재 여부 확인 예외처리
	 */
	public void isExistUser(String userId) throws ExistUserException{
		
	}
	
	/*
	 @Param 
	 파일이름
	 역직렬화
	 */
	public Object deSerialization(String fileName) {
		Object result = null;
		FileInputStream fs = null;
		ObjectInputStream os = null;
		try {
			fs = new FileInputStream(fileName);
			os = new ObjectInputStream(fs);
			result = os.readObject();			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//프로그램 종료시 users, books, checkOuts 데이터 저장
	public ObjectOutputStream fileSave() {
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		try {
			fs = new FileOutputStream(USER_FILE);
			os = new ObjectOutputStream(fs);
			os.writeObject(this.users);
			fs = new FileOutputStream(BOOK_FILE);
			os = new ObjectOutputStream(fs);
			os.writeObject(this.books);
			fs = new FileOutputStream(CHECKOUT_FILE);
			os = new ObjectOutputStream(fs);
			os.writeObject(this.checkOuts);
			//컬렉션 저장소에 저장된 모든 정보를 직렬화 시도
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return os;
	}
	
	

	
	
	// 전체 도서 출력

	public String viewAllBooks() {

		//0: 비치중, 1: 대출중, 2:연체중 을 수정하기
		this.changeBookStatus();
		
		StringBuilder sb = new StringBuilder();
		Set<String> key = this.books.keySet();

		// BOOK의 KEY값을 저장하기 위한 임시변수 LIST b
		List<String> b = new ArrayList<String>(key);

		Collections.sort(b, new Comparator<String>() {
			@Override
			public int compare(String b1, String b2) {
				return b1.compareTo(b2);
			}
		});

		for (String i : b) {
			sb.append(String.format("%-20s%-40s%-60s%-80s%-100s%n", this.books.get(i).getBookNo(),
					this.books.get(i).getBookTitle(), this.books.get(i).getPublisher(), this.books.get(i).getAuthor()
					,this.books.get(i).getBookStatusString()
					));
			

		}
		return sb.toString();

	}
	
	
	//0: 비치중, 1: 대출중, 2:연체중
	//연체 값(0: 비치중, 1: 대출중, 2:연체중) 변경 메소드
	//전체 변경 값으로 수정요
	//this.changeBookStatus(this.books.get(i).getBookStatus())));
	public String changeBookStatus(int BookStatus) {

		String temp = null;
		switch (BookStatus) {
		case 0:temp = "비치 중";break;
		case 1:temp = "대출 중";break;
		case 2:temp = "연체 중";break;
		}
		return temp;
	}

	//전체 북상태값 변경
	public void changeBookStatus() {
		String temp = null;
		Set<String> set = books.keySet();
		List<String> list = new ArrayList<String>(set);
		
		for(String i : list) {
	
		switch (this.books.get(i).getBookStatus()) {
		case 0:temp = "비치 중";break;
		case 1:temp = "대출 중";break;
		case 2:temp = "연체 중";break;
		   }
		this.books.get(i).setBookStatusString(temp);
		}
	
	}
	
	
	// 전체 사용자 출력
	public String viewAllUsers() {

		StringBuilder sb = new StringBuilder();
		Set<String> key = this.users.keySet();

		// USER의 KEY값을 저장하기 위한 임시변수 LIST b
		List<String> b = new ArrayList<String>(key);

		Collections.sort(b, new Comparator<String>() {
			@Override
			public int compare(String b1, String b2) {
				return b1.compareTo(b2);
			}
		});

		for (String i : b) {

			sb.append(String.format("%-10s%-10s%-10s%-10s%n", this.users.get(i).getUserNo(),
					this.users.get(i).getUserId(), this.users.get(i).getName(), this.users.get(i).getphone()));
		}
		return sb.toString();
	}

	// 연체 도서 전체 보기
	public String viewOverdueBooks() {

		// 연체일 계산 메소드 호출
		this.setAllOverdueDays();
 
		// BookNO와 UserNo를 저장하기 위한 임시변수
		String temp = null;
		String temp1 = null;

		StringBuilder sb = new StringBuilder();

		Set<String> key = this.books.keySet();

		List<String> b = new ArrayList<String>(key);
		Collections.sort(b, new Comparator<String>() {
			@Override
			public int compare(String b1, String b2) {
				return b1.compareTo(b2);
			}
		});
		
		
		for (String c : b) {
			if (this.books.get(c).getBookStatus() == 2) {
				sb.append(this.books.get(c).getBookNo());
				sb.append(this.books.get(c).getBookTitle());
				sb.append(this.books.get(c).getAuthor());
				sb.append(this.books.get(c).getPublisher());
				temp = this.books.get(c).getBookNo();

			}
			for (CheckOut d : checkOuts) {

				if (temp == d.getcBookNo() && d.getReturnDate() == null) {
					sb.append(d.getCheckOutDate());
					sb.append(d.getDueDate());
					sb.append(d.getOverdueDays());
					temp1 = d.getcUserNo();
				}

			}

			if (temp1 == null)
				continue;
			sb.append(this.users.get(temp1).getName());
			sb.append(this.users.get(temp1).getUserNo());

			temp = null;
			temp1 = null;
		}
		return sb.toString();
	}
		
	// 연체일수 계산 메소드
	public void setAllOverdueDays() {
		
		
		int a = 0;
		LocalDate nowDate = LocalDate.now();

		for (CheckOut c : this.checkOuts) {
			if (c.getReturnDate() == null && nowDate.isAfter(LocalDate.parse(c.getDueDate()))) {
				Period period = Period.between(nowDate, LocalDate.parse(c.getDueDate()));
				a = period.getDays();
				c.setOverdueDays(a);

			}
		}
	}
		
		/*

		// 연체일수 계산 메소드
		//효율적이 것 사용
		public void setOverdueDays(String BookNo) {
			int a = 0;
			LocalDate nowDate = LocalDate.now();
			
			//if(c.getReturnDate() == null)
		
			for (CheckOut c : this.checkOuts) {
				if (BookNo == c.getcBookNo() && nowDate.isAfter(LocalDate.parse(c.getDueDate()))) {
					Period period = Period.between(nowDate, LocalDate.parse(c.getDueDate()));
					a = period.getDays();
					c.setOverdueDays(a);
					
				}
			}
			
		
		}*/
		
	
		//예외처리
		//패턴형식
		//매개변수 : 책 등록번호
		//반납되지 않은 책만 수정가능
		/*
		public void changeDueDate(String bookNo, String dueDate) throws CheckDueDateException {

			try {
			String checkBookNo = "[B]\\d3";
			String checkDueDate = "\\d4";

			//날짜 형식?
			//사용자 정의 예외
			boolean result = Pattern.matches(checkBookNo, bookNo);
			}catch(Exception e) {
				
			}
			
			
			for(CheckOut d : TestClass.testCheckOut ) {
				if(bookNo == d.getcBookNo() && d.getReturnDate() == null) {
					d.setDueDate(dueDate);
					
					
				}
				
			}
			
			
		}*/
		
		
	// 반납 예정일 변경
	/*
	  @Param 
	  수정할 책의 고유번호, 수정할 날짜
	 */
	
	
	
	public String changeDueDate(String bookNo, String dueDate) {
 
		StringBuilder sb = new StringBuilder();
		boolean temp = books.containsKey(bookNo);
		if(temp == false) {
			sb.append("잘못된 형식입니다.");
		}		
		for (CheckOut d : checkOuts) {
			if (bookNo == d.getcBookNo() && d.getReturnDate() == null) {
				d.setDueDate(dueDate);
				sb.append("반납 예정일 변경이 완료되었습니다.");
			}
		}
		return sb.toString();
	}
		
	
	
	
}