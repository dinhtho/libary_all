package common.android.fiot.androidcommon;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by caoxuanphong on    4/28/16.
 */
public class ByteUtils {
    private static final String TAG = "ByteUtils";

    /**
     * Create byte array from list of integer
     *
     * Ex: ByteUtils.createByteArray(1,2,3,4,5,100, 500);
     * Result: [0x1, 0x2, 0x3, 0x4, 0x5, 0x64, 0xf4]
     *
     * @param numbers
     * @return
     */
    public static byte[] createByteArray(int ...numbers) {
        byte [] array = new byte[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            array[i] = (byte) numbers[i];
        }

        return array;
    }

    /**
     * Convert a String into byte array
     * @param string
     * @return
     */
    public static byte[] stringToByteArray(String string) {
        return string.getBytes();
    }

    /**
     * Convert interger into byte array
     * @param number
     * @param order
     * @return
     */
    public static byte[] integerToByteArray(int number, ByteOrder order) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.order(order);
        return byteBuffer.putInt(number).array();
    }

    /**
     * Convert long into byte array
     * @param number
     * @param order
     * @return
     */
    public static byte[] longToByteArray(long number, ByteOrder order) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.order(order);
        return byteBuffer.putLong(number).array();
    }

    /**
     * Convert short into byte array
     * @param number
     * @param order
     * @return
     */
    public static byte[] shortToByteArray(short number, ByteOrder order) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        byteBuffer.order(order);
        return byteBuffer.putShort(number).array();
    }

    /**
     * Convert float into byte array
     * @param number
     * @param order
     * @return
     */
    public static byte[] floatToByteArray(float number, ByteOrder order) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.order(order);
        return byteBuffer.putFloat(number).array();
    }

    /**
     * Convert double into byte array
     * @param number
     * @param order
     * @return
     */
    public static byte[] doubleToByteArray(double number, ByteOrder order) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.order(order);
        return byteBuffer.putDouble(number).array();
    }

    /**
     * Convert byte array into float
     * @param b
     * @param order
     * @return
     */
    public static float toFloat(byte[] b, ByteOrder order) {
        return ByteBuffer.wrap(b).order(order).getFloat();
    }

    /**
     * Convert byte array into integer
     * @param b
     * @param order
     * @return
     */
    public static int toInteger(byte[] b, ByteOrder order) {
        return ByteBuffer.wrap(b).order(order).getInt();
    }

    /**
     * Convert  byte array into long
     * @param b
     * @param order
     * @return
     */
    public static long toLong(byte[] b, ByteOrder order) {
        return ByteBuffer.wrap(b).order(order).getLong();
    }

    public static double toDouble(byte[] b, ByteOrder order) {
        return ByteBuffer.wrap(b).order(order).getDouble();
    }

    public static short toShort(byte[] b, ByteOrder order) {
        return ByteBuffer.wrap(b).order(order).getShort();
    }

    public static String toString(byte[] b) {
        return new String(b, StandardCharsets.UTF_8);
    }

    public static String toString(byte[] b, Charset charset) {
        return new String(b, charset);
    }

    public static byte[] add2ByteArray(byte[] a, byte[] b) {
        if (a == null && b == null) return null;
        if (a == null && b != null) return b;
        if (a != null && b == null) return a;

        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);

        return c;
    }

    public static byte[] addByte(byte[] a, byte b) {
        byte[] c;
        if (a == null) {
            return new byte[] {b};
        } else {
            c = new byte[a.length + 1];
        }


        if (a != null) {
            System.arraycopy(a, 0, c, 0, a.length);
            System.arraycopy(new byte[]{b}, 0, c, a.length, 1);
        } else {
            c[0] = b;
        }

        return c;
    }

    public static byte[] append(byte[] src, byte[] bytes, int startPos) {
        int j = 0;
        for (int i = startPos; i < startPos + bytes.length; i++) {
            src[i] = bytes[j++];
        }

        return src;
    }

    public static byte[] subByteArray(byte[] src, int startPos, int num) {
        int endPos = 0;

        if (startPos + num > src.length) {
            endPos = src.length;
        } else {
            endPos = startPos + num;
        }

        return Arrays.copyOfRange(src, startPos, endPos );
    }

    public static String toHexString(byte[] a) {
        if (a == null) return null;

        String[] s = new String[a.length];
        for (int  i = 0; i < a.length; i++) {
            s[i] = "0x" + Integer.toHexString((a[i] & 0xff));
        }

        return Arrays.toString(s);
    }

    public static String toIntegerString(byte[] a) {
        if (a == null) return null;

        return Arrays.toString(a);
    }
    
    public static boolean compare2Array(byte[] b1, byte[] b2) {
        if (b1.length != b2.length) return false;
        
        for (int i = 0; i < b1.length; i++) {
            if (b1[i] != b2[i]) return false;
        }
        
        return true;
    }

    public static int getHi(byte b) {
        return (((b&0xff) & 0xf0) >> 4);
    }

    public static int getLo(byte b) {
        return (((b&0xff) & 0x0F));
    }

    public static int merge2Bytes(byte bHi, byte bLo) {
        return ((bHi&0xff) << 8) + (bLo&0xff);
    }

    /**
     * Check @child is contain in @src
     * @param src
     * @param child
     * @return
     */
    public static boolean isContain(byte[] src, byte[] child) {
        if (src == null && child == null) return true;
        if (src == null || child == null) return false;

        if (src == child) return true;

        if (child.length > src.length) {
          return false;
        } else if (child.length == src.length) {
            for (int i = 0; i < src.length; i++) {
                if (src[i] != child[i]) {
                    return false;
                }
            }
            return true;
        } else {
            for (int i = 0; i < src.length; i++) {
                if (i > child.length - 1) return false;

                if (src[i] == child[i]) {
                    byte[] sub = subByteArray(src, i, child.length);
                    if (isContain(sub, child)) return true;
                }
            }
        }

        return false;
    }

}
