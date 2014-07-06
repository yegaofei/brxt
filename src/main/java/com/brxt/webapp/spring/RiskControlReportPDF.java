package com.brxt.webapp.spring;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.brxt.model.ProjectInfo;
import com.brxt.model.report.RiskControlReport;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class RiskControlReportPDF extends AbstractPdfView{
	
	private RiskControlReport report;
	private ProjectInfo projectInfo;
	
	
	public RiskControlReportPDF()
	{
		
	}
	
	public RiskControlReportPDF(RiskControlReport report, ProjectInfo projectInfo)
	{
		this.report = report;
		this.projectInfo = projectInfo;
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		if(report == null || projectInfo == null)
		{
			return;
		}
		
		String pdfFileName = projectInfo.getProjectName()+ "_" +report.getReportSeason() + ".pdf";  
        // 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用pdf打开  
        response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(pdfFileName, "UTF-8"));    
          
        //List stuList = (List) model.get("list");            
        //显示中文  
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);   
        com.lowagie.text.Font FontChinese = new com.lowagie.text.Font(bfChinese, 12, com.lowagie.text.Font.NORMAL);    
        
        
        
		
	}

}
