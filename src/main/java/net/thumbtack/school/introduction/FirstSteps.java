package net.thumbtack.school.introduction;

public class FirstSteps {
    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return (xLeft <= x) && (xRight >= x) && (yTop <= y) && (yBottom >= y);
    }

    public int sum(int[] array) {
        int s = 0;
        for (int element : array) {
            s += element;
        }
        return s;
    }

    public int mul(int[] array) {
        if (array.length == 0)
            return 0;
        int m = 1;
        for (int element : array) {
            m *= element;
        }
        return m;
    }

    public int min(int[] array) {
        int mi = Integer.MAX_VALUE;
        for (int element : array) {
            if (element < mi)
                mi = element;
        }
        return mi;
    }

    public int max(int[] array) {
        int ma = Integer.MIN_VALUE;
        for (int element : array) {
            if (element > ma)
                ma = element;
        }
        return ma;
    }

    public double average(int[] array) {
        if (array.length == 0)
            return 0;
        return (double) sum(array) / array.length;
    }

    public boolean isSortedDescendant(int[] array) {
        for (int i = 1; i < array.length; i++)
            if (array[i] >= array[i - 1])
                return false;
        return true;
    }

    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.pow(array[i], 3);
        }
    }

    public boolean find(int[] array, int value) {
        for (int element : array) {
            if (element == value)
                return true;
        }
        return false;
    }

    public void reverse(int[] array) {
        for (int i = 0; i < (array.length / 2); i++) {
            int buf = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = buf;
        }
    }

    public boolean isPalindrome(int[] array) {
        for (int i = 0; i < (array.length / 2); i++) {
            if (array[i] != array[array.length - 1 - i])
                return false;
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int s = 0;
        for (int[] array : matrix)
            s += sum(array);
        return s;
    }

    public int max(int[][] matrix) {
        int ma = Integer.MIN_VALUE;
        for (int[] array : matrix) {
            int ma_t = max(array);
            if (ma_t > ma)
                ma = ma_t;
        }
        return ma;
    }

    public int diagonalMax(int[][] matrix) {
        int ma = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++)
            if (matrix[i][i] > ma)
                ma = matrix[i][i];
        return ma;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int[] array : matrix)
            if (!isSortedDescendant(array))
                return false;
        return true;
    }
}
