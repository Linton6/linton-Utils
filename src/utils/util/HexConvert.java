package utils.util;

/**
 * @Date 2019/12/18 21:46
 * @
 */

public class HexConvert {

    /**
     * ʮ������ת��Ϊ�ֽ�����
     * @param hexString
     * @return
     */
    public  byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        // toUpperCase���ַ����е������ַ�ת��Ϊ��д
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        // toCharArray�����ַ���ת��Ϊһ���µ��ַ����顣
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    //����ƥ���ַ�
    private  byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}

