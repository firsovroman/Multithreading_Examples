package third;

import java.util.ArrayList;
import java.util.List;

/*
Horse Racing
*/

public class Solution {

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = prepareHorsesAndStart(10); // создали потоки запустили их
        while (calculateHorsesFinished(horses) != horses.size()) { // сравнивается цифра из метода calculate
            // если не соответствует запускается по новой и цикл calculate инициируется по новой
        }
    }

    public static int calculateHorsesFinished(List<Horse> horses) throws InterruptedException {
        int finishedCount = 0; // каждая инициация сначала сбрасывает переменную в ноль
        for(Horse h : horses){
            if(h.isFinished()){ // затем перебирает всех плюсует завершенных
                finishedCount = finishedCount + 1;
            }else{ // если обнаруживает незавершенного пишет и ждет пока завершится, а затем в main цикл while запускает все по новой
                System.out.println("Waiting for " + h.getName());
                h.join(); // ждет
            }
        }
        return finishedCount;
    }

    public static List<Horse> prepareHorsesAndStart(int horseCount) {
        List<Horse> horses = new ArrayList<>(horseCount);
        String number;
        for (int i = 1; i < horseCount + 1; i++) { // добавляем лошадей в лист
            number = i < 10 ? ("0" + i) : "" + i;
            horses.add(new Horse("Horse_" + number)); // создаем и называем объект класса хорс
        }

        for (int i = 0; i < horseCount; i++) {
            horses.get(i).start(); // запускаем всех созданных лошадей
        }
        return horses;
    }

    public static class Horse extends Thread {

        private boolean isFinished;

        public Horse(String name) {
            super(name);
        }

        public boolean isFinished() {
            return isFinished;
        }

        public void run() {
            String s = "";
            for (int i = 0; i < 1001; i++) {   // Delay
                s += "" + i;
                if (i == 1000) {
                    s = " has finished the race!";
                    System.out.println(getName() + s);
                    isFinished = true;
                }
            }
        }
    }
}
