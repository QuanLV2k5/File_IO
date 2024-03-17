package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class File_io {
	public static void createFile(String fileName) {
		try {
			File file = new File(fileName);
			if (file.createNewFile()) {
				System.out.println("File đã được tạo: " + file.getAbsolutePath());
			} else {
				System.out.println("File đã tồn tại.");
			}
		} catch (IOException e) {
			System.out.println("Lỗi khi tạo file: " + e.getMessage());
		}
	}

	// Lưu file
	public static void saveFile(String filePath, String content) {
		try {
			FileWriter fw = new FileWriter(filePath);
			fw.write(content);
			fw.close();
			System.out.println("Dữ liệu đã được lưu vào file.");
		} catch (IOException e) {
			System.out.println("Lỗi khi lưu file: " + e.getMessage());
		}
	}

	// Ghi dữ liệu vào file
	public static void writeToFile(String filePath, String content) {
		try {
			PrintWriter pw = new PrintWriter(filePath, "UTF-8");
			pw.print(content);
			pw.flush();
			pw.close();
			System.out.println("Dữ liệu đã được ghi vào file.");
		} catch (IOException e) {
			System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
		}
	}

	// Đọc file
	public static void readFile(String filePath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Lỗi khi đọc file: " + e.getMessage());
		}
	}

	// Lấy đường dẫn file
	public static String getFilePath(String fileName) {
		File file = new File(fileName);
		return file.getAbsolutePath();
	}

	// Xóa file
	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.delete()) {
			System.out.println("File đã được xóa thành công.");
		} else {
			System.out.println("Không thể xóa file.");
		}
	}

	// Tạo thư mục
	public static void createDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			if (directory.mkdirs()) {
				System.out.println("Thư mục đã được tạo: " + directory.getAbsolutePath());
			} else {
				System.out.println("Không thể tạo thư mục.");
			}
		} else {
			System.out.println("Thư mục đã tồn tại.");
		}
	}

	public static void main(String[] args) {
		String fileName = "example.txt";
		String content = "Đây là nội dung mẫu.";
		String directoryPath = "example_directory";

		createFile(fileName);

		saveFile(fileName, content);

		writeToFile(fileName, "\nDòng mới được thêm vào.");

		System.out.println("Nội dung của file:");
		readFile(fileName);

		System.out.println("Đường dẫn của file:");
		System.out.println(getFilePath(fileName));

		deleteFile(fileName);

		createDirectory(directoryPath);
	}
}
