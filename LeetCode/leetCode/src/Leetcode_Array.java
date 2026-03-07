import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_Array {
    /* Given a binary array nums, return the maximum number of consecutive 1's in the array.
*
* Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
* */
    /*
    CÁCH GIẢI:
    - Sử dụng 2 biến: max (lưu kết quả tốt nhất) và count (đếm số 1 liên tiếp hiện tại)
    - Duyệt qua từng phần tử:
      + Nếu gặp 1: tăng count và cập nhật max nếu count > max
      + Nếu gặp 0: reset count về 0 (bắt đầu đếm lại chuỗi mới)
    - Độ phức tạp: O(n) - chỉ duyệt 1 lần qua mảng
    */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;      // Biến lưu số lượng số 1 liên tiếp tối đa
        int count = 0;    // Biến đếm số lượng số 1 liên tiếp hiện tại
        for (int num : nums) {
            if (num == 1) {
                count++;  // Tăng bộ đếm khi gặp số 1
                max = Math.max(max, count);  // Cập nhật max nếu count lớn hơn
            } else {
                count = 0;  // Reset bộ đếm khi gặp số 0
            }

        }
        return max;  // Trả về số lượng số 1 liên tiếp tối đa

    }

    /*
    Find Numbers with Even Number of Digits
    Given an array nums of integers, return how many of them contain an even number of digits.

Constraints:

1 <= nums.length <= 500
1 <= nums[i] <= 105
     */
    /*
    CÁCH GIẢI:
    - Với mỗi số trong mảng, cần đếm số chữ số của nó
    - Cách đếm đơn giản: chuyển số thành String rồi lấy length()
    - Kiểm tra số chữ số có chia hết cho 2 không (% 2 == 0)
    - Đếm tổng số các số thỏa mãn điều kiện
    - Độ phức tạp: O(n * log(m)) với m là giá trị lớn nhất trong mảng
    */
    public int findNumbers(int[] nums) {
        int count = 0;  // Biến đếm số lượng các số có chữ số chẵn
        for (int num : nums) {
            int isdigit = String.valueOf(num).length();  // Chuyển số thành chuỗi để đếm số chữ số
            if (isdigit % 2 == 0) {  // Kiểm tra số chữ số có chia hết cho 2 không
                count++;  // Tăng bộ đếm nếu số chữ số là chẵn
            }

        }
        return count;  // Trả về tổng số các số có chữ số chẵn

    }

    /* Squares of a Sorted Array

    Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.



    Constraints:

    1 <= nums.length <= 104
    -104 <= nums[i] <= 104
    nums is sorted in non-decreasing order.



    */
    /*
    CÁCH GIẢI:
    - Mảng đã sắp xếp nhưng có số âm => sau khi bình phương sẽ mất thứ tự
    - Ví dụ: [-4,-1,0,3,10] => bình phương: [16,1,0,9,100]
    - Ý tưởng: Dùng kỹ thuật Two Pointers từ 2 đầu mảng
    - Lý do: Bình phương lớn nhất sẽ nằm ở đầu (số âm lớn) hoặc cuối (số dương lớn) mảng
    - So sánh giá trị tuyệt đối nums[left] và nums[right], chọn lớn hơn để bình phương
    - Đặt vào cuối mảng result và di chuyển con trỏ tương ứng
    - Độ phức tạp: O(n) - tối ưu hơn O(n log n) nếu bình phương rồi sắp xếp
    */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];  // Mảng kết quả để lưu bình phương đã sắp xếp

        int left = 0;       // Con trỏ trái bắt đầu từ đầu mảng
        int right = n - 1;  // Con trỏ phải bắt đầu từ cuối mảng
        int pos = n - 1;    // Vị trí để điền vào mảng kết quả (từ cuối về đầu)

        while (left <= right) {
            // So sánh giá trị tuyệt đối của hai đầu mảng
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[pos] = nums[left] * nums[left];  // Bình phương phần tử bên trái lớn hơn
                left++;  // Di chuyển con trỏ trái sang phải
            } else {
                result[pos] = nums[right] * nums[right];  // Bình phương phần tử bên phải lớn hơn hoặc bằng
                right--;  // Di chuyển con trỏ phải sang trái
            }
            pos--;  // Di chuyển vị trí điền về phía trước
        }

        return result;  // Trả về mảng đã sắp xếp
    }

    /*
      Duplicate Zeros

    Solution
    Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.

    Note that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and do not return anything

    Constraints:

    1 <= arr.length <= 104
    0 <= arr[i] <= 9
     */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Nhân đôi mỗi số 0, dịch các phần tử còn lại sang phải, in-place
    - Ví dụ: [1,0,2,3,0,4] => [1,0,0,2,3,0]
    - Bước 1: Đếm số lượng số 0 để biết cần dịch bao nhiêu vị trí
    - Bước 2: Duyệt từ CUỐI về ĐẦU (quan trọng!)
      + Lý do duyệt ngược: tránh ghi đè dữ liệu chưa xử lý
      + Khi gặp 0: copy nó vào vị trí i+count (nếu còn trong mảng), giảm count
      + Với mọi phần tử: dịch sang phải count vị trí
    - Độ phức tạp: O(n) thời gian, O(1) không gian
    */
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int count = 0;

        // Count the number of zeros in the array
        // Đếm số lượng số 0 trong mảng
        for (int num : arr) {
            if (num == 0) {
                count++;
            }
        }

        // Start from the end of the array and duplicate zeros
        // Bắt đầu từ cuối mảng và nhân đôi các số 0
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                if (i + count < n) {
                    arr[i + count] = 0; // Duplicate zero - Nhân đôi số 0
                }
                count--; // Decrease the count of zeros to duplicate - Giảm số lượng số 0 cần nhân đôi
            }
            if (i + count < n) {
                arr[i + count] = arr[i]; // Shift non-zero element - Dịch phần tử khác 0 sang phải
            }
        }
    }

    /*
    Merge Sorted Array

Solution
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109


Follow up: Can you come up with an algorithm that runs in O(m + n) time?
     */
    /*
    CÁCH GIẢI:
    - Đề bài: Merge 2 mảng đã sắp xếp, lưu kết quả vào nums1 (có sẵn chỗ trống)
    - Ý tưởng: Merge từ CUỐI về ĐẦU (không bị ghi đè dữ liệu)
    - Sử dụng 3 con trỏ:
      + i = m-1: phần tử cuối cùng hợp lệ của nums1
      + j = n-1: phần tử cuối cùng của nums2
      + k = m+n-1: vị trí điền vào nums1
    - So sánh nums1[i] vs nums2[j], chọn lớn hơn đặt vào nums1[k]
    - Di chuyển con trỏ tương ứng
    - Nếu nums2 còn phần tử, copy vào nums1
    - Độ phức tạp: O(m+n) - tối ưu
    */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Pointer for nums1 - Con trỏ cho nums1 (bắt đầu từ phần tử cuối cùng hợp lệ)
        int j = n - 1; // Pointer for nums2 - Con trỏ cho nums2 (bắt đầu từ phần tử cuối cùng)
        int k = m + n - 1; // Pointer for merged array - Con trỏ cho mảng đã merge (vị trí cuối cùng)

        // Duyệt từ cuối về đầu, so sánh và đặt phần tử lớn hơn vào cuối mảng nums1
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];  // Lấy phần tử lớn hơn từ nums1
            } else {
                nums1[k--] = nums2[j--];  // Lấy phần tử lớn hơn hoặc bằng từ nums2
            }
        }

        // If there are remaining elements in nums2, copy them
        // Nếu còn phần tử trong nums2, copy chúng vào nums1
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    /*
     Remove Element

    Solution
    Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

    Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

    Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
    Return k.
    Custom Judge:

    The judge will test your solution with the following code:

    int[] nums = [...]; // Input array
    int val = ...; // Value to remove
    int[] expectedNums = [...]; // The expected answer with correct length.
                                // It is sorted with no values equaling val.

    int k = removeElement(nums, val); // Calls your implementation

    assert k == expectedNums.length;
    sort(nums, 0, k); // Sort the first k elements of nums
    for (int i = 0; i < actualLength; i++) {
        assert nums[i] == expectedNums[i];
    }
    If all assertions pass, then your solution will be accepted.

    Constraints:

    0 <= nums.length <= 100
    0 <= nums[i] <= 50
    0 <= val <= 100

     */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Xóa tất cả phần tử = val, in-place, trả về số phần tử còn lại
    - Kỹ thuật: Two Pointers với con trỏ k (vị trí đặt phần tử hợp lệ)
    - Duyệt qua mảng, gặp phần tử != val thì đặt vào nums[k] và tăng k
    - Kết quả: k phần tử đầu là các phần tử != val
    - Không cần xóa thật, chỉ cần ghi đè
    - Độ phức tạp: O(n) thời gian, O(1) không gian
    */
    public int removeElement(int[] nums, int val) {
        int k = 0; // Biến đếm số lượng phần tử không bằng val
        for (int num : nums) {
            if (num != val) {
                nums[k++] = num; // Đặt phần tử không bằng val vào vị trí k và tăng k
            }
        }
        return k; // Trả về số lượng phần tử không bằng val
    }

    /*
    Remove Duplicates from Sorted Array

    Solution
    Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.

    Consider the number of unique elements in nums to be k​​​​​​​​​​​​​​. After removing duplicates, return the number of unique elements k.

    The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index k - 1 can be ignored.

    Custom Judge:

    The judge will test your solution with the following code:

    int[] nums = [...]; // Input array
    int[] expectedNums = [...]; // The expected answer with correct length

    int k = removeDuplicates(nums); // Calls your implementation

    assert k == expectedNums.length;
    for (int i = 0; i < k; i++) {
        assert nums[i] == expectedNums[i];
    }
    If all assertions pass, then your solution will be accepted.

    Constraints:

    1 <= nums.length <= 3 * 104
    -100 <= nums[i] <= 100
    nums is sorted in non-decreasing order.
     */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Xóa các phần tử trùng trong mảng ĐÃ SẮP XẾP, in-place
    - Lợi thế: Mảng đã sắp xếp => các phần tử trùng nằm cạnh nhau
    - Kỹ thuật: Two Pointers với k (vị trí đặt phần tử unique)
    - k bắt đầu từ 1 (phần tử đầu luôn unique)
    - Duyệt từ i=1, so sánh nums[i] với nums[i-1]:
      + Nếu khác nhau => phần tử unique mới, đặt vào nums[k] và tăng k
      + Nếu giống nhau => bỏ qua (duplicate)
    - Độ phức tạp: O(n) - tối ưu
    */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0; // Trả về 0 nếu mảng rỗng
        }

        int k = 1; // Biến đếm số lượng phần tử duy nhất, bắt đầu từ 1 vì phần tử đầu tiên luôn là duy nhất
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) { // So sánh phần tử hiện tại với phần tử trước đó
                nums[k++] = nums[i]; // Nếu khác nhau, đặt phần tử hiện tại vào vị trí k và tăng k
            }
        }
        return k; // Trả về số lượng phần tử duy nhất
    }

    /*
    Check If N and Its Double Exist

Solution
Given an array arr of integers, check if there exist two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]



Constraints:

2 <= arr.length <= 500
-103 <= arr[i] <= 103
     */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Tìm 2 chỉ số i,j khác nhau mà arr[i] = 2*arr[j]
    - Cách 1 (Brute Force - đơn giản):
      + Duyệt tất cả cặp (i,j) với 2 vòng lặp lồng nhau
      + Kiểm tra điều kiện i≠j và arr[i]==2*arr[j]
      + Độ phức tạp: O(n²)
    - Cách 2 (Tối ưu - dùng HashSet):
      + Duyệt qua mảng, với mỗi số kiểm tra num*2 hoặc num/2 có trong Set chưa
      + Thêm num vào Set
      + Độ phức tạp: O(n)
    - Code hiện tại dùng cách 1 (đơn giản, dễ hiểu)
    */
    public boolean checkIfExist(int[] arr) {
        // Duyệt qua tất cả các cặp chỉ số i và j
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                // Kiểm tra nếu i khác j và arr[i] bằng 2 lần arr[j]
                if (i != j && arr[i] == 2 * arr[j]) {
                    return true; // Trả về true nếu tìm thấy cặp thỏa mãn điều kiện
                }
            }
        }
        return false; // Trả về false nếu không tìm thấy cặp nào thỏa mãn
    }

    /*
      Valid Mountain Array

    Solution
    Given an array of integers arr, return true if and only if it is a valid mountain array.

    Recall that arr is a mountain array if and only if:

    arr.length >= 3
    There exists some i with 0 < i < arr.length - 1 such that:
    arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]

    Constraints:

    1 <= arr.length <= 104
    0 <= arr[i] <= 104
     */
    /*
    CÁCH GIẢI:
    - Mảng núi hợp lệ: có phần TĂNG + ĐỈNH + phần GIẢM
    - Điều kiện: length>=3, phải có cả 2 phần tăng VÀ giảm (không chỉ tăng hoặc chỉ giảm)
    - Thuật toán 3 bước:
      1. Đi lên dốc: tăng i khi arr[i] < arr[i+1]
      2. Kiểm tra: i phải >0 (có dốc lên) và <n-1 (có dốc xuống)
      3. Đi xuống dốc: tăng i khi arr[i] > arr[i+1]
    - Cuối cùng kiểm tra i == n-1 (đã đi hết mảng)
    - Độ phức tạp: O(n)
    */
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return false; // Một mảng có ít hơn 3 phần tử không thể là mảng núi
        }

        int i = 0;

        // Đi lên dốc
        while (i + 1 < n && arr[i] < arr[i + 1]) {
            i++;
        }

        // Kiểm tra nếu không có dốc lên hoặc dốc lên kéo dài đến cuối mảng
        if (i == 0 || i == n - 1) {
            return false; // Không có dốc lên hoặc dốc lên kéo dài đến cuối mảng
        }

        // Đi xuống dốc
        while (i + 1 < n && arr[i] > arr[i + 1]) {
            i++;
        }

        return i == n - 1; // Kiểm tra nếu đã đi hết mảng sau khi đi xuống dốc
    }

    /*
    Replace Elements with Greatest Element on Right Side
Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.




Constraints:

1 <= arr.length <= 104
1 <= arr[i] <= 105
     */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Thay mỗi phần tử bằng max của các phần tử bên phải nó
    - Phần tử cuối = -1 (không có phần tử bên phải)
    - Ý tưởng: Duyệt từ CUỐI về ĐẦU (để biết max bên phải)
    - Dùng biến maxRight lưu max bên phải, khởi tạo = -1
    - Với mỗi vị trí i:
      + Lưu arr[i] vào biến tạm current
      + Gán arr[i] = maxRight
      + Cập nhật maxRight = max(maxRight, current)
    - Độ phức tạp: O(n) - duyệt 1 lần
    */
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int maxRight = -1; // Biến lưu giá trị lớn nhất ở bên phải

        // Duyệt từ cuối về đầu để cập nhật giá trị lớn nhất ở bên phải
        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i]; // Lưu giá trị hiện tại
            arr[i] = maxRight; // Thay thế phần tử hiện tại bằng giá trị lớn nhất ở bên phải
            maxRight = Math.max(maxRight, current); // Cập nhật giá trị lớn nhất nếu cần
        }

        return arr; // Trả về mảng đã được thay thế
    }

    /*
    Move Zeroes

Solution
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.




Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1

     */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Di chuyển tất cả số 0 về cuối, giữ thứ tự các số khác 0, in-place
    - Ý tưởng: Giống kỹ thuật removeElement
    - Bước 1: Di chuyển tất cả số khác 0 về đầu mảng (giữ nguyên thứ tự)
      + Dùng con trỏ j để đánh dấu vị trí đặt số khác 0
      + Duyệt qua mảng, gặp số khác 0 thì đặt vào nums[j] và tăng j
    - Bước 2: Điền các vị trí từ j đến cuối mảng bằng 0
    - Độ phức tạp: O(n) thời gian, O(1) không gian
    */
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int j = 0; // Con trỏ để đặt phần tử không phải 0

        // Duyệt qua mảng và di chuyển các phần tử không phải 0 về phía trước
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i]; // Đặt phần tử không phải 0 vào vị trí j và tăng j
            }
        }

        // Điền các phần tử còn lại bằng 0
        while (j < n) {
            nums[j++] = 0; // Đặt phần tử còn lại là 0
        }
    }

    /*
    Sort Array By Parity

Solution
Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition.




Constraints:

1 <= nums.length <= 5000
0 <= nums[i] <= 5000
     */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Sắp xếp số chẵn (even) trước, số lẻ (odd) sau
    - Cách 1 (2 lần duyệt - đơn giản):
      + Tạo mảng result
      + Lần 1: Duyệt qua nums, đặt các số chẵn vào result
      + Lần 2: Duyệt qua nums, đặt các số lẻ vào result
      + Độ phức tạp: O(n) thời gian, O(n) không gian
    - Cách 2 (Two Pointers - tối ưu không gian):
      + Dùng 2 con trỏ left, right
      + Swap số lẻ ở đầu với số chẵn ở cuối
      + Độ phức tạp: O(n) thời gian, O(1) không gian
    - Code hiện tại dùng cách 1
    */
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int[] result = new int[n]; // Mảng kết quả để lưu trữ các phần tử đã sắp xếp
        int j = 0; // Con trỏ để đặt phần tử chẵn

        // Duyệt qua mảng và đặt các phần tử chẵn vào mảng kết quả
        for (int num : nums) {
            if (num % 2 == 0) {
                result[j++] = num; // Đặt phần tử chẵn vào vị trí j và tăng j
            }
        }

        // Duyệt lại mảng và đặt các phần tử lẻ vào mảng kết quả
        for (int num : nums) {
            if (num % 2 != 0) {
                result[j++] = num; // Đặt phần tử lẻ vào vị trí j và tăng j
            }
        }

        return result; // Trả về mảng đã được sắp xếp
    }

    /*
      Remove Element

Solution
Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
Return k.
Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100
     */
    /*
    CÁCH GIẢI: (Giống hàm removeElement)
    - Kỹ thuật Two Pointers in-place
    - Giữ lại các phần tử != val ở đầu mảng
    - Độ phức tạp: O(n)
    */
    public int removeElement2(int[] nums, int val) {
        int k = 0; // Biến đếm số lượng phần tử không bằng val
        for (int num : nums) {
            if (num != val) {
                nums[k++] = num; // Đặt phần tử không bằng val vào vị trí k và tăng k
            }
        }
        return k; // Trả về số lượng phần tử không bằng val

    }

    /*
      Height Checker


A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.

You are given an integer array heights representing the current order that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).

Return the number of indices where heights[i] != expected[i].


Constraints:

1 <= heights.length <= 100
1 <= heights[i] <= 100
     */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Đếm số học sinh đứng sai vị trí so với thứ tự chiều cao tăng dần
    - Ý tưởng:
      + Tạo mảng expected (copy của heights)
      + Sắp xếp mảng expected (thứ tự đúng)
      + So sánh từng vị trí giữa heights và expected
      + Đếm số vị trí khác nhau
    - Độ phức tạp: O(n log n) do phải sắp xếp
    */
    public int heightChecker(int[] heights) {
        int n = heights.length;
        int[] expected = new int[n]; // Mảng để lưu trữ chiều cao đã sắp xếp
        System.arraycopy(heights, 0, expected, 0, n); // Sao chép mảng heights vào expected
        Arrays.sort(expected); // Sắp xếp mảng expected

        int count = 0; // Biến đếm số lượng chỉ số không khớp
        for (int i = 0; i < n; i++) {
            if (heights[i] != expected[i]) {
                count++; // Tăng bộ đếm nếu chiều cao không khớp
            }
        }
        return count; // Trả về số lượng chỉ số không khớp
    }
    /*
    Third Maximum Number

Solution
Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.


Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
     */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Tìm số lớn thứ 3 DISTINCT (không trùng lặp)
    - Nếu không có số thứ 3 => trả về max
    - Ý tưởng: Dùng 3 biến Integer (max1, max2, max3) để theo dõi top 3
    - Duyệt qua mảng, với mỗi số:
      + Bỏ qua nếu trùng với 1 trong 3 max (để đảm bảo distinct)
      + So sánh và cập nhật max1, max2, max3 theo thứ tự cascade
      + Nếu num > max1: dịch max1->max2->max3, gán max1=num
      + Nếu num > max2: dịch max2->max3, gán max2=num
      + Nếu num > max3: gán max3=num
    - Dùng Integer thay vì int để xử lý null (chưa có giá trị)
    - Độ phức tạp: O(n)
    */
    public int thirdMax(int[] nums) {
        Integer max1 = null; // Biến lưu giá trị lớn nhất
        Integer max2 = null; // Biến lưu giá trị lớn thứ hai
        Integer max3 = null; // Biến lưu giá trị lớn thứ ba

        for (Integer num : nums) {
            if (num.equals(max1) || num.equals(max2) || num.equals(max3)) {
                continue; // Bỏ qua nếu số đã được tính là một trong ba giá trị lớn nhất
            }
            if (max1 == null || num > max1) {
                max3 = max2; // Cập nhật giá trị lớn thứ ba
                max2 = max1; // Cập nhật giá trị lớn thứ hai
                max1 = num;  // Cập nhật giá trị lớn nhất
            } else if (max2 == null || num > max2) {
                max3 = max2; // Cập nhật giá trị lớn thứ ba
                max2 = num;  // Cập nhật giá trị lớn thứ hai
            } else if (max3 == null || num > max3) {
                max3 = num;  // Cập nhật giá trị lớn thứ ba
            }
        }

        return max3 != null ? max3 : max1; // Trả về giá trị lớn thứ ba nếu tồn tại, ngược lại trả về giá trị lớn nhất
}
/*
 Find All Numbers Disappeared in an Array

Solution
Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.



Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]
Example 2:

Input: nums = [1,1]
Output: [2]


Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n

 */
    /*
    CÁCH GIẢI:
    - Yêu cầu: Tìm các số từ 1->n không xuất hiện trong mảng
    - Ràng buộc: 1 <= nums[i] <= n (quan trọng!)
    - Ý tưởng: Kỹ thuật Marking - đánh dấu số đã xuất hiện bằng cách đổi dấu
    - Bước 1: Duyệt mảng, với mỗi số num:
      + Tính index = |num| - 1 (vị trí tương ứng trong mảng)
      + Đổi nums[index] thành số âm (đánh dấu số num đã xuất hiện)
    - Bước 2: Duyệt lại mảng, vị trí i nào còn DƯƠNG => số i+1 chưa xuất hiện
    - Ví dụ: [4,3,2,7,8,2,3,1]
      + Số 4 xuất hiện => đổi nums[3] thành âm
      + Số 5,6 không xuất hiện => nums[4], nums[5] vẫn dương
    - Độ phức tạp: O(n) thời gian, O(1) không gian (không tính result)
    */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>(); // Danh sách để lưu trữ các số bị mất
        int n = nums.length;

        // Duyệt qua mảng và đánh dấu các số đã xuất hiện bằng cách đổi dấu phần tử tại vị trí tương ứng
        for (int num : nums) {
            int index = Math.abs(num) - 1; // Tính chỉ số tương ứng (giảm 1 vì chỉ số bắt đầu từ 0)
            if (nums[index] > 0) {
                nums[index] = -nums[index]; // Đánh dấu số đã xuất hiện bằng cách đổi dấu
            }
        }

        // Duyệt lại mảng để tìm các số không bị đánh dấu (vẫn dương)
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                result.add(i + 1); // Thêm số bị mất vào danh sách kết quả (tăng 1 vì chỉ số bắt đầu từ 0)
            }
        }

        return result; // Trả về danh sách các số bị mất
    }
    /*
    Squares of a Sorted Array

Solution
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.



Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.

     */
    /*
    CÁCH GIẢI: (Giống sortedSquares)
    - Two Pointers từ 2 đầu mảng
    - So sánh giá trị tuyệt đối, chọn lớn hơn để bình phương
    - Điền từ cuối về đầu result
    - Độ phức tạp: O(n)
    */
    public int[] sortedSquares2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int left = 0;
        int right = n - 1;
        int pos = n - 1;

        while (left <= right) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[pos] = nums[left] * nums[left];
                left++;
            } else {
                result[pos] = nums[right] * nums[right];
                right--;
            }
            pos--;
        }

        return result;
    }
}