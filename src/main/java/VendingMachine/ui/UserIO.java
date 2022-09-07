package VendingMachine.ui;

import java.math.BigDecimal;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 01:41
 * Interface that gives the methods for reading any user input.
 */
public interface UserIO {
    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);

    BigDecimal readBigDecimal(String prompt);
}
