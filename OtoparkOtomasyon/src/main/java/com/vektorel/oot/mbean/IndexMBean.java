package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.service.RaporService;
import com.vektorel.oot.util.CinsiyetYilDagilimi;

/**
 * 
 * @author temelt
 * 
 */
@Controller("indexBean")
@Scope("view")
public class IndexMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3330075364711917067L;

	/**
	 * Properties
	 */
	@Autowired
	private MessageMBean messageMBean;

	@Autowired
	private transient RaporService raporService;

	private LineChartModel areaModel;
	private BarChartModel barModel;

	@PostConstruct
	private void init() {
		messageMBean.mesajUyariGoster("", "HOÞGELDÝNÝZ");
		createAreaModel();
	}

	public void setMessageMBean(MessageMBean messageMBean) {
		this.messageMBean = messageMBean;
	}

	public LineChartModel getAreaModel() {
		return areaModel;
	}

	private void createAreaModel() {

		areaModel = new LineChartModel();
		barModel=new BarChartModel();

		List<CinsiyetYilDagilimi> liste = raporService.getCinsiyetYilDagilimiListesi();
		LineChartSeries erkek = new LineChartSeries();
		erkek.setFill(true);
		erkek.setLabel("ERKEK");
		BarChartSeries erkekBar = new BarChartSeries();
		erkekBar.setLabel("ERKEK");
		LineChartSeries kadin = new LineChartSeries();
		kadin.setFill(true);
		kadin.setLabel("KADIN");
		BarChartSeries kadinBar = new BarChartSeries();
		kadinBar.setLabel("KADIN");

		if(liste!=null){
			for (CinsiyetYilDagilimi kayit : liste) {
				if (kayit.getCinsiyet() == 0) {
					erkek.set(kayit.getYil().toString(), kayit.getSayisi());
					erkekBar.set(kayit.getYil().toString(), kayit.getSayisi());
				} else if (kayit.getCinsiyet() == 1) {
					kadin.set(kayit.getYil().toString(), kayit.getSayisi());
					kadinBar.set(kayit.getYil().toString(), kayit.getSayisi());
				}
			}
		}

		areaModel.addSeries(kadin);
		areaModel.addSeries(erkek);
		
		barModel.addSeries(erkekBar);
		barModel.addSeries(kadinBar);

		areaModel.setTitle("Cinsiyet Daðýlýmý");
		barModel.setTitle("Cinsiyet Daðýlýmý");
		areaModel.setLegendPosition("ne");
		areaModel.setStacked(true);
		areaModel.setShowPointLabels(true);

		Axis xAxis = new CategoryAxis("Yýllar");
		areaModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaModel.getAxis(AxisType.Y);
		yAxis.setLabel("Cinsiyet");
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public RaporService getRaporService() {
		return raporService;
	}

	public void setRaporService(RaporService raporService) {
		this.raporService = raporService;
	}

}
