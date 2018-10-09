package btl1;
import java.util.Scanner;
import java.io.*;
import java.lang.String;
import btl1.Word;
public class TuDien
{
	public static int i;
	public static Word words[];// khoi tao mang words kiêu Word
	public static Scanner input = new Scanner(System.in);
	public static final int n =100;
	public static int currentWords;
	public static Word result[];// khai bao mang
	public static Word temp;

	// tao phuong thuc dictionnary
	public static void menu()
	{
		System.out.printf("\n DICTIONARY");
		System.out.printf("\n- 1. New word");
		System.out.printf("\n- 2. Search");
		System.out.printf("\n- 3. List by type");
		System.out.printf("\n- 4. List All");
		System.out.printf("\n- 5. Modify word");
		System.out.printf("\n- 6. Delete word");
		System.out.printf("\n- 7. Exit");
		System.out.printf("\n------------------");
		System.out.printf("\n Choice :");
	}
// ham tao
	public TuDien()
	{
		words = new Word[n];
		result = new Word[n];// khoi tao mang
		currentWords = 0;

	}

	// tao method them tu moi
	public static void addWord()
	{
		addNew :

		while(true)
		{
			int stt = currentWords+1;
			String tu;
			String nghia;
			String loai;

			if(stt < n)
			{
				input = new Scanner(System.in);
				System.out.printf("\n STT :" +stt);

				// nhap word
				System.out.printf("\n\t Nhap tu :");
				tu= input.next().toLowerCase();

				for( int e= 0; e< currentWords; e++)
				{
					if(words[e].word.equals(tu))
					{
						System.out.printf("\n !!!! .....Trong tu dien da co tu nay, Ban nhap lai di nhe....\n");
						//input.nextLine();
						continue addNew;
					}
				}

				// nhap nghia
				input = new Scanner(System.in);
				System.out.printf("\n\t Nhap nghia :");
				nghia = input.nextLine();

				// nhap loai tu
				input = new Scanner(System.in);
				System.out.printf("\n\t Nhap loai tu :");
				loai = input.next();

				words[currentWords] = new Word(stt,tu,nghia,loai);
				currentWords++;
			}
			else
			{
				TuDien.stopScreen("\n\t da vuot qua luong tu cho phep nhap, khong the them nua !! ");
			}


			String question = "";

			do
			{
				System.out.printf("\n Ban muon them tu nua:(Y/N) :");

				input = new Scanner(System.in);
				question = input.next().toUpperCase();

				if(question.equals("N"))
				{
					break addNew;
				}

			}while(!question.equals("N") && !question.equals("Y"));
		}
	}

	// phuong thuc dung man hinh
	 static void  stopScreen(String title)
	{
		int choice =0;

		System.out.printf(title);
		do
		{
			try
			{
				choice = System.in.read();
			}
			catch(IOException _ioe)
			{
				choice =0;
			}
		}while(choice !='\n');
	}

	// phuong thuc de hien thi thong tin theo abc va 10/ 1 trang
	public static void displayInfor()
	{
		System.out.printf("\n\t\t HIEN THI TU TRONG TU DIEN ( 10 tu/trang )\n");

		Word temp1 = new Word();
		if(currentWords >0)
		{
			for(i=0; i<currentWords;i++)
			{
				for(int j =i; j<currentWords; j++)
				{
					if(words[i].word.codePointAt(0)> words[j].word.codePointAt(0))
					{

						 temp1=words[i];
						 words[i]=words[j];
						 words[j]= temp1;
					}
				}
			}

			//hien thi theo trang
			display :
			for(int g =0; g<( n/10); g++)
			{   
				System.out.printf("\n ..Trang %d...\n",(g+1));
				System.out.printf("\n|-----------------------------------------------------------------------------|");
				System.out.printf("\n|      Word          |          Mean                        |     Type        |");
				System.out.printf("\n|-----------------------------------------------------------------------------|");
				add : // label
				for(int j=(10*g); j< currentWords; j++)
				{
					words[j].displayWord();
					if( j==9)
					{
						break add;
					}
				}
				System.out.printf("\n\n														...  Het Trang : %d....\n\n",(g+1));

				if((currentWords/10)<=g)
				{
					break display;
				}
			}
		}
		else
		{
			System.out.printf("\n ban hay nhap tu ");
		}
	}

	// phuong thuc tim kiem theo type va sap xep theo ABC
	public static void search()
	{
		int count=0;
		int x=0;
		input = new Scanner(System.in);
		String inputType;
		temp = new Word();

		if(currentWords==0)
		{
			System.out.printf("\n******* Tu Dien rong, ban nhap tu di nhe");
		}
		else
		{
			System.out.printf("\n Nhap vao loai tu can tim kiem :");
			inputType = input.next();

			System.out.printf("\n|-----------------------------------------------------------------------------|");
			System.out.printf("\n|      Word          |          Mean                        |     Type        |");
			System.out.printf("\n|-----------------------------------------------------------------------------|");
			for(int s=0;s<currentWords;s++)
			{
				if (words[s].type.equals(inputType))
				{
					x=1;
					words[s].displayWord();
				}
			}
		}

		if(x==0 & currentWords>0)
		{
			System.out.printf("\n|Trong tu dien khong co loai tu nay, Ban nhap lai loai di nhe !!!!!           |");
			System.out.printf("\n|-----------------------------------------------------------------------------|");
		}
	}

