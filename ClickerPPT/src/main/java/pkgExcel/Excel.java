package pkgExcel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import pkgPPT.Question;
import pkgPPT.Response;

public class Excel {

	private static final String FILE_NAME = "/Questions.xlsx";
	
	public static LinkedList<Question> ReadQuestions(Workbook wb, Sheet ws) {

		LinkedList<Question> questions = new LinkedList<Question>();

		
		Iterator<Row> iterator = ws.iterator();
		//	Skip the first row;
		iterator.next();
		
		int iQuestionNbr = 0;
		
		Cell currentCell = null;

		
		//	Loop for each Row
		while (iterator.hasNext()) {

			int iResponseNbr = 0;
			
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();

			String strQuestionTitle = cellIterator.next().getStringCellValue();
			String strQuestion = cellIterator.next().getStringCellValue();
		
			Question q = new Question(++iQuestionNbr, strQuestionTitle, strQuestion);
			ArrayList<Response> responses = new ArrayList<Response>();
			
			//	Loop for each cell in each row
			while (cellIterator.hasNext()) {

				currentCell = cellIterator.next();	
				if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
					responses.add(new Response(++iResponseNbr,currentCell.getStringCellValue(),false));	
				}
				else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					responses.add(new Response(++iResponseNbr, String.valueOf(currentCell.getNumericCellValue()),false));
				}
				
			}
			q.setResponses(responses);
			questions.add(q);
			System.out.println();

		}

		return questions;
	}

}
