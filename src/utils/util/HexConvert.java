package utils.util;

/**
 * @Date 2019/12/18 21:46
 * @
 */

public class HexConvert {

    /**
     * 十六进制转化为字节数组
     * @param hexString
     * @return
     */
    public  byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        // toUpperCase将字符串中的所有字符转换为大写
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        // toCharArray将此字符串转换为一个新的字符数组。
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    //返回匹配字符
    private  byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}