	 // phuong thuc tim theo tu khoa nhap vao ke ca truong hop gan dung
	 public static void wordSearch()
	 {
		int d =0;
		String keyWord;
		input = new Scanner(System.in);

		if(currentWords==0)
		{
			System.out.printf("\n******* Tu Dien rong, ban nhap tu di nhe");
		}
		else
		{
			System.out.printf("\n Nhap tu khoa ");
			keyWord = input.next().toLowerCase();

			System.out.printf("\n|-----------------------------------------------------------------------------|");
			System.out.printf("\n|      Word          |          Mean                        |     Type        |");
			System.out.printf("\n|-----------------------------------------------------------------------------|");
			for(int b= 0; b<currentWords; b++)
			{
				if(words[b].word.substring(0,keyWord.length()).equals(keyWord))
				{
					d =1;
					words[b].displayWord();
				}
			}
		}
		if(d==0 & currentWords>0)
		{
			System.out.printf("\n            Khong co tu nao giong tu ban muon tim !!");
			System.out.printf("\n|-----------------------------------------------------------------------------|");
		}
	}

	// phuong thuc xoa tu trong tu dien
	public static void deleteWord()
   	{
		String delWord;
		int m=0;


		if(currentWords ==0)
		{
			System.out.printf("\n Tu Dien rong");
		}
		else
		{
			System.out.printf("\n Nhap tu ban muon xoa :");
			delWord = input.next().toLowerCase();

			for(i=0; i<currentWords ; i++)
			{
				if(words[i].word.equals(delWord))
				{
					for(int j =i; j< currentWords-1; j++)
					{
						words[i]= words[j+1];
						m=1;
					}
				}
			}
		}

		if(m==1)
		{
			System.out.printf("\n Ban da xoa thanh cong, ban co the kiem tra o list\n");

		}

		if(m==0 & currentWords>0)
		{
			System.out.print("\n\n--- Khong tim duoc tu muon xoa, Nhap lai tu----!!!!!!!");
		}
		currentWords--;
	}

	// sua tu duoc nhap tu ban phim
	public static void  editWord()
	{
		String edWord;
		String edited="";
		int a =0;

		if(currentWords==0)
		{
			System.out.printf("\n******* Tu Dien rong, ban nhap tu di nhe");
		}
		else
		{
			int t=0;
			int k = 0, t1=0, t2=0, t3=0;


			System.out.printf("\n # Chon thanh phan muon sua #");
			System.out.printf("\n 1. Word");
			System.out.printf("\n 2. Mean");
			System.out.printf("\n 3. Type");
			System.out.printf("\n---------> Choice :");
			input = new Scanner(System.in);
			a= input.nextInt();

			input = new Scanner(System.in);
			System.out.printf("\n Nhap tu can sua :");
			edWord = input.next().toLowerCase();

			for(i =0; i< currentWords; i++)
			{
				if(words[i].word.equals(edWord))
				{
					t=1;
					if(a==1)
					{
						t1=1;
						k=i;
					}
					if(a==2)
					{
						t2=1;
						k=i;
					}
					if(a==3)
					{
						t3=1;
						k=i;
					}
				}

			}
			if(t==1 & currentWords>0)
			{
				input = new Scanner(System.in);
				System.out.printf("\n Ban muon sua thanh:");
				edited = input.next().toLowerCase();
			}


			if(t1==1)
			{
				System.out.printf("\n Ban da sua thanh cong, co the hien thi list de kiem tra lai\n");
				words[k]= new Word((k+1),edited,words[k].mean,words[k].type);
			}

			if(t2==1)
			{
				System.out.printf("\n Ban da sua thanh cong, co the hien thi list de kiem tra lai\n");
				words[k]= new Word((k+1),words[k].word,edited,words[k].type);
			}

			if(t3==1)
			{

				System.out.printf("\n Ban da sua thanh cong, co the hien thi list de kiem tra lai\n");
				words[k]= new Word((k+1),words[k].word,words[k].mean,edited);
			}

			if(t==0 & currentWords>0)
			{
				System.out.printf("\n Khong co tu nay trong tu dien");
			}
		}
	}

	// phuong thuc de hien thi menu va chon tung chuc nang
	public static void displayMenu()
	{
		TuDien td = new TuDien();

		while(true)
		{
			td.menu();
			input = new Scanner(System.in);

			int choice =0;
			try
			{
				choice = input.nextInt();
			}
			catch(Exception ex)
			{
				choice =0;
				td.stopScreen("\n $$$$  Nhap 1 so nguyen ma, nhan phim bat ki...de bat dau lai !!!!");
				continue;
			}
			switch(choice)
			{
				case 1:// them moi tu
						System.out.printf("\n\n....................................................\n");
						td.addWord();
						System.out.printf("\n\n....................................................\n");
						break;
				case 2: // tim theo tu khoa
						System.out.printf("\n\n....................................................\n");
						td.wordSearch();
						System.out.printf("\n\n....................................................\n");
						break;
				case 3 :// tim theo loai tu
						System.out.printf("\n\n....................................................\n");
						td.search();
						System.out.printf("\n\n....................................................\n");
						break;
				case 4 :// hien thi tu trong tu dien
						System.out.printf("\n\n....................................................\n");
						td.displayInfor();
						System.out.printf("\n\n....................................................\n");
						break;
				case 5 :// thoat
						System.out.printf("\n\n....................................................\n");
						td.editWord();
						System.out.printf("\n\n....................................................\n");
						break;

				case 6 :// xoa tu
						System.out.printf("\n\n....................................................\n");
						td.deleteWord();
						System.out.printf("\n\n....................................................\n");
						break;

				case 7 : // thoat
						System.exit(0);
						break;
				default :
						System.out.printf("\n Nhap so tu 1- 7");
						break;
			}
		}
	}

	// ham main
	public static void main(String args[])
	{
		TuDien td = new TuDien();
		td.displayMenu();
	}
}