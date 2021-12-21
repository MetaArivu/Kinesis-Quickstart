package com.metaarivu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BankDataServer {
	public static void main(String[] args) throws IOException {
		System.out.println("Starting BankDataServer");
		ServerSocket listener = new ServerSocket(9996);
		BufferedReader br = null;
		Socket socket = null;
		try {

			socket = listener.accept();
			System.out.println(socket);
			System.out.println("Got new connection: " + socket.toString());

			while (true) {
				br = new BufferedReader(new FileReader(
						"/Users/ketangote/workspace_learning/kinesis-quickstart/09-flink-analytics/09-bank-fraud-detection/src/main/resources/bank_data.txt"));

				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					String line;
					Thread.sleep(3000);

					while ((line = br.readLine()) != null) {
						out.println(line);
						System.out.println(line);
						Thread.sleep(500);
					}

				} finally {

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			listener.close();
			
			if (br != null)
				br.close();
			

			if (socket != null)
				socket.close();
		}
	}
}