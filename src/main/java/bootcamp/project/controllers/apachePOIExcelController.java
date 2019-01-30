package bootcamp.project.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bootcamp.project.courses.Course;
import bootcamp.project.courses.Grade;
import bootcamp.project.repo.CourseRepo;
import bootcamp.project.repo.GradeRepo;
import bootcamp.project.repo.ProfessorRepo;
import bootcamp.project.repo.StudentRepo;
import bootcamp.project.users.Professor;

@Controller
public class apachePOIExcelController {
	@Autowired
	StudentRepo studentRepo;

	@Autowired
	GradeRepo gradeRepo;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	ProfessorRepo professorRepo;

	@GetMapping("/uploadExcelFile/{id}")
	public String uploadExcelFile(@PathVariable (required = false, name = "id") long id) {

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh1 = wb.createSheet("Student grades");
		XSSFRow row = sh1.createRow((short) 2);
		row.setHeight((short) 800);

		
		row.createCell(0).setCellValue("Last Name");
		row.createCell(1).setCellValue("Grades");

		Optional<Professor> pr = professorRepo.findById(id);
		Course c = courseRepo.findByProfessor(pr.get());
		//Professor pr = professorRepo.findByNameAndLastname("Baiba", "Jauka");
		//Course c = courseRepo.findByProfessor(pr);
		ArrayList<Grade> courseGrades = gradeRepo.findByCourse(c);

		int rowIndex = 3;
		for (Grade grades : courseGrades) {
			row = sh1.createRow((short) rowIndex);
			rowIndex++;
			row.createCell(0).setCellValue(grades.getStudent().getLastname());
			row.createCell(1).setCellValue(grades.getStudent().getName());
			row.createCell(1).setCellValue(grades.getGrade() + "");

		}

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File("Student grades.xls"));
			wb.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Student grades written successfully");
		return "redirect:/professorMenu/{id}";
	}

}