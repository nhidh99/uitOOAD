package helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class TablePDFCreateHelper {
	public static void drawTable(PDDocument doc, PDPage page, PDPageContentStream contentStream, float y, float margin,
			List<ArrayList<String>> content) throws IOException {
		final int rows = content.size();
		final int cols = content.get(0).size();
		final float rowHeight = 20.0f;
		final float tableWidth = page.getCropBox().getWidth() - margin - 30;
		final float tableHeight = rowHeight * (float) rows;
		final float colWidth = tableWidth / (float) cols;

		// draw the rows
		float nexty = y;
		for (int i = 0; i <= rows; i++) {
			contentStream.moveTo(margin, nexty);
			contentStream.lineTo(margin + tableWidth, nexty);
			contentStream.stroke();
			nexty -= rowHeight;
		}

		// draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.moveTo(nextx, y);
			contentStream.lineTo(nextx, y - tableHeight);
			contentStream.stroke();
			nextx += colWidth;
		}

		// now add the text
		PDFont font = PDType0Font.load(doc, new File("C:/Windows/Fonts/times.ttf"));
		contentStream.setFont(font, 13);

		float textx = margin + colWidth / 2;
		float texty = y - 15.0f;
		for (final ArrayList<String> aContent : content) {
			for (String text : aContent) {
				contentStream.beginText();
				contentStream.setCharacterSpacing(0.25f);
				//contentStream.setRenderingMode(RenderingMode.FILL_STROKE);
				contentStream.newLineAtOffset(textx - (font.getStringWidth(text) / 1000 * 13) / 2, texty);
				contentStream.showText(text);
				contentStream.endText();
				textx += colWidth;
			}
			texty -= rowHeight;
			textx = margin + colWidth / 2;
		}
	}
}
