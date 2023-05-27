package c_patterns.monad;

import java.util.function.Function;
import java.util.function.Supplier;

public class AppLazy {

    public static void main(String[] args) {

        Client client = new Client("Harry Potter", 19);
        LazyMonad<String> composed =  LazyMonad.from(client)
                .map(Client::getName)
                .map(s -> s.split(" "))
                .map(c -> {
                    System.out.println(c[0]);
                    return c[0];
                });

        composed.get();

    }

    public static class LazyMonad<T> {

        T value;

        Supplier<T> supplier;


        private LazyMonad(T value) {
            this.value = value;
        }

        public LazyMonad(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        public T get() {
            if (value == null) {
                value = supplier.get();
            }
            return value;
        }

        public static <T> LazyMonad<T> from(T value){
            return new LazyMonad<>(value);
        }

        public <U> LazyMonad<U> flatMap(Function<T, LazyMonad<U>> mapFunc){
            return mapFunc.apply(value);
        }

        public <U> LazyMonad<U> map(Function<T, U> mapFunc){
            LazyMonad<U> lazyMonad = new LazyMonad<>(() -> mapFunc.apply(get()));
            return lazyMonad;
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
