package base.io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestBufferedOutputStream {
	public static void outputToFile(String str, String fromDeviceID, String SN,
			String basePath) {
		BufferedOutputStream bos = null;
		try {
			StringBuilder strb = new StringBuilder(basePath);
			strb.append(fromDeviceID);
			strb.append("_");
			strb.append(SN);
			strb.append(".txt");
			bos = new BufferedOutputStream(new FileOutputStream(
					strb.toString(), true));
			bos.write(str.getBytes("GBK"));
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found" + fnfe);
		} catch (IOException ioe) {
			System.out.println("Error while writing to file" + ioe);
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
			} catch (Exception e) {
				System.out.println("Error while closing streams" + e);
			}

		}

	}
	
	public static void main(String[] args) {
		outputToFile("111\r\n","tttt","1","E:\\test");
		outputToFile("222\r\n","tttt","1","E:\\test");
	}
}
