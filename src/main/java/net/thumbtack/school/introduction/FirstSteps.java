package net.thumbtack.school.introduction;

public class FirstSteps {
    public int sum (int x, int y){
        return x + y;
    }

    public int mul (int x, int y){
        return x * y;
    }

    public int div (int x, int y){
        return x / y;
    }

    public int mod (int x, int y){
        return x % y;
    }

    public boolean isEqual (int x, int y){
        return x == y;
    }

    public boolean isGreater (int x, int y){
        return x > y;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y){
        return (xLeft <= x) && (xRight >= x) && (yTop <= y) && (yBottom >= y);
    }

    public int sum(int[] array){
        int s = 0;
        for (int i = 0; i < array.length; i++){
            s += array[i];
        }
        return s;
    }

    public int mul(int[] array){
        int m = 1;
        if(array.length == 0)
            return 0;
        for (int i = 0; i < array.length; i++) {
            m *= array[i];
        }
        return m;
    }
    public int min(int[] array) {
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < mi)
                mi = array[i];
        }
        return mi;
    }

    public int max(int[] array) {
        int ma = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > ma)
                ma = array[i];
        }
        return ma;
    }

    public double average(int[] array) {
        if (array.length == 0)
            return 0;
        int s = 0;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            count++;
            s += array[i];
        }
        return (double)s / (double)count;
    }

    public boolean isSortedDescendant(int[] array) {
        for (int i = 1; i < array.length; i++)
            if (array[i] >= array[i - 1])
                return false;
        return true;
    }

    public void cube(int[]array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)Math.pow(array[i], 3);
        }
    }

    public boolean find(int[]array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return true;
        }
        return false;
    }

    public void reverse(int[]array) {
        for (int i = 0; i < (array.length / 2); i++) {
            int buf = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = buf;
        }
    }

    public boolean isPalindrome(int[]array) {
        for (int i = 0; i < (array.length / 2); i++) {
            if (array[i] != array[array.length - 1 - i])
                return false;
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int s = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                s += matrix[i][j];
        return s;
    }

    public int max(int[][] matrix) {
        int ma = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            int ma_t = max(matrix[i]);
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
        for(int i = 0; i < matrix.length; i++)
            if(!isSortedDescendant(matrix[i]))
                return false;
        return true;
    }
}
