package com.galaxy.StockAdviser.constants;

/**
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class PrintConstants {

    public static String getTS(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    public static void printDashLine() {
        System.out.println("----------------------------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------------------------");
    }

    public static void getLS() {
        System.out.println("\n\n");
    }

}
