import java.math.*;
public class Main {

    // ints to count how many operations each method carries out
    public static int operationCount1 = 0;
    public static int operationCount2 = 0;
    public static int operationCount3 = 0;
    public static int operationCount4 = 0;

    public Main(){

    }

    // method 1 (chacks if the whole string input is equal to the whole string flipped)
    public static boolean isPalindromeFullCompare(String string) {
        // empty string that will store flipped string
        String newString = "";

        // iterates through the input string backwards and adds each char to the empty string
        for (int i = string.length(); i > 0; i--) {
            newString += string.charAt(i - 1);
            operationCount1 += 2; // Increment by 2 operations
        }

        operationCount1++; // Increment operation

        if (newString.equals(string)) {
            operationCount1++; // Increment operation
            return true;
        } else {
            operationCount1++; // Increment operation
            return false;
        }
    }

    // method 2 (checks if the strings first and last character is equal, if it is it checks second and scond last equal etc
    public static boolean isPalindromeElmByElm(String string) {
        int n = 0;
        int m = string.length() - 1;

        while (n < m) {
            char firstChar = string.charAt(n);
            char endChar = string.charAt(m);
            operationCount2 += 2; // Increment by 2 for each charAt operation

            if (firstChar != endChar) {
                return false;
            }

            n++;
            m--;
            operationCount2 += 2; // Increment for each n++ and m-- operation
        }

        return true;
    }

    // method 3 ( uses stack and queue to compare strings )
    public static boolean isPalindromeStackAndQueue(String string) {
        // new stack and queue created
        ArrayStack stringStack = new ArrayStack();
        ArrayQueue stringQueue = new ArrayQueue();
        //adds characters of string to queue and stack one by one
        for (int i = 0; i < string.length(); i++) {
            stringStack.push(string.charAt(i));
            stringQueue.enqueue(string.charAt(i));
            operationCount3 += 2; // Increment by 2 operations
        }

        while (!stringStack.isEmpty()) {
            // compares the character popped by stack(first letter of string) and character dequeued( last letter of string) and then compares the next elements if they are the same
            if (stringStack.pop() != stringQueue.dequeue()) {
                return false;
            }
            operationCount3 += 2; // Increment by 2 for each pop and dequeue operation
        }

        return true;
    }

    // reverse method for method 4
    // recursive method to flip string
    public static String reverse(String str) {

        if (str == null || str.length() <= 1) {
            return str;
        }
        else {
            // return calls itslef again and adds to str
            return reverse(str.substring(1)) + str.charAt(0);
        }
    }

    // method 4 ( uses a recursive method to flip the string and then compares them
    public static boolean isPalindromeRecursiveReverse(String string) {

        // calls recursive reverse method on input string
        String reversedString = reverse(string);
        operationCount4++; // Increment method

        // checks if string returned from reverse method is the same as input string
        if (string.equals(reversedString)) {
            operationCount4++; // Increment operation
            return true;
        }

        return false;
    }

    // utility method to change decimal number string to binary string
    public static String decimalToBinary(String string){
        // creates empty string to hold input string that will get changed to decimal
        String newString = "";
        for(int i = 0; i < string.length(); i++){
            newString += string.charAt(i);
        }
        // stores the newString as a decimal
        int decString = Integer.parseInt(newString);
        // changes the decimal value to a binary string
        String binString = Integer.toBinaryString(decString);

        // returns the new binary string
        return binString;
    }


    /////////////////////////////////////////////////////

