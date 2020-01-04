package ceshi;

/**
 * @Date 2019/12/18 18:32
 * @
 */

/**
 * Created by wly on 2018/4/17.
 */
public class HexConvert {

    public static String  convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }

    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for( int i=0; i<hex.length()-1; i+=2 ){

            String s = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(s, 16);
            sb.append((char)decimal);
            sb2.append(decimal);
        }

        return sb.toString();
    }

    //����ƥ���ַ�
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    //���ֽ�����ת��Ϊshort���ͣ���ͳ���ַ�������
    public static short bytes2Short2(byte[] b) {
        short i = (short) (((b[1] & 0xff) << 8) | b[0] & 0xff);
        return i;
    }
    //���ֽ�����ת��Ϊ16�����ַ���
    public static String BinaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex + " ";
        }
        return result;
    }

    public static void main(String[] args) {


        System.out.println("======ASCII��ת��Ϊ16����======");
        String str = "*00007VERSION\\n1$";
        System.out.println("�ַ���: " + str);
        String hex = HexConvert.convertStringToHex(str);
        System.out.println("====ת��Ϊ16����=====" + hex);

        System.out.println("======16����ת��ΪASCII======");
        System.out.println("Hex : " + hex);
        System.out.println("ASCII : " + HexConvert.convertHexToString(hex));

//        byte[] bytes = HexConvert.hexStringToBytes( hex );

//        System.out.println(HexConvert.BinaryToHexString( bytes ));
    }

}

