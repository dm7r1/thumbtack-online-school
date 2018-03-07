package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.figures.v3.Rectangle;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import java.io.*;
import java.util.Scanner;

public class FileService {

    public static void  writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    public static void  writeByteArrayToBinaryFile(File file, byte[] array) throws IOException  {
        try(FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(array);
        }
    }

    public static byte[]  readByteArrayFromBinaryFile(String fileName) throws IOException  {
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    public static byte[]  readByteArrayFromBinaryFile(File file) throws IOException {
        try (FileInputStream fin = new FileInputStream(file)) {
            byte[] array = new byte[fin.available()];
            fin.read(array);
            return array;
        }
    }

    public static byte[]  writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            bos.write(array);
            try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray())) {
                int length = bis.available() / 2;
                byte[] result = new byte[length];
                for (int i = 0; i < length; i++) {
                    result[i] = (byte) bis.read();
                    bis.skip(1);
                }
                return result;
            }
        }
    }

    public static void  writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    public static void  writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            bos.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] array = new byte[bis.available()];
            bis.read(array);
            return array;
        }
    }
    private static Rectangle readRectangleFromDataInput(DataInput din) throws IOException, ColorException {
        return new Rectangle(din.readInt(), din.readInt(), din.readInt(), din.readInt(), Color.RED);
    }

    public static Rectangle readRectangleFromBinaryFile(File file) throws IOException, ColorException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            return readRectangleFromDataInput(dis);
        }
    }

    private static void writeRectangleToDataOutput(DataOutput dout, Rectangle rect) throws IOException {
        dout.writeInt(rect.getTopLeft().getX());
        dout.writeInt(rect.getTopLeft().getY());
        dout.writeInt(rect.getBottomRight().getX());
        dout.writeInt(rect.getBottomRight().getY());
    }

    public static void  writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            writeRectangleToDataOutput(dos, rect);
        }
    }

    public static void  writeRectangleArrayToBinaryFile(File file, Rectangle[] rects ) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            for(Rectangle rect: rects) {
                writeRectangleToDataOutput(dos, rect);
            }
        }
    }

    public static Rectangle[]  readRectangleArrayFromBinaryFileReverse(File file) throws IOException, ColorException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            Rectangle[] rectangles = new Rectangle[(int)(raf.length() / 16)];
            int i = 0;
            for (long pos = raf.length() - 16; pos >= 0; pos -= 16, i++) {
                raf.seek(pos);
                rectangles[i] = readRectangleFromDataInput(raf);
            }
            return rectangles;
        }
    }

    public static void  writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.printf("%d %d %d %d",
                    rect.getTopLeft().getX(),
                    rect.getTopLeft().getY(),
                    rect.getBottomRight().getX(),
                    rect.getBottomRight().getY());
        }
    }

    public static Rectangle  readRectangleFromTextFileOneLine(File file) throws IOException, ColorException {
        try (Scanner sc = new Scanner(file)) {
            return new Rectangle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), Color.RED);
        }
    }

    public static void  writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(rect.getTopLeft().getX());
            pw.println(rect.getTopLeft().getY());
            pw.println(rect.getBottomRight().getX());
            pw.println(rect.getBottomRight().getY());
        }
    }

    public static Rectangle  readRectangleFromTextFileFourLines(File file) throws IOException, ColorException {
        return readRectangleFromTextFileOneLine(file);
    }

    public static void  writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        try (PrintWriter pw = new PrintWriter(file, "UTF-8")) {
            pw.printf("%s %s %s", trainee.getFirstName(), trainee.getLastName(), trainee.getRating());
        }
    }

    public static Trainee  readTraineeFromTextFileOneLine(File file) throws IOException, TrainingException {
        try (Scanner sc = new Scanner(file)) {
            return new Trainee(sc.next(), sc.next(), sc.nextInt());
        }
    }

    public static void  writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        try (PrintWriter pw = new PrintWriter(file, "UTF-8")) {
            pw.println(trainee.getFirstName());
            pw.println(trainee.getLastName());
            pw.println(trainee.getRating());
        }
    }

    public static Trainee  readTraineeFromTextFileThreeLines(File file) throws IOException, TrainingException {
        return readTraineeFromTextFileOneLine(file);
    }

    public static void  serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(trainee);
        }
    }

    public static Trainee  deserializeTraineeFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Trainee) ois.readObject();
        }
    }

    public static String  serializeTraineeToJsonString(Trainee trainee) {
        Gson gson = new Gson();
        return gson.toJson(trainee);
    }

    public static Trainee  deserializeTraineeFromJsonString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Trainee.class);
    }

    public static void  serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        Gson gson = new Gson();
        try (FileWriter fw = new FileWriter(file)) {
            gson.toJson(trainee, fw);
        }
    }

    public static Trainee  deserializeTraineeFromJsonFile(File file) throws IOException {
        Gson gson = new Gson();
        try (FileReader fr = new FileReader(file)) {
            return gson.fromJson(fr, Trainee.class);
        }
    }
}
