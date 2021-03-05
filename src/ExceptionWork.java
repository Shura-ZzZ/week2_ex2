import java.io.FileWriter;
import java.io.IOException;

public final class ExceptionWork<T extends Exception>{
    public ExceptionWork(){}


    public static<T extends Exception> RuntimeException toRuntimeException(T exep) throws T {
            return new RuntimeException(exep);
    }


    public static<T extends Exception>  RuntimeException wrapToExChain(T MainExeption, T ... cause)
    {
        for(int i = cause.length-1; i >0; i--)
        {
            cause[i-1].initCause(cause[i]);
        }
        MainExeption.initCause(cause[0]);
        return new RuntimeException(MainExeption);
    }

    public static<T extends Exception>  void printExeption(T exep, int level) {
        StackTraceElement[] methods = exep.getStackTrace();
        StackTraceElement randomElement = methods[level];
        System.out.println(randomElement.toString());
    }

    public static<T extends Exception>  void printExeption(T exep, int level, String path) {
        StackTraceElement[] methods = exep.getStackTrace();
        StackTraceElement randomElement = methods[level];
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(randomElement.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}
