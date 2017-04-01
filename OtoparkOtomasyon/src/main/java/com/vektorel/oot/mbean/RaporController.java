package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.util.RptEnum;

@Controller("raporController")
@Scope("singleton")
public class RaporController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -373505780445829775L;

	@Autowired
	private DataSource dataSource;
	
	private static String kisiRapor="classpath:rpt/rptKisiListe.jasper";
	private static String ilceRapor="classpath:rpt/rptKisiListe.jasper";
	
	@PostConstruct
	private void init() {
		System.out.println(kisiRapor = System.getProperty("webapp.root") +"WEB-INF\\classes\\rpt\\rptKisiListe.jasper"); 
	}
	
	    
	@SuppressWarnings("incomplete-switch")
	public void kisiRapor(RptEnum rptEnum) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("cinsiyet", 1);
		switch (rptEnum) {
			case DOCX: DOCX(kisiRapor, parameters); break;
			case EXCEL: XLSX(kisiRapor, parameters); break;
			case PDF: PDF(kisiRapor, parameters); break;
		}
	}
	
	@SuppressWarnings("incomplete-switch")
	public void ilceRapor(RptEnum rptEnum) {
		Map<String, Object> parameters = new HashMap<>();
		switch (rptEnum) {
			case DOCX: DOCX(ilceRapor, parameters); break;
			case EXCEL: XLSX(ilceRapor, parameters); break;
			case PDF: PDF(ilceRapor, parameters); break;
		}
	}
	     
	   private void PDF(String rapor,Map<String, Object> parameters){
		   try {
			  
			   JasperPrint jasperPrint=JasperFillManager.fillReport(rapor,parameters,dataSource.getConnection());
		       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
		       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
		       JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		       FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}	        
	        
	   }
	   
	   private void DOCX(String rapor,Map<String, Object> parameters){
	    	try {
				   JasperPrint jasperPrint=JasperFillManager.fillReport(rapor,parameters,dataSource.getConnection());
	 	       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		       httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.docx");
		       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
		       JRDocxExporter docxExporter=new JRDocxExporter();
		       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		       docxExporter.exportReport();
		       FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}

	   }
	   
	   private void XLSX(String rapor,Map<String, Object> parameters) {
	    	 try {
				   JasperPrint jasperPrint=JasperFillManager.fillReport(rapor,parameters,dataSource.getConnection());
	    	       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    		      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
	    		       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
	    		       JRXlsxExporter docxExporter=new JRXlsxExporter();
	    		       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    		       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
	    		       docxExporter.exportReport();
	    		       FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}
	
	   }
	   
	   @SuppressWarnings("unused")
	private void ODT(String rapor,Map<String, Object> parameters) {
	    	  try {
				   JasperPrint jasperPrint=JasperFillManager.fillReport(rapor,parameters,dataSource.getConnection());
		       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.odt");
		       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
		       JROdtExporter docxExporter=new JROdtExporter();
		       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		       docxExporter.exportReport();
		       FacesContext.getCurrentInstance().responseComplete();
		  	} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	       public void PPT(String rapor,Map<String, Object> parameters){
	    	   try {
				   JasperPrint jasperPrint=JasperFillManager.fillReport(rapor,parameters,dataSource.getConnection());
			       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
			      httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pptx");
			       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
			       JRPptxExporter docxExporter=new JRPptxExporter();
			       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
			       docxExporter.exportReport();
			       FacesContext.getCurrentInstance().responseComplete();
		   	} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	
	
}
