import java.util.Random;
import java.util.Scanner;


public class Main {


    public static int inputArraySize(Scanner scanner) {
        int size = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Enter number of array: ");
                size = Integer.parseInt(scanner.nextLine().trim());

                if (size <= 0) {
                    System.out.println("Error: Please enter a positive integer!");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer!");
            }
        }

        return size;
    }


    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();

        // Sinh số ngẫu nhiên từ 0 đến 99 cho mỗi phần tử
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }

        return array;
    }


    public static void displayArray(int[] array, String message) {
        System.out.println(message);
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * THUẬT TOÁN QUICK SORT
     *
     * Nguyên lý hoạt động:
     * 1. Chọn phần tử chốt (pivot) - thường chọn phần tử ở giữa mảng
     * 2. Phân hoạch (Partition):
     *    - Di chuyển các phần tử nhỏ hơn pivot sang bên trái
     *    - Di chuyển các phần tử lớn hơn pivot sang bên phải
     * 3. Đệ quy sắp xếp hai phần bên trái và bên phải
     *
     * Ví dụ: Sắp xếp mảng {1, 12, 5, 26, 7, 14, 3, 7, 2}
     *
     * Bước 1: Chọn pivot = 7 (phần tử giữa)
     * Ban đầu: [1, 12, 5, 26, 7, 14, 3, 7, 2]
     *            i                          j
     *
     * Bước 2: Di chuyển i từ trái sang phải tìm phần tử >= pivot
     *         Di chuyển j từ phải sang trái tìm phần tử <= pivot
     *
     * i tại 12 (12 >= 7), j tại 2 (2 <= 7) -> Hoán đổi 12 và 2
     * Kết quả: [1, 2, 5, 26, 7, 14, 3, 7, 12]
     *               i      j
     *
     * i tại 26 (26 >= 7), j tại 7 (7 <= 7) -> Hoán đổi 26 và 7
     * Kết quả: [1, 2, 5, 7, 7, 14, 3, 26, 12]
     *                  i       j
     *
     * i tại 14 (14 >= 7), j tại 3 (3 <= 7) -> Hoán đổi 14 và 3
     * Kết quả: [1, 2, 5, 7, 7, 3, 14, 26, 12]
     *                        j  i
     *
     * Lúc này i > j -> Dừng phân hoạch
     *
     * Bước 3: Đệ quy sắp xếp:
     * - Phần trái: [1, 2, 5, 7, 7, 3] (tất cả <= pivot)
     * - Phần phải: [14, 26, 12] (tất cả >= pivot)
     *
     * Tiếp tục đệ quy cho đến khi mảng được sắp xếp hoàn toàn
     * Kết quả cuối: [1, 2, 3, 5, 7, 7, 12, 14, 26]

     */
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Tìm vị trí phân hoạch
            int partitionIndex = partition(array, low, high);

            // Đệ quy sắp xếp phần bên trái pivot
            quickSort(array, low, partitionIndex - 1);

            // Đệ quy sắp xếp phần bên phải pivot
            quickSort(array, partitionIndex + 1, high);
        }
    }

    /**
     * THUẬT TOÁN PHÂN HOẠCH (PARTITION)
     *
     * Mục đích: Sắp xếp lại mảng sao cho:
     * - Tất cả phần tử bên trái pivot đều <= pivot
     * - Tất cả phần tử bên phải pivot đều >= pivot
     *
     * Các bước thực hiện:
     * 1. Chọn pivot (thường là phần tử giữa hoặc cuối mảng)
     * 2. Đặt i ở đầu mảng, j ở cuối mảng
     * 3. Di chuyển i sang phải cho đến khi gặp phần tử >= pivot
     * 4. Di chuyển j sang trái cho đến khi gặp phần tử <= pivot
     * 5. Nếu i <= j thì hoán đổi array[i] và array[j]
     * 6. Lặp lại cho đến khi i > j
     *
        * Ví dụ: Với mảng [1, 12, 5, 26, 7, 14, 3, 7, 2] và pivot = 7
        * - Ban đầu: i tại 1 (1 < 7), j tại 2 (2 < 7) -> i tiếp tục, j tiếp tục
        * - i tại 12 (12 >= 7), j tại 2 (2 <= 7) -> Hoán đổi 12 và 2
        *   Kết quả: [1, 2, 5, 26, 7, 14, 3, 7, 12]
        * - i tại 26 (26 >= 7), j tại 3 (3 <= 7) -> Hoán đổi 26 và 3
        *   Kết quả: [1, 2, 5, 7, 7, 14, 3, 26, 12]
        * - i tại 14 (14 >= 7), j tại 3 (3 <= 7) -> Hoán đổi lại không cần thiết vì i > j
        *   Dừng phân hoạch
     */
    public static int partition(int[] array, int low, int high) {
        // Chọn phần tử giữa làm pivot
        int pivot = array[(low + high) / 2];
        int i = low;
        int j = high;

        while (i <= j) {
            // Di chuyển i sang phải cho đến khi tìm thấy phần tử >= pivot
            while (array[i] < pivot) {
                i++;
            }

            // Di chuyển j sang trái cho đến khi tìm thấy phần tử <= pivot
            while (array[j] > pivot) {
                j--;
            }

            // Nếu i <= j thì hoán đổi array[i] và array[j]
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        return i;
    }

    /**
     * Hoán đổi hai phần tử trong mảng
     * @param array mảng chứa các phần tử
     * @param i chỉ số phần tử thứ nhất
     * @param j chỉ số phần tử thứ hai
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println("      ARRAY SORTING PROGRAM - QUICK SORT");
        System.out.println("=================================================");
        System.out.println();

        // Bước 1: Nhập số lượng phần tử
        int size = inputArraySize(scanner);
        System.out.println();

        // Bước 2: Tạo mảng ngẫu nhiên
        int[] array = generateRandomArray(size);

        // Hiển thị mảng trước khi sắp xếp
        displayArray(array, "Unsorted array:");
        System.out.println();

        // Bước 3: Sắp xếp mảng sử dụng Quick Sort
        quickSort(array, 0, array.length - 1);

        // Hiển thị mảng sau khi sắp xếp
        displayArray(array, "Sorted array:");
        System.out.println();

        System.out.println("=================================================");
        System.out.println("   SORTING COMPLETED SUCCESSFULLY!");
        System.out.println("   Complexity: O(n log n)");
        System.out.println("=================================================");

        scanner.close();
    }
}