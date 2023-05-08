package patterns.monad;

import java.util.function.Function;

public class App {

    public static void main(String[] args) {

        Client client = new Client("Harry Potter", 19);
        Monad.from(client)
                .map(Client::getName)
                .map(s -> s.split(" "))
                .map(c -> {
                    System.out.println(c[0]);
                    return c[0];
                });

    }

    public static class Monad<T> {

        final T value;

        private Monad(T value) {
            this.value = value;
        }

        public static <T> Monad<T> from(T value){
            return new Monad<>(value);
        }

        public <U> Monad<U> flatMap(Function<T, Monad<U>> mapFunc){
            return mapFunc.apply(value);
        }

        public <U> Monad<U> map(Function<T, U> mapFunc){
            return flatMap(val -> new Monad<>(mapFunc.apply(val)));
        }

    }

    public static class Client {

        private String name;
        private Integer age;

        public Client() {
        }

        public Client(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

}
