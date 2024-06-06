package at.ac.fhcampuswien.fhmdb.factoryPattern;


import javafx.util.Callback;

public class MyFactory implements Callback<Class<?>, Object> {

    //MainController.class -> new MainController()
    //MovieController.class -> new MovieController()
    //MainController.class -> new MainController()

    private static MyFactory instance;

    private MyFactory() {

    }

    public static MyFactory getInstance() {
        if (instance == null) {
            instance = new MyFactory();
        }
        return instance;
    }

    @Override
    public Object call(Class<?> aClass) {
        try{
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


