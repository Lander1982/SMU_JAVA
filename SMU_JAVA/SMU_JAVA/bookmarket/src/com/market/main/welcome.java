package com.market.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Scanner;
import com.market.bookitem.Book;
import com.market.cart.Cart;
import com.market.member.Admin;
import com.market.member.User;
import com.market.member.Person;

public class welcome {
	static final int NUM_BOOK = 3;//도서의 개수에 대한 상수 NUM_BOOK 선언
	static final int NUM_ITEM = 7;//도서 정보의 개수에 대한 상수 NUM_ITEM 선언
	//static CartItem[] mCartItem = new CartItem[NUM_BOOK];
	//static int mCartCount = 0;
	static Cart mCart = new Cart();
	static User mUser;
	public static void main(String[] args) {
		//String[][] mBook = new String[NUM_BOOK][NUM_ITEM];//도서 정보의 저장할 mBook 
		Book[] mBookList = new Book[NUM_BOOK];
		Scanner scanner = new Scanner(System.in);
		String name, phone;
		int menuNumber;
		System.out.print("당신의 이름을 입력하세요: ");
		name = scanner.next();
		System.out.print("당신의 전화번호를 입력하세요: ");
		phone = scanner.next();
		mUser = new User(name, phone);
		String hello = "서초도서관에 오신걸 환영합니다.";
		System.out.println("******************************************************");
		System.out.println("\t\t" + hello);
		String greeting = "Welcome to Shopping Mall";
		String tagline = "Welcome to Book Market";
		Boolean quit = false;
		//System.out.println("book번호를 입력하세요: ");
		//int book = scanner.nextInt();
		while (!quit)
		{
			System.out.println("******************************************************");
			System.out.println("\t\t" + greeting);
			System.out.println("\t\t" + tagline);
			menuIntroduction();
			System.out.print("메뉴 번호를 선택해 주세요: ");
			menuNumber = scanner.nextInt();
			if (menuNumber < 1 || menuNumber > 9) {
				System.out.println("1부터 9까지의 숫자를입력하세요.");
			}
			else {
				switch (menuNumber)
				{
				case 1:
					System.out.println("1. 고객 정보 확인하기");
					System.out.print("고객님의 전화번호 끝 4자리를 입력하세요:");
					String input = scanner.next();
					while (input.length() == 4)
					{
						if (input.equals(phone.substring(phone.length() - 4))) 
						{
							menuGuestInfo(name, phone);
							break;
				        } 
						else 
						{
				            System.out.println("입력한 끝 4자리와 원래 전화번호의 끝 4자리가 다릅니다.");
				        }
						System.out.println("고객님의 전화번호 끝 4자리를 입력하세요:");
						input = scanner.next();
					}
					break;
				case 2:
					menuCartItemList();
					break;
				case 3:
					menuCartClear();
					break;
				case 4:
					//menuCartAddItem(mBook);
					menuCartAddItem(mBookList);
					break;
				case 5:
					menuCartItemCount();
					break;
				case 6:
					menuCartRemoveItem();
					break;
				case 7:
					menuCartBill();
					break;
				case 8:
					menuExit();
					quit = true;
					break;
				case 9:
					menuAdminLogin();
					break;
				default:
					System.out.println("잘못 입력함");
					break;
				}
				System.out.println("도서관 관리 프로그램을 종료합니다.");
			}
		}
	}	
	public static void menuIntroduction() 
	{
		System.out.println("******************************************************");
		System.out.println("1. 고객 정보 확인하기 \t4.장바구니에 항목 추가하기");
		System.out.println("2. 장바구니 상품 목록 보기 \t5. 장바구니에 항목 수량 줄이기");
		System.out.println("3. 장바구니 비우기 \t\t6. 장바구니의 항목 삭제하기");
		System.out.println("7. 영수증 표시하기 \t\t8. 종료");
		System.out.println("9. 관리자 로그인");
		System.out.println("******************************************************");
	}
	public static void menuGuestInfo(String name, String mobile)
	{
		System.out.println("현재 고객 정보");
		//System.out.println("이름: " + name + "전화번호: " + mobile);
		//Person person = new Person(name, mobile);
		//System.out.println("이름 " + person.getName() + " 연락처 " + person.getPhone());	
		System.out.println("이름: " + mUser.getName() + "   연락처: " + mUser.getPhone());
	}
	public static void menuCartItemList()
	{
		/*System.out.println("2. 장바구니 상품 목록 보기");
		System.out.println("------------------------------");
		System.out.println("    도서ID   \t|   수량 \t|     합계  \t|");
		for (int i = 0; i < mCartCount; i++) {
			System.out.print("   " + mCartItem[i].getBookID() + " \t| ");
			System.out.print("   " + mCartItem[i].getQuantity() + " \t| ");
			System.out.print("   " + mCartItem[i].getTotalPrice() + " \t| ");
			System.out.println("  ");
		}
		System.out.println("------------------------------");*/
		if (mCart.mCartCount >= 0) {
			mCart.printCart();
		}
	}
	public static void menuCartClear()	
	{
		//System.out.println("3. 장바구니 비우기");
		if (mCart.mCartCount == 0)
			System.out.println("장바구니에 항목이 없습니다.");
		else {
			System.out.println("장바구니의 모든 항목을 삭제하겠습니까? Y | N ");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			if (str.toUpperCase().equals("Y")) {
				System.out.println("장바구니의 모든 항목을 삭제했습니다.");
				//mCart.mCartItem = new CartItemBook[NUM_BOOK];
				mCart.deleteBook();
			}
		}
	}
	public static void menuCartAddItem(Book[] booklist)
	{
		//System.out.println("4. 장바구니에 항목 추가하기");
	    BookList(booklist);
	    /*for (int i = 0; i < NUM_BOOK; i++) {
	        for (int j = 0; j < NUM_ITEM; j++) {
	            System.out.println(book[i][j] + " | ");
	        }
	        System.out.println("");
	    }*/
	    mCart.printBookList(booklist);
	    boolean quit = false;
	    while (!quit) {
	        System.out.println("장바구니에 추가할 도서의 ID를 입력하세요: ");
	        Scanner input = new Scanner(System.in);
	        String str = input.nextLine();
	        boolean flag = false;
	        int numId = -1;
	        for (int i = 0; i < NUM_BOOK; i++) {
	            if (str.equals(booklist[i].getBookId())) {
	                numId = i;
	                flag = true;
	                break;
	            }
	        }
	        if (flag) {
	            System.out.println("장바구니에 추가하시겠습니까? (Y | N)");
	            str = input.nextLine();
	            if (str.toUpperCase().equals("Y")) {
	                System.out.println(booklist[numId] + " 도서가 장바구니에 추가되었습니다.");
	                // 카트에 넣기
	                if (!isCartInBook(booklist[numId].getBookId())) {
	                	//mCartItem[mCartCount++] = new CartItem(book[numId]);
	                    //mCartItem[mCartCount++] = new CartItemBook(booklist[numId]);
	                    //mCart.mCartCount = mCartCount++;
	                    //mCartCount++;
	                    mCart.insertBook(booklist[numId]);
	                }
	            }
	            quit = true;
	        } else {
	            System.out.println("다시 입력하세요.");
	        }
	    }
	}
	public static void menuCartItemCount()
	{
		System.out.println("5. 장바구니에 항목 수량 줄이기");
	}
	public static void menuCartRemoveItem()
	{
		//System.out.println("6. 장바구니의 항목 삭제하기");
		if (mCart.mCartCount == 0)
			System.out.println("장바구니에 항목이 없습니다.");
		else {
			menuCartItemList();
			boolean quit = false;
			while (!quit) {
				System.out.print("장바구니에서 삭제할 도서의ㅣ ID를 입력하세요: ");
				Scanner input = new Scanner(System.in);
				String str = input.nextLine();
				boolean flag = false;
				int numId = -1;
				for (int i = 0; i < mCart.mCartCount; i++) {
					if (str.equals(mCart.mCartItem[i].getBookID())) {
						numId = i;
						flag = true;
						break;
					}
				}
				if (flag) {
					System.out.println("장바구니의 항목을 삭제하겠습니까? Y | N ");
					str = input.nextLine();
					if (str.toUpperCase().equals("Y")) {
						System.out.println(mCart.mCartItem[numId].getBookID() + "장바구니에서 도서가 삭제되었습니다.");
						//배열 이동
						/* CartItemBook[] cartItem = new CartItemBook[NUM_BOOK];
							int num = 0;
							for (int i = 0; i < mCartCount; i++) {
							 	if (numId! = o) cartIte,[num++] = mCart.mCartItem[i];
							 mCartCount = num;
							 mCart.mCartCount = num;
							 mCart.mCartItem = cartItem;
						 */
						mCart.removeCart(numId);;
					}
					quit = true;
				}
				else System.out.println("다시 입력해 주세요.");
			}
		}
	}
	public static void menuCartBill()
	{
		//System.out.println("7. 영수증 표시하기");
		if (mCart.mCartCount == 0) System.out.println("장바구니에 항목이 없습니다.");
		else {
			System.out.println("배송받을 분은 고객 정보와 같습니까? Y | N ");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			if (str.toUpperCase().equals("Y")) {
				System.out.print("배송지를 입력해 주세요 ");
				String address = input.nextLine();
				printBill(mUser.getName(), String.valueOf(mUser.getPhone()), address);
			}
			else {
				System.out.print("배송받을 고객명을 입력하세요 ");
				String name = input.nextLine();
				System.out.print("배송받을 연락처를 입력하세요 ");
				String phone = input.nextLine();
				System.out.print("배송받을 배송지를 입력하세요 ");
				String address = input.nextLine();
				printBill(name, phone, address);
			}
		}
	}
	public static void printBill(String name, String phone, String address) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/yyyy");
		String strDate = formatter.format(date);
		System.out.println();
		System.out.println("--------------배송받을 고객 정보--------------");
		System.out.println("고객명: " + name + "   \t\t연락처: " + phone);
		System.out.println("배송지: " + address + "\t\t발송일: " + strDate);
		mCart.printCart();
		int sum = 0;
		for (int i = 0; i < mCart.mCartCount; i++)
			sum += mCart.mCartItem[i].getTotalPrice();
		System.out.println("\t\t주문 총 금액: " + sum + "원\n");
		System.out.println("----------------------------------------");
		System.out.println();
	}
	public static void menuExit()
	{
		System.out.println("8. 종료");
	}
	public static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요: ");
		Scanner input = new Scanner(System.in);
		System.out.print("아이디: ");
		String adminId = input.next();
		System.out.print("비밀번호: ");
		String adminPW = input.next();
		Admin admin = new Admin(mUser.getName(), mUser.getPhone());
		if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
			System.out.println("이름: " + admin.getName() + "연락처: " + admin.getPhone());
			System.out.println("아이디: " + admin.getId() + "비밀번호: " + admin.getPassword());
		}
		else
			System.out.println("관리자 정보가 일치하지 않습니다.");
	}
	public static void BookList(Book[] booklist) {
		booklist[0] = new Book("ISBN1234", "쉽게 배우는 JSP 웹 프로그래밍", 27000);
		booklist[0].setAuthor("송미영");
		booklist[0].setDescription("단계별로 쇼핑몰을 구분하여 배우는 JSP 웹 프로그래밍");
		booklist[0].setCategory("IT전문서");
		booklist[0].setReleaseDate("2018/10/08");
		
		booklist[1] = new Book("ISBN1235", "안드로이드 프로그래밍", 33000);
		booklist[1].setAuthor("우재남");
		booklist[1].setDescription("실습 단계별 명쾌한 멘토링");
		booklist[1].setCategory("IT전문서");
		booklist[1].setReleaseDate("2022/01/22");
		
		booklist[2] = new Book("ISBN1236", "스크래치", 22000);
		booklist[2].setAuthor("고광일");
		booklist[2].setDescription("컴퓨터 사고력을 키우는 블록 코딩");
		booklist[2].setCategory("컴퓨터입문서");
		booklist[2].setReleaseDate("2019/06/10");
	}
	/*public static void BookList(String[][] book) {
		book[0][0] = "ISBN1234";
		book[0][1] = "쉽게 배우는 JSP 웹 프로그래밍";
		book[0][2] = "27000";
		book[0][3] = "송미영";
		book[0][4] = "단계별로 쇼핑몰을 구분하여 배우는 JSP 웹 프로그래밍";
		book[0][5] = "IT전문서";
		book[0][6] = "2018/10/08";
		
		book[1][0] = "ISBN1235";
		book[1][1] = "안드로이드 프로그래밍";
		book[1][2] = "33000";
		book[1][3] = "우재남";
		book[1][4] = "실습 단계별 명쾌한 멘토링";
		book[1][5] = "IT전문서";
		book[1][6] = "2022/01/22";
		
		book[2][0] = "ISBN1236";
		book[2][1] = "스크래치";
		book[2][2] = "22000";
		book[2][3] = "고광일";
		book[2][4] = "컴퓨터 사고력을 키우는 블록 코딩";
		book[2][5] = "컴퓨터입문서";
		book[2][6] = "2019/06/10";
	}*/
	public static boolean isCartInBook(String bookId) {
		/*boolean flag = false;
		for (int i = 0; i < mCartCount; i++) {
			if (bookId == mCartItem[i].getBookID()) {
				mCartItem[i].setQuantity(mCartItem[i].getQuantity()+1);
				flag = true;
			}
		}
		return flag;*/
		return mCart.isCartInBook(bookId);
	}
}