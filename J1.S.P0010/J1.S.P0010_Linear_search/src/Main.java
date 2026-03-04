import java.util.Random;
import java.util.Scanner;

/**
 * Thuật toán Linear Search:
 * - Là phương pháp tìm kiếm đơn giản nhất
 * - Duyệt qua từng phần tử trong mảng một cách tuần tự
 * - So sánh từng phần tử với giá trị cần tìm
 * - Trả về vị trí (index) của phần tử đầu tiên tìm thấy
 *
 * Ví dụ:
 * Mảng: [5, 2, 8, 1, 9]
 * Tìm: 8
 * Bước 1: So sánh 5 với 8 → Không bằng, tiếp tục
 * Bước 2: So sánh 2 với 8 → Không bằng, tiếp tục
 * Bước 3: So sánh 8 với 8 → Bằng nhau! Trả về index = 2
 *
 * Độ phức tạp:
 * - Best case: O(1) - phần tử ở đầu mảng
 * - Worst case: O(n) - phần tử ở cuối mảng hoặc không tồn tại
 * - Average case: O(n/2) ≈ O(n)
 */
public class Main {

    
    public static int getPositiveInteger(Scanner scanner) {
        int number;

        // Vòng lặp vô hạn cho đến khi nhận được input hợp lệ
        while (true) {
            try {
                // Hiển thị thông báo yêu cầu nhập
                System.out.print("Enter number of array: ");

                // Đọc chuỗi từ bàn phím, loại bỏ khoảng trắng, và chuyển thành số nguyên
                // Ví dụ: "  5  " → "5" → 5
                number = Integer.parseInt(scanner.nextLine().trim());

                // Kiểm tra số có dương hay không
                if (number > 0) {
                    return number; // Trả về số nếu hợp lệ và thoát method
                } else {
                    // Trường hợp number <= 0 (số âm hoặc 0)
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ khi input không phải là số
                // Ví dụ: "abc", "12.5", "1a2" sẽ gây ra exception này
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
    }


    /**
     * Tạo mảng với các số nguyên ngẫu nhiên
     *
     * Chức năng:
     * - Khởi tạo mảng với kích thước được chỉ định
     * - Điền các số ngẫu nhiên vào từng vị trí trong mảng
     * - Phạm vi số ngẫu nhiên: từ 1 đến size (bao gồm cả 2 đầu)
     *
     * Ví dụ:
     * Input: size = 5
     * Process:
     *   - Tạo mảng rỗng có 5 phần tử: [0, 0, 0, 0, 0]
     *   - i=0: random.nextInt(5) = 2 → array[0] = 2+1 = 3
     *   - i=1: random.nextInt(5) = 0 → array[1] = 0+1 = 1
     *   - i=2: random.nextInt(5) = 4 → array[2] = 4+1 = 5
     *   - i=3: random.nextInt(5) = 1 → array[3] = 1+1 = 2
     *   - i=4: random.nextInt(5) = 3 → array[4] = 3+1 = 4
     * Output: [3, 1, 5, 2, 4]
     *
     * Lưu ý:
     * - random.nextInt(size) trả về số từ 0 đến (size-1)
     * - Cộng thêm 1 để có số từ 1 đến size
     *
     * @param size - Kích thước của mảng cần tạo
     * @return mảng chứa các số nguyên ngẫu nhiên
     */
    public static int[] generateRandomArray(int size) {
        // Khởi tạo mảng với kích thước được chỉ định
        int[] array = new int[size];

        // Tạo đối tượng Random để sinh số ngẫu nhiên
        Random random = new Random();

        // Duyệt qua từng vị trí trong mảng
        for (int i = 0; i < size; i++) {
            // Sinh số ngẫu nhiên từ 0 đến (size-1), sau đó cộng 1
            // Kết quả: số ngẫu nhiên từ 1 đến size
            array[i] = random.nextInt(size) + 1;
        }

        // Trả về mảng đã được điền số ngẫu nhiên
        return array;
    }



    public static void displayArray(int[] array) {
        System.out.print("The array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

        // In phần đóng và xuống dòng
        System.out.println("]");
    }

    public static int linearSearch(int[] array, int searchValue) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchValue) {
                return i;
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== Linear Search Program ======");

        // Get the number of array elements from user
        int arraySize = getPositiveInteger(scanner);

        // Generate random array
        int[] array = generateRandomArray(arraySize);

        // Get the search number from user
        int searchNumber = getPositiveInteger(scanner);

        // Display the array
        displayArray(array);

        // Perform linear search
        int foundIndex = linearSearch(array, searchNumber);

        // Display result
        if (foundIndex != -1) {
            System.out.println("Found " + searchNumber + " at index: " + foundIndex);
        } else {
            System.out.println(searchNumber + " is not found in the array.");
        }

        scanner.close();
    }
}