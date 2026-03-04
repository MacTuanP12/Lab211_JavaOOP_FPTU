
public class ArrayManager {
    private int[] array;
    private final InputValidator validator;

    public ArrayManager(InputValidator validator) {
        this.validator = validator;
        this.array = null;
    }


    public void inputElements() {
        System.out.println("\n----- Input Element -----");

        int length = validator.getPositiveInteger(
            "Input Length Of Array: ",
            "Please input number and number is greater than zero"
        );

        array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = validator.getInteger(
                "Enter Number " + (i + 1) + ": ",
                "Please input number and number is greater than zero"
            );
        }

        System.out.println("Array input successfully!");
    }


    public int[] getArray() {
        return array;
    }

    public boolean isEmpty() {
        return array == null || array.length == 0;
    }


    public void displayArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return;
        }

        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print("]->[");
            }
        }
        System.out.println("]");
    }
}

