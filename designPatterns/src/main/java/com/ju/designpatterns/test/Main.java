package com.ju.designpatterns.test;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class Main {
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static void main(String[] strings) {
//        ArrayList<Student1> list = new ArrayList<Student1>(){};
//        Type type = list.getClass().getGenericSuperclass();
//        ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
//        for (Type typeArgument : parameterizedType.getActualTypeArguments()) {
//            System.out.println(typeArgument.getTypeName());
//        }
//            File file = new File("aaaabbbbb");
//        write();
//        read();
//        copyFile();
//        socket();
        nioRead();
        io6();
    }

    private static void write() {
        try {
            OutputStream outputStream = new FileOutputStream("./designPatterns/test.txt", false);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("a");
            bufferedWriter.write("b");
            bufferedWriter.write("c");
            bufferedWriter.write("d");
            bufferedWriter.write("e");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read() {
        try {
            InputStream inputStream = new FileInputStream("./designPatterns/test.txt");
//            InputStreamReader read = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(read);
//            System.out.println(bufferedReader.readLine());

            byte[] bytes = new byte[2];
            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                System.out.println(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile() {
        try {
            InputStream inputStream = new FileInputStream("./designPatterns/test.txt");
            OutputStream outputStream = new FileOutputStream("./designPatterns/newtest.txt", false);
            byte[] bytes = new byte[2];
            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                System.out.println(read);
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void socket() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("a");
            bufferedWriter.write("b");
            bufferedWriter.write("c");
            bufferedWriter.flush();
//            String data;
//            while ((data = bufferedReader.readLine()) != null) {
//                bufferedWriter.write(data+"end...");
//                bufferedWriter.write("\n");
//                bufferedWriter.flush();
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nioRead() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("./designPatterns/test.txt", "r");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            CharBuffer charBuffer =Charset.defaultCharset().decode(byteBuffer);
            System.out.println(charBuffer.toString());
            byteBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void okIoRead() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("./designPatterns/test.txt", "r");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            CharBuffer charBuffer =Charset.defaultCharset().decode(byteBuffer);
            System.out.println(charBuffer.toString());
            byteBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void io6(){
        try {
            OutputStream outputStream = new FileOutputStream("./designPatterns/okiotest.txt", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeChar('a');
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
