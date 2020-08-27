package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import functions.Countdown;

public class ServerClass {
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		OutputStream outputStream = null;
		DataOutputStream dataOutputStream = null;
		Scanner scanner = null;
		Countdown countdown = null;
		
		try {
			serverSocket = new ServerSocket(9000); //서버 소켓 생성(9000은 포트 번호)
			System.out.println("클라이언트 대기중 . . . ");

			socket = serverSocket.accept();
			System.out.println("클라이언트 연결 완료.");
			System.out.println("Socket : " + socket +"\n");
			
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			
			scanner = new Scanner(System.in);
			countdown = new Countdown();
			
			while(true) {
				System.out.println();
				System.out.println("<선택>");
				System.out.println("1. 가위 2. 바위 3. 보자기");
				System.out.printf("입력 : ");
				int sel = scanner.nextInt();
				System.out.println("상대 기다리는 중 . . .");
				int msg = dataInputStream.read();
				
				int result = (3 + sel - msg)%3;
				String rmsg;
				if (result == 0) {
					rmsg = "<비겼습니다.>";
				} else if(result == 1) {
					rmsg = "<서버가 승리했습니다.>";
				} else if(result == 2) {
					rmsg = "<클라이언트가 승리했습니다.>";
				} else {rmsg = "<에러 . . . >";}
				countdown.countdown();
				System.out.println(rmsg);
				dataOutputStream.writeUTF(rmsg);
				dataOutputStream.flush();
		
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) serverSocket.close();
				if (socket != null) socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}
	
}
