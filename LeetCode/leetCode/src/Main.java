import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Leetcode_Array solution = new Leetcode_Array();

        System.out.println("========== LEETCODE ARRAY PROBLEMS TEST ==========\n");

        // Test 1: Find Max Consecutive Ones
        System.out.println("1. FIND MAX CONSECUTIVE ONES");
        System.out.println("   Tìm số lượng số 1 liên tiếp tối đa trong mảng");
        int[] nums1 = {1, 1, 0, 1, 1, 1};
        System.out.println("   Input: " + Arrays.toString(nums1));
        System.out.println("   Output: " + solution.findMaxConsecutiveOnes(nums1));
        System.out.println("   Expected: 3\n");

        // Test 2: Find Numbers with Even Number of Digits
        System.out.println("2. FIND NUMBERS WITH EVEN NUMBER OF DIGITS");
        System.out.println("   Đếm số lượng các số có số chữ số chẵn");
        int[] nums2 = {12, 345, 2, 6, 7896};
        System.out.println("   Input: " + Arrays.toString(nums2));
        System.out.println("   Output: " + solution.findNumbers(nums2));
        System.out.println("   Expected: 2 (12 có 2 chữ số, 7896 có 4 chữ số)\n");

        // Test 3: Sorted Squares
        System.out.println("3. SQUARES OF A SORTED ARRAY");
        System.out.println("   Bình phương các phần tử và sắp xếp kết quả");
        int[] nums3 = {-4, -1, 0, 3, 10};
        System.out.println("   Input: " + Arrays.toString(nums3));
        System.out.println("   Output: " + Arrays.toString(solution.sortedSquares(nums3)));
        System.out.println("   Expected: [0, 1, 9, 16, 100]\n");

        // Test 4: Duplicate Zeros
        System.out.println("4. DUPLICATE ZEROS");
        System.out.println("   Nhân đôi mỗi số 0, dịch các phần tử còn lại sang phải");
        int[] nums4 = {1, 0, 2, 3, 0, 4, 5, 0};
        System.out.println("   Input: " + Arrays.toString(nums4));
        solution.duplicateZeros(nums4);
        System.out.println("   Output: " + Arrays.toString(nums4));
        System.out.println("   Expected: [1, 0, 0, 2, 3, 0, 0, 4]\n");

        // Test 5: Merge Sorted Array
        System.out.println("5. MERGE SORTED ARRAY");
        System.out.println("   Gộp 2 mảng đã sắp xếp thành 1 mảng sắp xếp");
        int[] nums5a = {1, 2, 3, 0, 0, 0};
        int[] nums5b = {2, 5, 6};
        System.out.println("   Input: nums1=" + Arrays.toString(nums5a) + ", m=3");
        System.out.println("          nums2=" + Arrays.toString(nums5b) + ", n=3");
        solution.merge(nums5a, 3, nums5b, 3);
        System.out.println("   Output: " + Arrays.toString(nums5a));
        System.out.println("   Expected: [1, 2, 2, 3, 5, 6]\n");

        // Test 6: Remove Element
        System.out.println("6. REMOVE ELEMENT");
        System.out.println("   Xóa tất cả các phần tử có giá trị bằng val");
        int[] nums6 = {3, 2, 2, 3};
        int val = 3;
        System.out.println("   Input: " + Arrays.toString(nums6) + ", val=" + val);
        int k6 = solution.removeElement(nums6, val);
        System.out.println("   Output: k=" + k6 + ", nums=" + Arrays.toString(Arrays.copyOf(nums6, k6)));
        System.out.println("   Expected: k=2, nums=[2, 2]\n");

        // Test 7: Remove Duplicates
        System.out.println("7. REMOVE DUPLICATES FROM SORTED ARRAY");
        System.out.println("   Xóa các phần tử trùng lặp trong mảng đã sắp xếp");
        int[] nums7 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("   Input: " + Arrays.toString(nums7));
        int k7 = solution.removeDuplicates(nums7);
        System.out.println("   Output: k=" + k7 + ", nums=" + Arrays.toString(Arrays.copyOf(nums7, k7)));
        System.out.println("   Expected: k=5, nums=[0, 1, 2, 3, 4]\n");

        // Test 8: Check If N and Its Double Exist
        System.out.println("8. CHECK IF N AND ITS DOUBLE EXIST");
        System.out.println("   Kiểm tra có tồn tại cặp i,j mà arr[i] = 2*arr[j]");
        int[] nums8 = {10, 2, 5, 3};
        System.out.println("   Input: " + Arrays.toString(nums8));
        System.out.println("   Output: " + solution.checkIfExist(nums8));
        System.out.println("   Expected: true (10 = 2*5)\n");

        // Test 9: Valid Mountain Array
        System.out.println("9. VALID MOUNTAIN ARRAY");
        System.out.println("   Kiểm tra mảng có phải là mảng núi hợp lệ không");
        int[] nums9 = {0, 3, 2, 1};
        System.out.println("   Input: " + Arrays.toString(nums9));
        System.out.println("   Output: " + solution.validMountainArray(nums9));
        System.out.println("   Expected: true (tăng từ 0->3, giảm từ 3->1)\n");

        // Test 10: Replace Elements with Greatest on Right
        System.out.println("10. REPLACE ELEMENTS WITH GREATEST ON RIGHT");
        System.out.println("    Thay mỗi phần tử bằng phần tử lớn nhất bên phải nó");
        int[] nums10 = {17, 18, 5, 4, 6, 1};
        System.out.println("    Input: " + Arrays.toString(nums10));
        int[] result10 = solution.replaceElements(nums10);
        System.out.println("    Output: " + Arrays.toString(result10));
        System.out.println("    Expected: [18, 6, 6, 6, 1, -1]\n");

        // Test 11: Move Zeroes
        System.out.println("11. MOVE ZEROES");
        System.out.println("    Di chuyển tất cả số 0 về cuối mảng");
        int[] nums11 = {0, 1, 0, 3, 12};
        System.out.println("    Input: " + Arrays.toString(nums11));
        solution.moveZeroes(nums11);
        System.out.println("    Output: " + Arrays.toString(nums11));
        System.out.println("    Expected: [1, 3, 12, 0, 0]\n");

        // Test 12: Sort Array By Parity
        System.out.println("12. SORT ARRAY BY PARITY");
        System.out.println("    Sắp xếp số chẵn trước, số lẻ sau");
        int[] nums12 = {3, 1, 2, 4};
        System.out.println("    Input: " + Arrays.toString(nums12));
        int[] result12 = solution.sortArrayByParity(nums12);
        System.out.println("    Output: " + Arrays.toString(result12));
        System.out.println("    Expected: [2, 4, 3, 1] (hoặc bất kỳ thứ tự nào với chẵn trước)\n");

        // Test 13: Height Checker
        System.out.println("13. HEIGHT CHECKER");
        System.out.println("    Đếm số vị trí không khớp với thứ tự đã sắp xếp");
        int[] nums13 = {1, 1, 4, 2, 1, 3};
        System.out.println("    Input: " + Arrays.toString(nums13));
        System.out.println("    Output: " + solution.heightChecker(nums13));
        System.out.println("    Expected: 3\n");

        // Test 14: Third Maximum Number
        System.out.println("14. THIRD MAXIMUM NUMBER");
        System.out.println("    Tìm số lớn thứ 3 (distinct)");
        int[] nums14 = {3, 2, 1};
        System.out.println("    Input: " + Arrays.toString(nums14));
        System.out.println("    Output: " + solution.thirdMax(nums14));
        System.out.println("    Expected: 1\n");

        // Test 15: Find Disappeared Numbers
        System.out.println("15. FIND ALL NUMBERS DISAPPEARED IN AN ARRAY");
        System.out.println("    Tìm các số từ 1->n không xuất hiện trong mảng");
        int[] nums15 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("    Input: " + Arrays.toString(nums15));
        List<Integer> result15 = solution.findDisappearedNumbers(nums15);
        System.out.println("    Output: " + result15);
        System.out.println("    Expected: [5, 6]\n");

        System.out.println("========== ALL TESTS COMPLETED ==========");
    }
}