import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {
    // Scanner để đọc input từ người dùng
    private static final Scanner scanner = new Scanner(System.in);

    // Random để tạo số ngẫu nhiên
    private static final Random random = new Random();

    public static void main(String[] args) {
        // In tiêu đề chương trình
        System.out.println("====== BINARY SEARCH ALGORITHM ======");
        System.out.println();

        // Bước 1: Nhập kích thước mảng từ người dùng
        int arraySize = inputArraySize();

        // Bước 2: Tạo mảng với các số ngẫu nhiên
        int[] array = generateRandomArray(arraySize);
        System.out.println("\nOriginal array: " + Arrays.toString(array));

        // Bước 3: Sắp xếp mảng theo thứ tự tăng dần (bắt buộc để dùng Binary Search)
        Arrays.sort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));

        // Bước 4: Nhập giá trị cần tìm kiếm
        int searchValue = inputSearchValue();

        // Bước 5: Thực hiện tìm kiếm nhị phân
        int foundIndex = binarySearch(array, searchValue);

        // Bước 6: Hiển thị kết quả
        displayResult(array, searchValue, foundIndex);

        // Đóng scanner
        scanner.close();
    }

    private static int inputArraySize() {
        int size;
        // Vòng lặp vô hạn cho đến khi nhập đúng
        while (true) {
            try {
                System.out.print("Enter number of elements in array: ");
                // Đọc input và loại bỏ khoảng trắng thừa
                String input = scanner.nextLine().trim();
                // Chuyển đổi chuỗi sang số nguyên
                size = Integer.parseInt(input);

                // Kiểm tra số phải lớn hơn 0
                if (size <= 0) {
                    System.out.println("Error! Array size must be a positive integer.");
                    continue; // Yêu cầu nhập lại
                }
                break; // Thoát vòng lặp nếu input hợp lệ
            } catch (NumberFormatException e) {
                // Xử lý lỗi khi input không phải số
                System.out.println("Error! Please enter a valid integer.");
            }
        }
        return size;
    }

    private static int[] generateRandomArray(int size) {
        // Khởi tạo mảng với kích thước đã cho
        // Initialize array with given size
        int[] array = new int[size];

        // Duyệt qua từng phần tử của mảng
        for (int i = 0; i < size; i++) {
            // random.nextInt(201) tạo số từ 0 đến 200
            // Trừ đi 100 để có khoảng [-100, 100]
            array[i] = random.nextInt(201) - 100;
        }
        return array;
    }

    private static int inputSearchValue() {
        int value;
        // Vòng lặp cho đến khi nhập hợp lệ
        while (true) {
            try {
                System.out.print("\nEnter search value: ");
                // Đọc và loại bỏ khoảng trắng
                String input = scanner.nextLine().trim();
                // Chuyển đổi sang số nguyên
                value = Integer.parseInt(input);
                break; // Thoát vòng lặp nếu thành công
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu input không phải số
                System.out.println("Error! Please enter a valid integer.");
            }
        }
        return value;
    }

    /**
     * ========== THUẬT TOÁN TÌM KIẾM NHỊ PHÂN (Binary Search Algorithm) ==========
     *
     * KHÁI NIỆM:
     * - Tìm kiếm nhị phân là thuật toán tìm kiếm hiệu quả trên mảng đã sắp xếp
     * - Ý tưởng: Chia đôi không gian tìm kiếm sau mỗi bước so sánh
     *
     * CÁC BƯỚC THUẬT TOÁN:
     * 1. Khởi tạo left = 0 (chỉ số đầu mảng), right = array.length - 1 (chỉ số cuối mảng)
     * 2. Trong khi left <= right (còn phần tử để tìm):
     *    a. Tính chỉ số giữa: mid = (left + right) / 2
     *    b. Nếu array[mid] == target: Tìm thấy! Trả về mid
     *    c. Nếu array[mid] < target: Giá trị cần tìm ở nửa phải,
     *       => Bỏ qua nửa trái (left = mid + 1)
     *    d. Nếu array[mid] > target: Giá trị cần tìm ở nửa trái,
     *       => Bỏ qua nửa phải (right = mid - 1)
     * 3. Nếu không tìm thấy, trả về -1
     *
     * ĐỘ PHỨC TẠP:
     * - Thời gian: O(log n) - Rất nhanh! Với mảng 1 triệu phần tử chỉ cần ~20 bước
     * - Không gian: O(1) - Chỉ dùng một số biến cố định
     *
     * YÊU CẦU: Mảng PHẢI được sắp xếp trước khi tìm kiếm
     *
     * ============================================================================
     *
     */
    private static int binarySearch(int[] array, int target) {
        // Khởi tạo con trỏ trái (bắt đầu mảng)
        int left = 0;

        // Khởi tạo con trỏ phải (cuối mảng)
        int right = array.length - 1;

        // Vòng lặp chính: tiếp tục khi còn phần tử để xét (left <= right)
        while (left <= right) {
            // Tính chỉ số giữa (mid)
            // Dùng left + (right - left) / 2 thay vì (left + right) / 2
            // để tránh tràn số khi left và right lớn
            int mid = left + (right - left) / 2;

            // SO SÁNH 1: Kiểm tra nếu phần tử giữa chính là giá trị cần tìm
            if (array[mid] == target) {
                return mid; // Tìm thấy! Trả về chỉ số
            }

            // SO SÁNH 2: Nếu phần tử giữa nhỏ hơn target
            // => Target phải nằm ở nửa bên phải (phần lớn hơn)
            // => Bỏ qua toàn bộ nửa trái bằng cách đặt left = mid + 1
            if (array[mid] < target) {
                left = mid + 1; // Dịch con trỏ trái sang phải
            }
            // SO SÁNH 3: Nếu phần tử giữa lớn hơn target
            // => Target phải nằm ở nửa bên trái (phần nhỏ hơn)
            // => Bỏ qua toàn bộ nửa phải bằng cách đặt right = mid - 1
            else {
                right = mid - 1; // Dịch con trỏ phải sang trái
            }
        }

        // Nếu thoát vòng lặp (left > right) mà không return
        // => Không tìm thấy phần tử trong mảng
        return -1; // Không tìm thấy
    }



    private static void displayResult(int[] array, int searchValue, int index) {
        System.out.println("\n====== SEARCH RESULT ======");
        // Hiển thị mảng đã sắp xếp
        System.out.println("Sorted array: " + Arrays.toString(array));
        // Hiển thị giá trị đã tìm kiếm
        System.out.println("Search value: " + searchValue);

        // Kiểm tra nếu tìm thấy (index != -1)
        if (index != -1) {
            // Trường hợp TÌM THẤY
            System.out.println("Result: FOUND at index " + index);
            System.out.println("Value at index " + index + " is: " + array[index]);
        } else {
            // Trường hợp KHÔNG TÌM THẤY
            System.out.println("Result: NOT FOUND");
            System.out.println("The value " + searchValue + " does not exist in the array.");
        }
        System.out.println("===============================");
    }
}