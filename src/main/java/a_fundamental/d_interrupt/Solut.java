package a_fundamental.d_interrupt;

public class Solut {
    /*
        Запускаем вывод имени потока в отдельном потоке,
        отправляем main поток в сон, а потом вызываем прерывание.
     */
    public static void main(String[] args) throws InterruptedException {
        CustomThreadManipulator manipulator = new TaskManipulator();

        manipulator.start("first");
        Thread.sleep(150);
        manipulator.stop();

        manipulator.start("second");
        Thread.sleep(250);
        manipulator.stop();

        manipulator.start("third");
        Thread.sleep(50);
        manipulator.stop();

        manipulator.start("forth");
        manipulator.stop();

        manipulator.start("fifth");
        Thread.sleep(1);
        manipulator.stop();
    }


}
