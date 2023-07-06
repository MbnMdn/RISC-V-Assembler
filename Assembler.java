import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Long.parseLong;

public class Assembler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // test cases are available on GitHub
        int n =scanner.nextInt();
        ArrayList<String> tests = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            tests.add(i,scanner.nextLine());
        }
        for (int i = 1; i < tests.size(); i++) {
            recognition(tests.get(i));
        }
    }

    public static void recognition(String input) {
        String[] sp = input.split(" ");
        StringBuilder sb1,sb2,sb3,sb4;
        long distance, offset;
        int sign=2;

        switch (sp[2]) {
            case "slli":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs1
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //shamt
                sb3.delete(0, 2);

                slli(sb1.toString(), sb2.toString(), sb3.toString());
                break;

            case "srai":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs1
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //shamt
                sb3.delete(0, 2);

                srai(sb1.toString(), sb2.toString(), sb3.toString());
                break;

            case "lui":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //imm
                sb2.delete(0, 2);

                lui(sb1.toString(), sb2.toString());
                break;

            case "auipc":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //imm
                sb2.delete(0, 2);

                auipc(sb1.toString(), sb2.toString());
                break;

            case "addi":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs1
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //imm
                sb3.delete(0, 2);

                addi(sb1.toString(), sb2.toString(), sb3.toString());
                break;

            case "add":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs1
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //rs2
                sb3.delete(0, 1);
                add(sb1.toString(), sb2.toString(), sb3.toString());
                break;

            case "and":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs1
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //rs2
                sb3.delete(0, 1);
                and(sb1.toString(), sb2.toString(), sb3.toString());
                break;

            case "or":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs1
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //rs2
                sb3.delete(0, 1);
                or(sb1.toString(), sb2.toString(), sb3.toString());
                break;

            case "xor":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs1
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //rs2
                sb3.delete(0, 1);
                xor(sb1.toString(), sb2.toString(), sb3.toString());
                break;

            case "sll":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs1
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //rs2
                sb3.delete(0, 1);
                sll(sb1.toString(), sb2.toString(), sb3.toString());
                break;

            case "lw":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //offset
                sb2.delete(0,2);
                offset=Integer.parseInt(String.valueOf(sb2),16);
                sb3 = new StringBuilder(sp[7]); //rs1
                sb3.delete(0, 1);
                lw(sb1.toString(), offset, sb3.toString());
                break;

            case "lb":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //offset
                sb2.delete(0,2);
                offset=Integer.parseInt(String.valueOf(sb2),16);
                sb3 = new StringBuilder(sp[7]); //rs1
                sb3.delete(0, 1);
                lb(sb1.toString(), offset, sb3.toString());
                break;

            case "lbu":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //offset
                sb2.delete(0,2);
                offset=Integer.parseInt(String.valueOf(sb2),16);
                sb3 = new StringBuilder(sp[7]); //rs1
                sb3.delete(0, 1);
                lbu(sb1.toString(), offset, sb3.toString());
                break;

            case "jalr":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //offset
                sb2.delete(0,2);
                offset=Integer.parseInt(String.valueOf(sb2),16);
                sb3 = new StringBuilder(sp[7]); //rs1
                sb3.delete(0, 1);
                jalr(sb1.toString(), offset, sb3.toString());

                break;

            case "sw":
                sb1 = new StringBuilder(sp[3]); //rs2
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //offset
                sb2.delete(0,2);
                offset=Integer.parseInt(String.valueOf(sb2),16);
                sb3 = new StringBuilder(sp[7]); //rs1
                sb3.delete(0, 1);
                sw(sb1.toString(),offset, sb3.toString());
                break;

            case "sb":
                sb1 = new StringBuilder(sp[3]); //rs2
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //offset
                sb2.delete(0,2);
                offset=Integer.parseInt(String.valueOf(sb2),16);
                sb3 = new StringBuilder(sp[7]); //rs1
                sb3.delete(0, 1);
                sb(sb1.toString(), offset, sb3.toString());
                break;

            case "jal":
                sb1 = new StringBuilder(sp[3]); //rd
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //to go label
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[0]); //current label
                sb3.delete(0, 1);
                distance = (Long.parseLong(String.valueOf(sb2)) - Long.parseLong(String.valueOf(sb3)))*4;
                jal(sb1.toString(), distance);
                break;

            case "beq":
                sb1 = new StringBuilder(sp[3]); //rs1
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs2
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //to go label
                sb3.delete(0, 1);
                sb4 = new StringBuilder(sp[0]); //current label
                sb4.delete(0, 1);
                distance = (Long.parseLong(String.valueOf(sb3)) - Long.parseLong(String.valueOf(sb4)))*4;
                beq(sb1.toString(),sb2.toString(),distance);
                break;

            case "bltu":
                sb1 = new StringBuilder(sp[3]); //rs1
                sb1.delete(0, 1);
                sb2 = new StringBuilder(sp[5]); //rs2
                sb2.delete(0, 1);
                sb3 = new StringBuilder(sp[7]); //to go label
                sb3.delete(0, 1);
                sb4 = new StringBuilder(sp[0]); //current label
                sb4.delete(0, 1);
                distance = (Long.parseLong(String.valueOf(sb3)) - Long.parseLong(String.valueOf(sb4)))*4;
                bltu(sb1.toString(),sb2.toString(),distance);
                break;
        }
    }

    public static void slli(String rd, String rs1, String shamt) {
        String rd_ssli = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String rs1_ssli = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String output = "0000000" + hexadecimalToBinary(shamt, 5) + rs1_ssli + "001" + rd_ssli + "0010011";
        System.out.println(binary_to_hex(output));
    }

    public static void lui(String rd, String imm) {
        String rd_lui = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String output = hexadecimalToBinary(imm, 20) + rd_lui + "0110111";
        System.out.println(binary_to_hex(output));
    }

    public static void auipc(String rd, String imm) {
        String rd_lui = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String output = hexadecimalToBinary(imm, 20) + rd_lui + "0010111";
        System.out.println(binary_to_hex(output));
    }

    public static void addi(String rd, String rs1, String imm) {
        String rd_addi = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String rs1_addi = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String output = hexadecimalToBinary(imm, 12) + rs1_addi + "000" + rd_addi + "0010011";
        System.out.println(binary_to_hex(output));
    }

    public static void add(String rd, String rs1, String rs2) {
        String rs1_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String rs2_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs2)), 5);
        String rd_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String output = "0000000" + rs2_add + rs1_add + "000" + rd_add + "0110011";
        System.out.println(binary_to_hex(output));
    }

    public static void and(String rd, String rs1, String rs2) {
        String rs1_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String rs2_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs2)), 5);
        String rd_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String output = "0000000" + rs2_add + rs1_add + "111" + rd_add + "0110011";
        System.out.println(binary_to_hex(output));
    }

    public static void or(String rd, String rs1, String rs2) {
        String rs1_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String rs2_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs2)), 5);
        String rd_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String output = "0000000" + rs2_add + rs1_add + "110" + rd_add + "0110011";
        System.out.println(binary_to_hex(output));
    }

    public static void xor(String rd, String rs1, String rs2) {
        String rs1_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String rs2_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs2)), 5);
        String rd_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String output = "0000000" + rs2_add + rs1_add + "100" + rd_add + "0110011";
        System.out.println(binary_to_hex(output));
    }

    public static void sll(String rd, String rs1, String rs2) {
        String rs1_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String rs2_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs2)), 5);
        String rd_add = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String output = "0000000" + rs2_add + rs1_add + "001" + rd_add + "0110011";
        System.out.println(binary_to_hex(output));
    }

    public static void srai(String rd, String rs1, String shamt) {
        String rd_ssli = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String rs1_ssli = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String output = "0100000" + hexadecimalToBinary(shamt, 5) + rs1_ssli + "101" + rd_ssli + "0010011";
        System.out.println(binary_to_hex(output));
    }

    public static void lw(String rd, long offset, String rs1) {
        String rd_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String rs1_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String imm_lw = fillEmptyBytes(Long.toBinaryString(offset), 11);
        String output = imm_lw + rs1_lw + "010" + rd_lw + "0000011";
        System.out.println(binary_to_hex(output));
    }

    public static void lb(String rd, long offset, String rs1) {
        String rd_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String rs1_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String imm_lw = fillEmptyBytes(Long.toBinaryString(offset), 11);
        String output = imm_lw + rs1_lw + "000" + rd_lw + "0000011";
        System.out.println(binary_to_hex(output));
    }

    public static void lbu(String rd, long offset, String rs1) {
        String rd_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String rs1_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String imm_lw = fillEmptyBytes(Long.toBinaryString(offset), 11);
        String output = imm_lw + rs1_lw + "100" + rd_lw + "0000011";
        System.out.println(binary_to_hex(output));
    }

    public static void jalr(String rd, long offset, String rs1) {
        String rd_jalr = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        String rs1_jalr = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String imm_jalr = fillEmptyBytes(Long.toBinaryString(offset), 11);
        String output = imm_jalr + rs1_jalr + "000" + rd_jalr + "1100111";
        System.out.println(binary_to_hex(output));
    }

    public static void sw(String rs2, long offset, String rs1) {
        String rd_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs2)), 5);
        String rs1_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String imm_lw = fillEmptyBytes(Long.toBinaryString(offset), 12);
        String imm_firstPart = imm_lw.substring(7, 12);
        String imm_secondPart = imm_lw.substring(0, 7);
        String output = imm_secondPart + rd_lw + rs1_lw + "010" + imm_firstPart + "0100011";
        System.out.println(binary_to_hex(output));
    }

    public static void sb(String rs2, long offset, String rs1) {
        String rd_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs2)), 5);
        String rs1_lw = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        String imm_lw = fillEmptyBytes(Long.toBinaryString(offset), 12);
        String imm_firstPart = imm_lw.substring(7, 12);
        String imm_secondPart = imm_lw.substring(0, 7);
        String output = imm_secondPart + rd_lw + rs1_lw + "000" + imm_firstPart + "0100011";
        System.out.println(binary_to_hex(output));
    }

    public static void jal(String rd, long distance) {
        String rd_jal = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rd)), 5);
        long shifted_imm = distance >> 1;
        String imm_jal = fillEmptyBytes(Long.toBinaryString(shifted_imm), 20);
        imm_jal = imm_jal.substring(imm_jal.length()-20,imm_jal.length());
        String output = imm_jal.charAt(0) + imm_jal.substring(10,20) + imm_jal.charAt(9) + imm_jal.substring(1,9) + rd_jal + "1101111";
        System.out.println(binary_to_hex(output));
    }

    public static void beq(String rs2,String rs1, long distance) {
        String rs2_beq = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs2)), 5);
        String rs1_beq = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        long shifted_imm = distance >> 1;
        String imm_beq = fillEmptyBytes(Long.toBinaryString(shifted_imm), 12);
        imm_beq = imm_beq.substring(imm_beq.length()-12,imm_beq.length());
        String output = imm_beq.charAt(0)+ imm_beq.substring(2,8) +rs1_beq+rs2_beq+"000"+imm_beq.substring(8,12)+imm_beq.charAt(1) + "1100011";
        System.out.println(binary_to_hex(output));
    }

    public static void bltu(String rs2,String rs1, long distance) {
        String rs2_beq = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs2)), 5);
        String rs1_beq = fillEmptyBytes(Integer.toBinaryString(Integer.parseInt(rs1)), 5);
        long shifted_imm = distance >> 1;
        String imm_beq = fillEmptyBytes(Long.toBinaryString(shifted_imm), 12);
        imm_beq = imm_beq.substring(imm_beq.length()-12,imm_beq.length());
        String output = imm_beq.charAt(0) + imm_beq.substring(2,8) +rs1_beq+rs2_beq+"110"+imm_beq.substring(8,12)+imm_beq.charAt(1) + "1100011";
        System.out.println(binary_to_hex(output));
    }

    public static String binary_to_hex(String binary) {
        long number = parseLong(binary, 2);
        String hexa = Long.toHexString(number);
        hexa = "0x" + fillEmptyBytes(hexa, 8);
        return hexa;
    }

    public static String fillEmptyBytes(String s, int n) {
        if (s.length() < n) {
            StringBuilder sBuilder = new StringBuilder(s);
            for (int i = 0; i < n - sBuilder.length(); i++) {
                s = "0" + s;
            }
        }
        return s;
    }

    public static String hexadecimalToBinary(String hexadecimalNumber, int lengthOfOutput) {
        String binaryNumber = "";
        hexadecimalNumber = hexadecimalNumber.toUpperCase();
        HashMap<Character, String> hm = new HashMap<Character, String>();
        hm.put('0', "0000");
        hm.put('1', "0001");
        hm.put('2', "0010");
        hm.put('3', "0011");
        hm.put('4', "0100");
        hm.put('5', "0101");
        hm.put('6', "0110");
        hm.put('7', "0111");
        hm.put('8', "1000");
        hm.put('9', "1001");
        hm.put('A', "1010");
        hm.put('B', "1011");
        hm.put('C', "1100");
        hm.put('D', "1101");
        hm.put('E', "1110");
        hm.put('F', "1111");
        for (int i = 0; i < hexadecimalNumber.length(); i++) {
            char c = hexadecimalNumber.charAt(i);
            if (hm.containsKey(c))
                binaryNumber += hm.get(c);
            else {
                binaryNumber = "You have entered an invalid Hexadecimal Number.";
                return binaryNumber;
            }
        }
        StringBuilder sb = new StringBuilder(binaryNumber);
        sb.delete(0, binaryNumber.length() - lengthOfOutput);
        binaryNumber = sb.toString();
        return binaryNumber;
    }
}
