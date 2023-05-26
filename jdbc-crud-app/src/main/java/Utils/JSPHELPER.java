package Utils;

public class JSPHELPER {
    public static final String JSP_FORMAT = "/%s.jsp";

    public static String getPath(String jspName) {
        return String.format(JSP_FORMAT, jspName);
    }


}
