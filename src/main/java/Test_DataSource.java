

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.Blob;


@WebServlet("/Test_DataSource")
public class Test_DataSource extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/plain; charset=Big5");
		PrintWriter out = res.getWriter();

		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jamigo");
			if (ds != null) {
				Connection conn = ds.getConnection();

				if (conn != null) {
					out.println("Got Connection: " + conn.toString());
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from shop_carousel");

					while (rs.next()) {
						 int shopCarouselNo = rs.getInt("shopCarouselNo");
		                    String shopCarouselTitle = rs.getString("shopCarouselTitle");
		                    String shopCarouselText = rs.getString("shopCarouselText");
		                    byte[] shopCarouselPicBytes = rs.getBytes("shopCarouselPic");
		                    LocalDateTime shopCarouselStartTime = rs.getTimestamp("shopCarouselStartTime").toLocalDateTime();
		                    LocalDateTime shopCarouselEndTime = rs.getTimestamp("shopCarouselEndTime").toLocalDateTime();


		                    out.println("shopCarouselNo = " + shopCarouselNo);
		                    out.println("shopCarouselTitle = " + shopCarouselTitle);
		                    out.println("shopCarouselText = " + shopCarouselText);
		                    String shopCarouselPicBase64 = Base64.getEncoder().encodeToString(shopCarouselPicBytes);
		                    out.println("shopCarouselPic = <img src='data:image/jpeg;base64," + shopCarouselPicBase64 + "'/>");
		                    out.println("shopCarouselStartTime = " + shopCarouselStartTime);
		                    out.println("shopCarouselEndTime = " + shopCarouselEndTime);
	                }
					conn.close();
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
