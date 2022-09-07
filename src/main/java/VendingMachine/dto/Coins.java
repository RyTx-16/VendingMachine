package VendingMachine.dto;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 01:42
 * Enums that hold the given coin values for the vending machine.
 */
public enum Coins {
    FIFTY(50), TWENTY(20), TEN(10), FIVE(5), TWO(2), ONE(1);
    private final int value;

    Coins(int value) {
        this.value = value;
    }

    private int getValue() {
        return value;
    }

    public String toString(){
        return switch (this) {
            case FIFTY -> "50";
            case TWENTY -> "20";
            case TEN -> "10";
            case FIVE -> "5";
            case TWO -> "2";
            case ONE -> "1";
        };
    }

}