    public static void main(String[] args) {

        int numPalindromesBoth = 0;
        int numPalindromesDec = 0;
        int numPalindromesBin = 0;


        // call each method for first 1000000 numbers and check if they are a palindrome as decimal and binary
        // each function call is timed and the number of operations carried out is
        System.out.println("-----Method 1 stats: -----");
        double startTime = System.currentTimeMillis();

        for(int i = 0; i <= 1000000; i++){
            String decimalString = String.valueOf(i);
            String binaryString = Integer.toBinaryString(i);
            if(isPalindromeFullCompare(decimalString) == true){
                numPalindromesDec++;

            }
            if(isPalindromeFullCompare(binaryString) == true){
                numPalindromesBin++;
            }
            if(isPalindromeFullCompare(decimalString) == true && isPalindromeFullCompare(binaryString) == true){
                numPalindromesBoth++;
            }

        }
        double endTime = System.currentTimeMillis();
        System.out.println(numPalindromesDec+" palindromes found for decimal");
        System.out.println(numPalindromesBin+" palindromes found for binary");
        System.out.println(numPalindromesBoth+" palindromes found for both decimal and binary");
        System.out.println(operationCount1+" operations");
        double totalTime = (endTime - startTime)/1000;
        System.out.println(totalTime+" seconds taken");

        startTime = 0;
        endTime = 0;
        totalTime = 0;
        startTime = System.currentTimeMillis();
        numPalindromesBoth = 0;
        numPalindromesDec = 0;
        numPalindromesBin = 0;

        System.out.println("-----Method 2 stats: -----");
        for(int i = 0; i <= 1000000; i++){
            String decimalString = String.valueOf(i);
            String binaryString = Integer.toBinaryString(i);
            if(isPalindromeElmByElm(decimalString) == true){
                numPalindromesDec++;
            }
            if(isPalindromeElmByElm(binaryString) == true){
                numPalindromesBin++;
            }
            if(isPalindromeElmByElm(decimalString) == true && isPalindromeFullCompare(binaryString) == true){
                numPalindromesBoth++;
            }

        }

        endTime = System.currentTimeMillis();
        System.out.println(numPalindromesDec+" palindromes found for decimal");
        System.out.println(numPalindromesBin+" palindromes found for binary");
        System.out.println(numPalindromesBoth+" palindromes found for both decimal and binary");
        System.out.println(operationCount2+" operations");
        totalTime = (endTime - startTime)/1000;
        System.out.println(totalTime+" seconds taken");

        startTime = 0;
        endTime = 0;
        totalTime = 0;
        startTime = System.currentTimeMillis();
        numPalindromesBoth = 0;
        numPalindromesDec = 0;
        numPalindromesBin = 0;

        System.out.println("-----Method 3 stats: -----");
        for(int i = 0; i <= 1000000; i++){
            String decimalString = String.valueOf(i);
            String binaryString = Integer.toBinaryString(i);
            if(isPalindromeStackAndQueue(decimalString) == true){
                numPalindromesDec++;
            }
            if(isPalindromeStackAndQueue(binaryString) == true){
                numPalindromesBin++;
            }
            if(isPalindromeStackAndQueue(decimalString) == true && isPalindromeFullCompare(binaryString) == true){
                numPalindromesBoth++;
            }

        }

        endTime = System.currentTimeMillis();
        System.out.println(numPalindromesDec+" palindromes found for decimal");
        System.out.println(numPalindromesBin+" palindromes found for binary");
        System.out.println(numPalindromesBoth+" palindromes found for both decimal and binary");
        System.out.println(operationCount3+" operations");
        totalTime = (endTime - startTime)/1000;
        System.out.println(totalTime+" seconds taken");

        startTime = 0;
        endTime = 0;
        totalTime = 0;
        startTime = System.currentTimeMillis();
        numPalindromesBoth = 0;
        numPalindromesDec = 0;
        numPalindromesBin = 0;

        System.out.println("-----Method 4 stats: -----");
        for(int i = 0; i <= 1000000; i++){
            String decimalString = String.valueOf(i);
            String binaryString = Integer.toBinaryString(i);
            if(isPalindromeRecursiveReverse(decimalString) == true){
                numPalindromesDec++;
            }
            if(isPalindromeRecursiveReverse(binaryString) == true){
                numPalindromesBin++;
            }
            if(isPalindromeRecursiveReverse(decimalString) == true && isPalindromeFullCompare(binaryString) == true){
                numPalindromesBoth++;
            }

        }

        endTime = System.currentTimeMillis();
        System.out.println(numPalindromesDec+" palindromes found for decimal");
        System.out.println(numPalindromesBin+" palindromes found for binary");
        System.out.println(numPalindromesBoth+" palindromes found for both decimal and binary");
        System.out.println(operationCount4+" operations");
        totalTime = (endTime - startTime)/1000;
        System.out.println(totalTime+" seconds taken");
    }
}

