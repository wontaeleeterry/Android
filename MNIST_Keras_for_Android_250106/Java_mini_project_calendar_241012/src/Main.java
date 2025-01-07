package calendar;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
    // 달력 객체와 해당달을 이루고 있는 주의 수, 해당 달의 총 일수 등을 static 변수로 선언
    static Calendar cal = Calendar.getInstance();
    static int week_count = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
    static int day_count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    static String[] days = new String[day_count];
    static String[] weeks = {"일","월","화","수","목","금","토"};
    static int month = cal.get(Calendar.MONTH)+1;
    // 해당일에 일정이 남아있는지 확인하고 남아있으면 표시를 해주는 메소드
    public static void check_count(Diary[] diarys) {
        for(int i=0; i<day_count; i++) {
            if (diarys[i].is_null() == false)
                days[i] = "*";
            else
                days[i] = null;
        }
    }
    // 달력을 보여주는 메소드
    public static void show_calendar(int[][] data,Diary[] diarys) {

        check_count(diarys);
        System.out.println("                      <"+month+"월 달력"+">                      ");
        System.out.println("----------------------------------------------------");
        for (int i=0; i<7; i++) {
            System.out.printf("%3s\t",weeks[i]);
        }
        System.out.println();
        System.out.println("----------------------------------------------------");

        for (int i = 0; i < data.length; i++) {// 배열 출력을 위한 반복문
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] == 0) {
                    System.out.print("\t");
                } else {
                    System.out.printf("%3s\t",
                            (days[data[i][j] - 1] == "*") ? days[data[i][j] - 1] + data[i][j] : data[i][j]);
                }
            }
            System.out.println("\n");
        }

    }

    public static void main(String[] args) {
        // 다이어리를 객체를 담을 배열 선언 및 객체할당
        Diary[] diarys = new Diary[day_count];
        for (int i=0; i<day_count; i++) {
            diarys[i] = new Diary();
        }

        Scanner scan = new Scanner(System.in);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        int first_day = cal.get(Calendar.DAY_OF_WEEK);

        int [][] data = new int[week_count][7];
        int count = 1;

        for (int i=0; i<data.length; i++) { // 달력의 정보를 보여주기 위하여 이차원배열에 데이터 저장
            for(int j=0; j<data[i].length; j++) {
                if(i==0 && j<first_day-1) {
                    data[i][j] = 0;
                }
                else if (count > day_count) {
                    // 이번 달의 마지막 날을 초과한 경우
                    data[i][j] = 0;
                }
                else {
                    // 그 외의 경우는 날짜값을 할당하고, 날짜값 1 증가
                    data[i][j] = count++;
                }

            }
        }
        // 기능 출력과 각 기능에 맞는 인스턴스메소드 호출
        do {
            System.out.println();
            System.out.println(" -------------- ");
            System.out.println("|   <기능목록>   |");
            System.out.println("|0: 종료        |");
            System.out.println("|1: 달력보기     |");
            System.out.println("|2: 일정추가     |");
            System.out.println("|3: 일정삭제     |");
            System.out.println("|4: 세부일정보기  |");
            System.out.println(" -------------- ");

            System.out.print(" 기능을 선택하세요: ");
            int ans = scan.nextInt();
            System.out.println();
            // 기능에 따른 인스턴스메소드 호출
            if (ans == 1)
                show_calendar(data,diarys);
            else if(ans == 2) {
                System.out.print("추가할 날짜를 입력하세요: ");

                int day = scan.nextInt();
                String tmp = scan.nextLine();

                if( 1>day || day>day_count)
                    System.out.println("(잘못 입력하였습니다.)");
                else {
                    System.out.print("상세 일정내용을 입력하세요: \n");
                    System.out.print("> ");

                    String content = scan.nextLine();
                    diarys[day-1].push_content(content);
                    System.out.print("(추가완료)");
                }
            }
            else if(ans == 3) {
                System.out.print("삭제할 날짜를 입력하세요: ");

                int day = scan.nextInt();
                diarys[day-1].show_content();

                if(diarys[day-1].is_null())
                    continue;

                if( 1>day || day>day_count)
                    System.out.println("(잘못 입력하였습니다.)");
                else {
                    System.out.print("삭제할 항목의 번호를 입력하세요: ");
                    int num = scan.nextInt();

                    try {
                        diarys[day-1].remove_content(num-1);
                        System.out.println("(삭제완료)");
                    }catch(Exception e){
                        System.out.println("(잘못 입력하였습니다.)");
                    }
                }
            }
            else if(ans == 4) {
                System.out.print("상세일정을 볼 날짜를 선택하세요: ");

                int day = scan.nextInt();

                if( 1>day || day>day_count)
                    System.out.println("(잘못 입력하였습니다.)");
                else
                    diarys[day-1].show_content();
            }
            else if(ans == 0) {
                System.out.println("(종료되었습니다.)");
                break;
            }

            else
                System.out.println("(잘못 입력하였습니다.)");
        }
        while(true);

        scan.close();
    }

}