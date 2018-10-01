package pkgPPT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pkgCommon.Common;
import pkgExcel.Excel;

public class CreatePresentation {

	public static void main(String args[]) throws IOException {

		FindWorkbooks();

	}

	public static void FindWorkbooks() throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("Questions.xlsx");
		Workbook workbook = new XSSFWorkbook(is);

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			CreateSlideShow(workbook, sheet);
			// System.out.println(sheet.getSheetName());

			// Process your sheet here.
		}
	}

	public static void CreateSlideShow(Workbook wb, Sheet ws) throws IOException {

		LinkedList<Question> Questions = Excel.ReadQuestions(wb, ws);

		// creating a new empty slide show
		XMLSlideShow ppt = new XMLSlideShow();

		// showSlideLayouts(ppt);

		makeTitleSlide(ppt, "Clicker Quiz");

		for (Question q : Questions) {
			makeContentSlide(ppt, q);
		}

		// creating an FileOutputStream object
		String strFileName = ws.getSheetName() + ".pptx";
		//File file = new File("example1.pptx");
		File file = new File(strFileName);
		FileOutputStream out = new FileOutputStream(file);

		// saving the changes to a file
		ppt.write(out);
		System.out.println("Presentation created successfully");
		out.close();
	}

	public static void showSlideLayouts(XMLSlideShow ppt) {
		System.out.println("Available slide layouts:");

		// getting the list of all slide masters
		for (XSLFSlideMaster master : ppt.getSlideMasters()) {

			// getting the list of the layouts in each slide master
			for (XSLFSlideLayout layout : master.getSlideLayouts()) {

				// getting the list of available slides
				System.out.println(layout.getType());
			}

		}
	}

	public static void makeTitleSlide(XMLSlideShow ppt, String strTitle) {

		// getting the slide master object
		XSLFSlideMaster slideMaster = ppt.getSlideMasters()[0];

		// get the desired slide layout
		XSLFSlideLayout titleLayout = slideMaster.getLayout(SlideLayout.TITLE);

		// creating a slide with title layout
		XSLFSlide slide1 = ppt.createSlide(titleLayout);

		// selecting the place holder in it
		XSLFTextShape title1 = slide1.getPlaceholder(0);

		// setting the title init
		title1.setText(strTitle);
	}

	public static void makeContentSlide(XMLSlideShow ppt, Question q) {

		// getting the slide master object
		XSLFSlideMaster slideMaster = ppt.getSlideMasters()[0];

		// get the desired slide layout
		XSLFSlideLayout titleLayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);

		// creating a slide with title layout
		XSLFSlide slide1 = ppt.createSlide(titleLayout);

		// selecting the place holder in it
		XSLFTextShape title1 = slide1.getPlaceholder(0);

		// setting the title init
		title1.setText(q.getStrQuestionTitle());

		// selecting the place holder in it
		XSLFTextShape body = slide1.getPlaceholder(1);

		body.clearText();

		XSLFTextParagraph paragraph = body.addNewTextParagraph();

		XSLFTextRun runQuestion = paragraph.addNewTextRun();
		runQuestion.setText(q.getStrQuestion());
		runQuestion.setFontSize(24);
		paragraph.addLineBreak();
		paragraph.addLineBreak();

		int iResponseNbr = 0;
		for (Response r : q.getResponses()) {
			XSLFTextRun run1 = paragraph.addNewTextRun();
			run1.setText(Common.getCharForNumber(++iResponseNbr) + ") " + r.getStrResponse());
			paragraph.addLineBreak();
		}
	}
}