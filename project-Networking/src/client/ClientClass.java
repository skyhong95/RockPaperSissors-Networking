package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass {

	public static void main(String[] args) {
		
		Socket socket = null;
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		OutputStream outputStream = null;
		DataOutputStream dataOutputStream = null;
		Scanner scanner = null;
		
		try {
			socket = new Socket("localhost",9000); // local host - 127.0.0.1  port# - 9000
			System.out.println("서버와 연결 완료.");
			System.out.println("Socket : " + socket + "\n");
			
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			
			scanner = new Scanner(System.in);
			
			while(true) {
				System.out.println();
				System.out.println("<선택>");
				System.out.println("1. 가위 2. 바위 3. 보자기");
				System.out.printf("입력 : ");
				int sel = scanner.nextInt();
				dataOutputStream.write(sel);
				dataOutputStream.flush();
				System.out.println("상대 기다리는 중 . . .");
				String msg = dataInputStream.readUTF();
				System.out.println(msg);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null) socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
