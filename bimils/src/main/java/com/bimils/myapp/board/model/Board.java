package com.bimils.myapp.board.model;

import java.util.List;

import com.bimils.myapp.common.fileitem.model.FileItem;


public class Board {
	
	private int bo_seq_no;
	private String bo_title;
	private String bo_type;
	private String bo_writer;
	private String bo_writer_open;
	private String bo_content;
	private int bo_hit_cnt;
	private int bo_price;
	private String reg_user;
	private String reg_date;
	private String upd_user;
	private String upd_date;
	private String bo_del_yn;
	private int bo_up;
	private int bo_down;
	private String  bo_writer_name;
	private int bo_up_cnt;
	private List<BoardEffect> boEffectList;
	private List<FileItem> fileItemList;
	private String[] delFileSeqNo;
	
	
	public int getBo_seq_no() {
		return bo_seq_no;
	}
	public void setBo_seq_no(int bo_seq_no) {
		this.bo_seq_no = bo_seq_no;
	}
	public String getBo_title() {
		return bo_title;
	}
	public void setBo_title(String bo_title) {
		this.bo_title = bo_title;
	}
	public String getBo_type() {
		return bo_type;
	}
	public void setBo_type(String bo_type) {
		this.bo_type = bo_type;
	}
	public String getBo_writer() {
		return bo_writer;
	}
	public void setBo_writer(String bo_writer) {
		this.bo_writer = bo_writer;
	}
	public String getBo_writer_open() {
		return bo_writer_open;
	}
	public void setBo_writer_open(String bo_writer_open) {
		this.bo_writer_open = bo_writer_open;
	}
	public String getBo_content() {
		return bo_content;
	}
	public void setBo_content(String bo_content) {
		this.bo_content = bo_content;
	}
	public int getBo_hit_cnt() {
		return bo_hit_cnt;
	}
	public void setBo_hit_cnt(int bo_hit_cnt) {
		this.bo_hit_cnt = bo_hit_cnt;
	}
	public int getBo_price() {
		return bo_price;
	}
	public void setBo_price(int bo_price) {
		this.bo_price = bo_price;
	}
	public String getReg_user() {
		return reg_user;
	}
	public void setReg_user(String reg_user) {
		this.reg_user = reg_user;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUpd_user() {
		return upd_user;
	}
	public void setUpd_user(String upd_user) {
		this.upd_user = upd_user;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public String getBo_del_yn() {
		return bo_del_yn;
	}
	public void setBo_del_yn(String bo_del_yn) {
		this.bo_del_yn = bo_del_yn;
	}
	public int getBo_up() {
		return bo_up;
	}
	public void setBo_up(int bo_up) {
		this.bo_up = bo_up;
	}
	public int getBo_down() {
		return bo_down;
	}
	public void setBo_down(int bo_down) {
		this.bo_down = bo_down;
	}
	public String getBo_writer_name() {
		return bo_writer_name;
	}
	public void setBo_writer_name(String bo_writer_name) {
		this.bo_writer_name = bo_writer_name;
	}
	public List<BoardEffect> getBoEffectList() {
		return boEffectList;
	}
	public void setBoEffectList(List<BoardEffect> boEffectList) {
		this.boEffectList = boEffectList;
	}
	public int getBo_up_cnt() {
		return bo_up_cnt;
	}
	public void setBo_up_cnt(int bo_up_cnt) {
		this.bo_up_cnt = bo_up_cnt;
	}
	public List<FileItem> getFileItemList() {
		return fileItemList;
	}
	public void setFileItemList(List<FileItem> fileItemList) {
		this.fileItemList = fileItemList;
	}
	public String[] getDelFileSeqNo() {
		return delFileSeqNo;
	}
	public void setDelFileSeqNo(String[] delFileSeqNo) {
		this.delFileSeqNo = delFileSeqNo;
	}
	
	
	
	
	
}
