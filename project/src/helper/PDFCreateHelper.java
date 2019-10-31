package helper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import DTO.HDPtckDTO;
import DTO.PTPhongDTO;

public class PDFCreateHelper {
	public static void createHoaDonPDF(List<PTPhongDTO> listPhong, List<HDPtckDTO> listPtck,
			List<String> thongTinHoaDon) throws IOException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);
		float yCordinate = page.getCropBox().getUpperRightY() - 70;
		float startX = page.getCropBox().getLowerLeftX() + 70;
		float endX = page.getCropBox().getUpperRightX() - 30;
		float leading = 20;

		PDFont fontBold = PDType0Font.load(doc, new File("C:/Windows/Fonts/timesbd.ttf"));
		PDFont fontBoldItalic = PDType0Font.load(doc, new File("C:/Windows/Fonts/timesbi.ttf"));
		PDFont fontItalic = PDType0Font.load(doc, new File("C:/Windows/Fonts/timesi.ttf"));
		PDFont font = PDType0Font.load(doc, new File("C:/Windows/Fonts/times.ttf"));
		PDPageContentStream contents = new PDPageContentStream(doc, page);
		contents.beginText();
		contents.newLineAtOffset(
				(page.getCropBox().getUpperRightX() / 2) - ((fontBold.getStringWidth("ABC Hotel") / 1000 * 36) / 2),
				yCordinate);
		contents.setFont(fontBold, 36);
		contents.showText("ABC Hotel");
		contents.endText();
		yCordinate -= 36;

		contents.beginText();
		contents.newLineAtOffset((page.getCropBox().getUpperRightX() / 2)
				- ((fontBold.getStringWidth("HÓA ĐƠN THANH TOÁN") / 1000 * 20) / 2), yCordinate);
		contents.setFont(fontBold, 20);
		contents.showText("HÓA ĐƠN THANH TOÁN");
		contents.endText();
		yCordinate -= 20;

		// THÔNG TIN HÓA ĐƠN
		contents.beginText();
		contents.setFont(fontBoldItalic, 20);
		contents.newLineAtOffset(startX, yCordinate - leading);
		yCordinate -= leading;
		contents.showText("A. THÔNG TIN HÓA ĐƠN");
		contents.endText();
		yCordinate -= 6;
		contents.moveTo(startX, yCordinate);
		contents.lineTo(endX, yCordinate);
		contents.stroke();

		// Cột trái:

		contents.beginText();
		contents.setFont(fontBold, 13);
		contents.newLineAtOffset(startX, yCordinate - leading);
		contents.showText("Tên khách hàng: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Số điện thoại: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("CMND: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Email: ");
		contents.endText();

		// Cột thông tin phải:
		contents.beginText();
		contents.setFont(fontBold, 13);
		contents.newLineAtOffset(endX - 260, yCordinate - leading);
		contents.showText("Hóa đơn số: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Ngày thanh toán: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Thu ngân: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Ghi chú: ");
		contents.endText();

		// Cột value trái

		contents.beginText();
		contents.setFont(font, 13);
		contents.newLineAtOffset(startX + 105, yCordinate - leading);
		for (int i = 0; i < 4; i++) {
			contents.showText(thongTinHoaDon.get(i));
			contents.newLineAtOffset(0, -leading);
		}
		contents.endText();
		// Cột value phải
		contents.beginText();
		contents.setFont(font, 13);
		contents.newLineAtOffset(endX - 140, yCordinate - leading);
		for (int i = 4; i < 8; i++) {
			contents.showText(thongTinHoaDon.get(i));
			contents.newLineAtOffset(0, -leading);
		}
		contents.endText();

		// Cập nhật lại yCordinate -= (leading * số row + fontSize * số row)
		yCordinate -= (leading * 5);

		// B.CHI TIẾT HÓA ĐƠN
		contents.beginText();
		contents.setFont(fontBoldItalic, 20);
		contents.newLineAtOffset(startX, yCordinate - leading);
		yCordinate -= leading;
		contents.showText("B. CHI TIẾT HÓA ĐƠN");
		contents.endText();
		yCordinate -= 6;
		contents.moveTo(startX, yCordinate);
		contents.lineTo(endX, yCordinate);
		contents.stroke();
		// Chi tiết
		contents.beginText();
		contents.setFont(fontBold, 13);
		contents.newLineAtOffset(startX, yCordinate - leading);
		contents.showText("PHÒNG");
		contents.newLineAtOffset(endX - 260 - 69, 0);
		contents.showText("THÀNH TIỀN");
		contents.endText();
		yCordinate -= leading;
		// Bắt đầu add chi tiết hóa đơn, mỗi lần thêm dòng yCordinate -= leading
		for (PTPhongDTO phong : listPhong) {
			contents.beginText();
			contents.setFont(font, 13);
			contents.newLineAtOffset(startX, yCordinate - leading);
			yCordinate -= leading;
			contents.showText("Phòng " + phong.getMaPhong());
			contents.newLineAtOffset(endX - 330, 0);
			contents.showText(phong.getThanhTien() + " VND");
			contents.endText();
		}

		if (!listPtck.isEmpty()) {
			yCordinate -= leading;
			// C.PHỤ THU CHIẾT KHẤU
			contents.beginText();
			contents.setFont(fontBoldItalic, 20);
			contents.newLineAtOffset(startX, yCordinate - leading);
			yCordinate -= leading;
			contents.showText("C. PHỤ THU CHIẾT KHẤU");
			contents.endText();
			yCordinate -= 6;
			contents.moveTo(startX, yCordinate);
			contents.lineTo(endX, yCordinate);
			contents.stroke();

			contents.beginText();
			contents.setFont(fontBold, 13);
			contents.newLineAtOffset(startX, yCordinate - leading);
			contents.showText("NỘI DUNG");
			contents.newLineAtOffset(endX - 260 - 69, 0);
			contents.showText("GIÁ TRỊ");
			contents.endText();
			yCordinate -= leading;

			for (HDPtckDTO ptck : listPtck) {
				contents.beginText();
				contents.setFont(font, 13);
				contents.newLineAtOffset(startX, yCordinate - leading);
				yCordinate -= leading;
				contents.showText(ptck.getTriGiaValue() > 0 ? ("Phụ thu: " + ptck.getNoiDung())
						: ("Chiết khấu: " + ptck.getNoiDung()));
				contents.newLineAtOffset(endX - 330, 0);
				contents.showText(ptck.getTriGia() + " VND");
				contents.endText();
			}
		}

		// Kết thúc add chi tiết hóa đơn
		yCordinate -= 6;

		contents.moveTo(endX - 300, yCordinate - leading);
		contents.lineTo(endX - 150, yCordinate - leading);
		yCordinate -= leading;
		contents.stroke();

		contents.beginText();
		contents.setFont(fontBold, 13);
		contents.newLineAtOffset(startX + 150, yCordinate - leading);
		contents.showText("Tổng cộng:");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Tiền nhận:");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Tiền cọc:");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Tiền thừa:");
		contents.endText();

		contents.beginText();
		contents.setFont(font, 13);
		contents.newLineAtOffset(endX - 260, yCordinate - leading);
		contents.showText(MoneyFormatHelper.format((MoneyFormatHelper.fromString(thongTinHoaDon.get(9))
				+ MoneyFormatHelper.fromString(thongTinHoaDon.get(11)))) + " VND");
		contents.newLineAtOffset(0, -leading);
		contents.showText(thongTinHoaDon.get(8) + " VND");
		contents.newLineAtOffset(0, -leading);
		contents.showText(thongTinHoaDon.get(11) + " VND");
		contents.newLineAtOffset(0, -leading);
		contents.showText(thongTinHoaDon.get(10) + " VND");
		contents.endText();

		yCordinate -= leading * 6;
		contents.beginText();
		contents.setFont(fontItalic, 11);
		contents.newLineAtOffset(startX, yCordinate);
		contents.showText("(Đã bao gồm thuế VAT)");
		contents.newLineAtOffset(0 + 26, -leading);
		contents.setFont(fontBold, 13);
		contents.showText("Thu ngân");
		contents.newLineAtOffset(endX - 260, 0);
		contents.showText("Khách hàng");
		contents.endText();

		contents.close();
		doc.save(String.format("./hd/HD_%s.pdf", thongTinHoaDon.get(4)));
		doc.close();
		Desktop.getDesktop().open(new File(String.format("./hd/HD_%s.pdf", thongTinHoaDon.get(4))));
	}

	public static void createPhieuThuePDF(List<String> thongTinPhieu, List<ArrayList<String>> content)
			throws IOException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);

		float yCordinate = page.getCropBox().getUpperRightY() - 70;
		float startX = page.getCropBox().getLowerLeftX() + 70;
		float endX = page.getCropBox().getUpperRightX() - 30;
		float leading = 20;

		PDFont fontBold = PDType0Font.load(doc, new File("./font/timesbd.ttf"));
		PDFont fontBoldItalic = PDType0Font.load(doc, new File("./font/timesbi.ttf"));
		PDFont fontItalic = PDType0Font.load(doc, new File("./font/timesi.ttf"));
		PDFont font = PDType0Font.load(doc, new File("./font/times.ttf"));
		PDPageContentStream contents = new PDPageContentStream(doc, page);

		contents.beginText();
		contents.newLineAtOffset(
				(page.getCropBox().getUpperRightX() / 2) - ((fontBold.getStringWidth("ABC Hotel") / 1000 * 36) / 2),
				yCordinate);
		contents.setFont(fontBold, 36);
		contents.showText("ABC Hotel");
		contents.endText();
		yCordinate -= 36;

		contents.beginText();
		contents.newLineAtOffset((page.getCropBox().getUpperRightX() / 2)
				- ((fontBold.getStringWidth("PHIẾU THUÊ PHÒNG") / 1000 * 20) / 2), yCordinate);
		contents.setFont(fontBold, 20);
		contents.showText("PHIẾU THUÊ PHÒNG");
		contents.endText();
		yCordinate -= 20;

		contents.beginText();
		contents.newLineAtOffset(startX, yCordinate - leading);
		yCordinate -= leading;
		contents.setFont(fontBoldItalic, 16);
		contents.showText("A. THÔNG TIN PHIẾU:");
		contents.endText();
		yCordinate -= 4;

		contents.moveTo(startX, yCordinate);
		contents.lineTo(endX, yCordinate);
		contents.stroke();

		// A. Thông tin phiếu:
		// Cột trái
		contents.beginText();
		contents.setFont(fontBold, 13);
		contents.newLineAtOffset(startX, yCordinate - leading);
		contents.showText("Mã phiếu: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Tên khách: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Số CMND: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Email: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Số lượng: ");
		contents.endText();
		// Cột phải
		contents.beginText();
		contents.setFont(fontBold, 13);
		contents.newLineAtOffset(endX - 250, yCordinate - leading);
		contents.showText("Nhân viên: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Ngày lập: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Điện thoại: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Tổng cọc: ");
		contents.newLineAtOffset(0, -leading);
		contents.showText("Ghi chú: ");
		contents.endText();
		// Cột value trái

		contents.beginText();
		contents.setFont(font, 13);
		contents.newLineAtOffset(startX + 70, yCordinate - leading);
		for (int i = 0; i < 5; i++) {
			contents.showText(thongTinPhieu.get(i));
			contents.newLineAtOffset(0, -leading);
		}
		contents.endText();
		// Cột value phải
		contents.beginText();
		contents.setFont(font, 13);
		contents.newLineAtOffset(endX - 170, yCordinate - leading);
		for (int i = 5; i < 10; i++) {
			contents.showText(thongTinPhieu.get(i));
			contents.newLineAtOffset(0, -leading);
		}
		contents.endText();
		// Cập nhật lại yCordinate -= (leading * số row + fontSize * số row)
		yCordinate -= (leading * 6);
		// B. Danh sách phòng:
		contents.beginText();
		contents.newLineAtOffset(startX, yCordinate - leading);
		yCordinate -= leading;
		contents.setFont(fontBoldItalic, 16);
		contents.showText("B. DANH SÁCH PHÒNG:");
		contents.endText();
		yCordinate -= 4;

		contents.moveTo(startX, yCordinate);
		contents.lineTo(endX, yCordinate);
		contents.stroke();

		// Tạo bảng:

		TablePDFCreateHelper.drawTable(doc, page, contents, yCordinate - leading, startX, content);
		yCordinate -= leading * (content.size() + 1) + 13;
		contents.beginText();
		contents.setFont(fontItalic, 13);
		contents.newLineAtOffset(
				endX - 13 - (fontItalic.getStringWidth("Tp.HCM, ngày ...... tháng ...... năm .........") / 1000 * 13),
				yCordinate - leading);
		contents.showText("Tp.HCM, ngày ...... tháng ...... năm .........");
		yCordinate -= leading + 13;
		contents.endText();

		contents.beginText();
		contents.setFont(fontBoldItalic, 13);
		contents.newLineAtOffset(startX + 40, yCordinate - leading);
		contents.showText("Người lập phiếu");

		contents.newLineAtOffset(endX - 260, 0);
		contents.showText("Khách thuê");
		yCordinate -= leading + 13;
		contents.endText();
		contents.close();

		doc.save(String.format("./pt/PT_%s.pdf", thongTinPhieu.get(0)));
		doc.close();
		Desktop.getDesktop().open(new File(String.format("./pt/PT_%s.pdf", thongTinPhieu.get(0))));
	}

	public static void createDoanhThuNamPDF(Integer nam, List<ArrayList<String>> content, Integer tongDoanhThu)
			throws IOException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);

		float yCordinate = page.getCropBox().getUpperRightY() - 70;
		float startX = page.getCropBox().getLowerLeftX() + 50;
		float endX = page.getCropBox().getUpperRightX() - 30;
		float leading = 20;

		PDFont fontBold = PDType0Font.load(doc, new File("./font/timesbd.ttf"));
		PDFont fontItalic = PDType0Font.load(doc, new File("./font/timesi.ttf"));
		PDFont fontBoldItalic = PDType0Font.load(doc, new File("./font/timesbi.ttf"));
		PDPageContentStream contents = new PDPageContentStream(doc, page);

		contents.beginText();
		contents.newLineAtOffset(
				(page.getCropBox().getUpperRightX() / 2) - ((fontBold.getStringWidth("ABC Hotel") / 1000 * 36) / 2),
				yCordinate);
		contents.setFont(fontBold, 36);
		contents.showText("ABC Hotel");
		contents.endText();
		yCordinate -= 36;

		contents.beginText();
		contents.newLineAtOffset(
				(page.getCropBox().getUpperRightX() / 2)
						- ((fontBold.getStringWidth("BÁO CÁO DOANH THU NĂM " + nam.toString()) / 1000 * 20) / 2),
				yCordinate);
		contents.setFont(fontBold, 20);
		contents.showText("BÁO CÁO DOANH THU NĂM " + nam.toString());
		contents.endText();
		yCordinate -= 20;

		TablePDFCreateHelper.drawTable(doc, page, contents, yCordinate - leading, startX, content);

		yCordinate -= leading * (content.size() + 1) + 13;
		contents.beginText();
		contents.setFont(fontBoldItalic, 13);
		contents.newLineAtOffset(startX, yCordinate - leading);
		contents.showText("Tổng doanh thu: " + MoneyFormatHelper.format(tongDoanhThu, "VND"));
		contents.endText();

		yCordinate -= leading + 13;
		contents.beginText();
		contents.setFont(fontItalic, 13);
		contents.newLineAtOffset(
				endX - 13 - (fontItalic.getStringWidth("Tp.HCM, ngày ...... tháng ...... năm .........") / 1000 * 13),
				yCordinate - leading);
		contents.showText("Tp.HCM, ngày ...... tháng ...... năm .........");
		contents.endText();

		yCordinate -= leading + 13;
		contents.beginText();
		contents.newLineAtOffset(endX - 165, yCordinate - leading);
		contents.showText("Người lập báo cáo");
		yCordinate -= leading + 10;
		contents.endText();
		contents.close();

		String fileName = String.format("./bc/BCDT_%d.pdf", nam);
		doc.save(fileName);
		doc.close();
		Desktop.getDesktop().open(new File(fileName));
	}

	public static void createLoaiPhongThangPDF(int thang, int nam, List<ArrayList<String>> content, Integer tongDoanhThu) throws IOException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);

		float yCordinate = page.getCropBox().getUpperRightY() - 70;
		float startX = page.getCropBox().getLowerLeftX() + 50;
		float endX = page.getCropBox().getUpperRightX() - 30;
		float leading = 20;

		PDFont fontBold = PDType0Font.load(doc, new File("./font/timesbd.ttf"));
		PDFont fontItalic = PDType0Font.load(doc, new File("./font/timesi.ttf"));
		PDFont fontBoldItalic = PDType0Font.load(doc, new File("./font/timesbi.ttf"));
		PDPageContentStream contents = new PDPageContentStream(doc, page);

		contents.beginText();
		contents.newLineAtOffset(
				(page.getCropBox().getUpperRightX() / 2) - ((fontBold.getStringWidth("ABC Hotel") / 1000 * 36) / 2),
				yCordinate);
		contents.setFont(fontBold, 36);
		contents.showText("ABC Hotel");
		contents.endText();
		yCordinate -= 36;

		contents.beginText();
		contents.newLineAtOffset((page.getCropBox().getUpperRightX() / 2)
				- ((fontBold.getStringWidth(String.format("BÁO CÁO LOẠI PHÒNG THÁNG %d/%d", thang, nam)) / 1000 * 20)
						/ 2), yCordinate);
		contents.setFont(fontBold, 20);
		contents.showText(String.format("BÁO CÁO LOẠI PHÒNG THÁNG %d/%d ", thang, nam));
		contents.endText();
		yCordinate -= 20;

		TablePDFCreateHelper.drawTable(doc, page, contents, yCordinate - leading, startX, content);

		yCordinate -= leading * (content.size() + 1) + 13;
		contents.beginText();
		contents.setFont(fontBoldItalic, 13);
		contents.newLineAtOffset(startX, yCordinate - leading);
		contents.showText("Tổng doanh thu: " + MoneyFormatHelper.format(tongDoanhThu, "VND"));
		contents.endText();

		yCordinate -= leading + 13;
		contents.beginText();
		contents.setFont(fontItalic, 13);
		contents.newLineAtOffset(
				endX - 13 - (fontItalic.getStringWidth("Tp.HCM, ngày ...... tháng ...... năm .........") / 1000 * 13),
				yCordinate - leading);
		contents.showText("Tp.HCM, ngày ...... tháng ...... năm .........");
		contents.endText();

		yCordinate -= leading + 13;
		contents.beginText();
		contents.newLineAtOffset(endX - 165, yCordinate - leading);
		contents.showText("Người lập báo cáo");
		yCordinate -= leading + 10;
		contents.endText();
		contents.close();

		String fileName = String.format("./bc/BCLP_%d_%d.pdf", thang, nam);
		doc.save(fileName);
		doc.close();
		Desktop.getDesktop().open(new File(fileName));
	}

	public static void createSoKhachNamPDF(Integer nam, List<ArrayList<String>> content, Integer tongSoKhach) throws IOException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);

		float yCordinate = page.getCropBox().getUpperRightY() - 70;
		float startX = page.getCropBox().getLowerLeftX() + 50;
		float endX = page.getCropBox().getUpperRightX() - 30;
		float leading = 20;

		PDFont fontBold = PDType0Font.load(doc, new File("./font/timesbd.ttf"));
		PDFont fontItalic = PDType0Font.load(doc, new File("./font/timesi.ttf"));
		PDFont fontBoldItalic = PDType0Font.load(doc, new File("./font/timesbi.ttf"));
		PDPageContentStream contents = new PDPageContentStream(doc, page);

		contents.beginText();
		contents.newLineAtOffset(
				(page.getCropBox().getUpperRightX() / 2) - ((fontBold.getStringWidth("ABC Hotel") / 1000 * 36) / 2),
				yCordinate);
		contents.setFont(fontBold, 36);
		contents.showText("ABC Hotel");
		contents.endText();
		yCordinate -= 36;

		contents.beginText();
		contents.newLineAtOffset(
				(page.getCropBox().getUpperRightX() / 2)
						- ((fontBold.getStringWidth("BÁO CÁO LƯỢNG KHÁCH " + nam.toString()) / 1000 * 20) / 2),
				yCordinate);
		contents.setFont(fontBold, 20);
		contents.showText("BÁO CÁO LƯỢNG KHÁCH NĂM " + nam.toString());
		contents.endText();
		yCordinate -= 20;

		TablePDFCreateHelper.drawTable(doc, page, contents, yCordinate - leading, startX, content);

		yCordinate -= leading * (content.size() + 1) + 13;
		contents.beginText();
		contents.setFont(fontBoldItalic, 13);
		contents.newLineAtOffset(startX, yCordinate - leading);
		contents.showText("Tổng số khách: " + tongSoKhach.toString());
		contents.endText();

		yCordinate -= leading + 13;
		contents.beginText();
		contents.setFont(fontItalic, 13);
		contents.newLineAtOffset(
				endX - 13 - (fontItalic.getStringWidth("Tp.HCM, ngày ...... tháng ...... năm .........") / 1000 * 13),
				yCordinate - leading);
		contents.showText("Tp.HCM, ngày ...... tháng ...... năm .........");
		contents.endText();

		yCordinate -= leading + 13;
		contents.beginText();
		contents.newLineAtOffset(endX - 165, yCordinate - leading);
		contents.showText("Người lập báo cáo");
		yCordinate -= leading + 10;
		contents.endText();
		contents.close();

		String fileName = String.format("./bc/BCSK_%d.pdf", nam);
		doc.save(fileName);
		doc.close();
		Desktop.getDesktop().open(new File(fileName));
	}

	public static void createLoaiDichVuPDF(int thang, int nam, List<ArrayList<String>> content, Integer tongDoanhThu) throws IOException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);

		float yCordinate = page.getCropBox().getUpperRightY() - 70;
		float startX = page.getCropBox().getLowerLeftX() + 50;
		float endX = page.getCropBox().getUpperRightX() - 30;
		float leading = 20;

		PDFont fontBold = PDType0Font.load(doc, new File("./font/timesbd.ttf"));
		PDFont fontItalic = PDType0Font.load(doc, new File("./font/timesi.ttf"));
		PDFont fontBoldItalic = PDType0Font.load(doc, new File("./font/timesbi.ttf"));
		PDPageContentStream contents = new PDPageContentStream(doc, page);

		contents.beginText();
		contents.newLineAtOffset(
				(page.getCropBox().getUpperRightX() / 2) - ((fontBold.getStringWidth("ABC Hotel") / 1000 * 36) / 2),
				yCordinate);
		contents.setFont(fontBold, 36);
		contents.showText("ABC Hotel");
		contents.endText();
		yCordinate -= 36;

		contents.beginText();
		contents.newLineAtOffset((page.getCropBox().getUpperRightX() / 2)
				- ((fontBold.getStringWidth(String.format("BÁO CÁO LOẠI DỊCH VỤ THÁNG %d/%d", thang, nam)) / 1000 * 20)
						/ 2), yCordinate);
		contents.setFont(fontBold, 20);
		contents.showText(String.format("BÁO CÁO LOẠI DỊCH VỤ THÁNG %d/%d ", thang, nam));
		contents.endText();
		yCordinate -= 20;

		TablePDFCreateHelper.drawTable(doc, page, contents, yCordinate - leading, startX, content);

		yCordinate -= leading * (content.size() + 1) + 13;
		contents.beginText();
		contents.setFont(fontBoldItalic, 13);
		contents.newLineAtOffset(startX, yCordinate - leading);
		contents.showText("Tổng doanh thu: " + MoneyFormatHelper.format(tongDoanhThu, "VND"));
		contents.endText();

		yCordinate -= leading + 13;
		contents.beginText();
		contents.setFont(fontItalic, 13);
		contents.newLineAtOffset(
				endX - 13 - (fontItalic.getStringWidth("Tp.HCM, ngày ...... tháng ...... năm .........") / 1000 * 13),
				yCordinate - leading);
		contents.showText("Tp.HCM, ngày ...... tháng ...... năm .........");
		contents.endText();

		yCordinate -= leading + 13;
		contents.beginText();
		contents.newLineAtOffset(endX - 165, yCordinate - leading);
		contents.showText("Người lập báo cáo");
		yCordinate -= leading + 10;
		contents.endText();
		contents.close();

		String fileName = String.format("./bc/BCLDV_%d_%d.pdf", thang, nam);
		doc.save(fileName);
		doc.close();
		Desktop.getDesktop().open(new File(fileName));
	}
}
