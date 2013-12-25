<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page autoFlush="false"
         import="java.awt.*,java.awt.image.*,com.sun.image.codec.jpeg.*,java.util.*"%>
<%@ page import="org.apache.commons.lang.RandomStringUtils"%>
<%@page import="java.io.OutputStream"%>
<%@page import="javax.imageio.ImageIO"%>
<%
    RandomStringUtils rs = new RandomStringUtils();
    String random = rs.randomAlphanumeric(4);
    random = random.replaceAll("1", "a").replaceAll("l", "a")
            .replaceAll("L", "a").replaceAll("l", "a").replaceAll("o",
                    "a").replaceAll("0", "a").toLowerCase();
%>

<%
    out.clear();
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    Font font = new Font("\u96B6\u4E66", Font.PLAIN, 17);
    response.setContentType("image/jpeg");

    int width = 50, height = 20;
    BufferedImage image = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();

    g.setColor(Color.white);
    g.fillRect(0, 0, width, height);
    // String random="random";
    Color tt = new Color(255, 0, 0);
    g.setColor(tt);
    g.setFont(font);

    g.drawString(random, 5, 15);
    g.dispose();
    OutputStream os = response.getOutputStream();
    // 输出图象到页面
    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os); //换个输出图像的方法，解决javax.imageio.IIOException: Can't create output stream问题
    encoder.encode(image);

    os.flush();
    os.close();
    os = null;
    response.flushBuffer();
    out.clear();
    out = pageContext.pushBody();
    session.setAttribute("rand", random);
    System.out.println(session.getAttribute("rand"));
%>