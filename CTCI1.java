import java.util.Hashtable;
import java.lang.StringBuilder;
import java.lang.Character;

public class CTCI1 {
    public static void main(String args[])
    {
        System.out.println("Solution I-1");
        SolutionI1.run();
        System.out.println();

        System.out.println("Solution I-2");
        SolutionI2.run();
        System.out.println();

        System.out.println("Solution I-3");
        SolutionI3.run();
        System.out.println();

        System.out.println("Solution I-4");
        SolutionI4.run();

        System.out.println("Solution I-5");
        SolutionI5.run();
        System.out.println();

        System.out.println("Solution I-6");
        SolutionI6.run();
        System.out.println();

        System.out.println("Solution I-7");
        SolutionI7.run();
        System.out.println();

        System.out.println("Solution I-8");
        SolutionI8.run();
        System.out.println();

        System.out.println("Solution I-9");
        SolutionI9.run();
        System.out.println();
    }
}

// Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
// cannot use additional data structures?
class SolutionI1 {
    public static void run()
    {
        String testStrings[] = {"abcdaer", "abcdzg"};

        for (String tS : testStrings)
        {
            System.out.print("The characters in the string \"" + tS + "\" are ");
            System.out.println(isStringUnique(tS) ? "all unique." : "not unique.");
        }
    }

    public static boolean isStringUnique(String inputString)
    {
        // Use an array of boolean values for each ASCII characters
        boolean charsEncountered[] = new boolean[128];
        for (char c : inputString.toCharArray())
        {
            if (charsEncountered[c])
                return false;
            charsEncountered[c] = true;
        }
        return true;
    }
}

// Check Permutation: Given two strings, write a method to decide if one is a permutation of the
// other.
class SolutionI2
{
    public static void run()
    {
        String a = "kalml", b = "maalk";
        System.out.print("The characters in the string \"" + a + "\" and \"" + b + "\" are ");
        System.out.print(isPermutation(a, b) ? "permutations" : "not permutations");
        System.out.println(" of each other.");

        String c = "kalml", d = "mallk";
        System.out.print("The characters in the string \"" + c + "\" and \"" + d + "\" are ");
        System.out.print(isPermutation(c, d) ? "permutations" : "not permutations");
        System.out.println(" of each other.");
    }

    public static boolean isPermutation(String stringA, String stringB)
    {
        // Create the hash table for the first string
        Hashtable<Character, Integer> charCountA = new Hashtable<>();
        for (char c : stringA.toCharArray())
        {
            if (charCountA.containsKey(c))
            {
                charCountA.replace(c, charCountA.get(c) + 1);
            }
            else
                charCountA.put(c, 1);
        }

        // Create the hash table for the second string
        Hashtable<Character, Integer> charCountB = new Hashtable<>();
        for (char c : stringB.toCharArray())
        {
            if (charCountB.containsKey(c))
            {
                charCountB.replace(c, charCountB.get(c) + 1);
            }
            else
                charCountB.put(c, 1);
        }

        // If the hash table are the same, then they are permutations of each other.
        return charCountA.equals(charCountB);
    }
}

// URLify: Write a method to replace all spaces in a string with "%20". You may assume that the string
// has sufficient space at the end to hold the additional characters, and that you are given the "true"
// length of the string. (Note: If implementing in Java, please use a character array so that you can
// perform this operation in place.)
class SolutionI3
{
    public static void run()
    {
        String testString = "Mr John Smith";
        System.out.println("\"" + testString + "\" becomes \"" + URLify(testString) + "\".");
    }

    public static String URLify(String inputString)
    {
        char inputCharArray[] = inputString.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : inputCharArray)
        {
            if (c == ' ')
            {
                stringBuilder.append("%20");
            }
            else
            {
                stringBuilder.append(c);
            }
        }

        return stringBuilder.toString();
    }
}

// Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
// A palindrome is a word or phrase that is the same forwards and backwards. A permutation
// is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
class SolutionI4
{
    public static void run()
    {
        String testStrings[] = {"Tact Coa", "Tactt Coa"};
        for (String s : testStrings)
        {
            System.out.print("\"" + s + "\" is ");
            System.out.println(palindromePermutation(s) ? "a palindrome permutation." : "not a palindrome permutation.");
        }
    }

    public static boolean palindromePermutation(String inputString)
    {
        String processedString;

        // Remove all the spaces
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : inputString.toCharArray())
        {
            if (c != ' ')
                stringBuilder.append(c);
        }
        processedString = stringBuilder.toString();

