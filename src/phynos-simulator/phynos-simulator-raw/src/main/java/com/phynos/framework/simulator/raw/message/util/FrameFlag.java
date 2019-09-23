package com.phynos.framework.simulator.raw.message.util;

/**
 * 帧标记
 * @author phynos
 *
 */
public enum FrameFlag {

	/**
	 * 第一帧
	 */
	FRAME_FIRST(1,0),
	/**
	 * 最后一帧
	 */
	FRAME_LAST(0,1),
	/**
	 * 中间帧
	 */
	FRAME_MULTIPLE(0,0),
	/**
	 * 单帧
	 */
	FRAME_SINGLE(1,1);
	
	private int fir;
	private int fin;
	
	FrameFlag(int fir,int fin){
		this.setFir(fir);
		this.setFin(fin);
	}

	public int getFir() {
		return fir;
	}

	public void setFir(int fir) {
		this.fir = fir;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}
	
}
