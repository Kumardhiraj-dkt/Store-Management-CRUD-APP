package com.myprojects.storemanager.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.storemanager.repository.ProductRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api/v1/")
public class ReportController {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/pdf")
	public String generatePdf() throws Exception, JRException {
		JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(this.productRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
		JasperReport compileReport=JasperCompileManager.compileReport(new FileInputStream("src/main/resources/report.jrxml"));
		HashMap<String, Object> map=new HashMap<>();
		JasperPrint fillReport=JasperFillManager.fillReport(compileReport,map,beanCollectionDataSource);
		JasperExportManager.exportReportToPdfFile(fillReport, "report.pdf");
		return "report generated";
	}
	
}