        // Turn all uppercase to lowercase
        stringBuilder = new StringBuilder();
        for (char c : processedString.toCharArray())
        {
            if (Character.isUpperCase(c))
                stringBuilder.append(Character.toLowerCase(c));
            else
                stringBuilder.append(c);
        }
        processedString = stringBuilder.toString();

        // Get counts of all characters
        int charCounts[] = new int[128];
        for (char c : processedString.toCharArray())
        {
            charCounts[c] += 1;
        }

        // Assume that a palindrome happens when there is
        // at most only one odd count of character.
        boolean oddCountEncountered = false;
        for (int i : charCounts)
        {
            if (i % 2 != 0)
            {
                if (oddCountEncountered)
                    return false;
                else
                    oddCountEncountered = true;
            }
        }
        return true;
    }
}

// One Away: There are three types of edits that can be performed on strings: insert a character,
// remove a character, or replace a character. Given two strings, write a function to check if they are
// one edit (or zero edits) away.
class SolutionI5
{
    public static void run()
    {
        test("ple", "pale");
        test("pale", "ple");
        test("pales", "pale");
        test("pale", "bale");
        test("pale", "bake");
    }

    public static void test(String a, String b)
    {
        System.out.print("The strings \"" + a + "\" and \"" + b + "\" are ");
        System.out.print(oneAway(a, b) ? "one edit away" : "not one edit away");
        System.out.println(" of each other.");
    }

    public static boolean checkInsert(String a, String b)
    {
        // Get the char arrays for both strings.
        char aChars[] = a.toCharArray(), bChars[] = b.toCharArray();

        // Compare the chars in both arrays.
        int i = 0, j = 0;
        while (i < aChars.length)
        {   
            // Check if the first iterator has reached the end
            if (i == bChars.length)
                return true;
            // If not then try to compare the characters in both strings
            else
            {
                // If there is a difference in characters...
                if (aChars[i] != bChars[j])
                {
                    // Move the first iterator by one
                    i++;

                    // If they are not the same, return false
                    if (aChars[i] != bChars[j])
                        return false;
                }
            }
            
            // Increment both iterators.
            i++;
            j++;
        }

        return true;
    }

    public static boolean checkReplace(String a, String b)
    {
        // Get the char arrays for both strings.
        char aChars[] = a.toCharArray(), bChars[] = b.toCharArray();

        boolean replacedOneChar = false;
        for (int i = 0; i < aChars.length; i++)
        {
            if (aChars[i] != bChars[i])
            {
                if (replacedOneChar)
                    return false;
                else
                    replacedOneChar = true;
            }
        }
        return true;
    }

    public static boolean oneAway(String a, String b)
    {
        // If the string length difference is more than 1 characters,
        // return false.
        if (a.length() - b.length() < -1 || a.length() - b.length() > 1)
            return false;
        else
        {
            // Check for delete condition (a is longer than b by one char)
            if (a.length() - b.length() == 1)
                return checkInsert(a, b);
            // Check for insert condition (a is shorter than b by one char)
            else if (a.length() - b.length() == -1)
                return checkInsert(b, a);
            else
                return checkReplace(a, b);
        }
    }
}

// String Compression: Implement a method to perform basic string compression using the counts
// of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
// "compressed" string would not become smaller than the original string, your method should return
// the original string. You can assume the string has only uppercase and lowercase letters (a - z).
class SolutionI6
{
    public static void run()
    {
        String testString = "aabcccccaaa";
        System.out.println("\"" + testString + "\" becomes \"" + stringCompression(testString) + "\".");
    }

    public static String stringCompression(String inputString)
    {
        // Declare variables
        char inputChar[] = inputString.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        // Keep record of the last character and its count
        char lastChar = ' ';
        int currentCharCount = 0;
        for (char c : inputChar)
        {
            if (c == lastChar)
            {
                currentCharCount++;
            }
            else
            {
                if (lastChar != ' ')
                    stringBuilder.append(lastChar);
                if (currentCharCount > 0)
                    stringBuilder.append(currentCharCount);
                
                currentCharCount = 1;
            }

            // Store the last char
            lastChar = c;
        }

        // Append the last char
        stringBuilder.append(lastChar);
        stringBuilder.append(currentCharCount);

        // Return the compressed output if the length is smaller than
        // the input string
        String compressedString = stringBuilder.toString();
        if (compressedString.length() < inputString.length())
            return compressedString;
        else
            return inputString;
    }
}

// Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
// bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
class SolutionI7
{
    public static void run()
    {
        int testMatrix[][] = {
            {1, 2, 3, 4}, 
            {5, 6, 7, 8}, 
            {9, 10, 11, 12}, 
            {13, 14, 15, 16}
        };

        System.out.println("The original matrix is: ");
        printMatrix(testMatrix);
        System.out.println("The rotated matrix is: ");
        printMatrix(rotateMatrix(testMatrix));
    }

    private static void printMatrix(int matrix[][])
    {
        if (matrix.length < 1)
            return;
        
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Rotate counterclockwise
    private static int[][] rotateMatrix(int matrix[][])
    {
        int n = matrix.length;
        int layers = n / 2;

        // Iterate through all the layers
        for (int layer = 0; layer < layers; layer++)
        {
            int first = layer;
            int last = n - layer - 1;

            for (int i = first; i < last; i++)
            {
                // save the top
                int temp = matrix[first][i];

                // right to top
                matrix[first][i] = matrix[i][last];

                // bottom to right
                matrix[i][last] = matrix[last][last - (i - first)];

                // left to bottom
                matrix[last][last - (i - first)] = matrix[last - (i - first)][first];

                // top to left
                matrix[last - (i - first)][first] = temp;
            }
        }

        return matrix;
    }
}

// Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
// column are set to O.
class SolutionI8
{
    public static void run()
    {
        int testMatrix[][] = {
            {1, 2, 3, 4, 0}, 
            {6, 7, 8, 9, 10}, 
            {11, 0, 13, 14, 15}, 
            {16, 17, 18, 19, 20}
        };

        System.out.println("The original matrix is: ");
        printMatrix(testMatrix);
        System.out.println("The zeroed matrix is: ");
        printMatrix(zeroMatrix(testMatrix));
    }

    private static void printMatrix(int matrix[][])
    {
        if (matrix.length < 1)
            return;
        
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] zeroMatrix(int matrix[][])
    {
        boolean zeroRows[] = new boolean[matrix.length];
        boolean zeroCols[] = new boolean[matrix[0].length];

        // Get all the row and column index where 0 existed
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                if (matrix[i][j] == 0)
                {
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }

        // Modify the matrix:
        // Zero all the elements in the row
        for (int i = 0; i < zeroRows.length; i++)
        {
            if (zeroRows[i] == true)
            {
                for (int j = 0; j < matrix[i].length; j++)
                    matrix[i][j] = 0;
            }
        }
        // Zero all the elements in the columns
        for (int j = 0; j < zeroCols.length; j++)
        {
            if (zeroCols[j] == true)
            {
                for (int i = 0; i < matrix.length; i++)
                    matrix[i][j] = 0;
            }
        }

        return matrix;
    }
}

// String Rotation: Assume you have a method isSubst ring which checks if one word is a substring
// of another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one
// call to isSubstring (e.g., "waterbottle" is a rotation of "erbottlewat").
class SolutionI9
{
    public static void run()
    {
        test("waterbottle", "erbottlewat");
        test("waterbottler", "erbottlewat");
        test("tenet", "erbottlewat");
        test("tenet", "netet");
        test("tenet", "nette");
    }

    private static void test(String a, String b)
    {
        System.out.print("The string \"" + a + "\" is ");
        System.out.print(stringRotation(a, b) ? "a rotation" : "not a rotation");
        System.out.println(" of \"" + b + "\".");
    }

    private static boolean isSubstring(String sub, String full)
    {
        char subChars[] = sub.toCharArray(), fullChars[] = full.toCharArray();

        for (int i = 0; i < fullChars.length; i++)
        {
            if (fullChars[i] == subChars[0])
            {
                boolean potentialSubstring = true;
                for (int j = 0; j < subChars.length; j++)
                {
                    // If the sum of the index is out of bounds,
                    // it is not a substring.
                    if (i + j >= fullChars.length)
                    {
                        potentialSubstring = false;
                        break;
                    }

                    // If any of the chained characters is not the same,
                    // it is not a substring.
                    if (fullChars[i + j] != subChars[j])
                    {
                        potentialSubstring = false;
                        break;
                    }
                }
                if (potentialSubstring)
                    return true;
            }
        }

        return false;
    }

    private static boolean stringRotation(String a, String b)
    {
        if (a.length() != b.length())
            return false;

        // Concatenate b onto itself
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append(b);
        String doubleB = stringBuilder.toString();

        return isSubstring(a, doubleB);
    }
}