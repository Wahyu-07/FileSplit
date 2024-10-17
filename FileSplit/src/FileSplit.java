import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplit {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jalur file teks: ");
        String filePath = input.nextLine();

        System.out.print("Masukkan jumlah baris per bagian: ");
        int chunkSize = 0;
        try {
            chunkSize = input.nextInt();
            if (chunkSize <= 0) {
                System.out.println("Ukuran chunk harus lebih dari 0.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Input ukuran chunk harus berupa bilangan bulat.");
            return;
        }

        input.nextLine();

        Queue<String> queue = new LinkedList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder chunk = new StringBuilder();
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                chunk.append(line).append("\n");
                lineCount++;

                if (lineCount == chunkSize) {
                    queue.add(chunk.toString());
                    chunk.setLength(0);
                    lineCount = 0;
                }
            }

            if (lineCount > 0) {
                queue.add(chunk.toString());
            }

            reader.close();

            int partNumber = 1;
            while (!queue.isEmpty()) {
                System.out.println("Bagian " + partNumber + ":");
                System.out.println(queue.poll());
                partNumber++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan dalam membaca file: " + e.getMessage());
        }

        input.close();
    }
}
