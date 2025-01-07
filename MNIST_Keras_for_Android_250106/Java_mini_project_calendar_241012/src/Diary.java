package calendar;

import java.util.ArrayList;

import java.util.List;

public class Diary {
    // 세부일정 항목을 관리하기 위한 ArrayList 생성
    List<String> contents = new ArrayList<String>();

    // 세부일정을 추가하기 위한 메소드
    public void push_content(String s) {
        contents.add(s);
    }
    // 세부일정을 삭제하기 위한 메소드
    public void remove_content(int idx) {
        if(is_null()) {
            System.out.println("----------------------------------------------------");
            System.out.println("일정이 없습니다.");
            System.out.println("----------------------------------------------------");
        }
        contents.remove(idx);
    }
    // 일정이 비었는지 확인하는 메소드
    public boolean is_null() {
        if (contents.size() == 0)
            return true;
        else
            return false;
    }
    // 세부일정을 항목별로 보여주기 위한 메소드
    public void show_content() {
        System.out.println("----------------------------------------------------");
        if(is_null()) {
            System.out.println("일정이 없습니다.");
        }
        else{
            for(int i=0; i<contents.size(); i++) {
                System.out.println(i+1+". "+contents.get(i));
            }
        }
        System.out.println("----------------------------------------------------");
    }



}